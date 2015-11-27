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
public class SLPDdatabase {
    
private String mobile;    
private String referenceNo;
private String customerIDNo;
private String amount;

    public SLPDdatabase(){}
    public SLPDdatabase(String ref, String id, String amount){
    this.referenceNo = ref;
    this.customerIDNo = id;
    this.amount = amount;
    }
    
    public String getreferenceNo()
    {
        return referenceNo;
    }
    
    public String getcustomerIDNo()
    {
        return customerIDNo;
    }
    
    public String getamount()
    {
        return amount;
    }
    
}

