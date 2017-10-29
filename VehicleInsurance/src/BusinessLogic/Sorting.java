package BusinessLogic;

import Connection.Connection;
import Connection.DBRequests;
import FileUtilities.ExportData;
import Model.Vehicle;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


//for debug
import FileUtilities.ImportData;
import java.util.LinkedList;
import java.util.Scanner;

import static Connection.Connection.ConnectionToDatabase;

public  class Sorting {

    static Connection connection = new Connection();
    static DBRequests dbRequests = new DBRequests();

    public static final Integer queryNumberthree=3;
    public static ArrayList<Vehicle> plates= new ArrayList<Vehicle>();

    public static void sortPlates() {

        for (int i = 0; i < plates.size(); i++) {
            for (int j = 1; j < plates.size() - i; j++) {
                if (plates.get(j - 1).compareTo(plates.get(j)) < 0) {
                    //System.out.println("Swapping");
                    Vehicle temp = plates.get(j - 1);
                    plates.set(j - 1, plates.get(j));
                    plates.set(j, temp);
                    //System.out.println("swap " + (j - 1) + "and" + j);
                }
            }

        }

    }

    public void PrintOutputToConsole(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        if(plates.isEmpty()){
            System.out.println("There are no vehicles.");
        }
        else {
            for (int i = 0; i < plates.size(); i++) {
                Vehicle car = plates.get(i);
                System.out.println("Plate No: "+car.getPlateNo()+"  Tax No: "+car.getVehicleOwner()+"  ExpireDate: "+ formatter.format(car.getExpireDate()));
            }
        }

    }

    public void SortFunctionality(){

        //This gina change and took data for database
        /*
        String fileNameDefined = "VehiclesData.csv";
        ImportData CSVinput = new ImportData();
        LinkedList<Vehicle> Data= new LinkedList<Vehicle>();
        Data = CSVinput.LoadFormCSV(fileNameDefined);
        for(Vehicle car : Data) {
            plates.add(car);
        }
        */

        ConnectionToDatabase(queryNumberthree);


        sortPlates();
        int ChOut=0;
        do{
            System.out.println("Export type options :  ");
            System.out.println("1 CSV File ");
            System.out.println("2 Console ");
            Scanner in = new Scanner(System.in);
            String input = in.next();
            try {
                ChOut = Integer.parseInt(input);
                if(ChOut==1){
                    ExportData export =new ExportData();
                    export.SaveToCSV(plates);
                }
                else if(ChOut==2){
                    PrintOutputToConsole();
                }
                else{
                    System.out.println("There are two option for output");
                }

            } catch (NumberFormatException ex) {
                System.out.println("You are supposed to give a whole number");
            }

        }while(ChOut<=0 || ChOut>=3);



    }
}