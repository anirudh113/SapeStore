
package com.sapestore.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

// TODO: Auto-generated Javadoc
/**
 * The Class BookVO.
 */
@Component
/**
 * Bean class for adding a book.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
public class BookVO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -3972545417811168092L;

  /** The isbn. */
  @NotEmpty
  private String isbn;

  /** The publisher name. */
  @NotEmpty
  private String publisherName;

  /** The category id. */
  @NotEmpty
  private String categoryId;

  /** The category name. */
  private String categoryName;

  /** The book title. */
  @NotEmpty
  private String bookTitle;

  /** The quantity. */
  @NotNull
  private int quantity;

  /** The book author. */
  @NotEmpty
  private String bookAuthor;

  /** The book price. */
  @NotEmpty
  private String bookPrice;

  /** The book short desc. */
  @NotEmpty
  private String bookShortDesc;

  /** The book detail desc. */
  private String bookDetailDesc;

  /** The active. */
  private String active;

  /** The thumb image. */
  private MultipartFile thumbImage;

  /** The full image. */
  private MultipartFile fullImage;

  /** The thumb path. */
  private String thumbPath;

  /** The full path. */
  private String fullPath;

  /** The rent available. */
  @NotEmpty
  private String rentAvailable;

  /** The rent price. */
  private String rentPrice;

  /** The old isbn. */
  private String oldIsbn;

  /** The type. */
  private String type;

  /** The expected return date. */
  private Date expectedReturnDate;

  /** The available quantity. */
  private String availableQuantity;

  /** The return date. */
  private String returnDate;

  /**
   * Gets the return date.
   *
   * @return the return date
   */
  public String getReturnDate() {
    return returnDate;
  }

  /**
   * Sets the return date.
   *
   * @param duration the new return date
   */
  public void setReturnDate(String duration) {
    Date today = new Date();
    int after = today.getDate() + Integer.parseInt(duration);
    today.setDate(after);
    this.returnDate = today.toString();
  }

  /**
   * Gets the available quantity.
   *
   * @return the available quantity
   */
  public String getAvailableQuantity() {
    return availableQuantity;
  }

  /**
   * Sets the available quantity.
   *
   * @param availableQuantity the new available quantity
   */
  public void setAvailableQuantity(String availableQuantity) {
    this.availableQuantity = availableQuantity;
  }

  /**
   * Gets the type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type the new type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the expected return date.
   *
   * @return the expected return date
   */
  public Date getExpectedReturnDate() {
    return expectedReturnDate;
  }

  /**
   * Sets the expected return date.
   */
  @SuppressWarnings("deprecation")
  public void setExpectedReturnDate() {
    Date today = new Date();
    int after = today.getDate() + 14;
    today.setDate(after);
    this.expectedReturnDate = today;
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
   * @param isbn          the isbn to set
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * Gets the publisher name.
   *
   * @return the publisherName
   */
  public String getPublisherName() {
    return publisherName;
  }

  /**
   * Sets the publisher name.
   *
   * @param publisherName          the publisherName to set
   */
  public void setPublisherName(String publisherName) {
    this.publisherName = publisherName;
  }

  /**
   * Gets the category id.
   *
   * @return the categoryId
   */
  public String getCategoryId() {
    return categoryId;
  }

  /**
   * Sets the category id.
   *
   * @param categoryId          the categoryId to set
   */
  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * Gets the category name.
   *
   * @return the categoryName
   */
  public String getCategoryName() {
    return categoryName;
  }

  /**
   * Sets the category name.
   *
   * @param categoryName          the categoryName to set
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * Gets the book title.
   *
   * @return the bookTitle
   */
  public String getBookTitle() {
    return bookTitle;
  }

  /**
   * Sets the book title.
   *
   * @param bookTitle          the bookTitle to set
   */
  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  /**
   * Gets the quantity.
   *
   * @return the quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity.
   *
   * @param quantity          the quantity to set
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Gets the book author.
   *
   * @return the bookAuthor
   */
  public String getBookAuthor() {
    return bookAuthor;
  }

  /**
   * Sets the book author.
   *
   * @param bookAuthor          the bookAuthor to set
   */
  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }

  /**
   * Gets the book price.
   *
   * @return the bookPrice
   */
  public String getBookPrice() {
    return bookPrice;
  }

  /**
   * Sets the book price.
   *
   * @param bookPrice          the bookPrice to set
   */
  public void setBookPrice(String bookPrice) {
    this.bookPrice = bookPrice;
  }

  /**
   * Gets the book short desc.
   *
   * @return the bookShortDesc
   */
  public String getBookShortDesc() {
    return bookShortDesc;
  }

  /**
   * Sets the book short desc.
   *
   * @param bookShortDesc          the bookShortDesc to set
   */
  public void setBookShortDesc(String bookShortDesc) {
    this.bookShortDesc = bookShortDesc;
  }

  /**
   * Gets the book detail desc.
   *
   * @return the bookDetailDesc
   */
  public String getBookDetailDesc() {
    return bookDetailDesc;
  }

  /**
   * Sets the book detail desc.
   *
   * @param bookDetailDesc          the bookDetailDesc to set
   */
  public void setBookDetailDesc(String bookDetailDesc) {
    this.bookDetailDesc = bookDetailDesc;
  }

  /**
   * Gets the active.
   *
   * @return the active
   */
  public String getActive() {
    return active;
  }

  /**
   * Sets the active.
   *
   * @param active          the active to set
   */
  public void setActive(String active) {
    this.active = active;
  }

  /**
   * Gets the thumb image.
   *
   * @return the thumb image
   */
  public MultipartFile getThumbImage() {
    return thumbImage;
  }

  /**
   * Sets the thumb image.
   *
   * @param thumbImage the new thumb image
   */
  public void setThumbImage(MultipartFile thumbImage) {
    this.thumbImage = thumbImage;
  }

  /**
   * Gets the full image.
   *
   * @return the full image
   */
  public MultipartFile getFullImage() {
    return fullImage;
  }

  /**
   * Sets the full image.
   *
   * @param fullImage the new full image
   */
  public void setFullImage(MultipartFile fullImage) {
    this.fullImage = fullImage;
  }

  /**
   * Gets the thumb path.
   *
   * @return the thumbPath
   */
  public String getThumbPath() {
    return thumbPath;
  }

  /**
   * Sets the thumb path.
   *
   * @param thumbPath          the thumbPath to set
   */
  public void setThumbPath(String thumbPath) {
    this.thumbPath = thumbPath;
  }

  /**
   * Gets the full path.
   *
   * @return the fullPath
   */
  public String getFullPath() {
    return fullPath;
  }

  /**
   * Sets the full path.
   *
   * @param fullPath          the fullPath to set
   */
  public void setFullPath(String fullPath) {
    this.fullPath = fullPath;
  }

  /**
   * Gets the rent available.
   *
   * @return the rentAvailable
   */
  public String getRentAvailable() {
    return rentAvailable;
  }

  /**
   * Sets the rent available.
   *
   * @param rentAvailable          the rentAvailable to set
   */
  public void setRentAvailable(String rentAvailable) {
    this.rentAvailable = rentAvailable;
  }

  /**
   * Gets the rent price.
   *
   * @return the rentPrice
   */
  public String getRentPrice() {
    return rentPrice;
  }

  /**
   * Sets the rent price.
   *
   * @param rentPrice          the rentPrice to set
   */
  public void setRentPrice(String rentPrice) {
    this.rentPrice = rentPrice;
  }

  /**
   * Gets the old isbn.
   *
   * @return the oldIsbn
   */
  public String getOldIsbn() {
    return oldIsbn;
  }

  /**
   * Sets the old isbn.
   *
   * @param oldIsbn          the oldIsbn to set
   */
  public void setOldIsbn(String oldIsbn) {
    this.oldIsbn = oldIsbn;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "BookVO [isbn=" + isbn + ", publisherName=" + publisherName
        + ", categoryId=" + categoryId + ", categoryName=" + categoryName
        + ", bookTitle=" + bookTitle + ", quantity=" + quantity
        + ", bookAuthor=" + bookAuthor + ", bookPrice=" + bookPrice
        + ", bookShortDesc=" + bookShortDesc + ", bookDetailDesc="
        + bookDetailDesc + ", active=" + active + ", thumbImage=" + thumbImage
        + ", fullImage=" + fullImage + ", thumbPath=" + thumbPath
        + ", fullPath=" + fullPath + ", rentAvailable=" + rentAvailable
        + ", rentPrice=" + rentPrice + ", oldIsbn=" + oldIsbn + "]";
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return super.hashCode();
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    BookVO book = (BookVO) obj;
    if (this.getIsbn().equalsIgnoreCase(book.getIsbn())
        && this.getType().equalsIgnoreCase(book.getType()))
      return true;
    else
      return false;
  }

}
