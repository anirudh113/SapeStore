package com.sapestore.service.impl;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.OrderConfirmationDao;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.OrderConfirmationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderConfirmationServiceImpl.
 */
@Service("orderConfirmationService")
@Transactional

public class OrderConfirmationServiceImpl implements OrderConfirmationService {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(OrderConfirmationServiceImpl.class.getName());

  /** The orderconfirmationdao. */
  @Autowired
  OrderConfirmationDao orderconfirmationdao;

  /**
   * Gets the orderconfirmationdao.
   *
   * @return the orderconfirmationdao
   */
  public OrderConfirmationDao getOrderconfirmationdao() {
    return orderconfirmationdao;
  }

  /**
   * Sets the orderconfirmationdao.
   *
   * @param orderconfirmationdao
   *          the new orderconfirmationdao
   */
  public void setOrderconfirmationdao(
      OrderConfirmationDao orderconfirmationdao) {
    this.orderconfirmationdao = orderconfirmationdao;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.OrderConfirmationService#getCustomerDetails(java.lang.String)
   */
  @Override
  public User getCustomerDetails(String userId) {
    LOGGER.debug("getCustomerDetails: START");
    User user = (User) orderconfirmationdao.getCustomerDetails(userId);
    LOGGER.debug("getCustomerDetails: END");
    return user;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.OrderConfirmationService#getCustomerAddress(java.lang.String)
   */
  @Override
  public Address getCustomerAddress(String userId) {
    LOGGER.debug("getCustomerAddress: START");
    Address address = (Address) orderconfirmationdao.getCustomerAddress(userId);
    LOGGER.debug("getCustomerAddress: END");
    return address;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.OrderConfirmationService#getCustomerCity(int)
   */
  @Override
  public City getCustomerCity(int cityId) {
    LOGGER.debug("getCustomerCity: START");
    City city = (City) orderconfirmationdao.getCustomerCity(cityId);
    LOGGER.debug("getCustomerCity: END");
    return city;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.OrderConfirmationService#getCustomerState(int)
   */
  @Override
  public State getCustomerState(int stateId) {
    LOGGER.debug("getCustomerState: START");
    State state = (State) orderconfirmationdao.getCustomerState(stateId);
    LOGGER.debug("getCustomerState: END");
    return state;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.OrderConfirmationService#getorderItemDetails(int)
   */
  @Override
  public List<OrderItemInfo> getorderItemDetails(int orderId) {
    LOGGER.debug("getorderItemDetails: START");
    List<OrderItemInfo> listOrderItems = (List<OrderItemInfo>) orderconfirmationdao
        .getorderItemDetails(orderId);
    LOGGER.debug("getorderItemDetails: END");
    return listOrderItems;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.OrderConfirmationService#getOrdersInfo(int)
   */
  @Override
  public OrderInfo getOrdersInfo(int orderId) {
    LOGGER.debug("getOrdersInfo: START");
    OrderInfo orderInfo = (OrderInfo) orderconfirmationdao
        .getOrdersInfo(orderId);
    LOGGER.debug("getOrdersInfo: END");
    return orderInfo;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.OrderConfirmationService#sendMail(java.lang.String, java.lang.String, com.sapestore.hibernate.entity.OrderInfo, java.util.List)
   */
  @Override
  public void sendMail(String username, String mailid, OrderInfo orderInfo,
      List<OrderItemInfo> orderitems) {
    LOGGER.debug("sendMail: START");
    orderconfirmationdao.sendMail(username, mailid, orderInfo, orderitems);
    LOGGER.debug("sendMail: END");
  }

}
