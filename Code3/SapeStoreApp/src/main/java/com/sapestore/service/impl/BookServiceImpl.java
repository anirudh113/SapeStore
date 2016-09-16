package com.sapestore.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.partner.services.SSPartnerBooksListBean;
import com.sapestore.partner.services.SSPartnerWebService;
import com.sapestore.partner.services.SSPartnerWebServicePortType;
import com.sapestore.service.BookService;
import com.sapestore.vo.BookVO;

// TODO: Auto-generated Javadoc
/**
 * Service class for fetching books information.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Service("bookService")
public class BookServiceImpl implements BookService {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = 
      SapeStoreLogger.getLogger(BookServiceImpl.class.getName());

  /** The book dao. */
  @Autowired
  private ProductDao bookDao;

  /**
   * Returns list of books belonging to the specified category .
   *
   * @param catId the cat id
   * @param checkMeFromSession the check me from session
   * @return the book list
   * @throws SapeStoreException the sape store exception
   */
  @Override
  public List<Book> getBookList(int catId, Object checkMeFromSession) throws SapeStoreException {
    LOGGER.debug("getBookList method: START");
    List<Book> bookBeanList = null;
    bookBeanList = bookDao.getBookList(catId, checkMeFromSession);

    if (bookBeanList != null && bookBeanList.size() > 0) {
      Collections.sort(bookBeanList, new Comparator<Book>() {
        @Override
        public int compare(final Book arg0, final Book arg1) {
          return arg0.getBookTitle().compareTo(arg1.getBookTitle());
        }
      });
    }
    LOGGER.debug("getBookList method: END");
    return bookBeanList;
  }

  /**
   * Returns list of book categories .
   *
   * @return the category list
   * @throws SapeStoreException the sape store exception
   */
  @Override
  public List<BookCategory> getCategoryList() throws SapeStoreException {
    LOGGER.debug("getCategoryList method: START");
    List<BookCategory> bookCategoryBeanList = null;
    bookCategoryBeanList = bookDao.getBookCategoryList();
    if (bookCategoryBeanList.size() > 0) {
      Collections.sort(bookCategoryBeanList, new Comparator<BookCategory>() {

        @Override
        public int compare(final BookCategory arg0, final BookCategory arg1) {
          return arg0.getCategoryName().compareTo(arg1.getCategoryName());
        }
      });
    }
    LOGGER.debug("getCategoryList method: END");
    return bookCategoryBeanList;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.BookService#getBookFromWebService(int)
   */
  @Override
  public List<Book> getBookFromWebService(int catId) {
    LOGGER.debug("getBookFromWebService method: START");
    SSPartnerWebService service = new SSPartnerWebService();
    SSPartnerWebServicePortType client = service.getSSPartnerWebServiceHttpSoap11Endpoint();
    List<SSPartnerBooksListBean> partnerBookList = client.getBookList(String.valueOf(catId));
    LOGGER.debug("getBookFromWebService method: END");
    return mapToBookListBean(partnerBookList);
  }

  /**
   * Maps book list collected from partner services to book bean.
   *
   * @param partnerList the partner list
   * @return the list
   */
  private List<Book> mapToBookListBean(List<SSPartnerBooksListBean> partnerList) {
    List<Book> bookListBeanList = new ArrayList<Book>();
    Book bookBean = null;

    for (SSPartnerBooksListBean partnerBook : partnerList) {
      bookBean = new Book();
      bookBean.setIsActive(partnerBook.getActive());
      bookBean.setBookAuthor(partnerBook.getBookAuthor());
      bookBean.setBookDetailDescription(partnerBook.getBookDetailDesc());
      bookBean.setBookPrice(new BigDecimal(partnerBook.getBookPrice()));
      bookBean.setBookShortDescription(partnerBook.getBookShortDesc());
      bookBean.setBookTitle(partnerBook.getBookTitle());
      bookBean.setCategoryId(partnerBook.getCategoryIdpr());
      bookBean.setBookFullImage(partnerBook.getFullImageUrl());
      bookBean.setIsbn(partnerBook.getIsbn());
      bookBean.setPublisherName(partnerBook.getPublisherName());
      bookBean.setQuantity(partnerBook.getQuantity());
      bookBean.setBookThumbImage(partnerBook.getThumbImageUrl());
      bookListBeanList.add(bookBean);
    }
    return bookListBeanList;
  }

  /**
   * Add new books to the store.
   *
   * @param addBooks the add books
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  @Override
  public void addBooks(BookVO addBooks) throws SapeStoreException {
    LOGGER.debug("addBooks method: START");
    if (null != addBooks) {
      bookDao.addNewBooks(addBooks);
    }
    LOGGER.debug("addBooks method: END");
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.BookService#deleteBook(java.lang.String)
   */
  @Override
  public void deleteBook(String isbn) throws SapeStoreException {
    LOGGER.debug("deleteBook method: START");
    if (null != isbn) {
      bookDao.deleteBook(isbn);
    }
    LOGGER.debug("deleteBook method: END");
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.BookService#getFilteredBooks
   * (java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String)
   */
  @Override
  public List<Book> getFilteredBooks(String bookTitle, 
      String bookAuthor, String isbn, int categoryId,
      String publisherName, String isSort) {
    LOGGER.debug("getFilteredBooks method: START");
    List<Book> bookBeanFilteredList = bookDao.getFilteredBookList(bookTitle, 
        bookAuthor, isbn, categoryId,
        publisherName, isSort);
    LOGGER.debug("getFilteredBooks method: END");
    return bookBeanFilteredList;
  }

  @Override
  public List<Book> getRecommendList(int catId, Object checkMeFromSession, int userId) throws SapeStoreException {
    LOGGER.debug("getRecommendList method: START");
    List<Book> bookBeanList = null;
    bookBeanList = bookDao.getRecommendList(catId, checkMeFromSession, userId);

    if (bookBeanList != null && bookBeanList.size() > 0) {
      Collections.sort(bookBeanList, new Comparator<Book>() {
        @Override
        public int compare(final Book arg0, final Book arg1) {
          return arg0.getBookTitle().compareTo(arg1.getBookTitle());
        }
      });
    }
    LOGGER.debug("getBookList method: END");
    return bookBeanList;
  }
  
  @Override
  public Book getadBook(int catId, Object checkMeFromSession, int userId) throws SapeStoreException {
    LOGGER.debug("getadBook method: START");
    Book advBook = null;
    advBook = bookDao.getadvBook(catId, checkMeFromSession, userId);

    
    LOGGER.debug("getadBook method: END");
    return advBook;
  }

@Override
public Book getisbnBook(String isbn) throws SapeStoreException {
	
	Book isbnbook = null;
	isbnbook = bookDao.getisbnBook(isbn);
	
	return isbnbook;
}
}
