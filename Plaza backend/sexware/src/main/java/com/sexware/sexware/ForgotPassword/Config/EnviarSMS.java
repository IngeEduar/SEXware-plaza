package com.sexware.sexware.ForgotPassword.Config;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
public class EnviarSMS {

    public static final String ACCOUNT_SID = "AC5cb723027df08f20d29136310483f181";
    public static final String AUTH_TOKEN = "c47ca8f6fd3fac91a611f0049f289c9b";

        public static void enviarSMS(String numero, String codigo, String rest){

            String mensaje = "Tu pedido en el Restaurante: "+rest+" esta listo. PIN: "+codigo;

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(numero),
                            new com.twilio.type.PhoneNumber("+13203810900"),
                            mensaje)
                    .create();

            System.out.println(message.getSid());
        }

}
