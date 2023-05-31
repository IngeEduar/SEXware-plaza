package com.sexware.sexware.ForgotPassword.Config;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
public class EnviarSMS {

    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";

        public static void enviarSMS(String numero, String codigo, String rest){

            String mensaje = "Tu pedido en el Restaurante: "+rest+" esta listo. PIN: "+codigo;

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(numero),
                            new com.twilio.type.PhoneNumber(""),
                            mensaje)
                    .create();

            System.out.println(message.getSid());
        }

}
