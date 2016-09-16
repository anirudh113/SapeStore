package com.sapestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.BookrentService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.RentedShoppingCartVO;

// TODO: Auto-generated Javadoc
/**
 * The Class BookRentServiceImpl for handling book rent service.
 * 
 * @author nkum85
 */
@Service("bookrentService")
public class BookRentServiceImpl implements BookrentService {

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
   * Adds books to cart.
   *
   * @param shoppingCart the shopping cart
   * @param isbn the isbn
   * @param type the type
   * @param duration the duration
   * @param cost the cost
   * @return the rented shopping cart vo
   * @throws SapeStoreException the sape store exception
   */
  @Override
  public RentedShoppingCartVO addRentedBookToCart(
      RentedShoppingCartVO shoppingCart, String isbn, String type,
      Integer duration, Float cost) throws SapeStoreException {
    BookVO addToCart = null;
    BookVO existingBookBean = null;
    int bookPosition = 0;
    int quantity = 0;
    boolean flag = false;
    String extype;
    addToCart = this.getBookInfo(isbn);
    if (shoppingCart != null) {
      flag = shoppingCart.getBooksInCart().contains(addToCart);

      bookPosition = shoppingCart.getBooksInCart().indexOf(addToCart);
      if (!flag) {
        shoppingCart.setBooksInCart(addToCart, flag, type, duration, cost);
      } else {
        existingBookBean = shoppingCart.getBooksInCart().remove(bookPosition);
        extype = existingBookBean.getType();
        if (extype.equalsIgnoreCase(type)) {
          quantity = existingBookBean.getQuantity();
          existingBookBean.setQuantity(++quantity);
          shoppingCart.setBooksInCart(existingBookBean, flag, type, duration,
              cost);
        } else {
          float totalprice = shoppingCart.getTotalPrice();
          int totalquan = existingBookBean.getQuantity();
          float exprice = existingBookBean.getQuantity() * cost;
          shoppingCart.setTotalPrice(totalprice - exprice);
          shoppingCart
              .setTotalQuantity(shoppingCart.getTotalQuantity() - totalquan);
          shoppingCart.setBooksInCart(existingBookBean, false, extype, duration,
              cost);
          shoppingCart.setBooksInCart(addToCart, false, type, duration, cost);
        }
      }
    } else {
      shoppingCart = new RentedShoppingCartVO();
      shoppingCart.setBooksInCart(addToCart, flag, type, duration, cost);
    }
    return shoppingCart;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sapestore.service.BookrentService#removeRentedBookFromCart(com.
   * sapestore.vo.RentedShoppingCartVO, java.lang.String, java.lang.String)
   */
  @Override
  public RentedShoppingCartVO removeRentedBookFromCart(
      RentedShoppingCartVO shoppingCart, String isbn, String type) {
    BookVO bookvo = productDao.getBookByIsbn(isbn);
    int position = shoppingCart.getBooksInCart().indexOf(bookvo);
    BookVO existing = shoppingCart.getBooksInCart().remove(position);
    shoppingCart.reduceTotal(existing, existing.getQuantity());
    return shoppingCart;

  }

  /**
   * Gets the book info.
   *
   * @param isbn
   *          the isbn
   * @return the book info
   * @throws SapeStoreException
   *           the sape store exception
   */
  private BookVO getBookInfo(String isbn) throws SapeStoreException {
    System.out.println("Inside getbook");
    BookVO addToCartbean = null;
    addToCartbean = productDao.getBookByIsbn(isbn);
    return addToCartbean;
  }

  /*
   * @Override public RentedShoppingCartVO
   * updaterentedBookCart(RentedShoppingCartVO shoppingCart, String isbn, String
   * type, String quantity) { BookVO bookvo=productDao.getBookByIsbn(isbn); int
   * position=shoppingCart.getBooksInCart().indexOf(bookvo); BookVO
   * existing=shoppingCart.getBooksInCart().remove(position);
   * 
   * shoppingCart.reduceTotal(existing, existing.getQuantity());
   * bookvo.setQuantity(Integer.parseInt(quantity));
   * if(type.equalsIgnoreCase("rent")) {
   * bookvo.setBookPrice(bookvo.getRentPrice()); bookvo.setType("Rent"); } else
   * if(type.equalsIgnoreCase("purchase")){
   * bookvo.setBookPrice(bookvo.getBookPrice()); bookvo.setType("Purchase"); }
   * shoppingCart.updateTotal(bookvo);
   * shoppingCart.getBooksInCart().add(position, bookvo); return shoppingCart; }
   */

}
