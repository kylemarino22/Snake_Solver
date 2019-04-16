package core;

import java.io.FileNotFoundException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Main{

    public static void main(String[] args) {

        final String fileName = "resources/level51.txt";

        final String ACCOUNT_SID = "AC59dc87bd410ad8e7b4026100ff3abb70";
        final String AUTH_TOKEN = "f8a70a1e6783755b91e4781bf06fa0ea";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


        GridParser gp;
        try {
             gp = new GridParser(fileName);
             PrintGrid.printGrid(gp.getGrid().grid, true);
             new Solver(gp.getGrid());

        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }

        Message message = Message.creator(new PhoneNumber("+16509467854"),
                new PhoneNumber("+16504762597"),
                "The level has finished!").create();


    }
}
