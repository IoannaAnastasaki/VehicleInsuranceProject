package Model;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Owner {
    private String TaxNo;

    /** Give the Tax number of person
     * @return TaxNo is return as a string 9 char size
     */
    public String getTaxNo(){
        return this.TaxNo;
    }

    /**Set the TaxNo to the owner
     * @param taxNo which is a String
     */
    public void setTaxNo( String taxNo){
        this.TaxNo = taxNo;
    }

    /**Check the validation of a Tax Number must be 9 digits and nit 000 000 000
     * @param taxNo the tax number as String
     * @return boolean true or false
     */
    public boolean TaxValidation(String taxNo){
        int TaxSize = taxNo.length();
        //must be
        if (TaxSize==9){
            //must have 9  digits use regex
            Pattern p=Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d\\d");
            //m check if the given string mach with the pattern
            Matcher m=p.matcher(taxNo);
            if (m.find()){
                //There no registration with taxNo 000 000 000
                if (taxNo.equals("000000000")){
                    return false;
                }
                return true;
            }
            else{
                return false;
            }
        }
        else{
            /*I can't be sure if it was with the old system and have a Zero at
              begin or he has forget a number of the middle
            */
            return false;
        }
    }
}
