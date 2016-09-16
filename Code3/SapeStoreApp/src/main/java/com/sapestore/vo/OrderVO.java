package com.sapestore.vo;

import java.io.Serializable;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * Bean class for Rented orders.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
public class OrderVO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The order number. */
  private int orderNumber; // ORDER_ITEM_ID from ORDER_ITEM_INFO
  
  /** The return received. */
  private boolean returnReceived; // RETURN_STATUS from ORDER_ITEM_INFO
  
  /** The item name. */
  private String itemName; // BOOK_TITLE from SAPESTORE_BOOKS
  
  /** The rent amount. 
   */
  private BigDecimal rentAmount; // RENT_PRICE from SAPESTORE_BOOKS
  
  /** The order status. */
  private boolean orderStatus; // ORDER_STATUS from ORDER_INFO
  
  /** The expected return date. */
  private String expectedReturnDate; // EXPECTED_RETURN_DATE from
                                     
                                     /** The actual return date. */
                                     // ORDER_ITEM_INFO
  private String actualReturnDate; // ACTUAL_RETURN_DATE from ORDER_ITEM_INFO
  
  /** The late fee. */
  private BigDecimal lateFee; // LATE_FEE from SAPESTORE_BOOKS

  /**
   * Gets the order number.
   *
   * @return orderId
   */
  public int getOrderNumber() {
    return orderNumber;
  }

  /**
   * Sets the order number.
   *
   * @param orderNumber   sets orderId.
   */
  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }

  /**
   * Gets the item name.
   * @return book title/name
   */
  public String getItemName() {
    return itemName;
  }

  /**
   * Sets the item name.
   * @param itemName          sets book title/name.
   */
  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  /**
   * Gets the rent amount.
   * @return the rentAmount.
   */
  public BigDecimal getRentAmount() {
    return rentAmount;
  }

  /**
   * Sets the rent amount.
   * @param rentAmount          the rentAmount to set.
   */
  public void setRentAmount(BigDecimal rentAmount) {
    this.rentAmount = rentAmount;
  }

  /**
   * Checks if is return received.
   * @return book return status(returned/pending).
   */
  public boolean isReturnReceived() {
    return returnReceived;
  }

  /**
   * Sets the return received.
   * @param returnReceived          sets book return status(returned/pending).
   */
  public void setReturnReceived(boolean returnReceived) {
    this.returnReceived = returnReceived;
  }

  /**
   * Checks if is order status.
   * @return order status(dispatched/pending).
   */
  public boolean isOrderStatus() {
    return orderStatus;
  }

  /**
   * Sets the order status.
   *
   * @param orderStatus          sets order status(dispatched/pending)
   */
  public void setOrderStatus(boolean orderStatus) {
    this.orderStatus = orderStatus;
  }

  /**
   * Gets the expected return date.
   *
   * @return rented book expected return date
   */
  public String getExpectedReturnDate() {
    return expectedReturnDate;
  }

  /**
   * Sets the expected return date.
   *
   * @param expectedReturnDate          rented book expected return date
   */
  public void setExpectedReturnDate(String expectedReturnDate) {
    this.expectedReturnDate = expectedReturnDate;
  }

  /**
   * Gets the actual return date.
   *
   * @return rented book actual return date
   */
  public String getActualReturnDate() {
    return actualReturnDate;
  }

  /**
   * Sets the actual return date.
   *
   * @param actualReturnDate          sets rented book actual return date
   */
  public void setActualReturnDate(String actualReturnDate) {
    this.actualReturnDate = actualReturnDate;
  }

  /**
   * Gets the late fee.
   *
   * @return the lateFee
   */
  public BigDecimal getLateFee() {
    return lateFee;
  }

  /**
   * Sets the late fee.
   *
   * @param lateFee          the lateFee to set
   */
  public void setLateFee(BigDecimal lateFee) {
    this.lateFee = lateFee;
  }

}