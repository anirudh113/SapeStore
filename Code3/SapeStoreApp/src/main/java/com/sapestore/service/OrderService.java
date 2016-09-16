package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.vo.DispatchSlip;

/**
 * Service interface for updating rent information.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

/**
 * Interface Order Service.
 * 
 * @author Vipul Garg
 * @author Siddharth
 * @author Afreen
 * @author Dhina
 *
 */

public interface OrderService {

  /**
   * Returns list of dispatched orders.
   * 
   * @param list
   *          List of order numbers
   * @return list of DispatchSlip beans
   * @throws SapeStoreSystemException
   *           exception.
   */
  List<DispatchSlip> getDispatchedOrders(List<Integer> list)
      throws SapeStoreException;

  /**
   * Gets orderItem details by userId
   * 
   * @param userId
   *          userId.
   * @return list of OrderItemInfo beans
   * @throws SapeStoreException
   *           exception.
   */
  public List<OrderItemInfo> getOrderItemDetailByUserId(String userId)
      throws SapeStoreException;

  /**
   * Updates the dispatch status and details in the database
   * 
   * @param dispatchedOrderItemIds
   *          OrderItem ids.
   * @return list of orderId
   * @throws SapeStoreException
   *           exception.
   */
  List<Integer> updateDispatch(List<Integer> dispatchedOrderItemIds)
      throws SapeStoreException;

  /**
   * Updates return received status of given order in the database
   * 
   * @param orderId
   *          orderId.
   * @return boolean value
   * @throws SapeStoreException
   *           exception.
   */
  List<Boolean> updateReturnOrder(Integer orderId) throws SapeStoreException;

  /**
   * Updates payment status of given order in the database
   * 
   * @param orderId
   *          orderId.
   * @return boolean value
   * @throws SapeStoreException
   *           exception.
   */
  List<Boolean> updatePaymentOrder(Integer orderId) throws SapeStoreException;

  /**
   * @param orderId
   *          orderId.
   */
  void updateCustomer(Integer orderId);

  /**
   * @param orderId
   *          orderId.
   */
  void updateReturnCustomer(Integer orderId);
}
