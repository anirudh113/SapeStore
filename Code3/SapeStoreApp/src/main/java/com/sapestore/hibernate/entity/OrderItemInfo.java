package com.sapestore.hibernate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * braavos Added named query and edited existing findByPurchaseType query
 * 
 * @author asiraj
 *
 */
@Entity
@Table(name = "ORDER_ITEM_INFO")
@NamedQueries(value = {
    @NamedQuery(name = "OrderItemInfo.getbyOrderId", query = "from OrderItemInfo o where o.orderId = :orderid and o.purchaseType = :purchasetype"),
    @NamedQuery(name = "OrderItemInfo.findByPurchaseType1", query = "from OrderItemInfo o where o.purchaseType = 'buy'"),
    @NamedQuery(name = "OrderItemInfo.findByPurchaseType2", query = "from OrderItemInfo o where o.purchaseType = 'rent'"),
    @NamedQuery(name = "OrderItemInfo.findDefaulters", query = "from OrderItemInfo o where o.purchaseType = 'rent' and "
        + "((o.returnStatus = 'Not Returned' and o.expectedReturnDate < SYSDATE) or "
        + "(o.returnStatus = 'Returned' and o.actualReturnDate > o.expectedReturnDate))"),
    @NamedQuery(name = "OrderItemInfo.getAllIds", query = "from OrderItemInfo o "),
    @NamedQuery(name = "OrderItemInfo.findByPurchaseType3", query = "from OrderItemInfo o where o.orderId=:orderId and (o.purchaseType = 'buy' or o.purchaseType = 'rent')"),
    @NamedQuery(name = "OrderItemInfo.findByOrderId", query = "from OrderItemInfo o where o.orderId = :orderId") })
public class OrderItemInfo implements Serializable {


  private static final long serialVersionUID = 3555506529249166772L;

  @Id
  @Column(name = "ORDER_ID")
  private Integer orderId;

  @Id
  @Column(name = "ORDER_ITEM_ID")
  private Integer orderItemId;

  @Column(name = "ISBN")
  private String isbn;

  @Column(name = "BOOK_PRICE")
  private Integer bookPrice;

  @Column(name = "QUANTITY")
  private Integer orderQuantity;

  @Column(name = "CREATED_DATE")
  private Date createdDate;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Column(name = "IS_ACTIVE")
  private String isActive;

  @Column(name = "EXPECTED_RETURN_DATE")
  private Date expectedReturnDate;

  @Column(name = "ACTUAL_RETURN_DATE")
  private Date actualReturnDate;

  @Column(name = "PURCHASE_TYPE")
  private String purchaseType;

  @Column(name = "RETURN_STATUS")
  private String returnStatus;

  @Column(name = "ORDER_STATUS")
  private String orderStatus;

  @Column(name = "DISPATCH_DATE")
  private Date dispatchDate;

  @Column(name = "PAYMENT_STATUS")
  private String paymentStatus;

  @OneToOne
  @JoinColumn(name = "ISBN", referencedColumnName = "ISBN", insertable = false, updatable = false)
  private Book book;

  @Transient
  private String categoryName;

  @Transient
  private String bookAuthor;

  @Transient
  private String publisherName;

  @Transient
  private String bookTitle;

  @Transient
  private BigDecimal rentPrice;

  @Transient
  private BigDecimal lateFee;

  @Transient
  private String bookThumbImage;

  public String getBookThumbImage() {
    return book.getBookThumbImage();
  }

  /**
   * @return the categoryName
   */
  public String getCategoryName() {
    return book.getCategoryName();
  }

  /**
   * @return the bookAuthor
   */
  public String getBookAuthor() {
    return book.getBookAuthor();
  }

  /**
   * @return the publisherName
   */
  public String getPublisherName() {
    return book.getPublisherName();
  }

  /**
   * @return the bookTitle
   */
  public String getBookTitle() {
    return book.getBookTitle();
  }

  /**
   * @return the rentPrice
   */
  public BigDecimal getRentPrice() {
    return book.getRentPrice();
  }

  /**
   * @return the lateFee
   */
  public BigDecimal getLateFee() {
    return book.getLateFee();
  }

  /**
   * @return the orderId
   */
  public Integer getOrderId() {
    return orderId;
  }

  /**
   * @param orderId
   *          the orderId to set
   */
  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  /**
   * @return the orderItemId
   */
  public Integer getOrderItemId() {
    return orderItemId;
  }

  /**
   * @param orderItemId
   *          the orderItemId to set
   */
  public void setOrderItemId(Integer orderItemId) {
    this.orderItemId = orderItemId;
  }

  /**
   * @return the isbn
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * @param isbn
   *          the isbn to set
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * @return the bookPrice
   */
  public Integer getBookPrice() {
    return bookPrice;
  }

  /**
   * @param bookPrice
   *          the bookPrice to set
   */
  public void setBookPrice(Integer bookPrice) {
    this.bookPrice = bookPrice;
  }

  /**
   * @return the orderQuantity
   */
  public Integer getOrderQuantity() {
    return orderQuantity;
  }

  /**
   * @param orderQuantity
   *          the orderQuantity to set
   */
  public void setOrderQuantity(Integer orderQuantity) {
    this.orderQuantity = orderQuantity;
  }

  /**
   * @return the createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * @param createdDate
   *          the createdDate to set
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * @return the updatedDate
   */
  public Date getUpdatedDate() {
    return updatedDate;
  }

  /**
   * @param updatedDate
   *          the updatedDate to set
   */
  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  /**
   * @return the isActive
   */
  public String getIsActive() {
    return isActive;
  }

  /**
   * @param isActive
   *          the isActive to set
   */
  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }

  /**
   * @return the expectedReturnDate
   */

  /**
   * @param expectedReturnDate
   *          the expectedReturnDate to set
   */
  public void setExpectedReturnDate(Date expectedReturnDate) {
    this.expectedReturnDate = expectedReturnDate;
  }

  public Date getExpectedReturnDate() {
    return expectedReturnDate;
  }

  /**
   * @return the actualReturnDate
   */
  public String getActualReturnDate() {
    if (actualReturnDate != null) {
      return new SimpleDateFormat("dd-MMM-yyyy").format(actualReturnDate);
    } else
      return null;
  }

  /**
   * @param actualReturnDate
   *          the actualReturnDate to set
   */
  public void setActualReturnDate(Date actualReturnDate) {
    this.actualReturnDate = actualReturnDate;
  }

  /**
   * @return the purchaseType
   */
  public String getPurchaseType() {
    return purchaseType;
  }

  /**
   * @param purchaseType
   *          the purchaseType to set
   */
  public void setPurchaseType(String purchaseType) {
    this.purchaseType = purchaseType;
  }

  /**
   * @return the returnStatus
   */
  public String getReturnStatus() {
    return returnStatus;
  }

  /**
   * @param returnStatus
   *          the returnStatus to set
   */
  public void setReturnStatus(String returnStatus) {
    this.returnStatus = returnStatus;
  }

  /**
   * @return the orderStatus
   */
  public String getOrderStatus() {
    return orderStatus;
  }

  /**
   * @param orderStatus
   *          the orderStatus to set
   */
  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }

  /**
   * @return the dispatchDate
   */
  public Date getDispatchDate() {
    return dispatchDate;
  }

  /**
   * @param dispatchDate
   *          the dispatchDate to set
   */
  public void setDispatchDate(Date dispatchDate) {
    this.dispatchDate = dispatchDate;
  }

  /**
   * @return the paymentStatus
   */
  public String getPaymentStatus() {
    return paymentStatus;
  }

  /**
   * @param paymentStatus
   *          the paymentStatus to set
   */
  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  @Override
  public String toString() {
    return "OrderItemInfo [orderId=" + orderId + ", orderItemId=" + orderItemId
        + ", isbn=" + isbn + ", bookPrice=" + bookPrice + ", orderQuantity="
        + orderQuantity + ", createdDate=" + createdDate + ", updatedDate="
        + updatedDate + ", isActive=" + isActive + ", expectedReturnDate="
        + expectedReturnDate + ", actualReturnDate=" + actualReturnDate
        + ", purchaseType=" + purchaseType + ", returnStatus=" + returnStatus
        + ", orderStatus=" + orderStatus + ", dispatchDate=" + dispatchDate
        + ", paymentStatus=" + paymentStatus + ", book=" + book
        + ", categoryName=" + categoryName + ", bookAuthor=" + bookAuthor
        + ", publisherName=" + publisherName + ", bookTitle=" + bookTitle
        + ", rentPrice=" + rentPrice + ", lateFee=" + lateFee + "]";
  }

}
