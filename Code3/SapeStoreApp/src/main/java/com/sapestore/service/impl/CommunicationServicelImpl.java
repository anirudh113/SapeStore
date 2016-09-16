package com.sapestore.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.CommunicationService;

// TODO: Auto-generated Javadoc
/**
 * The Class CommunicationServicelImpl.
 */
@Service("communicateService")
public class CommunicationServicelImpl implements CommunicationService {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(CommunicationServicelImpl.class.getName());

  /** The str. */
  String str = null;

  /*
   * (non-Javadoc)
   * 
   * @see com.sapestore.service.CommunicationService#communicate(com.sapestore.
   * hibernate.entity.User)
   */
  @Override
  public String communicate(User user) throws SapeStoreException {

    // TODO Auto-generated method stub
    try {
      if (user != null) {
        String mailid = user.getEmailAddress();
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date dateobj = new Date();
        String date = df.format(dateobj);
        String asB65 = Base64.getEncoder()
            .encodeToString(date.getBytes("utf-8"));
        String asB64 = Base64.getEncoder()
            .encodeToString(user.getUserId().getBytes("utf-8"));
        URL myURL = new URL(
            "http://localhost:8080/SapeStore/forgotpassword?userId=" + asB64
                + "&date=" + asB65);

        String to = "vijaysv93@gmail.com";
        String from = "admin@sapient.com";
        String host = "inrelaymail.sapient.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties);
        try {
          MimeMessage message = new MimeMessage(session);
          message.setFrom(new InternetAddress(from));
          message.addRecipient(Message.RecipientType.TO,
              new InternetAddress(to));
          message.setSubject("SapeStore: Password Retrieval");
          message.setText(",\r\n" + "Hello " + user.getName() + ",\r\n"
              + "\r\n Your password reset link is " + myURL + "."
              + "Please reset the password of your account using this email."
              + ".\r\n"
              + "\r\nNote : For any query, please give a reply to "
              + "this mail or contact our customer by calling on 65478.\r\n"
              + "\r\n" + "\r\nRegards\r\n" + "Admin");

          Transport.send(message);

        } catch (MessagingException mex) {
          mex.printStackTrace();
        }

        File file = new File("D:/Password.txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
          file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.append(",\r\n" + "Hello " + user.getName() + ",\r\n"
            + "\r\n Your password reset link is " + myURL + "."
            + "Please reset the password of your account using this email."
            + ".\r\n"
            + "\r\nNote : For any query, please give a reply to this "
            + "mail or contact our customer by calling on 65478.\r\n"
            + "\r\n" + "\r\nRegards\r\n" + "Admin");

        bw.append('\n');
        bw.close();
        str = "Emailsuccess";
      } else {

        str = "InvalidEmail";
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return str;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.sapestore.service.CommunicationService#communicateUpdate(com.sapestore.
   * hibernate.entity.User)
   */
  @Override
  public void communicateUpdate(User user) throws SapeStoreException {

    String mailid = user.getEmailAddress();
    String to = mailid;
    String from = "admin@sapient.com";
    String host = "inrelaymail.sapient.com";
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    properties.put("mail.smtp.starttls.enable", "true");
    Session session = Session.getInstance(properties);
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("Account Update");
      message.setText(",\r\n" + "Hello " + user.getName() + ",\r\n"
          + "\r\n Your Profile has been updated");

      Transport.send(message);

    } catch (MessagingException mex) {
      mex.printStackTrace();
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sapestore.service.CommunicationService#communicateRegister(com.
   * sapestore.hibernate.entity.User)
   */
  @Override
  public void communicateRegister(User user) throws SapeStoreException {
    String mailid = user.getEmailAddress();
    String to = mailid;
    String from = "admin@sapient.com";
    String host = "inrelaymail.sapient.com";
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    properties.put("mail.smtp.starttls.enable", "true");
    Session session = Session.getInstance(properties);
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("Account Created");
      message.setText(",\r\n" + "Hello " + user.getName() + ",\r\n"
          + "\r\n Your Account has been created");

      Transport.send(message);

    } catch (MessagingException mex) {
      mex.printStackTrace();
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.sapestore.service.CommunicationService#communicateUpdatePassword(com.
   * sapestore.hibernate.entity.User)
   */
  @Override
  public String communicateUpdatePassword(User user) throws SapeStoreException {
    // TODO Auto-generated method stub
    try {
      if (user != null) {
        String mailid = user.getEmailAddress();

        String to = mailid;
        String from = "admin@sapient.com";
        String host = "inrelaymail.sapient.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties);
        try {
          MimeMessage message = new MimeMessage(session);
          message.setFrom(new InternetAddress(from));
          message.addRecipient(Message.RecipientType.TO,
              new InternetAddress(to));
          message.setSubject("SapeStore: Password Retrieval");
          message.setText(",\r\n" + "Hello " + user.getName() + ",\r\n"
              + "\r\n Your password "
              + "has been updated.Login your account and enjoy SapeStore!"
              + ".\r\n"
              + "\r\nNote : For any query, please give a reply to "
              + "this mail or contact our customer by calling on 65478.\r\n"
              + "\r\n" + "\r\nRegards\r\n" + "Admin");

          Transport.send(message);

        } catch (MessagingException mex) {
          mex.printStackTrace();
        }

        File file = new File("D:/UpdatePassword.txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
          file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.append(",\r\n" + "Hello " + user.getName() + ",\r\n"
            + "\r\n Your password "
            + "has been updated.Login your account and enjoy SapeStore!"
            + ".\r\n"
            + "\r\nNote : For any query, please give a reply to this "
            + "mail or contact our customer by calling on 65478.\r\n"
            + "\r\n" + "\r\nRegards\r\n" + "Admin");

        bw.append('\n');
        bw.close();
        str = "updatePswdSucss";
      } else {
        return "error";
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return str;
  }

}
