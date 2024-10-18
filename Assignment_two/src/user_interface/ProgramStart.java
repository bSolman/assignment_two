package user_interface;

import controller.MainController;
import customer.Customer;

import java.io.IOException;

public class ProgramStart {
    MainController controller;
    UI ui = new UI();
    private final String path_logg;
    private final String path_users;
    public ProgramStart(String path_logg, String path_users){
        this.path_logg = path_logg;
        this.path_users = path_users;
        try {
            controller = new MainController(path_users);
        } catch (IOException e) {
            e.printStackTrace();
        }

        runProgram();
    }

    private void runProgram() {
        String userInput = "";
        while(true){
            userInput = ui.promptWelcome();
            if(userInput.equals("exit")){
                System.exit(0);
            } else{
                if (controller.doesPersonExistString(userInput) != null){
                    Customer customer = controller.doesPersonExistString(userInput);
                    userFound(customer);
                } else if (controller.doesPersonExistLong(testLong(userInput)) != null){
                    Customer customer = controller.doesPersonExistLong(testLong(userInput));
                    userFound(customer);
                }/* else {
                    ui.promptUserDoesNotExist();
                }*/
            }
        }
    }


     public boolean userFound(Customer customer) {
        if (controller.doesPersonHaveValidAccount(customer)){
            ui.promptWelcomeUser(customer.getName());
            try {
                controller.writeToFile(path_logg, customer.getName(), String.valueOf(customer.getPersNr()));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ui.promptUserDontHaveValidAccount(customer);
        }
        return false;
    }

    public long testLong(String userInput) {
        try {
            return Long.parseLong(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid personal number or name");
            return 0;
        }
    }
}
