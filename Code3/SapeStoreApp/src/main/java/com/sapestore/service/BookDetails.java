package com.sapestore.service;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;

// TODO: Auto-generated Javadoc
/**
 * The Interface BookDetails.
 */
public interface BookDetails {

  /**
   * Performs get Book Details.
   *
   * @author aaga47
   * @version 1.0
   * @param isbn the isbn
   * @return the book details
   * @throws SapeStoreException the sape store exception
   */

  public Book getBookDetails(String isbn) throws SapeStoreException;

}
