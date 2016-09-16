package com.sapestore.vo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * Bean class for Defaulter report.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
public class AdminDefaulterReport implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The customer name. */
  private String customerName;
  
  /** The book title. */
  private String bookTitle;
  
  /** The rent price. */
  private double rentPrice;
  
  /** The category name. */
  private String categoryName;
  
  /** The expected return date. */
  private String expectedReturnDate;
  
  /** The actual return date. */
  private String actualReturnDate;
  
  /** The return status. */
  private String returnStatus;
  
  /** The late fee. */
  private double lateFee;
  
  /** The order id. */
  private long orderID;
  
  /** The email. */
  private String email;

  /**
   * Gets the email.
   *
   * @return email .
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email.
   *
   * @param email the emailId to set.
   */
  public void setEmail(String email) {
    this.email = email;
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
   * @param customerName the customer's name to set.
   */
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  /**
   * Gets the book title.
   *
   * @return bookTitle.
   */
  public String getBookTitle() {
    return bookTitle;
  }

  /**
   * Sets the book title.
   *
   * @param bookTitle the bookTitle to set.
   */
  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  /**
   * Gets the rent price.
   *
   * @return rentPrice.
   */
  public double getRentPrice() {
    return rentPrice;
  }

  /**
   * Sets the rent price.
   *
   * @param rentPrice the rentPrice to set.
   */
  public void setRentPrice(double rentPrice) {
    this.rentPrice = rentPrice;
  }

  /**
   * Gets the category name.
   *
   * @return categoryName.
   */
  public String getCategoryName() {
    return categoryName;
  }

  /**
   * Sets the category name.
   *
   * @param categoryName the categoryName to set.
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * Gets the expected return date.
   *
   * @return expectedReturnDate.
   */
  public String getExpectedReturnDate() {
    return expectedReturnDate;
  }

  /**
   * Sets the expected return date.
   *
   * @param expectedReturnDate the expectedReturnDate to set.
   */
  public void setExpectedReturnDate(String expectedReturnDate) {
    this.expectedReturnDate = expectedReturnDate;
  }

  /**
   * Gets the actual return date.
   *
   * @return actualReturnDate.
   */
  public String getActualReturnDate() {
    return actualReturnDate;
  }

  /**
   * Sets the actual return date.
   *
   * @param actualReturnDate the actualReturnDate to set.
   */
  public void setActualReturnDate(String actualReturnDate) {
    this.actualReturnDate = actualReturnDate;
  }

  /**
   * Gets the return status.
   *
   * @return returnStatus.
   */
  public String getReturnStatus() {
    return returnStatus;
  }

  /**
   * Sets the return status.
   *
   * @param returnStatus the returnStatus to set.
   */
  public void setReturnStatus(String returnStatus) {
    this.returnStatus = returnStatus;
  }

  /**
   * Gets the late fee.
   *
   * @return lateFee.
   */
  public double getLateFee() {
    return lateFee;
  }

  /**
   * Sets the late fee.
   *
   * @param lateFee the lateFee to set.
   */
  public void setLateFee(double lateFee) {
    this.lateFee = lateFee;
  }

  /**
   * Gets the order id.
   *
   * @return orderID.
   */
  public long getOrderID() {
    return orderID;
  }

  /**
   * Sets the order id.
   *
   * @param orderID the orderID to set.
   */
  public void setOrderID(long orderID) {
    this.orderID = orderID;
  }
}