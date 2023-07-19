public class Message{
    String content;
    String recipientPhoneNumber;
    public Message(String content, String recipientPhoneNumber){
        this.content = content;
        this.recipientPhoneNumber = recipientPhoneNumber;
    }
    String getContent(){
        return this.content;
    }

    public void encode(String phoneNumber, String content) {
        // Swap digits in the phone number
        String swappedPhoneNumber = "";
        for (int i = 0; i < phoneNumber.length(); i += 2) {
            swappedPhoneNumber += phoneNumber.charAt(i);
            swappedPhoneNumber += phoneNumber.charAt(i);
        }

        // Length of the phone number
        String phoneNumberLength = Integer.toHexString(phoneNumber.length());

        // Encode text into GSM 7-bit
        String encodedText = encodeGsm7Bit(content);

        // Length of the text
        String textLength = Integer.toHexString(content.length());

        // Put everything together
        this.content = ( "001100" + phoneNumberLength + "91" + swappedPhoneNumber + "0000" + textLength + encodedText);
    }

    private static String encodeGsm7Bit(String text) {
        String gsm7BitChars = "@£$¥èéùìòÇ\nØø\rÅåΔ_ΦΓΛΩΠΨΣΘΞÆæßÉ !\"#¤%&'()*+,-./0123456789:;<=>?¡ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÑÜ§¿abcdefghijklmnopqrstuvwxyzäöñüà";
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            int index = gsm7BitChars.indexOf(c);
            if (index == -1) {
                throw new IllegalArgumentException("Nieobsługiwany znak: " + c);
            }
            result.append(String.format("%02X", index));
        }

        return result.toString();
    }

    public void decode(String pdu) {
        // Split the PDU string into components
        String phoneNumberLengthHex = pdu.substring(6, 8);
        String phoneNumberHex = pdu.substring(10, 10 + Integer.parseInt(phoneNumberLengthHex) * 2);
        String textLengthHex = pdu.substring(14 + Integer.parseInt(phoneNumberLengthHex) * 2, 16 + Integer.parseInt(phoneNumberLengthHex) * 2);
        String textHex = pdu.substring(16 + Integer.parseInt(phoneNumberLengthHex) * 2);

        // Swap digits back
        String phoneNumber = "";
        for (int i = 0; i < phoneNumberHex.length(); i += 2) {
            phoneNumber += phoneNumberHex.charAt(i);
            phoneNumber += phoneNumberHex.charAt(i);
        }

        // Decode text from GSM 7-bit
        String text = decodeGsm7Bit(textHex);

        // Put everything together
        this.content= ("Phone Number: " + phoneNumber + ", Text: " + text);
    }

    private String decodeGsm7Bit(String textHex) {
        String gsm7BitChars = "@£$¥èéùìòÇ\nØø\rÅåΔ_ΦΓΛΩΠΨΣΘΞÆæßÉ !\"#¤%&'()*+,-./0123456789:;<=>?¡ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÑÜ§¿abcdefghijklmnopqrstuvwxyzäöñüà";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < textHex.length(); i += 2) {
            String hexChar = textHex.substring(i, i + 2);
            int charIndex = Integer.parseInt(hexChar, 16);
            if (charIndex >= gsm7BitChars.length()) {
                throw new IllegalArgumentException("Nieobsługiwany kod: " + hexChar);
            }
            result.append(gsm7BitChars.charAt(charIndex));
        }

        return result.toString();
    }
}
