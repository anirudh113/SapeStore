package com.sapestore.vo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * Bean class for Book Dispatch Slip.

 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
public class DispatchSlip implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The order number. */
  private int orderNumber;
  
  /** The customer name. */
  private String customerName;
  
  /** The shipping address. */
  private String shippingAddress;

  /**
   * Gets the order number.
   *
   * @return orderID.
   */
  public int getOrderNumber() {
    return orderNumber;
  }

  /**
   * Sets the order number.
   *
   * @param orderNumber          sets orderID.
   */
  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }

  /**
   * Gets the customer name.
   *
   * @return customer name.
   */
  public String getCustomerName() {
    return customerName;
  }

  /**
   * Sets the customer name.
   *
   * @param customerName          sets customer name.
   */
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  /**
   * Gets the shipping address.
   *
   * @return shipping address for the order.
   */
  public String getShippingAddress() {
    return shippingAddress;
  }

  /**
   * Sets the shipping address.
   *
   * @param shippingAddress          sets shipping address for the order.
   */
  public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
  }
}