package com.sapestore.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
// TODO: Auto-generated Javadoc

/**
 * The Class RentedShoppingCartVO.
 * 
 * @author nkum85
 */
@Component
public class RentedShoppingCartVO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The books in cart. */
  private List<BookVO> booksInCart;

  /** The total price. */
  private float totalPrice;

  /** The total quantity. */
  private int totalQuantity;

  /**
   * Sets the total price.
   *
   * @param totalPrice
   *          the new total price
   */
  public void setTotalPrice(Float totalPrice) {
    this.totalPrice = totalPrice;
  }

  /**
   * Sets the total quantity.
   *
   * @param totalQuantity
   *          the new total quantity
   */
  public void setTotalQuantity(int totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  /**
   * Add book to the list of books in rented shopping cart
   * @param book.
   * @param flag.
   */
  public void setBooksInCart(BookVO book, boolean flag, String type,
      Integer duration, Float cost) {

    if (this.getBooksInCart() == null) {
      this.booksInCart = new ArrayList<BookVO>();
    }
    float price = 0;

    price = cost;
    book.setRentPrice(cost.toString());
    this.booksInCart.add(book);
    if (!flag) {
      if (this.totalPrice != 0) {
        this.totalPrice = this.totalPrice + book.getQuantity() * price;
      } else {
        this.totalPrice = book.getQuantity() * price;
      }

    } else {
      this.totalPrice = this.totalPrice + price;
    }
    this.totalQuantity++;
    book.setType(type);
    book.setReturnDate(duration.toString());

  }

  /**
   * @return the list of books in shopping cart.
   */
  public List<BookVO> getBooksInCart() {
    return booksInCart;
  }

  /**
   * 
   * @return totalPrice.
   */
  public float getTotalPrice() {
    return totalPrice;
  }

  /**
   * 
   * @return totalQuantity.
   */
  public int getTotalQuantity() {
    return totalQuantity;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "RentedShoppingCartVO [booksInCart=" + booksInCart + ", totalPrice="
        + totalPrice + ", totalQuantity=" + totalQuantity + "]";
  }

  /**
   * Update total.
   *
   * @param bookvo
   *          the bookvo
   */
  public void updateTotal(BookVO bookvo) {
    this.totalPrice = this.totalPrice
        + bookvo.getQuantity() * Integer.parseInt(bookvo.getBookPrice());
    this.totalQuantity = this.totalQuantity + bookvo.getQuantity();
  }

  /**
   * Reduce total.
   *
   * @param bookvo
   *          the bookvo
   * @param quant
   *          the quant
   */
  public void reduceTotal(BookVO bookvo, int quant) {

    this.totalPrice = this.totalPrice
        - quant * Integer.parseInt(bookvo.getBookPrice());

    this.totalQuantity = this.totalQuantity - quant;
    if (this.totalQuantity < 0) {
      this.totalQuantity = 0;
    }
  }

}
