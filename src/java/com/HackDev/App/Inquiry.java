package com.HackDev.App;
//
import java.sql.Blob;

/**
 * Created by Sathindu on 2015-09-25.
 */
public class Inquiry {
    private String serialNo;
    private String IDNO;
    private String amount;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getIDNO() {
        return IDNO;
    }

    public void setIDNO(String IDNO) {
        this.IDNO = IDNO;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
