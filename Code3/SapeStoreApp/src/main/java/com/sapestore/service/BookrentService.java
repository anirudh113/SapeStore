
package com.sapestore.service;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.vo.RentedShoppingCartVO;

// TODO: Auto-generated Javadoc
/**
 * The Interface BookrentService.
 * 
 * @author nkum85
 */
public interface BookrentService {

  /**
   * Adds the rented book to cart.
   *
   * @param shoppingCart
   *          the shopping cart
   * @param isbn
   *          the isbn
   * @param type
   *          the type
   * @param duration
   *          the duration
   * @param cost
   *          the cost
   * @return the rented shopping cart vo
   * @throws SapeStoreException
   *           the sape store exception
   */
  RentedShoppingCartVO addRentedBookToCart(RentedShoppingCartVO shoppingCart,
      String isbn, String type, Integer duration, Float cost)
          throws SapeStoreException;

  /**
   * Removes the rented book from cart.
   *
   * @param shoppingCart
   *          the shopping cart
   * @param isbn
   *          the isbn
   * @param type
   *          the type
   * @return the rented shopping cart vo
   */
  RentedShoppingCartVO removeRentedBookFromCart(
      RentedShoppingCartVO shoppingCart, String isbn, String type);

  // RentedShoppingCartVO updaterentedBookCart(RentedShoppingCartVO
  // shoppingCart, String isbn, String type, String quantity);

}
