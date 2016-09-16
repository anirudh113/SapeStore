package com.sapestore.service;

import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;

import java.util.List;

/**
 * The Interface OrderConfirmationService.
 */
public interface OrderConfirmationService {

  /**
   * Gets the customer details.
   *
   * @param userId
   *          The user id to obtain the users details from database.
   * @return the customer details
   */
  User getCustomerDetails(String userId);

  /**
   * Gets the customer address.
   *
   * @param userId
   *          The user id which is used to obtain the users address
   * @return the customer address
   */
  Address getCustomerAddress(String userId);

  /**
   * Gets the customer city.
   *
   * @param cityId
   *          The city id of the user to obtain the city name
   * @return the customer city
   */
  City getCustomerCity(int cityId);

  /**
   * Gets the customer state.
   *
   * @param stateId
   *          the state id
   * @return the customer state
   */
  State getCustomerState(int stateId);

  /**
   * Gets the orders info.
   *
   * @param orderId
   *          The order id to obtain info about the order
   * @return the orders info
   */
  OrderInfo getOrdersInfo(int orderId);

  /**
   * Gets the order item details.
   *
   * @param orderId
   *          The order id to obtain details about individual items
   * @return the order item details
   */
  List<OrderItemInfo> getorderItemDetails(int orderId);

  /**
   * Send order confirmation mail with order details to the user.
   *
   * @param username
   *          The username as entered by the user in the shipping address page.
   * @param mailid
   *          The mailid of the user to which the order confirmation mail needs
   *          to be sent.
   * @param orderInfo
   *          The order info which gives details of the order
   * @param orderitems
   *          The orderitem which gives details of the individual order items.
   */
  void sendMail(String username, String mailid, OrderInfo orderInfo,
      List<OrderItemInfo> orderitems);

}
