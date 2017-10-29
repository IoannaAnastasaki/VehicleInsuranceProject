package Connection;

import BusinessLogic.TotalDebt;
import Model.Vehicle;

import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import java.sql.*;
import java.sql.Connection;
import java.time.LocalDate;

public  class DBRequests
{

public int  totalDebtQuery (Connection connection, int afmNumber ) throws SQLException {


    ResultSet NumberOfVehicl = null; //tha mas epistrafei edw to apotelesma
    PreparedStatement preparedStatement4 = null; //to PrepareStatement sql pou tha treksw meta

    try {


        Integer afm = afmNumber;  //to afm tou xrhsth poy metefera apo thn Connection
        LocalDate tod = LocalDate.now();//shmerinh mera,to kratame

        Date today = Date.valueOf(tod); //date to mysql.Date(aplo casting)

        Integer NumberOfVehicles = 0;

        //to erwthma sql me tis parametrous pou pairnei
        preparedStatement4 = connection.prepareStatement("Select count(Vehicles.VehicleID) as NumberOfVehicles" +
                " from Vehicles,Owners where Owners.OwnerID=Vehicles.OwnerID and Vehicles.ExpireDate<? and Owners.TaxNO=?");
        preparedStatement4.setDate(1, today);
        preparedStatement4.setInt(2, afm);

        //ektelesh tou erwthmatos
        NumberOfVehicl = preparedStatement4.executeQuery();

        //diavazei oles tis eggrafes
            while (NumberOfVehicl.next())
            {
            NumberOfVehicles = NumberOfVehicl.getInt("NumberOfVehicles");
            }
//epistrefei pisw sthn Connection ton arithmo twn oxhmatwn
        return NumberOfVehicles;

       }
    catch(SQLException e){

        System.out.println("There is no Owner with such TaxNo");

    }

     catch (Exception exc)
     {
        exc.printStackTrace();
     }
    finally
    {
        if (NumberOfVehicl != null)
        {
            NumberOfVehicl.close();
        }

        if (preparedStatement4 != null)
        {
            preparedStatement4.close();
        }

        if (connection != null)
        {
            connection.close();
        }
    }

    return 0;
}





public ArrayList<Vehicle> ShortingPlatesQuery(Connection connection) throws SQLException {

    ArrayList<Vehicle> DataBaseVehicles =new ArrayList<Vehicle>();
    ResultSet SQLtable=null;
    PreparedStatement preparedStatement3 =null; //to preparedStatement sql gia shorting
    try {

        //sql query
        preparedStatement3 = connection.prepareStatement("Select Vehicles.PlateNumber, Owners.TaxNo, Vehicles.ExpireDate  as tables"+
                        " from Vehicles,Owners where Owners.OwnerID=Vehicles.OwnerID;");

        //execute sql
        SQLtable = preparedStatement3.executeQuery();
        String plate;
        String tax= new String();
        Date expDate;
        //read all table
        while (SQLtable.next()) {
            plate=SQLtable.getString(1);
            tax=(SQLtable.getString(2));
            expDate=SQLtable.getDate(3);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String DateText = df.format(expDate);
            Vehicle car = new Vehicle(plate,tax,DateText);
            //System.out.println("Plate "+plate+" Tax "+tax+" date "+DateText);
            DataBaseVehicles.add(car);
        }
        return DataBaseVehicles;

    }
    catch(SQLException e){

        System.out.println("There is no data in my database.");

    }

    catch (Exception exc)
    {
        exc.printStackTrace();
    }
    finally
    {
        if (SQLtable != null)
        {
            SQLtable.close();
        }

        if (preparedStatement3 != null)
        {
            preparedStatement3.close();
        }

        if (connection != null)
        {
            connection.close();
        }
    }

    return DataBaseVehicles;

}








}

