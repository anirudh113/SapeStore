package com.sapestore.service.impl;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.WishListDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.service.WishListService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.WishListVO;

// TODO: Auto-generated Javadoc
/**
 * The Class WishListServiceImpl.
 */
@Service("wishListService")
@Transactional
public class WishListServiceImpl implements WishListService {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(WishListServiceImpl.class.getName());

  /** The wish list dao. */
  @Autowired
  private WishListDao wishListDao;

  /**
   * Gets the wish list dao.
   *
   * @return the wish list dao
   */
  public WishListDao getWishListDao() {
    return wishListDao;
  }

  /**
   * Sets the wish list dao.
   *
   * @param wishListDao the new wish list dao
   */
  public void setWishListDao(WishListDao wishListDao) {
    this.wishListDao = wishListDao;
  }

  /*
   * @Override public boolean saveBook(Book book) throws SapeStoreException {
   * 
   * return wishListDao.saveBook(book); }
   */

  /* (non-Javadoc)
   * @see com.sapestore.service.WishListService#bookVOToWishListVO(com.sapestore.vo.BookVO, java.lang.String)
   */
  @Override
  public WishListVO bookVOToWishListVO(BookVO bookVO, String userId)
      throws SapeStoreException {
    BasicConfigurator.configure();
    return wishListDao.bookVOToWishListVO(bookVO, userId);
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.WishListService#WishListVOToBookVO(com.sapestore.vo.WishListVO)
   */
  @Override
  public BookVO WishListVOToBookVO(WishListVO wishListVO)
      throws SapeStoreException {
    BasicConfigurator.configure();
    return wishListDao.WishListVOToBookVO(wishListVO);
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.WishListService#addToWishList(com.sapestore.vo.WishListVO, java.lang.String)
   */
  @Override
  public boolean addToWishList(WishListVO wishListVO, String userId)
      throws SapeStoreException {
    BasicConfigurator.configure();
    return wishListDao.addToWishList(wishListVO, userId);
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.WishListService#getBookByISBN(java.lang.String)
   */
  @Override
  public Book getBookByISBN(String isbn) throws SapeStoreException {
    BasicConfigurator.configure();
    return wishListDao.getBookByISBN(isbn);
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.WishListService#getAllWishListVO(java.lang.String)
   */
  @Override
  public List<WishListVO> getAllWishListVO(String userId)
      throws SapeStoreException {
    BasicConfigurator.configure();
    System.out.println("i am here");
    return wishListDao.getAllWishListVO(userId);
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.WishListService#getWishListVO(java.lang.Integer)
   */
  @Override
  public WishListVO getWishListVO(Integer wishId) throws SapeStoreException {
    // TODO Auto-generated method stub
    return wishListDao.getWishListVO(wishId);
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.WishListService#deleteFromWishList(java.lang.Integer)
   */
  @Override
  public void deleteFromWishList(Integer wishId) throws SapeStoreException {
    // TODO Auto-generated method stub
    wishListDao.deleteFromWishList(wishId);
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.WishListService#getCategoryNameById(java.lang.Integer)
   */
  @Override
  public String getCategoryNameById(Integer categoryId)
      throws SapeStoreException {
    // TODO Auto-generated method stub
    return wishListDao.getCategoryNameById(categoryId);
  }

}
