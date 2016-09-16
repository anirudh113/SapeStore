package com.sapestore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.OrderDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.service.OrderService;
import com.sapestore.vo.DispatchSlip;
import com.sapestore.vo.OrderVO;
import com.sapestore.vo.RentedUpdate;

// TODO: Auto-generated Javadoc
/**
 * Service class for updating rent information.
 * 
 * @author vipul garg
 * @author afreen
 * @author siddharth
 * @author dhina
 * 
 *         CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial
 *         version
 */

@Service("orderService")
public class OrderServiceImpl implements OrderService {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(OrderServiceImpl.class.getName());

  /** The order dao. */
  @Autowired
  private OrderDao orderDao;

  /*
   * (non-Javadoc)
   * 
   * @see com.sapestore.service.OrderService#updateDispatch(java.util.List)
   */
  @Override
  public List<Integer> updateDispatch(List<Integer> dispatchedOrderItemIds)
      throws SapeStoreException {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("updateDispatch method: START");
    }

    List<Integer> dispatchedOrders = orderDao
        .updateDispatch(dispatchedOrderItemIds);
    orderDao.updateDispatchForOrder(dispatchedOrders);

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("updateDispatch method: END");
    }
    return dispatchedOrders;
  }

  /**
   * set the status of rented books from the admin console.
   *
   * @param orderItemInfoList
   *          the order item info list
   * @return beans
   */
  private List<OrderVO> setRentedOrders(List<OrderItemInfo> orderItemInfoList) {
    BasicConfigurator.configure();
    List<OrderVO> beans = null;

    if (orderItemInfoList != null && !orderItemInfoList.isEmpty()) {
      beans = new ArrayList<OrderVO>();
      for (int i = 0; i < orderItemInfoList.size(); i++) {
        OrderVO tempList = new OrderVO();
        tempList.setOrderNumber(orderItemInfoList.get(i).getOrderId());
        tempList.setItemName(orderItemInfoList.get(i).getBookTitle());
        tempList.setRentAmount(orderItemInfoList.get(i).getRentPrice());

        String sd = orderItemInfoList.get(i).getOrderStatus();

        if (sd.equals("true")) {
          tempList.setOrderStatus(true);
        } else {
          tempList.setOrderStatus(false);
        }
        String sr = orderItemInfoList.get(i).getReturnStatus();
        if (sr.equalsIgnoreCase("Returned")) {
          tempList.setReturnReceived(true);
        } else {
          tempList.setReturnReceived(false);
        }
        if (orderItemInfoList.get(i).getExpectedReturnDate() == null) {

        } else {
          tempList.setExpectedReturnDate(
              orderItemInfoList.get(i).getExpectedReturnDate().toString());
        }
        if (orderItemInfoList.get(i).getActualReturnDate() == null) {

        } else {
          tempList.setActualReturnDate(
              orderItemInfoList.get(i).getExpectedReturnDate().toString());
        }
        tempList.setLateFee(orderItemInfoList.get(i).getLateFee());
        beans.add(tempList);
      }
    }
    return beans;
  }

  /**
   * Returns list of dispatched orders.
   *
   * @param list
   *          List of order numbers
   * @return list of DispatchSlip beans
   * @throws SapeStoreException
   *           the sape store exception
   * @throws SapeStoreSystemException
   *           the sape store system exception
   */

  @Override
  public List<DispatchSlip> getDispatchedOrders(List<Integer> list)
      throws SapeStoreException {
    LOGGER.debug("getDispatchedOrders method: START");
    List<DispatchSlip> dispatchList = orderDao.returnDispatchedSlips(list);
    LOGGER.debug("getDispatchedOrders method: END");
    return dispatchList;
  }

  /**
   * Updates return received status of given order in the database.
   *
   * @param orderId
   *          orderId.
   * @return boolean value
   * @throws SapeStoreException
   *           exception.
   */

  @Override
  public List<Boolean> updateReturnOrder(Integer orderId)
      throws SapeStoreException {
    // TODO Auto-generated method stub

    return orderDao.updateReturnOrder(orderId);

  }

  /**
   * Updates payment status of given order in the database.
   *
   * @param orderId
   *          orderId.
   * @return boolean value
   * @throws SapeStoreException
   *           exception.
   */

  @Override
  public List<Boolean> updatePaymentOrder(Integer orderId)
      throws SapeStoreException {
    // TODO Auto-generated method stub
    return orderDao.updatePaymentOrder(orderId);
  }

  /**
   * Gets orderItem details by userId.
   *
   * @param userId
   *          the user id
   * @return list of OrderItemInfo beans
   * @throws SapeStoreException
   *           the sape store exception
   */

  @Override
  public List<OrderItemInfo> getOrderItemDetailByUserId(String userId)
      throws SapeStoreException {
    LOGGER.error("GetOrderId method impl:  START");

    List<OrderItemInfo> completeItemList = orderDao
        .getOrderItemDetailByUserId(userId);

    LOGGER.error("GetOrderId method impl:  END");
    return completeItemList;
  }

  /**
   * sends mail to the customer on payment received .
   *
   * @param orderId
   *          orderId.
   */
  @Override
  public void updateCustomer(Integer orderId) {
    // TODO Auto-generated method stub

    orderDao.sendmail(orderId);

  }

  /**
   * sends mail to the customer on return received.
   *
   * @param orderId
   *          orderId.
   */

  @Override
  public void updateReturnCustomer(Integer orderId) {
    // TODO Auto-generated method stub

    orderDao.sendReturnmail(orderId);

  }

}
