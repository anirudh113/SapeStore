package com.sapestore.service;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.vo.ShoppingCartVO;

// TODO: Auto-generated Javadoc
/**
 * Service interface for Add to Cart functionality. CHANGE LOG VERSION DATE
 * AUTHOR MESSAGE 1.1 20-10-2015 Added functionalities
 * 
 * @author ngupta79
 * @author cgoyal
 * @author ntrip6
 */

public interface ShoppingCartService {

  /**
   * Adds books to cart.
   *
   * @param shoppingCart
   *          the shopping cart
   * @param isbn
   *          the isbn
   * @param type
   *          the type
   * @return the shopping cart vo
   * @throws SapeStoreException
   *           the sape store exception
   * @throws SapeStoreSystemException
   *           the sape store system exception
   */

  ShoppingCartVO addBookToCart(ShoppingCartVO shoppingCart, String isbn,
      String type) throws SapeStoreException;

  /**
   * Remove books from cart.
   *
   * @param shoppingCart
   *          the shopping cart
   * @param isbn
   *          the isbn
   * @param oldtype
   *          the oldtype
   * @return the shopping cart vo
   * @throws SapeStoreSystemException
   *           the sape store system exception
   */

  ShoppingCartVO removeFromCart(ShoppingCartVO shoppingCart, String isbn,
      String oldtype);

  /**
   * Update cart.
   *
   * @param shoppingCart
   *          the shopping cart
   * @param isbn
   *          the isbn
   * @param type
   *          the type
   * @param quantity
   *          the quantity
   * @param oldtype
   *          the oldtype
   * @return the shopping cart vo
   * @throws SapeStoreSystemException
   *           the sape store system exception
   */

  ShoppingCartVO updateCart(ShoppingCartVO shoppingCart, String isbn,
      String type, String quantity, String oldtype);
}
