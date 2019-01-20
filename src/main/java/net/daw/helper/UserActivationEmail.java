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
        String link = "json?ob=usuario&op=activar&token="+token;//Esto está mal, no se puede acceder desde el email

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
                    + ",<br> Haz click en <a href='"+link+"'>este enlace</a> para confirmar tu cuenta"
                    + "<br>"
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
