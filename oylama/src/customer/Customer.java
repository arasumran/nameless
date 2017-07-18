/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customer;


public class Customer {
    
    
private int CustomerID;
private  String Name ="";
private  String Surname;
      

     public Customer()
{
        this.Surname = "";
  this.CustomerID=111;
  this.Name="";
   
}
public Customer(int CustomerID, String Name,String Surname)
{
        this.Surname = "";
    this.CustomerID=CustomerID;
    this.Name=Name;
    this.Surname=Surname;
    
    
    
}

    /**
     * @return the CustomerID
     */
    public int getCustomerID() {
        return CustomerID;
    }

    /**
     * @param CustomerID the CustomerID to set
     */
    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    /**
     * @return the Name
     */
     /*
                          GETTER SETTER METODLARI
    */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Surname
     */
    public String getSurname() {
        return Surname;
    }

    /**
     * @param Surname the Surname to set
     */
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    /**
     *
     * @return
     */
    @Override
 public String toString( )
    {
        return "id no : " + CustomerID +  " name : " +Name +" Surname : " + Surname;
    }

}
