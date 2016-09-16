package com.sapestore.vo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * Bean class for Purchased/Rented report.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
public class ReportVO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The publisher name. */
  private String publisherName;

  /** The book title. */
  private String bookTitle;

  /** The quantity. */
  private int quantity;

  /** The book author. */
  private String bookAuthor;

  /** The category name. */
  private String categoryName;

  /** The book price. */
  private int bookPrice;

  /** The order type. */
  private String orderType;

  /** The isbn. */
  private String isbn;

  /**
   * Gets the book price.
   *
   * @return the bookPrice
   */
  public int getBookPrice() {
    return bookPrice;
  }

  /**
   * Sets the book price.
   *
   * @param bookPrice
   *          the bookPrice to set
   */
  public void setBookPrice(int bookPrice) {
    this.bookPrice = bookPrice;
  }

  /**
   * Gets the isbn.
   *
   * @return the isbn
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * Sets the isbn.
   *
   * @param isbn
   *          the isbn to set
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * Gets the category name.
   *
   * @return book category name
   */
  public String getCategoryName() {
    return categoryName;
  }

  /**
   * Sets the category name.
   *
   * @param categoryName
   *          sets book category name
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * Gets the publisher name.
   *
   * @return book publisher name
   */
  public String getPublisherName() {
    return publisherName;
  }

  /**
   * Sets the publisher name.
   *
   * @param publisherName
   *          sets book publisher name
   */
  public void setPublisherName(String publisherName) {
    this.publisherName = publisherName;
  }

  /**
   * Gets the book title.
   *
   * @return book title/name
   */
  public String getBookTitle() {
    return bookTitle;
  }

  /**
   * Sets the book title.
   *
   * @param bookTitle
   *          sets book title/name
   */
  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  /**
   * Gets the quantity.
   *
   * @return available book quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity.
   *
   * @param quantity
   *          sets available book quantity
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Gets the book author.
   *
   * @return book author name
   */
  public String getBookAuthor() {
    return bookAuthor;
  }

  /**
   * Sets the book author.
   *
   * @param bookAuthor
   *          sets book author name
   */
  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }

  /**
   * Gets the order type.
   *
   * @return book order type(purchased/rented)
   */
  public String getOrderType() {
    return orderType;
  }

  /**
   * Sets the order type.
   *
   * @param orderType
   *          sets book order type(purchased/rented)
   */
  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }
}