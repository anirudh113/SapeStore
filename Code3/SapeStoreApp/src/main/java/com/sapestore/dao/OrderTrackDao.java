package com.sapestore.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderTrackDao.
 */
@Repository
public class OrderTrackDao {

  /** The hibernate template. */
  @Autowired
  private HibernateTemplate hibernateTemplate;
  /**
   * Logger for log messages.
   */

  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(OrderTrackDao.class.getName());

  /**
   * Gets the hibernate template.
   *
   * @return the hibernate template
   */
  public HibernateTemplate getHibernateTemplate() {
    return hibernateTemplate;
  }

  /**
   * Sets the hibernate template.
   *
   * @param hibernateTemplate the new hibernate template
   */
  public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
    this.hibernateTemplate = hibernateTemplate;
  }

  /**
   * Gets the tracking status.
   *
   * @param orderId the order id
   * @param userId the user id
   * @return the tracking status
   */
  public OrderInfo getTrackingStatus(int orderId, String userId) {
    LOGGER.debug("InventoryDao.getTrackingStatus method: START");
    String[] namedParams = { "orderId", "userId" };
    Object[] paramValues = { orderId, userId };
    @SuppressWarnings("unchecked")
    List<OrderInfo> orderInfoList = (List<OrderInfo>) hibernateTemplate
        .findByNamedQueryAndNamedParam("OrderInfo.findByOrderId1", namedParams,
            paramValues);
    return orderInfoList.get(0);
  }

  /**
   * Gets the rent purchase.
   *
   * @param orderId the order id
   * @return the rent purchase
   */
  public OrderItemInfo getRentPurchase(int orderId) {
    LOGGER.debug("InventoryDao.getRentPurchase method: START");
    String[] namedParams = { "orderId" };
    Object[] paramValues = { orderId };
    @SuppressWarnings("unchecked")
    List<OrderItemInfo> orderItemInfoList = (List<OrderItemInfo>) hibernateTemplate
        .findByNamedQueryAndNamedParam("OrderItemInfo.findByPurchaseType3",
            namedParams, paramValues);
    return orderItemInfoList.get(0);
  }
}
