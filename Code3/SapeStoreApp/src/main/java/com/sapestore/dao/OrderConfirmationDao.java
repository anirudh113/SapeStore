package com.sapestore.dao;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// TODO: Auto-generated Javadoc
/**
 * .
 * 
 * @author vsaara
 *
 */
@Repository
public class OrderConfirmationDao {

  /** The Constant LOGGER. */
  private static final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(OrderConfirmationDao.class.getName());

  /** The hibernate template. */
  @Autowired
  private HibernateTemplate hibernateTemplate;

  /**
   * Gets the hibernate template.
   *
   * @return the hibernate template
   */
  public HibernateTemplate getHibernateTemplate() {
    return hibernateTemplate;
  }

  /**
   * . Setter for Hibernate Template
   *
   * @author vsaara
   * @param hibernateTemplate
   *          the new hibernate template
   * @since 30-10-2015
   */
  public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
    this.hibernateTemplate = hibernateTemplate;
  }

  /**
   * . Getting user details for obtaining username and email
   *
   * @author vsaara
   * @param userId
   *          the user id
   * @return the customer details
   * @since 30-10-2015
   */
  public User getCustomerDetails(String userId) {
    LOGGER.debug("OrderConfirmationDao:getCustomerDetails: START");
    String[] namedParams = { "userId" };
    Object[] paramValues = { userId };
    List<User> listUser = (List<User>) hibernateTemplate
        .findByNamedQueryAndNamedParam("User.findByUserId", namedParams,
            paramValues);
    LOGGER.debug("OrderConfirmationDao:getCustomerDetails: END");
    return listUser.get(0);
  }

  /**
   * . Getting customer address using userid
   *
   * @author unatar
   * @param userId
   *          The user id
   * @return the customer address along with city id.
   * @since 30-10-2015
   */
  public Address getCustomerAddress(String userId) {
    LOGGER.debug("OrderConfirmationDao:getCustomerAddress: START");
    String[] namedParams = { "userId" };
    Object[] paramValues = { userId };
    List<Address> listAddress = (List<Address>) hibernateTemplate
        .findByNamedQueryAndNamedParam("Address.findByUserId", namedParams,
            paramValues);
    LOGGER.debug("OrderConfirmationDao:getCustomerAddress: END");
    return listAddress.get(0);

  }

  /**
   * . Getting customer city using cityid
   *
   * @author unatar
   * @param cityId
   *          The city id
   * @return the customer city
   * @since 30-10-2015
   */
  public City getCustomerCity(int cityId) {
    LOGGER.debug("OrderConfirmationDao:getCustomerCity: START");
    String[] namedPar = { "cityId" };
    Object[] paramVal = { cityId };
    List<City> listCity = (List<City>) hibernateTemplate
        .findByNamedQueryAndNamedParam("City.findByCityId", namedPar, paramVal);
    LOGGER.debug("OrderConfirmationDao:getCustomerCity: END");
    return listCity.get(0);

  }

  /**
   * . Getting customer state using stateid
   *
   * @author unatar
   * @param stateId
   *          The state id
   * @return the customer state
   * @since 30-10-2015
   */
  public State getCustomerState(int stateId) {
    LOGGER.debug("OrderConfirmationDao:getCustomerState: START");
    String[] namedPar = { "stateId" };
    Object[] paramVal = { stateId };
    List<State> listState = (List<State>) hibernateTemplate
        .findByNamedQueryAndNamedParam("State.findByStateId", namedPar,
            paramVal);
    LOGGER.debug("OrderConfirmationDao:getCustomerState: END");
    return listState.get(0);

  }

  /**
   * . Getting order info from orders table
   *
   * @author vsaara
   * @param orderId
   *          The order id
   * @return the orders info
   * @since 30-10-2015
   */
  public OrderInfo getOrdersInfo(int orderId) {
    LOGGER.debug("OrderConfirmationDao:getOrdersInfo: START");
    String[] namedPar = { "orderId" };
    Object[] paramVal = { orderId };
    List<OrderInfo> orderlist = (List<OrderInfo>) hibernateTemplate
        .findByNamedQueryAndNamedParam("OrderInfo.findByOrderId", namedPar,
            paramVal);
    LOGGER.debug("OrderConfirmationDao:getOrdersInfo: END");
    return orderlist.get(0);
  }

  /**
   * . Getting orderitem info from orderiteminfo table
   *
   * @author vsaara
   * @param orderId
   *          The order id
   * @return the order item details
   * @since 30-10-2015
   */
  public List<OrderItemInfo> getorderItemDetails(int orderId) {
    LOGGER.debug("OrderConfirmationDao:getOrderItemDetails: START");
    String[] namedPar = { "orderId" };
    Object[] paramVal = { orderId };
    List<OrderItemInfo> listOrderItems = (List<OrderItemInfo>) hibernateTemplate
        .findByNamedQueryAndNamedParam("OrderItemInfo.findByOrderId", namedPar,
            paramVal);
    LOGGER.debug("OrderConfirmationDao:getOrderItemDetails: END");
    return listOrderItems;
  }

  /**
   * Send confirmation mail along with order id and order item details to the
   * user.
   * 
   * @author vsaara
   * @param username
   *          The existing or changed username (as entered on the shipping
   *          address form)
   * @param mailid
   *          The mailid to which the mail needs to be sent.
   * @param orderInfo
   *          the order info
   * @param orderitems
   *          the orderitems
   */
  public void sendMail(String username, String mailid, OrderInfo orderInfo,
      List<OrderItemInfo> orderitems) {
    LOGGER.debug("OrderConfirmationDao:SendMail: START");
    String to = mailid;
    String msg;
    msg = null;
    String from = "admin@sapient.com";
    String host = "inrelaymail.sapient.com";
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    Session session = Session.getDefaultInstance(properties);
    try {

      msg = "Congrats your order has been successfully placed";
      msg += "\nThanks " + username + " for shopping with us \n";
      msg += "OrderID :" + orderInfo.getOrderId() + "\nOrder Details:\n";
      for (OrderItemInfo orderItemInfo : orderitems) {
        int quantity = orderItemInfo.getOrderQuantity();
        int price = orderItemInfo.getBookPrice();
        int subtotal = quantity * price;
        msg += "\nBook ID:\t" + orderItemInfo.getBookTitle().trim()
            + "\nAuthor:\t" + orderItemInfo.getBookAuthor().trim()
            + "\nPurchase type:\t" + orderItemInfo.getPurchaseType().trim()
            + "\nQuantity:\t" + quantity + "\nBook Price:\t" + price
            + "\nSub-total:\t" + subtotal;
        if (orderItemInfo.getPurchaseType().equalsIgnoreCase("Rent"))
          msg += "\nExpected Return Date:\t"
              + orderItemInfo.getExpectedReturnDate();
        msg += "\n";
      }

      msg += "\n\nTotal Payment: " + orderInfo.getTotalPayment()
          + "\n Payment Mode: " + orderInfo.getPaymentMode();
      msg += "\n\nThanks a lot for shopping with us."
          + "We have received your request."
          + "You can track the order using your order id";
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("SapeStore: Your order Details");
      message.setText(msg);
      Transport.send(message);
      System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
      LOGGER.fatal("A problem occured while sending mail", mex);
    }
    LOGGER.debug("OrderConfirmationDao:SendMail: END");
  }

}
