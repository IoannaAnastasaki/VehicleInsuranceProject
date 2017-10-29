package FileUtilities;


import Model.Vehicle;


import java.io.PrintWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.util.LinkedList;

public class ExportData {


    /**Take a list of Vehicle and make a new CSV file the file name is given by the user
     * he Give it without put .csv
     * for example "output"
     * @param Data is the list of Vehicles
     * The format is csv is PlateNo;TaxNo;ExpireDate<dd/mm/uuuu>
     */
    public void SaveToCSV (LinkedList<Vehicle> Data){
        Scanner input = new Scanner(System.in);
        //only name with .csv
        System.out.print("Enter the filename you want to create: ");
        // Below Statement used for getting String including sentence
        String FileName = input.nextLine();
        //append the .csv to our file
        FileName=FileName+".csv";
        //System.out.println(FileName);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        try{
            PrintWriter writer = new PrintWriter(FileName, "UTF-8");

            if (Data.isEmpty()){
                writer.println("There are no vehicles that are about to expire.");
                //System.out.println("My list is empty so there is nothing on it");
            }
            else{
                for(Vehicle car : Data) {
                    try{
                       // LocalDate localDate = LocalDate.parse(car.getExpireDate(), formatter);
                        writer.println(car.getPlateNo()+";"+car.getVehicleOwner()+";"+formatter.format(car.getExpireDate()));

                    }catch (Exception ex){

                    }

                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("There is a problem with the file.");
        }

    }

    /**
     * Take a list of Vehicle and make a new TXT file the file name is given by the user
     * he Give it without put .txt
     * @param Data The sTring we want to print
     */
    public void SaveToCSV (String Data){
        Scanner input = new Scanner(System.in);
        //only name with .csv
        System.out.print("Enter the filename you want to create: ");
        // Below Statement used for getting String including sentence
        String FileName = input.nextLine();
        //append the .csv to our file
        FileName=FileName+".txt";
        //System.out.println(FileName);

        try{
            PrintWriter writer = new PrintWriter(FileName, "UTF-8");
            writer.println(Data);
            writer.close();
        } catch (IOException e) {
            System.out.println("There is a problem with the file.");
        }
    }

    public void SaveToCSV(ArrayList<Vehicle> Plates) {
        Scanner input = new Scanner(System.in);
        //only name with .csv
        System.out.print("Enter the filename you want to create: ");
        // Below Statement used for getting String including sentence
        String FileName = input.nextLine();
        //append the .csv to our file
        FileName = FileName + ".csv";
        //System.out.println(FileName);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try {
            PrintWriter writer = new PrintWriter(FileName, "UTF-8");
            for (int i = 0; i < Plates.size(); i++) {
                Vehicle car= Plates.get(i);
                writer.println(car.getPlateNo()+";"+car.getVehicleOwner()+";"+formatter.format(car.getExpireDate()));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("There is a problem with the file.");
        }
    }



}
