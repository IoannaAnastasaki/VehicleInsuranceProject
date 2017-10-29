package BusinessLogic;

import Connection.Connection;
import Connection.DBRequests;
import FileUtilities.ExportData;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Connection.Connection.ConnectionToDatabase;

public class TotalDebt
{

    static Connection connection = new Connection();
    static DBRequests dbRequests = new DBRequests();
    private Integer afmNumber;
    public static final Integer queryNumberfour=4;
    public int numberOfExpiredVehicles;
    public double TotalCost=0;
    double fine = 0;

public double CalculateTotalCost()

{

    Boolean rightAfm=false;
    Scanner userAfm = new Scanner(System.in);
    Scanner userFine=new Scanner(System.in);



    /*Ayto to while einai gia validation.Perimenoume o xrhsths gia timh xrhmatwn na dwsei
    * mono double aksia*/

    while (true) {
        System.out.println("Please enter your fine for a vehicle: ");
        try {
            fine = Double.parseDouble(userFine.next());
            break; // vgainei apo thn epanalhpsh mono an dwsei double
        } catch (NumberFormatException ignore) {
            System.out.println("Please insert the exact amount of money");
        }
    }


/*ayto to do-while xrhsimopoieitai kai san validation gia to afm.An einai swsto
* to afm,tote tha kanei syndesh me th vash kai tha ektelesei to query tou erwthmatos 4*/

    do {


        System.out.println("Please enter your tax number: ");
        //take the input for the user
        afmNumber = userAfm.nextInt();

        String afmString=afmNumber.toString();

       int lengthAfm = afmString.length();


        if (lengthAfm != 9){
            System.out.println("Tax number can not be more or less than 9 characters");
        }
        else {

            Pattern p1 = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d\\d");

            Matcher m1 = p1.matcher(afmString);


            if (m1.find() && afmString!="000000000" )
            {

                rightAfm = true;
                this.setAfmNumber(afmNumber); //efoson einai valid,to kanw set ws timh field se ayth th class
                ConnectionToDatabase(queryNumberfour); /*twra paw na kanw syndesh me th vash.To orisma einai
                stathera gia na deiksw poio erwthma pernaw(to 3 h to 4)*/

                //ypologismos tou sunolikou prostimou gia ta anasfalista oxhmata
                TotalCost=this.numberOfExpiredVehicles*this.fine;




                String Output="You have "+ this.numberOfExpiredVehicles+" expired vehicles "
                +"and your total debt is "+TotalCost+" €";
                OutputDebt(Output);


            }

            else {
                System.out.println(afmString + " is not a valid afm number ");
            }

        }
    }//sunexizei na zhtaei afm apo to xrhsth mexri na dwsei valid aksia
    while (rightAfm == false);


    return fine;
}


    public int getAfmValue()
    {
        return this.afmNumber;
    }

    public void setAfmNumber(Integer afmNumber) {
        this.afmNumber = afmNumber;
    }

    public void OutputDebt(String Output ){
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
                    export.SaveToCSV(Output);
                }
                else if(ChOut==2){
                    System.out.println(Output);
                }
                else{
                    System.out.println("There are two option for output.");
                }

            } catch (NumberFormatException ex) {
                System.out.println("You are supposed to give a whole number.");
            }

        }while(ChOut<=0 || ChOut>=3);
    }



}
