
package com.sapestore.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.sapestore.service.DefaulterEmailService;
// TODO: Auto-generated Javadoc
/** 
 * This class contains implementation of DefaulterEmailService method.
* @author hsin59
*
*/
@Service("defaulterEmailService")
public class DefaulterEmailServiceImpl implements DefaulterEmailService {

/**
 * This method contains implementation of DefaulterEmailService method.
 *
 * @param ids the ids
 * @return the string
 */
  @Override
  public final String sendDefaulterNotification(final String ids) {
    // TODO Auto-generated method stub
    String[] sepIds = ids.split(":");
    for (int i = 0; i < sepIds.length; i++) {
      String[] details = sepIds[i].split("#");
      String from = "admin@sapestore.com";
      String host = "inrelaymail.sapient.com";

      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
      properties.put("mail.smtp.starttls.enable", "true");
      Session session = Session.getInstance(properties);
      try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.
            TO, new InternetAddress(details[0]));

        if (details[5].equals("RETURNED")) {
          message.setSubject("Dear Customer, Your"
              + " late book return has levied a late fee!!");
          message.setText(
              "Dear " + details[1] + ",\r\n" + "\r\nYou returned the rented "
          + details[2] + " post its due date i.e. "
                  + details[3] + ". Hence you have been "
                      + "charged a late fee of $" + details[4] + ".\r\n"
                  + "\r\nNote : In case of any query, please give"
                  + " a call to our customer support at +1 2444448080.\r\n"
                  + "\r\n" + "\r\nHappy Reading!!\r\n" + "Sape Store Admin");

        } else {
          message.setSubject("Dear Customer, Your book return is pending!!");
          message.setText(
              "Dear " + details[1] + ",\r\n" + "\r\nYou have rented "
          + details[2] + " and its due date for return was "
                  + details[3] + ". We have not received the "
                      + "book and you have been levied a late"
                      + " fee of $" + details[4]
                  + ".\r\n" + "Please return the book at the"
                      + " earliest to avoid further increase in "
                      + "late fee charge.\r\n"
                  + "\r\nNote In case of any query, please give a "
                  + "call to our customer support at +1 2444448080.\r\n"
                  + "\r\n" + "\r\nHappy Reading!!\r\n" + "Sape Store Admin");

        }
        Transport.send(message);
      } catch (MessagingException mex) {

        return "failure";
      }
    }

    return "success";

  }

}
