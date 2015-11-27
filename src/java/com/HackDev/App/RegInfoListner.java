package com.HackDev.App;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.ussd.MoUssdListener;
import hms.kite.samples.api.ussd.OperationType;
import hms.kite.samples.api.ussd.UssdRequestSender;
import hms.kite.samples.api.ussd.messages.MoUssdReq;
import hms.kite.samples.api.ussd.messages.MtUssdReq;
import java.net.MalformedURLException;
import java.net.URL;


public class RegInfoListner implements MoUssdListener {

    @Override
    public void init() {
        System.out.println("In In IN");
    }

    @Override
    public void onReceivedUssd(MoUssdReq moUssdReq) {
        System.out.print(moUssdReq);
        sendUssdContent(moUssdReq);
    }
    
    private void sendUssdContent(MoUssdReq moUssdReq) {
        
        
            System.out.println("Awa Awa ::::::::::::::::::::::::::::::");
		MtUssdReq mtUssdReq = new MtUssdReq();
		mtUssdReq.setApplicationId("APP_015733");
		mtUssdReq.setPassword("c2d9a8547741d76824c7232d22797ee2");
		mtUssdReq.setDestinationAddress(moUssdReq.getSourceAddress());
                //link
		mtUssdReq.setMessage(SessionManager.getUssdContent(moUssdReq.getSourceAddress(), moUssdReq.getMessage(),
		moUssdReq.getUssdOperation()));
                //mtUssdReq.setMessage("Wecome to ussd!");
                System.out.println(mtUssdReq.toString());
		mtUssdReq.setSessionId(moUssdReq.getSessionId());
		mtUssdReq.setUssdOperation(OperationType.MT_CONT.getName());

		try {
			UssdRequestSender ussdRequestSender = new UssdRequestSender(new URL("http://api.dialog.lk:8080/ussd/send/"));
			ussdRequestSender.sendUssdRequest(mtUssdReq);
                        System.out.println("Sent sent ::::::::::::::::::::::::::::::");
                        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SdpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}