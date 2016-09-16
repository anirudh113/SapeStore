package com.sapestore.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.Country;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.DispatchSlip;
import com.sun.mail.iap.ConnectionException;

// TODO: Auto-generated Javadoc
/**
 * DAO class for order management module
 */

/**
 * @author Siddharth .
 * @author Afreen
 * @author Vipul Garg
 *
 */

@Repository
@Transactional
public class OrderDao {

  /** The hibernate template. */
  @Autowired
  private HibernateTemplate hibernateTemplate;

  /**
   * Logger for log messages.
   */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(OrderDao.class.getName());

  /**
   * Returns purchased orders to OrderService
   * @return List of OrderItemInfo objects
   * @throws SapeStoreException .
   */
  @SuppressWarnings("unchecked")
  public List<OrderItemInfo> getPurchasedOrders() throws SapeStoreException {
    LOGGER.debug("InventoryDao.getBookDetails method for purchase: START");
    List<OrderItemInfo> orderItemInfoList = null;
    orderItemInfoList = (List<OrderItemInfo>) hibernateTemplate
        .findByNamedQuery("OrderItemInfo.findByPurchaseType1");
    return orderItemInfoList;
  }

  /**
   * Updates the dispatch status for given list of order item IDs .
   * @param orderItemNums .
   * @return List of order IDs for corresponding order item IDs
   * @throws SapeStoreException .
   */
  public List<Integer> updateDispatch(List<Integer> orderItemNums)
      throws SapeStoreException {

    List<Integer> orderNums = new ArrayList<>();
    String query = "from OrderItemInfo o where o.orderItemId = :orderItemIdsel";

    Query query2 = hibernateTemplate.getSessionFactory().getCurrentSession()
        .createQuery(query);
    for (Integer orderItemId : orderItemNums) {
      OrderItemInfo orderItemInfoObj = new OrderItemInfo();
      query2.setParameter("orderItemIdsel", orderItemId);
      orderItemInfoObj = (OrderItemInfo) query2.uniqueResult();

      sendDispatchmail(orderItemInfoObj.getOrderId());

      orderNums.add(orderItemInfoObj.getOrderId());
      orderItemInfoObj.setDispatchDate(new Date());
      orderItemInfoObj.setOrderStatus("true");
      hibernateTemplate.save(orderItemInfoObj);
    }
    return orderNums;
  }

  /**
   * Update dispatch for order status tracking
   * @param orderNums .
   */

  public void updateDispatchForOrder(List<Integer> orderNums) {
    // TODO Auto-generated method stub
    String selectQuery = "from OrderItemInfo o where o.orderId = :orderIdsel";
    String updateQuery = "update OrderInfo o set o.orderStatus = "
        + ":orderState where o.orderId = :orderIdsel";
    Query query3 = hibernateTemplate.getSessionFactory().getCurrentSession()
        .createQuery(updateQuery);

    for (Integer orderId : orderNums) {
      int flag = 0;
      @SuppressWarnings("unchecked")
      List<OrderItemInfo> orderItems = (List<OrderItemInfo>) hibernateTemplate
          .findByNamedParam(selectQuery, "orderIdsel", orderId);
      for (OrderItemInfo orderItem : orderItems) {
        if (orderItem.getOrderStatus() == null) {
          flag++;
        }
      }
      if (flag > 0) {
        query3.setParameter("orderState", "Not Dispatched");
        query3.setParameter("orderIdsel", orderId);
        query3.executeUpdate();
      } else if (flag == 0) {
        query3.setParameter("orderState", "Dispatched");
        query3.setParameter("orderIdsel", orderId);
        query3.executeUpdate();
      }
    }

  }

  /**
   * Gets the rented status of the book
   * @return List of OrderItemInfo objects
   * @throws ConnectionException .
   * @throws TransactionExecutionException .
   */
  @SuppressWarnings("unchecked")
  public List<OrderItemInfo> getRentedOrders() throws SapeStoreException {
    LOGGER.debug("InventoryDao.getBookDetails method: START");
    List<OrderItemInfo> orderItemInfoList = null;
    orderItemInfoList = (List<OrderItemInfo>) hibernateTemplate
        .findByNamedQuery("OrderItemInfo.findByPurchaseType2");
    return orderItemInfoList;
  }

  /**
   * This method sets dispatch slip.
   * 
   * @param list
   *          List of all the ordersIds to be dispatched
   * @return List of all the orders to be dispatched
   * @throws ConnectionException . 
   * @throws TransactionExecutionException .
   */
  @SuppressWarnings("unchecked")
  public List<DispatchSlip> returnDispatchedSlips(List<Integer> list)
      throws SapeStoreException {
    LOGGER.debug("returnDispatchedSlips method: START");
    List<DispatchSlip> dispatchSlipBeans = new ArrayList<DispatchSlip>();

    List<OrderItemInfo> allorders = (List<OrderItemInfo>) hibernateTemplate
        .findByNamedParam(
            "from OrderItemInfo o where o.orderStatus = :os and "
            + "o.returnStatus IS NULL and o.paymentStatus IS NULL",
            "os", "true");
    for (OrderItemInfo item : allorders) {
      list.add(item.getOrderId());

    }

    try {
      for (Integer i : list) {

        OrderInfo orInfo = (OrderInfo) hibernateTemplate.get(OrderInfo.class,
            i);

        List<Address> addressInfo = (List<Address>) hibernateTemplate
            .findByNamedParam("from Address a where a.userId = :userid",
                "userid", orInfo.getUserId());

        City city = (City) hibernateTemplate.get(City.class,
            addressInfo.get(0).getCityId());

        Country country = (Country) hibernateTemplate.get(Country.class,
            addressInfo.get(0).getCountryId());

        DispatchSlip dispatchSlipBean = new DispatchSlip();

        if (orInfo != null) {
          LOGGER.debug("List obtained is " + list);
          dispatchSlipBean.setOrderNumber(i);
          dispatchSlipBean.setCustomerName(orInfo.getName());
          String shippingAddress = addressInfo.get(0).getAddressLine1() + ","
              + addressInfo.get(0).getAddressLine2() + "," + city.getCityName()
              + "," + country.getCountryName();
          dispatchSlipBean.setShippingAddress(shippingAddress);
          dispatchSlipBeans.add(dispatchSlipBean);
        }

      }
    } catch (SapeStoreSystemException se) {
      LOGGER.fatal(
          "A DB exception occured while getting the dispatch orders list", se);
    }
    LOGGER.debug("returnDispatchedSlips method: END");
    return dispatchSlipBeans;
  }

  /**
   * Updates return status of an order
   * @param orderId Order ID whose return status is to be updated
   * @return Boolean value list for validation
   * @throws SapeStoreException .
   */
  @SuppressWarnings("unchecked")
  public List<Boolean> updateReturnOrder(Integer orderId)
      throws SapeStoreException {

    List<Boolean> checks = new ArrayList<>();

    boolean canUpdate = true;
    boolean dispatched = true;
    OrderInfo order = hibernateTemplate.get(OrderInfo.class, orderId);
    if (order == null) {
      canUpdate = false;
    } else {

      String[] name = { "orderid", "purchasetype" };
      Object[] value = { orderId, "rent" };
      LOGGER.debug("InventoryDao.getBookDetails method: START");
      List<OrderItemInfo> orderitemlist = (List<OrderItemInfo>) hibernateTemplate
          .findByNamedQueryAndNamedParam("OrderItemInfo.getbyOrderId", name,
              value);

      for (OrderItemInfo item : orderitemlist) {

        if (item.getOrderStatus() == null) {

          dispatched = false;
          canUpdate = false;
        } else if (item.getOrderStatus().equalsIgnoreCase("true")) {

          item.setReturnStatus("Returned");
          item.setActualReturnDate(new Date());
          hibernateTemplate.update(item);
        }

      }
    }

    checks.add(dispatched);
    checks.add(canUpdate);
    return checks;
  }

  /**
   * Updates payment status of given order
   * @param orderId Order ID whose payment status is to be updated
   * @return Boolean value list for validation
   * 
   */
  @SuppressWarnings("unchecked")
  public List<Boolean> updatePaymentOrder(Integer orderId) {
    List<Boolean> checks = new ArrayList<>();
    boolean canUpdate = true;
    boolean dispatched = true;

    OrderInfo order = hibernateTemplate.get(OrderInfo.class, orderId);
    if (order == null) {
      canUpdate = false;
    } else {
      String[] name = { "orderid", "purchasetype" };
      Object[] value = { orderId, "buy" };
      LOGGER.debug("InventoryDao.getBookDetails method: START");
      List<OrderItemInfo> orderitemlist = (List<OrderItemInfo>) hibernateTemplate
          .findByNamedQueryAndNamedParam("OrderItemInfo.getbyOrderId", name,
              value);
      for (OrderItemInfo item : orderitemlist) {
        if (item.getOrderStatus() == null) {
          dispatched = false;
          canUpdate = false;
        } else if (item.getOrderStatus().equalsIgnoreCase("true")) {
          item.setPaymentStatus("true");
          hibernateTemplate.update(item);
        }
      }
    }

    checks.add(dispatched);
    checks.add(canUpdate);
    return checks;
  }

  /**
   * @param userId .
   * @return
   * @throws SapeStoreException .
   */
  @SuppressWarnings("unchecked")
  public List<OrderItemInfo> getOrderItemDetailByUserId(String userId)
      throws SapeStoreException {

    LOGGER.error("getOrderItemDetailByUserId method dao: start");
    String[] namedParams = { "userId" };
    Object[] paramValues = { userId };
    LOGGER.error("User ID in dao:" + userId);

    List<OrderInfo> orderList = (List<OrderInfo>) hibernateTemplate
        .findByNamedQueryAndNamedParam("OrderInfo.getByUserId", namedParams,
            paramValues);

    List<OrderItemInfo> completeList = new ArrayList<>();

    for (OrderInfo orderInfo : orderList) {

      Integer currentOrderId = orderInfo.getOrderId();
      LOGGER.error("for loop " + currentOrderId);
      String[] named = { "orderId" };
      Object[] paramValue = { currentOrderId };

      List<OrderItemInfo> currentList = (List<OrderItemInfo>) hibernateTemplate
          .findByNamedQueryAndNamedParam("OrderItemInfo.findByOrderId", named,
              paramValue);

      for (OrderItemInfo orderItemInfo : currentList) {
        LOGGER.error("in 2nd for loop isbn is " + orderItemInfo.getIsbn());
        completeList.add(orderItemInfo);
      }
    }

    if (!completeList.isEmpty()) {
      for (OrderItemInfo orderItemInfo : completeList) {
        System.out.println(orderItemInfo);
      }

      LOGGER.error("getOrderItemDetailByUserId method dao: end");
      return completeList;
    } else {
      return null;
    }
  }

  /**
   * Sends an email to customer on dispatch of books .
   * @param orderId .
   */
  private void sendDispatchmail(Integer orderId) {
    OrderInfo orderInfo = hibernateTemplate.get(OrderInfo.class, orderId);
    User user = hibernateTemplate.get(User.class, orderInfo.getUserId());

    String to = user.getEmailAddress();
    String from = "admin@sapient.com";
    String host = "inrelaymail.sapient.com";
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    Session session = Session.getDefaultInstance(properties);

    try {
      MimeMessage message = new MimeMessage(session);

      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("SapeStore: Your Order Dispatched");
      message.setText("Hello " + user.getName() + ".Your order with OrderID "
          + orderInfo.getOrderId()
          + "has been dispatched and will reach you soon.");

      Transport.send(message);
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
  }

  /**
   * Send an email to customer on successful receipt of payment
   * @param mail .
   * @param orderId .
   */
  public void sendmail(Integer orderId) {

    OrderInfo orderInfo = hibernateTemplate.get(OrderInfo.class, orderId);
    User user = hibernateTemplate.get(User.class, orderInfo.getUserId());

    String to = user.getEmailAddress();
    String from = "admin@sapient.com";
    String host = "inrelaymail.sapient.com";
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    Session session = Session.getDefaultInstance(properties);
    try {
      MimeMessage message = new MimeMessage(session);

      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("SapeStore: Your Payment Received");
      message.setText("Thanks " + user.getName()
          + " for shopping with us. \nWe have received your payment."
          + "\nOrder details:\nOrderID :" + orderInfo.getOrderId()
          + "\n Total Payment: " + orderInfo.getTotalPayment()
          + "\n Payment Mode: " + orderInfo.getPaymentMode());

      Transport.send(message);
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }

  }

  /**
   * Send an email to customer on successful receipt of book
   * @param mailId .
   * @param orderId .
   */
  public void sendReturnmail(Integer orderId) {

    OrderInfo orderInfo = hibernateTemplate.get(OrderInfo.class, orderId);
    User user = hibernateTemplate.get(User.class, orderInfo.getUserId());

    String to = user.getEmailAddress();
    String from = "admin@sapient.com";
    String host = "inrelaymail.sapient.com";
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    Session session = Session.getDefaultInstance(properties);

    try {
      MimeMessage message = new MimeMessage(session);

      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("SapeStore: Your Return Received");
      message.setText("Thanks " + user.getName()
          + " for shopping with us. \nWe have received the return of "
          + "books for the order with OrderID "
          + orderInfo.getOrderId());

      Transport.send(message);
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
  }
}
