package Connection;

import BusinessLogic.TotalDebt;
import BusinessLogic.Sorting;
import Model.Vehicle;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//imports for main
import UserInput.*;
import BusinessLogic.*;


import java.util.ArrayList;
import java.util.Arrays;


public class Connection
{

    //jdbc driver
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //db url
    public static final String DB_URL = "jdbc:mysql://192.168.33.10/VehicleInsuranceProject";

    //username
    public static final String USER = "root";
    //password
    public static final String PASS = "admin123";

    static DBRequests dbRequests = new DBRequests();

    //instance gia na perasw etsi to afm
    public  static TotalDebt totalDebt = new TotalDebt();
    //instance gia to f3 shorting
    public  static Sorting Sorted = new Sorting();

    public static void ConnectionToDatabase(int numberOfQuery)
    {
        //object for connection
        java.sql.Connection conn = null;
        //object for making sql statements

        try {


            Class.forName("com.mysql.jdbc.Driver");
            //connection to database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);



            if(numberOfQuery==3)
            {
                ArrayList<Vehicle> Plates=dbRequests.ShortingPlatesQuery(conn);
                for(Vehicle car : Plates) {
                    Sorting.plates.add(car);
                }

            }

            else if(numberOfQuery==4) //ean to erwthma poy thelw na ektelesw einai to erwthma 4
            {
                /* kalw th synarthsh totalDebtQuery ths klasshs dbRequests.
                 * Ayth h synarthsh upologizei ton arithmo twn anasfalistwn oxhmatwn
                  * tou xrhsth me to sygekrimeno afm */
                int numberOfExpiredVehicles=dbRequests.totalDebtQuery(conn,totalDebt.getAfmValue());

                 //molis mou epistrafei aytos o arithmos,ton eisagw sto field ths totalDebt klasshs m
                //etsi mporw na kanw ton sunoliko upologismo tou TotalDebt poy thelw
                totalDebt.numberOfExpiredVehicles=numberOfExpiredVehicles;

            }




        }


        catch (SQLException ex)
        {

            System.out.println("SQLException: " + ex.getMessage());

            System.out.println("VendorError: " + ex.getErrorCode());

        }

        catch (ClassNotFoundException e)
        {

        e.printStackTrace();

        }



    }

    public static void main(String []argv){

        // ----------------------------Print Menu---------------------------------------------------

        //Main Menu
        ConsoleMenu mainMenu = new ConsoleMenu("         Main Menu");
        mainMenu.setOptions(new ArrayList<String>(Arrays.asList("Check vehicle's insurance","Expiring list of vehicles till a specific date","Retrieve a sorted list of vehicles by plate", "Personal total debt of expired vehicles")));
        int actionChoice = mainMenu.printOptionMenu(); //save user's choice

        //Take action according to user's choice
        switch (actionChoice) {
            case 1:
                //run check vehicle
                VehicleStatus j=new VehicleStatus();
                j.CheckVehiclesInsurance();
                break;
            case 2:
                //run expire after day
                ExpireList i =new ExpireList();
                i.AboutToExpire();
                break;
            case 3:
                // run Sorting
                Sorted.SortFunctionality();
                break;
            case 4:
                //run Total cost
                totalDebt.CalculateTotalCost();
                break;
        }

        //totalDebt.CalculateTotalCost();
    }

}




