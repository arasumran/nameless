/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customer;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class NationalCustomer extends Customer {
    private int LicencePlateNumber; 
    private String Occupation="";

    public NationalCustomer()
    {
        super();
        this.LicencePlateNumber=123;
        this.Occupation="noOccupation";
    }
    public NationalCustomer(int CustomerID,String Name, String Surname, int LisenceplateNumber,String Occupation)
    {
        super(CustomerID, Name, Surname);
        this.LicencePlateNumber=LisenceplateNumber;
        this.Occupation=Occupation;
    }
    /**
     * @return the LicencePlateNumber
     */
    /*
                          GETTER SETTER METODLARI
    */
    public int getLicencePlateNumber() {
        return LicencePlateNumber;
    }

    /**
     * @param LicencePlateNumber the LicencePlateNumber to set
     */
    public void setLicencePlateNumber(int LicencePlateNumber) {
        this.LicencePlateNumber = LicencePlateNumber;
    }

    /**
     * @return the Occupation
     */
    public String getOccupation() {
        return Occupation;
    }

    /**
     * @param Occupation the Occupation to set
     */
    public void setOccupation(String Occupation) {
        this.Occupation = Occupation;
    }
    @Override
    public String toString()
    {
        return  super.toString()  + " LicencePlateNumber : " +LicencePlateNumber +"  Occupation :  " +Occupation;
    }
     
}
