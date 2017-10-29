import Model.Vehicle;
import UserInput.*;
import BusinessLogic.*;


import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    //instance gia na perasw etsi to afm
    public  static TotalDebt totalDebt = new TotalDebt();
    public  static Sorting Sorted = new Sorting();

    public static void main(String[] args) {
        // ----------------------------Print Menu---------------------------------------------------

        //Main Menu
        ConsoleMenu mainMenu = new ConsoleMenu("Main Menu");
        mainMenu.setOptions(new ArrayList<String>(Arrays.asList("Check by Plate No","Expiring soon","Retrieve sorted Database", "Total Cost")));
        int actionChoice = mainMenu.printOptionMenu(); //save user's choice

        //Take action according to user's choice
        switch (actionChoice) {
            case 1:
                //run check vehicle (Elini)
                VehicleStatus j=new VehicleStatus();
                j.CheckVehiclesInsurance();
                break;
            case 2:
                //run expire after day (Eleni)
                ExpireList i =new ExpireList();
                i.AboutToExpire();
                break;
            case 3:
                // run Sorting (Nickos)
                Sorted.SortFunctionality();
                break;
            case 4:
                //run Total cost (Iwanna)
                totalDebt.CalculateTotalCost();
                break;
        }

    }
}
