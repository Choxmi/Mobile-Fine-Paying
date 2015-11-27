package com.HackDev.App;

public class CEBDatabase {
    
private String customerName;
private int locID;
private int customerID;
private String location;
    
    public String getCustomerName(int cusID)
    {
        this.customerID = cusID;
        //Set to the database
        return customerName;
    }
    
    public int getCustomerLocationID()
    {
        //find loc id for cust ID
        //temporary use
        locID = 15;
        return locID;
    }
    
    public String getLocation()
    {
        //find using cus id
        location = "Pallebage";
        return location;
    }
    
}
