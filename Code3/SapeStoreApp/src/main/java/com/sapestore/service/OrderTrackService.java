package com.sapestore.service;

import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;

// TODO: Auto-generated Javadoc
/**
 * Service interface for getting the oder tracking information. CHANGE LOG
 * VERSION DATE AUTHOR MESSAGE 1.0 20-10-2015 SAPIENT Initial version
 */

public interface OrderTrackService {

  /**
   * Order tracking.
   * 
   * @param orderId
   *          the order id
   * @param userId
   *          the user id
   * @return the order info
   */
  public OrderInfo orderTracking(int orderId, String userId);

  /**
   * Order rent purchase.
   * 
   * @param orderId
   *          the order id
   * @return the order item info
   */
  public OrderItemInfo orderRentPurchase(int orderId);
}
