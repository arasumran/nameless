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
public class InternationalCustomer extends Customer {
    private String Country = " ";
    private String City = " ";
    
    public InternationalCustomer()
    {
         super();
        this.City="";
        this.Country="";
        
    }
    public InternationalCustomer(int CustomerID,String Name,String Surname,String Country ,String City)
    {
        super(CustomerID,Name,Surname);
        this.City=City;
        this.Country=Country;
    }

    /**
     * @return the Country
     */
     /*
                          GETTER SETTER METODLARI
    */
    public String getCountry() {
        return Country;
    }

    /**
     * @param Country the Country to set
     */
    public void setCountry(String Country) {
        this.Country = Country;
    }

    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City the City to set
     */
    public void setCity(String City) {
        this.City = City;
    }
    
    @Override
    public String toString()
{
    return super.toString() + " Country : " + Country +" City : " +City;
}
        }
