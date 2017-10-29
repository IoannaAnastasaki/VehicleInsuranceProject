package BusinessLogic;

import FileUtilities.ExportData;
import FileUtilities.ImportData;
import Model.Vehicle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleStatus {

    /**
     * Take an input for the user and check if it valid pattern of plate
     * No stop util user give a right pattern
     * @return the plate as a String
     */
    private String PlateValidation(){

        //True of False field use for the loop check till the user enter a right plate number
        boolean rightPlate = false;
        //read the input for the user
        Scanner userInput = new Scanner(System.in);
        //Plate's size
        int lengthPlate=0;

        do {

            System.out.println("Please enter your vehicle's plate number: ");
            //take the input for the user
            String plate = userInput.nextLine();
            //make sure that there no problem if the user put low case or upper case
            //AxT-1234 -> AXT-1234
            plate = plate.toUpperCase();
            //Each plate must have size 8 not more or less
            lengthPlate = plate.length();
            //if it not 8 it not a plate number
            if (lengthPlate != 8){
                System.out.println("Vehicle's plate numbers can not be more or less than 8 characters");
            }
            //if it is 8 we have to check the patterns
            else {
                /* compile the regular expression to look like the pattern
                 * [A-Z]: all english chars A to Z
                 * d : all digits 0-9
                 */
                Pattern p1 = Pattern.compile("[A-Z][A-Z][A-Z]\\-\\d\\d\\d\\d");
                /* An object which make the compare and check the match between the
                 * input and the pattern
                 */
                Matcher m1 = p1.matcher(plate);

                //Take the digits of the plate to check them
                String plateDigits = plate.substring(4, 8);
                //pattern to check the digits 1000-9999
                Pattern p2 = Pattern.compile("^([1-9][0-9][0-9][0-9])$");
                Matcher m2 = p2.matcher(plateDigits);

                /* Now we have to check is the input follow the new pattern
                 * m1 -> [A-Z][A-Z][A-Z]\\-\\d\\d\\d\\d
                 * m2 -> check digits substring [1-9][0-9][0-9][0-9]
                 */
                if (m1.find() && m2.find()) {
                    System.out.println(plate + " is a valid number of plate.");
                    rightPlate = true;
                    return plate;
                }
                //my plate do not make match
                else {
                    System.out.println(plate + " is not a valid number of plate.");
                }

            }
        }//Continues till the user give a corect palte
        while (rightPlate == false);

        return "";
    }

    /**
     * Find the expire date of a specific vehicle plate number
     * @param Elements the list with Vehicles imported form a file
     * @param InputPlateNo the plate as string given by the user
     * @return the expire date if it is in our list or 1970-01-01 if it is not found
     */
    private LocalDate SearchInVehicles(LinkedList<Vehicle> Elements, String InputPlateNo){
        boolean PlateFound=false;
        if (Elements.isEmpty()){
            //if my list is empty then we cant search for vehicles in it
            System.out.println("There are no vehicles at all.");
        }
        else{
            //for each one vehicle in the list
            for(Vehicle car : Elements) {
                if (InputPlateNo.equals(car.getPlateNo())){
                    PlateFound=true;
                    return car.getExpireDate();
                }
                //System.out.println("Plate No: "+car.getPlateNo()+" Tax No: "+car.getVehicleOwner()+" ExpireDate: "+ car.getExpireDate());
            }
            if (PlateFound=false){
                System.out.println("The vehicle with plate number "+InputPlateNo+" is not found");
            }
        }

        String date = "1970-01-01";
        LocalDate PlateNotFound = LocalDate.parse(date);
        return PlateNotFound;

    }



    public void OutputMenu(String Output1, String Output2 ){
        int ChOut=0;
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("Export type options :  ");
            System.out.println("1 TXT File ");
            System.out.println("2 Console ");
            String input = in.next();
            try {
                ChOut = Integer.parseInt(input);
                if(ChOut==1){
                    ExportData export =new ExportData();
                    export.SaveToCSV(Output1+ "\r\n" +Output2);
                }
                else if(ChOut==2){
                    System.out.println(Output1);
                    System.out.println(Output2);
                }
                else{
                    System.out.println("There are two option for output.");
                }

            } catch (NumberFormatException ex) {
                System.out.println("You are supposed to give a whole number.");
            }

        }while(ChOut<=0 || ChOut>=3);
    }

    /** Data are taken from file
     * If the user choose 1 to menu  he have to give an plate
     * and check if it valid pattern exist in vehicle list
     * ans when it is expire
     */
    public void CheckVehiclesInsurance(){
        String Output1="", Output2="";
        //VehicleStatus start with the import from file
        String fileNameDefined = "VehiclesData.csv";
        ImportData CSVinput = new ImportData();
        LinkedList<Vehicle> Data= CSVinput.LoadFormCSV(fileNameDefined);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        String plate=PlateValidation();
        /*If the SearchInVehicles return me 1970-01-01
         * then that meant the given plate is in a valid format but it
         * is not in the Vehicle list so the Vehicle does not exist
         */
        LocalDate PlateNotFound = LocalDate.parse("1970-01-01");
        LocalDate VehicleExpireInsurance = SearchInVehicles(Data,plate);
        if(VehicleExpireInsurance.isEqual(PlateNotFound)){
            System.out.println("Your plate number is valid but there is no vehicle with plate : "+plate);
        }else{

            //System.out.println("Vehicle with plate "+plate+" insurance expire "+formatter.format(VehicleExpireInsurance));
            LocalDate Today = LocalDate.now();

            if(Today.isAfter(VehicleExpireInsurance)){
                Output1 ="Your vehicle's insurance has expired.";
                Output2 ="Vehicle with plate "+plate+" insurance has expired since "+formatter.format(VehicleExpireInsurance);
            }
            else{
                Output1 ="Your vehicle's insurance is active.";
                Output2 = "Vehicle with plate "+plate+" insurance expire at "+formatter.format(VehicleExpireInsurance);
            }
        }

        if (Output1.equals(Output2)){
            //System.out.println("The vehicle not found");
        }
        else{
            OutputMenu(Output1,Output2);
        }


    }



}