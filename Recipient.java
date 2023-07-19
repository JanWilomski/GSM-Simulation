import java.util.Random;

public class Recipient{
    String recipientPhoneNumber;
    Recipient(String recipientPhoneNumber){
        this.recipientPhoneNumber = recipientPhoneNumber;
    }


    public String getRecipientPhoneNumber(){
        return recipientPhoneNumber;
    }

    String newRandomPhoneNumber(){
        Random random = new Random();
        StringBuilder phoneNumberBuilder = new StringBuilder();

        // Dodaj pierwszą cyfrę (1-9)
        phoneNumberBuilder.append(random.nextInt(9) + 1);

        // Dodaj kolejne 8 cyfr
        for (int i = 0; i < 8; i++) {
            phoneNumberBuilder.append(random.nextInt(10));
        }
        return phoneNumberBuilder.toString();
    }


}
