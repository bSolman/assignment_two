package user_interface;

import customer.Customer;

import java.util.Scanner;

public class UI {
    public String promptWelcome(){
        System.out.println("///////////////////////////////////////////");
        System.out.println("////                                   ////");
        System.out.println("////                                   ////");
        System.out.println("////        Welcome To The GYM!        ////");
        System.out.println("////                                   ////");
        System.out.println("////     mata in antingen personNr     ////");
        System.out.println("////      eller fullständigt namn!     ////");
        System.out.println("////                                   ////");
        System.out.println("////                                   ////");
        System.out.println("///////////////////////////////////////////");
        Scanner in = new Scanner(System.in);
        System.out.print("Starta sökning: ");
        return in.nextLine();
    }

    public void promptWelcomeUser(String name) {
        System.out.println("Welcome to the gym " + name + "!" );
    }

    public void promptUserDoesNotExist() {
        System.out.println("Something went wrong. Please try again.");
    }

    public void promptUserDontHaveValidAccount(Customer customer) {
        System.out.println(customer.getName() + " har inget aktivt konto.");
    }
}
