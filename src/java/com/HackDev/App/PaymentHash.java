/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.HackDev.App;

/**
 *
 * @author Choxmi
 */
import java.util.HashMap;

public class PaymentHash {
    public static String mobile;
    public static final HashMap<String,SLPDdatabase> pay = new HashMap<String,SLPDdatabase>();
    public static void setMap(String id,String licenseID,String amount,String ref)
    {
        mobile = id;
        pay.put(id,new SLPDdatabase(ref, licenseID, amount));
    }
    
    public static HashMap<String,SLPDdatabase> getMap()
    {
        return pay;
    }
}
