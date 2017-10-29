package UserInput;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleMenu {
    private String title;
    private ArrayList<String> options;

    //constructor
    public ConsoleMenu(String title){
        this.title = title;
    }

    //getters
    public String getTitle() {
        return title;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    //setters
    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //methods
    public static boolean isMemuChoice(int choice, ArrayList<Integer> validChoices){
        return validChoices.contains(choice);
    }

    public static void printMenuOptions(String title, ArrayList<String> options){
        /*
           prints out menu title and options
         */
        System.out.println(title);
        System.out.println("--------------------------");

        for(int i=0; i< options.size();i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.println();
    }


    public int printOptionMenu() {
        /*
            prints menu options
            asks user to enter an int as an action choice
            returns int choice
         */

        int choice = 0;
        Scanner sc = new Scanner(System.in);

        do {
            printMenuOptions(title, options);
            System.out.println("Please enter a number of choice:");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("\nThis is not a valid choice\n");
                sc.next();
            }
        } while (choice < 1 || choice > options.size());

        System.out.println("You have chosen a functionality with number "+ choice);
        return choice;
    }
}
