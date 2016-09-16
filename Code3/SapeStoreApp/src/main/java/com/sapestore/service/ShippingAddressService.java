package com.sapestore.service;

import java.util.List;

import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.BookVO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ShippingAddressService.
 */
public interface ShippingAddressService {

  /**
   * Gets the customer details.
   *
   * @param userId the user id
   * @return the customer details
   */
  User getCustomerDetails(String userId);

  /**
   * Gets the customer address.
   *
   * @param userId the user id
   * @return the customer address
   */
  Address getCustomerAddress(String userId);

  /**
   * Gets the city by state id.
   *
   * @param stateId the state id
   * @return the city by state id
   */
  List<City> getCityByStateId(int stateId);

  /**
   * Gets the all state.
   *
   * @return the all state
   */
  List<State> getAllState();

  /**
   * Gets the city by id.
   *
   * @param cityId the city id
   * @return the city by id
   */
  City getCityById(Integer cityId);

  /**
   * Gets the state by id.
   *
   * @param stateId the state id
   * @return the state by id
   */
  State getStateById(Integer stateId);

  /**
   * Update customer infomation.
   *
   * @param user the user
   * @param address the address
   * @return true, if successful
   */
  boolean updateCustomerInfomation(User user, Address address);

  /**
   * Gets the order ids.
   *
   * @return the order ids
   */
  List<OrderItemInfo> getorderIds();

  /**
   * Update order info.
   *
   * @param orderinfo the orderinfo
   * @param orderItem the order item
   * @param bookvo the bookvo
   * @return true, if successful
   */
  // boolean saveShippingAddress(ShippingAddress shippingAddresssave);
  boolean updateOrderInfo(OrderInfo orderinfo, List<OrderItemInfo> orderItem,
      List<BookVO> bookvo);

}
