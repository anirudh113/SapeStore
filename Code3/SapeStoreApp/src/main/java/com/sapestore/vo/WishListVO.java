package com.sapestore.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

// TODO: Auto-generated Javadoc
/**
 * The Class WishListVO.
 */
public class WishListVO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 6344881547419789445L;

  /** The wish id. */
  @NotNull
  private Integer wishId;

  /** The user id. */
  @NotEmpty
  private String userId;

  /** The isbn. */
  @NotEmpty
  private String isbn;

  /** The created date. */
  private Date createdDate;

  /** The updated date. */
  private Date updatedDate;

  /** The is active. */
  private String isActive;

  /** The book price. */
  @NotEmpty
  private String bookPrice;

  /** The book title. */
  private String bookTitle;

  /** The book thumb image. */
  private String bookThumbImage;

  /** The book full image. */
  private String bookFullImage;

  /**
   * Gets the book thumb image.
   *
   * @return the book thumb image
   */
  public String getBookThumbImage() {
    return bookThumbImage;
  }

  /**
   * Sets the book thumb image.
   *
   * @param bookThumbImage
   *          the new book thumb image
   */
  public void setBookThumbImage(String bookThumbImage) {
    this.bookThumbImage = bookThumbImage;
  }

  /**
   * Gets the book full image.
   *
   * @return the book full image
   */
  public String getBookFullImage() {
    return bookFullImage;
  }

  /**
   * Sets the book full image.
   *
   * @param bookFullImage
   *          the new book full image
   */
  public void setBookFullImage(String bookFullImage) {
    this.bookFullImage = bookFullImage;
  }

  /**
   * Gets the book title.
   *
   * @return the book title
   */
  public String getBookTitle() {
    return bookTitle;
  }

  /**
   * Sets the book title.
   *
   * @param bookTitle
   *          the new book title
   */
  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  /**
   * Gets the wish id.
   *
   * @return the wish id
   */
  public Integer getWishId() {
    return wishId;
  }

  /**
   * Sets the wish id.
   *
   * @param wishId
   *          the new wish id
   */
  public void setWishId(Integer wishId) {
    this.wishId = wishId;
  }

  /**
   * Gets the user id.
   *
   * @return the user id
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Sets the user id.
   *
   * @param userId
   *          the new user id
   */
  public void setUserId(String userId) {
    this.userId = userId;
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
   *          the new isbn
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * Gets the created date.
   *
   * @return the created date
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * Sets the created date.
   *
   * @param createdDate
   *          the new created date
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * Gets the updated date.
   *
   * @return the updated date
   */
  public Date getUpdatedDate() {
    return updatedDate;
  }

  /**
   * Sets the updated date.
   *
   * @param updatedDate
   *          the new updated date
   */
  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  /**
   * Gets the checks if is active.
   *
   * @return the checks if is active
   */
  public String getIsActive() {
    return isActive;
  }

  /**
   * Sets the checks if is active.
   *
   * @param isActive
   *          the new checks if is active
   */
  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }

  /**
   * Gets the book price.
   *
   * @return the book price
   */
  public String getBookPrice() {
    return bookPrice;
  }

  /**
   * Sets the book price.
   *
   * @param bookPrice
   *          the new book price
   */
  public void setBookPrice(String bookPrice) {
    this.bookPrice = bookPrice;
  }

  /**
   * Gets the serialversionuid.
   *
   * @return the serialversionuid
   */
  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "WishListVO [wishId=" + wishId + ", userId=" + userId + ", isbn="
        + isbn + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
        + ", isActive=" + isActive + ", bookPrice=" + bookPrice + "]";
  }

}
