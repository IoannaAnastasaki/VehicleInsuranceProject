package Model;

import java.lang.NumberFormatException;
import java.time.DateTimeException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;

public class Vehicle implements Comparable<Vehicle> {

    //Object vehicle composition with owner
    private String PlateNo;
    private Owner VehicleOwner;
    private LocalDate ExpireDate;

    /**
     * I made a Constructor cause i need to make new objects to put in List
     * @param plateNo String with the plate number you have to validate it first
     * @param taxNumber Use validation for Tax Number  also a String
     * @param expireDate String and also validate it ans make it a LocalDate uuuu-mm-dd
     */
    public Vehicle(String plateNo, String taxNumber,String expireDate){
        this.setPlateNo(plateNo);
        this.setVehicleOwner(taxNumber);
        this.setExpireDate(expireDate);
    }

    /**
     * Give the plate number of a vehicle
     * @return a String
     */
    public String getPlateNo() {
        return PlateNo;
    }

    /**
     * Give the TaxNo of Vehicle owner
     * @return TaxNo as String
     */
    public String getVehicleOwner() {
        return VehicleOwner.getTaxNo();
    }

    /**
     * Give the date where Expire the Vehicle Insurance
     * @return LocalDate  format uuuu-mm-dd
     */
    public LocalDate getExpireDate() {
        return ExpireDate;
    }

    /**
     * Set the plate Number without make validation so you have to make it before
     * @param plateNo String AAA-1111
     */
    public void setPlateNo(String plateNo) {
        PlateNo = plateNo;
    }

    /**
     * Valoigate the Tax Number and the asing to an owner
     * @param taxNumber as a String
     */
    public void setVehicleOwner(String taxNumber){
        Owner NewVehicleOwner = new Owner();
        //validate if the give String is a Tax number (9 digits not 000 000 000)
        if (NewVehicleOwner.TaxValidation(taxNumber)) {
            NewVehicleOwner.setTaxNo(taxNumber);
            VehicleOwner = NewVehicleOwner;
        }
    }

    /**
     * Take a String of date like the one to CSV and make a LocalDate
     * and set it to the object with format uuuu-mm-dd
     * @param expireDate as String with format dd/mm/uuuu
     * @return bolean true false id the date is valid
     */
    public boolean setExpireDate(String expireDate) {

        String[] parts = expireDate.split("/");
        if (parts.length==3){
            try{
                //Split the string in / so I take year month and day of month
                int year=Integer.parseInt(parts[2]);
                int month=Integer.parseInt(parts[1]);
                int day=Integer.parseInt(parts[0]);
                //System.out.println("mera "+parts[0] +" minas "+parts[1]+" etos "+parts[2] );
                ExpireDate = LocalDate.of(year, month,day);
                return true;

            //Catch a exception if a part of my string its not a number
            } catch (NumberFormatException ex) {
                System.out.println("You can not have alphabet letters in a date.");
                return false;
            //catch an exception if the date does not excist for example 31/11/2017
            }catch (DateTimeException  e) {
                System.out.println("There is no such a day in the calendar.");
                return false;
            }
        }
        return false;

    }

    /**
     * Check if the given string is valid with a plate format
     * @param plate a String to check
     * @return boolean if the pattern is right
     */
    public boolean PlateValidation(String plate){
        //abc-1324 -> ABC-1324
        plate = plate.toUpperCase();
        int lengthPlate = plate.length();
        //must be 8
        if (lengthPlate==8){
            //must have 3 char - 4 digits
            Pattern p1=Pattern.compile("[A-Z][A-Z][A-Z]\\-\\d\\d\\d\\d");
            Matcher m1=p1.matcher(plate);
            //digits can be 1000-9999 for a plate to be valid
            String plateDigits=plate.substring(4,8);
            Pattern p2=Pattern.compile("^([1-9][0-9][0-9][0-9])$");
            Matcher m2=p2.matcher(plateDigits);
            if (m1.find() && m2.find()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }

    }


    @Override
    public int compareTo(Vehicle vehicle) {
        String plate1 = this.PlateNo;
        String plate2 = vehicle.getPlateNo();

        for (int i=0;i<plate1.length();i++){
            if( (int) plate1.charAt(i)> (int) plate2.charAt(i)){
                return -1;
            }else if ( (int) plate1.charAt(i)< (int) plate2.charAt(i)){
                return 1;
            }
        }
        return 0;
    }
}
