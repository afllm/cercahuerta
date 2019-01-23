/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.helper;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author a007243984l
 */
public class UserActivationEmail {

    public static String respuesta = "";

    public static String sendActivationEmail(String email, String nombre, String token) throws Exception {
        // Recipient's email ID needs to be mentioned.
        String to = email;
        String from = "info.cercahuerta@gmail.com";
        String pass = "cercahuerta2019";
        //http://localhost:8081/cercahuerta/json?ob=usuario&op=activar&token=2V5lCaun1BtWNChMXGm3uVCMT2iH1ggn4WnYDbCwNnW3gIUeke5A7wumEyCOgGjBL8fzbP4sEOhWfWsePc8SeTgTXlYu48YZYNZ6jAG4vwdY16XbRPyNxSoaCS4UxtvPTWYIGWgBFLXOS2EIg76vkFRJdRQyh8Mv363VpD1ovh1bkYZFk0VyfbiZVCgRv5CiFxtcuTJRqgIhlVW93f4iAX6srC58ybc45w3wPDEdGQ38Oonznl2UN8mgLIszFjij
        //Hacer pagina confirmación en cliente, que lleve a servidor
        String link = "json?ob=usuario&op=activar&token=" + token;//Esto está mal, no se puede acceder desde el email
        //Con lo de localhost/127.0.0.1 los enlaces no funcionan
        //String link2 = "127.0.0.1:8081/cercahuerta/json?ob=usuario&op=activar&token=" + token;
        //http://localhost:8081/cercahuerta/json?ob=usuario&op=activar&token=M8RXoweslPc7SJJixdX1xcnSoiuntThHPbd2JkOdZdToF6XKroNR5OIplc0tB6iX1mQsnMsHSdXrar0jeflyNxHkQzx3gbauZPR3R3Buqb6HxkBerMO0WlSs1NejhyYp69BxE0EU9l77j57T975Qrmrzjzrl4LVhXondrQVMuLppkgo6E97DKZ3XhZBMokFNkCHQSTAF0pmZLPv7bkSqBBJpXnwuaT42YmSQMiO3DxBmKJXwDQM5JGLXlBUJ221P
        //http://json/?ob=usuario&op=activar&token=M8RXoweslPc7SJJixdX1xcnSoiuntThHPbd2JkOdZdToF6XKroNR5OIplc0tB6iX1mQsnMsHSdXrar0jeflyNxHkQzx3gbauZPR3R3Buqb6HxkBerMO0WlSs1NejhyYp69BxE0EU9l77j57T975Qrmrzjzrl4LVhXondrQVMuLppkgo6E97DKZ3XhZBMokFNkCHQSTAF0pmZLPv7bkSqBBJpXnwuaT42YmSQMiO3DxBmKJXwDQM5JGLXlBUJ221P
        //http://json/?ob=usuario&op=activar&token=2V5lCaun1BtWNChMXGm3uVCMT2iH1ggn4WnYDbCwNnW3gIUeke5A7wumEyCOgGjBL8fzbP4sEOhWfWsePc8SeTgTXlYu48YZYNZ6jAG4vwdY16XbRPyNxSoaCS4UxtvPTWYIGWgBFLXOS2EIg76vkFRJdRQyh8Mv363VpD1ovh1bkYZFk0VyfbiZVCgRv5CiFxtcuTJRqgIhlVW93f4iAX6srC58ybc45w3wPDEdGQ38Oonznl2UN8mgLIszFjij
        //http://localhost:8081/cercahuerta/json?ob=usuario&op=activar&token=M8RXoweslPc7SJJixdX1xcnSoiuntThHPbd2JkOdZdToF6XKroNR5OIplc0tB6iX1mQsnMsHSdXrar0jeflyNxHkQzx3gbauZPR3R3Buqb6HxkBerMO0WlSs1NejhyYp69BxE0EU9l77j57T975Qrmrzjzrl4LVhXondrQVMuLppkgo6E97DKZ3XhZBMokFNkCHQSTAF0pmZLPv7bkSqBBJpXnwuaT42YmSQMiO3DxBmKJXwDQM5JGLXlBUJ221P
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", from);
        properties.setProperty("mail.smtp.auth", "true");
        //Properties properties = System.getProperties();
        //properties.setProperty("mail.smtp.host", "localhost");
        //properties.setProperty("mail.smtp.port", "8081");
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("confirme su alta en Cerca de la Huerta");
            message.setText("Bienvenido a Cerca de la Huerta " + nombre
                    + ",<br> Haz click en <a href='" + link + "'>este sin lo de localhost enlace</a> para confirmar tu cuenta"
                    //+ "<br> Haz click en <a href='" + link2 + "'>este otro enlace</a> a ver si va o que"
                    + "<br><br><small>Mensaje automático; por favor, no responda este correo</small>",
                    "utf-8", "html");
            // Transport.send(message);
            Transport t = session.getTransport("smtp");
            t.connect(from, pass);
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();

            respuesta = "Mesaje enviado con exito";
        } catch (MessagingException mex) {
            respuesta = "Ha habido un error al enviar el mensaje: " + mex.getMessage();
            throw new Exception("Error en UserActivationEmail sendActivationEmail: " + mex.getMessage(), mex);

        }
        return respuesta;
    }

    public static String sendCofirmationEmail(String email, String nombre) throws Exception {
        // Recipient's email ID needs to be mentioned.
        String to = email;
        String from = "info.cercahuerta@gmail.com";
        String pass = "cercahuerta2019";

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", from);
        properties.setProperty("mail.smtp.auth", "true");
        //Properties properties = System.getProperties();
        //properties.setProperty("mail.smtp.host", "localhost");
        //properties.setProperty("mail.smtp.port", "8081");
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Su cuenta en Cerca de la Huerta ha sido confirmada");
            message.setText("Enahorabuena " + nombre
                    + "<br> Haz click en <a href='#'>este enlace</a> para entrar en Cerca de la Huerta"
                    + "<br>Ya puedes empezar a disfrutar de tus ventajas como usuario registrado"
                    + "<br><br><small>Mensaje automático; por favor, no responda este correo</small>",
                    "utf-8", "html");
            // Transport.send(message);
            Transport t = session.getTransport("smtp");
            t.connect(from, pass);
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();

            respuesta = "Mesaje enviado con exito";
        } catch (MessagingException mex) {
            respuesta = "Ha habido un error al enviar el mensaje: " + mex.getMessage();
            throw new Exception("Error en UserActivationEmail sendCofirmationEmail: " + mex.getMessage(), mex);

        }
        return respuesta;
    }

}
