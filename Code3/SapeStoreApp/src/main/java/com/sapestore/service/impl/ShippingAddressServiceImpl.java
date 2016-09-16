package com.sapestore.service.impl;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ShippingAddressDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.ShippingAddressService;
import com.sapestore.vo.BookVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ShippingAddressServiceImpl.
 */
@Service("shippingAddressService")
@Transactional
public class ShippingAddressServiceImpl implements ShippingAddressService {

  /** The Constant LOGGER. */
  private static final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(ShippingAddressServiceImpl.class.getName());

  /** The shipping dao. */
  @Autowired
  ShippingAddressDao shippingDao;

  /**
   * Gets the shipping dao.
   *
   * @return the shipping dao
   */
  public ShippingAddressDao getShippingDao() {
    return shippingDao;
  }

  /**
   * Sets the shipping dao.
   *
   * @param shippingDao
   *          the new shipping dao
   */
  public void setShippingDao(ShippingAddressDao shippingDao) {
    this.shippingDao = shippingDao;
  }

  @Override
  public User getCustomerDetails(String userId) {
    // TODO Auto-generated method stub
    LOGGER.debug("authenticate method: START");
    User user = shippingDao.getCustomerDetails(userId);
    LOGGER.debug("authenticate method: END");
    return user;
  }

  @Override
  public Address getCustomerAddress(String userId) {
    // TODO Auto-generated method stub
    LOGGER.debug("authenticate method: START");
    Address address = shippingDao.getCustomerAddress(userId);
    LOGGER.debug("authenticate method: END");
    return address;
  }

  @Override
  public List<State> getAllState() {
    // TODO Auto-generated method stub
    LOGGER.debug("authenticate method: START");
    List<State> state = shippingDao.getState();
    LOGGER.debug("authenticate method: END");
    return state;
  }

  @Override
  public List<City> getCityByStateId(int stateId) {
    // TODO Auto-generated method stub
    return shippingDao.getCityByStateId(stateId);

  }

  @Override
  public City getCityById(Integer cityId) {
    // TODO Auto-generated method stub
    return shippingDao.getCityById(cityId);
  }

  @Override
  public State getStateById(Integer stateId) {
    // TODO Auto-generated method stub
    return shippingDao.getStateById(stateId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.sapestore.service.ShippingAddressService#updateCustomerInfomation(com.
   * sapestore.hibernate.entity.User, com.sapestore.hibernate.entity.Address)
   */
  @Override
  public boolean updateCustomerInfomation(User user, Address address) {
    // TODO Auto-generated method stub
    shippingDao.updateCutsomerInfo(user, address);
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sapestore.service.ShippingAddressService#getorderIds()
   */
  @Override
  public List<OrderItemInfo> getorderIds() {
    // TODO Auto-generated method stub
    List<OrderItemInfo> orderIteminfo = shippingDao.getHighIds();
    return orderIteminfo;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.sapestore.service.ShippingAddressService#updateOrderInfo(com.sapestore.
   * hibernate.entity.OrderInfo, java.util.List, java.util.List)
   */
  @Override
  public boolean updateOrderInfo(OrderInfo orderinfo,
      List<OrderItemInfo> orderItem, List<BookVO> bookvo) {
    // TODO Auto-generated method stub
    int quantity;
    shippingDao.updateOrderInfo(orderinfo, orderItem);
    for (BookVO bookVO2 : bookvo) {
      BookVO bookvo3 = shippingDao.getBookByIsbn(bookVO2.getIsbn());
      if (bookvo3.getQuantity() > bookVO2.getQuantity()) {
        quantity = bookvo3.getQuantity() - bookVO2.getQuantity();
        bookVO2.setQuantity(quantity);
        try {
          shippingDao.updateBooks(bookVO2);
        } catch (SapeStoreException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    return true;
  }

  /*
   * @Override public boolean saveShippingAddress(ShippingAddress
   * shippingAddresssave) { // TODO Auto-generated method stub boolean
   * saveship=shippingDao.saveShippingAddress(shippingAddresssave); return
   * saveship; }
   */

}
