package com.sapestore.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ShoppingCartVO.
 *
 * @author ntrip6
 * @author cgoyal
 * @author ngupta79
 * Bean class for keeping shopping cart information .
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version .
 */

public class ShoppingCartVO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The books in cart. */
  private List<BookVO> booksInCart;
  
  /** The total price. */
  private int totalPrice;
  
  /** The total quantity. */
  private int totalQuantity;

  /**
   * Sets the total price.
   * @param totalPrice the new total price
   */
  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }

  /**
   * Sets the total quantity.
   *
   * @param totalQuantity the new total quantity
   */
  public void setTotalQuantity(int totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  /**
   * Add book to the list of books in shopping cart.
   *
   * @param book the book
   * @param flag the flag
   * @param type the type
   */
  public void setBooksInCart(BookVO book, boolean flag, String type) {

    if (this.getBooksInCart() == null) {
      this.booksInCart = new ArrayList<BookVO>();
    }
    int price = 0;
    if (type.equalsIgnoreCase("Rent")) {
      price = Integer.parseInt(book.getRentPrice());
      book.setBookPrice(book.getRentPrice());
    } else {
      price = Integer.parseInt(book.getBookPrice());
    }
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
  }

  /**
   * Gets the books in cart.
   *
   * @return the list of books in shopping cart.
   */
  public List<BookVO> getBooksInCart() {
    return booksInCart;
  }

  /**
   * Gets the total price.
   *
   * @return totalPrice.
   */
  public int getTotalPrice() {
    return totalPrice;
  }

  /**
   * Gets the total quantity.
   *
   * @return totalQuantity.
   */
  public int getTotalQuantity() {
    return totalQuantity;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ShoppingCartVO [booksInCart=" + booksInCart + ", totalPrice="
        + totalPrice + ", totalQuantity=" + totalQuantity + "]";
  }

  /**
   * Update total.
   *
   * @param bookvo the bookvo
   */
  public void updateTotal(BookVO bookvo) {
    this.totalPrice = this.totalPrice
        + bookvo.getQuantity() * Integer.parseInt(bookvo.getBookPrice());
    this.totalQuantity = this.totalQuantity + bookvo.getQuantity();
  }

  /**
   * Reduce total.
   *
   * @param bookvo the bookvo
   * @param quant the quant
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
