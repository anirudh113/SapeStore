package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.vo.BookVO;

// TODO: Auto-generated Javadoc
/**
 * Service interface for fetching books information.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
/**
 * @author Amogh.
 * @author Shrihari
 * @author Vishak
 *
 */
public interface BookService {

  /**
   * Returns list of book belonging to the specified category.
   * @param catId the cat id
   * @param checkMeFromSession the check me from session
   * @return list of Book objects
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  public List<Book> getBookList(int catId, Object checkMeFromSession)
      throws SapeStoreException;

  /**
   * Returns list of categories.
   * @return list of BookCategory objects
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  public List<BookCategory> getCategoryList() throws SapeStoreException;

  /**
   * Returns list of books pulled from the partner services.
   * @param catId the cat id
   * @return list of Book objects from SapeStore Partner Service
   */
  public List<Book> getBookFromWebService(int catId);

  /**
   * Add new books to the store.
   * @param addBooks the add books
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  void addBooks(BookVO addBooks) throws SapeStoreException;

  /**
   * Deletes the book with the specified ISBN.
   * @param isbn the isbn
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  void deleteBook(String isbn) throws SapeStoreException;

  /**
   * Gets the filtered books.
   * @param bookTitle the book title
   * @param bookAuthor the book author
   * @param isbn the isbn
   * @param categoryId the category id
   * @param publisherName the publisher name
   * @param isSort the is sort
   * @return the filtered books
   */
  public List<Book> getFilteredBooks(String bookTitle, String bookAuthor,
      String isbn, int categoryId, String publisherName, String isSort);

  public List<Book> getRecommendList(int catId, Object checkMeFromSession, int userId)
	      throws SapeStoreException;
  
  public Book getadBook(int catId, Object checkMeFromSession, int userId)
	      throws SapeStoreException;
  
  public Book getisbnBook(String isbn) throws SapeStoreException;

}
