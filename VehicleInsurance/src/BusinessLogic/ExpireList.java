package BusinessLogic;

import Model.Vehicle;
import FileUtilities.*;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.NumberFormatException;

public class ExpireList {
    /**
     * Select the Vehicle where expire dates is between After and Before
     * @param Data the Vehicle list taken for the CSV file
     * @param AfterThisDay the current day
     * @param BeforeThisDate the current day  + num of days user gives
     * @return The list with the vehicles insurance is between AfterThisDay and BeforeThisDate
     */
    private LinkedList<Vehicle> CheckInsurance(LinkedList<Vehicle> Data,LocalDate AfterThisDay ,LocalDate BeforeThisDate){
        LinkedList<Vehicle> ExpireBefore=new LinkedList<Vehicle>();
        if (Data.isEmpty()){
            System.out.println("My list is empty so there is nothing on it");
        }
        else{
            for(Vehicle car : Data) {
                //System.out.println("Plate No: "+car.getPlateNo()+" Tax No: "+car.getVehicleOwner()+" ExpireDate: "+ car.getExpireDate());
                if((car.getExpireDate().isAfter(AfterThisDay))&&car.getExpireDate().isBefore(BeforeThisDate)){
                    ExpireBefore.add(car);

                }

            }
        }
        return ExpireBefore;
    }

    /**
     * Is the user choose to print the output in console
     * @param Elements Take a list where the Expire date is between today and after n dates
     */
    private void PrintOutputToConsole(LinkedList<Vehicle> Elements){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        if (Elements.isEmpty()){
            System.out.println("There are no vehicles that are about to expire");
        }
        else{
            for(Vehicle car : Elements) {
                try{
                    // LocalDate localDate = LocalDate.parse(car.getExpireDate(), formatter);
                    System.out.println("Plate No: "+car.getPlateNo()+"  Tax No: "+car.getVehicleOwner()+"  ExpireDate: "+ formatter.format(car.getExpireDate()));

                }catch (Exception ex) {

                }
            }
        }

    }

    /** Data are taken from file
     * If the user choose 2 to menu  he have to give an int to plus on today and then
     * check which vehicle is about to expire in next n days
     * and print to console or export it to a file
     */
    public void AboutToExpire() {
        LocalDate Today = LocalDate.now();

        //Take an int from inout to add to days
        int numOfDays =-1;
        Scanner in = new Scanner(System.in);
        String input;
        do {
            System.out.printf("Enter a number of days :  ");
            input = in.next();
            try {
                numOfDays = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("You are supposed to give a whole number of days.");
            }
        } while (numOfDays < 0);


        //LocalDate CheckInsurance = Today.plus(numOfDays, ChronoUnit.DAYS);
        LocalDate Until = Today.plusDays(numOfDays);

        //Debug
        //System.out.println(Today);
        //System.out.println(CheckInsurance);
        System.out.println("We check insurance status from "+ Today + " to "+Until);

        //Open CSVFile and take the list
        String fileNameDefined = "VehiclesData.csv";
        ImportData CSVinput = new ImportData();
        LinkedList<Vehicle> Data= new LinkedList<Vehicle>();
        Data = CSVinput.LoadFormCSV(fileNameDefined);
        //find the
        LinkedList<Vehicle> ExpList =CheckInsurance(Data,Today,Until);
        //give the output to the Console or file
        int ChOut=0;
        do{
            System.out.println("Export type options :  ");
            System.out.println("1 CSV File ");
            System.out.println("2 Console ");
            input = in.next();
            try {
                ChOut = Integer.parseInt(input);
                if(ChOut==1){
                    ExportData export =new ExportData();
                    export.SaveToCSV(ExpList);
                }
                else if(ChOut==2){
                    PrintOutputToConsole(ExpList);
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
