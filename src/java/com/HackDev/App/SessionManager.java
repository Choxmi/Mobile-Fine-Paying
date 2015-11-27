package com.HackDev.App;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.caas.ChargingRequestSender;
import hms.kite.samples.api.caas.messages.ChargingRequestResponse;
import hms.kite.samples.api.caas.messages.DirectDebitRequest;
import hms.kite.samples.api.subscription.SubscriptionRequestSender;
import hms.kite.samples.api.subscription.messages.SubscriptionRequest;
import hms.kite.samples.api.subscription.messages.SubscriptionResponse;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionManager {

    public static ConcurrentHashMap<String, String> sessions = new ConcurrentHashMap<String, String>();
    public static final HashMap<String, Inquiry> detail = PassToHash.getData();
    String amount ;
    public static String getUssdContent(String mobileNo, String input, String ussdOperation) {
        PassToHash.setData();
        String amount ;
        /*
        int[] array = new int[5];

         for(int i=0;i<5;i++)
         {
         array[i] = i+1;
         }*/
        if ("mo-init".equals(ussdOperation)) {
            sessions.remove(mobileNo);
            sessions.put(mobileNo, "welcome");
            return "Welcome to SLPD MFine service. \n\n1. Make a payment \n\n99.Exit"; //\n1. Register \n2. Menu \n3. Feedback \n4. Order
        } else if ("welcome".equals(sessions.get(mobileNo)) && "1".equals(input)) {
            sessions.remove(mobileNo);
            sessions.put(mobileNo, "ReferenceNo");
            return "Enter your reference number : ";
        }else if ("welcome".equals(sessions.get(mobileNo)) && "99".equals(input)) { 
            System.exit(1);
        }else if ("ReferenceNo".equals(sessions.get(mobileNo))) {
           /* for(int i=0;i<array.length;i++)
             {                        
             if(array[i] == Integer.parseInt(input))
             {
             sessions.remove(mobileNo);
             sessions.put(mobileNo, "Confirm");
             return "Confirm your payment \n1. Yes Confirm \n2.No";
             }
             }*/

            for (String i : detail.keySet()) {
                Inquiry database = detail.get(i);
                System.out.println("Serial Number is"+database.getSerialNo());
                //System.out.println(database.getreferenceNo()+"  "+database.getcustomerIDNo()+""+database.getamount());
                System.out.println("Input is "+input);
                System.out.println("Amount pre99. Exit");
                if (database.getSerialNo().equals(input)) {
                    sessions.remove(mobileNo);
                    sessions.put(mobileNo, "Confirm");
                    amount = database.getAmount();
                    return "Confirm your payment of Rs."+amount+" for Reference No. "+database.getSerialNo()+" \n1. Yes Confirm \n2.No";
                }
            }

            return "Your reference number is invalid";
        } else if ("Confirm".equals(sessions.get(mobileNo)) && "1".equals(input)) {
            System.out.println("Amount post");
            return FinePayment(mobileNo, "6");
        } else if ("Confirm".equals(sessions.get(mobileNo)) && "2".equals(input)) {
            return "Payment Cancelled";
        }else{return "Invalid Input!!!";}

        return "Error occurred while processing request";
    }

    private static String FinePayment(String mobile, String Amount) {
        DirectDebitRequest debitRequest = new DirectDebitRequest();
        debitRequest.setApplicationId("APP_015733");
        debitRequest.setPassword("c2d9a8547741d76824c7232d22797ee2");
        debitRequest.setSubscriberId(mobile);
        debitRequest.setAmount(Amount);
        debitRequest.setExternalTrxId(String.valueOf(System.currentTimeMillis()));

        String licenseID = "20";
        String ref = "5";

        try {
            ChargingRequestSender chargeReq = new ChargingRequestSender(new URL("http://api.dialog.lk:8080/caas/direct/debit"));
            System.out.println(debitRequest);
            ChargingRequestResponse requestRespose = chargeReq.sendChargingRequest(debitRequest);
            System.out.println(requestRespose);
            if ("S1000".equals(requestRespose.getStatusCode())) {               
                PaymentHash.setMap(mobile, licenseID, Amount, ref);
                return "Payment Succssful  \n\n";
            } else {
                return requestRespose.getStatusDetail();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SdpException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }
}

/*else if ("welcome".equals(sessions.get(mobileNo))) {
 if ("1".equals(input)) {

 SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
 subscriptionRequest.setApplicationId("APP_000101");
 subscriptionRequest.setPassword("password");
 subscriptionRequest.setSubscriberId(mobileNo);
 subscriptionRequest.setAction("1");

 try {
 SubscriptionRequestSender subscriptionRequestSender = new SubscriptionRequestSender(new URL(
 "http://127.0.0.1:7000/subscription/send"));
 SubscriptionResponse subscriptionResponse = subscriptionRequestSender
 .sendSubscriptionRequest(subscriptionRequest);
 String statusCode = subscriptionResponse.getStatusCode();

 if ("S1000".equals(statusCode)) {
                                            
 //=====================      
 return "Enter your reference number";
                                                
                                                
 //=====================
 } else if ("E1351".equals(statusCode)) {
 return statusCode;
 }

 } catch (MalformedURLException e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 } catch (SdpException e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 }

 return "Error occrrued while registering.";
 }
 */
//============================================================================================================
//============================================= 2 ==== status ====================================================                        
//===========================================================================================================
//============================================================================================================
//============================================= 3 ==== complaint ====================================================                        
//===========================================================================================================
                    /*    else if ("3".equals(input)) {
           
 return "Your complaint has been recorded";
 }
                        
 //============================================================================================================
 //============================================= 4 ==== Details ====================================================                        
 //===========================================================================================================
 else if ("4".equals(input)) {
           
 return "CEB info system";
 }

 //============================================================================================================
 //============================================= 99 ============================================================                        
 //===========================================================================================================
                     
 else if ("99".equals(input)) {
                                                         
 //				SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
 //				subscriptionRequest.setApplicationId("APP_000101");
 //				subscriptionRequest.setPassword("password");
 //				subscriptionRequest.setSubscriberId(mobileNo);
 //                                subscriptionRequest.setAction("1");
 //
 //				try {
 //					SubscriptionRequestSender subscriptionRequestSender = new SubscriptionRequestSender(new URL(
 //							"http://127.0.0.1:7000/subscription/send"));
 //					SubscriptionResponse subscriptionResponse = subscriptionRequestSender
 //							.sendSubscriptionRequest(subscriptionRequest);
 //					String statusCode = subscriptionResponse.getStatusCode();
 //
 //				if ("S1000".equals(statusCode)) {
 //                                           
 return "Thank you for using CEB services";
 //                                                
 //					} else if ("E1351".equals(statusCode)) {
 //					
 //                                            return "Couldn't process request. Try again.";
 //					}
 //
 //				} catch (MalformedURLException e) {
 //					// TODO Auto-generated catch block
 //					e.printStackTrace();
 //				} catch (SdpException e) {
 //					// TODO Auto-generated catch block
 //					e.printStackTrace();
 //				}
 //
 //				return "Error occrrued while registering.";
 }

                        
 }*/
