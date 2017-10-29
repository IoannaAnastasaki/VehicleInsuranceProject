package FileUtilities;

import Model.Owner;
import Model.Vehicle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.String;
import java.util.LinkedList;


public class ImportData {

    /**Take a name of a CSV file and read it line line each line is a Vehicle entry and
     * first column is plate number of vehicle
     * second column is tax number of the owner
     * third column is the date which the insurance expire
     * @param FileName the name of the file example as a String example VehiclesData.csv
     * @return a LinkedList of Vehicles where every node is
     * vehicle.getPlateNo ->give plate number
     * vehicle.getVehicleOwner -> give the tax number of the vehicle owners
     * vehicle.getExpireDate -> The date insurance expire format uuuu-mm-dd
     */
    public LinkedList<Vehicle> LoadFormCSV(String FileName){
        Vehicle Car = new Vehicle("", "", "");
        Owner Person = new Owner();
        String SPlate= "";
        String STax = "";
        //one list with vehicles
        LinkedList<Vehicle> myData = new LinkedList<Vehicle>();
        //If are all correct then I insert a node to my list.
        boolean plate=false;
        boolean tax= false;
        boolean date=false;

        //The path of file is a string
        File CSVFile = new File(FileName);
        /* Take numbers 1-2-3
           1  is PlateNo
           2  is OwnerTaxNo
           3  is ExpireInsuranceDate
        */
        int EntryFlag=0;
        try{
            // -read from filePooped with Scanner class
            Scanner input = new Scanner(CSVFile);
            // read line-by-line
            while(input.hasNext()){
                //read single line
                input =input.useDelimiter(";|\\r\\n");
                //split in ;
                EntryFlag=1;
                while(input.hasNext()){
                    String data = input.next();
                    if (EntryFlag==1){
                        if (Car.PlateValidation(data)) {
                            Car.setPlateNo(data);
                            SPlate=data;
                            plate=true;
                        }
                        //System.out.println("Plate "+EntryFlag+" "+Car.getPlateNo());
                        EntryFlag++;
                    }
                    else if (EntryFlag==2){
                        if (Person.TaxValidation(data)){
                            Person.setTaxNo(data);
                            Car.setVehicleOwner(data);
                            STax=data;
                            tax =true;
                        }
                        //System.out.println("AFM "+EntryFlag+" "+Car.getVehicleOwner());
                        EntryFlag++;
                    }
                    //each line of CSV file has 3 columns plate tax no and expire date
                    else if (EntryFlag==3){
                        date = Car.setExpireDate(data);
                        //System.out.println("Expire Insurance at "+EntryFlag+" "+Car.getExpireDate());
                        EntryFlag=1;
                        //If all of the formats are correct i insert a new node in my list
                        if (date && tax && plate){
                            //myData.add(Car);
                            myData.add(new Vehicle(SPlate,STax, data));
                        }

                    }
                    else{
                        System.out.println("There is no other fields.");
                    }

                }

            }

            // after loop, close scanner
            input.close();
            return myData;

        //if the file does not exist i catch the exception
        }catch (FileNotFoundException e){
            System.out.println("The given file doesn't exist.");
            //e.printStackTrace();
        }
        return myData;
    }
}
