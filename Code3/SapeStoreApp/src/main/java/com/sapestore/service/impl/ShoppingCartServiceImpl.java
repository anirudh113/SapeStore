package com.sapestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.ShoppingCartVO;

// TODO: Auto-generated Javadoc
/**
 * This is a Service Implementation class for ShoppingCart functionality. CHANGE
 * LOG VERSION DATE AUTHOR MESSAGE 1.1 23-10-2015 Added functionalities
 * 
 * @author ngupta79
 * @author cgoyal
 * @author ntrip6
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(ShoppingCartServiceImpl.class.getName());

  /** The product dao. */
  @Autowired
  private ProductDao productDao;

  /**
   * Gets the product dao.
   *
   * @return the product dao
   */
  public ProductDao getProductDao() {
    return productDao;
  }

  /**
   * Sets the product dao.
   *
   * @param productDao
   *          the new product dao
   */
  public void setProductDao(ProductDao productDao) {
    this.productDao = productDao;
  }

  /**
   * This is a method for add books to cart functionality. CHANGE LOG VERSION
   * DATE AUTHOR MESSAGE 1.1 25-10-2015 Added functionalities Adds books to cart
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
  @Override
  public ShoppingCartVO addBookToCart(ShoppingCartVO shoppingCart, String isbn,
      String type) throws SapeStoreException {
    BookVO addToCart = null;
    BookVO existingBookBean = null;
    int bookPosition = 0;
    int quantity = 0;
    boolean flag = false;
    String extype;
    addToCart = this.getBookInfo(isbn);
    addToCart.setType(type);
    if (shoppingCart != null) {
      flag = shoppingCart.getBooksInCart().contains(addToCart);

      bookPosition = shoppingCart.getBooksInCart().indexOf(addToCart);
      if (!flag) {
        shoppingCart.setBooksInCart(addToCart, flag, type);
      } else {
        existingBookBean = shoppingCart.getBooksInCart().remove(bookPosition);
        extype = existingBookBean.getType();
        if (extype.equalsIgnoreCase(type)) {
          quantity = existingBookBean.getQuantity();
          existingBookBean.setQuantity(++quantity);
          shoppingCart.setBooksInCart(existingBookBean, flag, type);
        } else {
          int totalprice = shoppingCart.getTotalPrice();
          int exprice = existingBookBean.getQuantity()
              * Integer.parseInt(existingBookBean.getBookPrice());
          shoppingCart.setTotalPrice(totalprice - exprice);
          shoppingCart.setTotalQuantity(shoppingCart.getTotalQuantity() - 1);
          shoppingCart.setBooksInCart(existingBookBean, false, extype);
          shoppingCart.setBooksInCart(addToCart, false, type);
        }
      }
    } else {
      shoppingCart = new ShoppingCartVO();
      shoppingCart.setBooksInCart(addToCart, flag, type);
    }
    return shoppingCart;
  }

  /**
   * This is a method for removing books from cart. CHANGE LOG VERSION DATE
   * AUTHOR MESSAGE 1.1 25-11-2015 Added this new method
   *
   * @param shoppingCart
   *          the shopping cart
   * @param isbn
   *          the isbn
   * @param type
   *          the type
   * @return the shopping cart vo
   * @throws SapeStoreSystemException
   *           the sape store system exception
   */

  @Override
  public ShoppingCartVO removeFromCart(ShoppingCartVO shoppingCart, String isbn,
      String type) {
    BookVO bookvo = productDao.getBookByIsbn(isbn);
    bookvo.setType(type);
    int position = shoppingCart.getBooksInCart().indexOf(bookvo);
    BookVO existing = shoppingCart.getBooksInCart().remove(position);
    shoppingCart.reduceTotal(existing, existing.getQuantity());
    return shoppingCart;
  }

  /**
   * Get book information on the basis of the ISBN provided.
   *
   * @param isbn
   *          the isbn
   * @return the book info
   * @throws SapeStoreException
   *           the sape store exception
   * @throws SapeStoreSystemException
   *           the sape store system exception
   */
  private BookVO getBookInfo(String isbn) throws SapeStoreException {
    System.out.println("Inside getbook");
    BookVO addToCartbean = null;
    addToCartbean = productDao.getBookByIsbn(isbn);
    return addToCartbean;
  }

  /**
   * *This is a method for updating cart. CHANGE LOG VERSION DATE AUTHOR MESSAGE
   * 1.1 25-11-2015 Added this new method
   *
   * @author ngupta79 Update cart
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
  @Override
  public ShoppingCartVO updateCart(ShoppingCartVO shoppingCart, String isbn,
      String type, String quantity, String oldtype) {
    BookVO bookvo = productDao.getBookByIsbn(isbn);
    LOGGER.error("bookvo " + bookvo);
    bookvo.setType(oldtype);
    int position;
    int mergePosition;
    BookVO mergeBook;
    BookVO existing;
    position = shoppingCart.getBooksInCart().indexOf(bookvo);
    existing = shoppingCart.getBooksInCart().remove(position);
    LOGGER.error("existing book type " + existing);

    shoppingCart.reduceTotal(existing, existing.getQuantity());
    bookvo.setQuantity(Integer.parseInt(quantity));
    if (type.equalsIgnoreCase("rent")) {
      bookvo.setBookPrice(bookvo.getRentPrice());
      bookvo.setType("Rent");
    } else if (type.equalsIgnoreCase("purchase")) {
      bookvo.setBookPrice(bookvo.getBookPrice());
      bookvo.setType("Purchase");
    }

    if (shoppingCart.getBooksInCart().contains(bookvo)) {
      mergePosition = shoppingCart.getBooksInCart().indexOf(bookvo);
      mergeBook = shoppingCart.getBooksInCart().remove(mergePosition);
      mergeBook.setQuantity(mergeBook.getQuantity() + bookvo.getQuantity());
      shoppingCart.getBooksInCart().add(mergePosition, mergeBook);
    } else {
      shoppingCart.getBooksInCart().add(position, bookvo);
    }
    shoppingCart.updateTotal(bookvo);
    return shoppingCart;
  }
}
