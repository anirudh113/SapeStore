
package com.sapestore.dao;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.service.BookDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;


// TODO: Auto-generated Javadoc
/**
 * The Class BookDetailsDao.
 */
@Repository
public class BookDetailsDao implements BookDetails {

  /** The hibernate template. */
  @Autowired
  private HibernateTemplate hibernateTemplate;

  /**
   * Logger for log messages.
   */

  private static final SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(
                                                  ProductDao.class.getName());

  /*
   * (non-Javadoc)
   * 
   * @see com.sapestore.service.BookDetails#getBookDetails(java.lang.String)
   */

  /**
   * Method to get book details from the database.
   *
   * @author aaga47
   * @version 1.0
   * @param isbn the isbn
   * @return book
   * @throws SapeStoreException the sape store exception
   */

  public Book getBookDetails(String isbn) throws SapeStoreException {
    LOGGER.debug("BookDetailsDao.getBookDetails method: START");
    Book book = null;
    try {

      book = hibernateTemplate.get(Book.class, isbn.trim());
    } catch (SapeStoreSystemException se) {
      LOGGER.fatal("A DB exception occured while inserting the book's information", se);
    }
    return book;
  }

}
