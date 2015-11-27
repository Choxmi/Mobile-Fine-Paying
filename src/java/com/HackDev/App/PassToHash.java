/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.HackDev.App;

import java.util.HashMap;

/**
 *
 * @author Choxmi
 */
public class PassToHash {
    
    public static final HashMap<String,Inquiry> detail = new HashMap<String,Inquiry>();
    
    public static void setData()
    {
        Inquiry details = new Inquiry();
        details.setIDNO("930000000v");
        details.setSerialNo("KLN00101");
        details.setAmount("6");
        detail.put("Key", details);
    }
    
    public static HashMap<String,Inquiry> getData()
    {
        return detail;
    }
    
}
