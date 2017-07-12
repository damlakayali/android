package com.damlakayali.notdefterim;

/**
 * Created by damla on 28.06.2017.
 */

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by mesutpiskin on 15.03.2016.
 */
public class ServiceManager {
    private static final String METHOD_NAME = "UyeGiris";
    private static final String NAMESPACE = "";
    private static final String SOAP_ACTION = " http://damlakayali.com/damlakayali/server1.php/UyeGiris";
    private static final String URL = " http://damlakayali.com/damlakayali/server1.php";

    SoapObject soapObject;
    SoapSerializationEnvelope soapSerializationEnvelope;
    HttpTransportSE httpTransportSE;

    public void PushData(String email,String pass) {

        soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
        soapObject.addProperty("Email", email);
        soapObject.addProperty("PASS", pass);


        soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope);
            SoapPrimitive soapPrimitive=(SoapPrimitive)soapSerializationEnvelope.getResponse();
            System.out.println(soapPrimitive.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
