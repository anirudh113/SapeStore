package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.vo.BookVO;
import com.sun.mail.iap.ConnectionException;

// TODO: Auto-generated Javadoc
/**
 * DAO class for retrieving the book's list from the database.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

/**
 * @author Chirag
 * @author Amogh
 * @author Shrihari
 * @author Vishak
 * @author Madhav
 * @author Nishtha G.
 *
 */
@Repository
public class ProductDao {

  /** The review dao. */
  @Autowired
  private ReviewDao reviewDao;

  /** The hibernate template. */
  @Autowired
  private HibernateTemplate hibernateTemplate;

  /**
   * Gets the hibernate template.
   *
   * @return the hibernate template
   */
  public HibernateTemplate getHibernateTemplate() {
    return hibernateTemplate;
  }

  /**
   * Sets the hibernate template.
   *
   * @param hibernateTemplate the new hibernate template
   */
  public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
    this.hibernateTemplate = hibernateTemplate;
  }

  /**
   * Logger for log messages.
   */
  private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ProductDao.class.getName());

  /**
   * Method to fetch the book list from the database.
   *
   * @param categoryId the category id
   * @param checkMeFromSession the check me from session
   * @return listBook List of all books from database
   * @throws SapeStoreException the sape store exception
   */
  @SuppressWarnings("unchecked")
  public List<Book> getBookList(int categoryId, Object checkMeFromSession) throws SapeStoreException {
    List<Book> listBook = null;
    if (categoryId == 0) {
    	List<Book> listBookall = (List<Book>) hibernateTemplate.findByNamedQuery("Book.findAll");
    	listBook = listBookall.subList(0, 15);

    } else
      listBook = (List<Book>) hibernateTemplate.findByNamedQueryAndNamedParam("Book.findByCategoryId", "categoryId",
          categoryId);

    return listBook;
  }

  /**
   * Method to fetch the book's category list.
   *
   * @return list of book categories
   * @throws SapeStoreException the sape store exception
   */
  @SuppressWarnings("unchecked")
  public List<BookCategory> getBookCategoryList() throws SapeStoreException {
    List<BookCategory> listBookCategories = (List<BookCategory>) hibernateTemplate
        .findByNamedQuery("BookCategory.findAll");
    return listBookCategories;
  }

  /**
   * deleteBook method updates the quantity of the selected book to zero in the
   * database.
   *
   * @param isbn the isbn
   * @throws SapeStoreException the sape store exception
   */
  public void deleteBook(String isbn) throws SapeStoreException {
    LOGGER.debug(" BookDao.deleteBook method: START");
    Book book = hibernateTemplate.get(Book.class, isbn.trim());
    book.setQuantity(0);
    hibernateTemplate.saveOrUpdate(book);
  }

  /**
   * Method to add a new book to the database.
   *
   * @param vo the vo
   * @throws SapeStoreException the sape store exception
   */
  public void addNewBooks(BookVO vo) throws SapeStoreException {
    LOGGER.debug(" ProductDao.addNewBooks method: START");
    try {
      Book book = new Book();
      book.setIsbn(vo.getIsbn());
      book.setPublisherName(vo.getPublisherName());
      book.setCategoryId(Integer.parseInt(vo.getCategoryId()));
      book.setBookTitle(vo.getBookTitle());
      book.setQuantity(vo.getQuantity());
      book.setBookAuthor(vo.getBookAuthor());
      book.setBookThumbImage(vo.getThumbPath());
      book.setBookFullImage(vo.getFullPath());
      book.setBookPrice(new BigDecimal(vo.getBookPrice()));
      book.setBookShortDescription(vo.getBookShortDesc());
      book.setBookDetailDescription(vo.getBookDetailDesc());
      book.setRentAvailability(vo.getRentAvailable());
      book.setRentPrice(new BigDecimal(vo.getRentPrice()));
      book.setCreatedDate(new java.util.Date());
      book.setUpdatedDate(new java.util.Date());
      book.setIsActive("Y");
      hibernateTemplate.save(book);
    } catch (SapeStoreSystemException se) {
      LOGGER.fatal("A DB exception occured while inserting the book's information", se);
    }
    LOGGER.debug(" ProductDao.addNewBooks method: END ");
  }

  /**
   * Update Book method updates the detail of the corresponding book.
   *
   * @param updateInventoryBean the update inventory bean
   * @throws SapeStoreException the sape store exception
   */
  public void updateBooks(BookVO updateInventoryBean) throws SapeStoreException {
    LOGGER.debug(" ProductDao.updateBooks method: START ");

    try {
      Book book = hibernateTemplate.get(Book.class, updateInventoryBean.getOldIsbn());
      if (book != null) {
        book.setIsbn(updateInventoryBean.getIsbn());
        book.setPublisherName(updateInventoryBean.getPublisherName().trim());
        book.setBookTitle(updateInventoryBean.getBookTitle().trim());
        book.setQuantity(updateInventoryBean.getQuantity());
        book.setBookAuthor(updateInventoryBean.getBookAuthor().trim());
        book.setBookPrice(new BigDecimal(updateInventoryBean.getBookPrice()));
        book.setBookShortDescription(updateInventoryBean.getBookShortDesc().trim());
        book.setRentAvailability(updateInventoryBean.getRentAvailable());
        book.setRentPrice(new BigDecimal(updateInventoryBean.getRentPrice().trim()));
        book.setBookDetailDescription(updateInventoryBean.getBookDetailDesc().trim());
        book.setCategoryId(Integer.parseInt(updateInventoryBean.getCategoryId()));
        if (updateInventoryBean.getThumbPath() != null) {
          book.setBookThumbImage(updateInventoryBean.getThumbPath());
        }
        if (updateInventoryBean.getFullPath() != null) {
          book.setBookFullImage(updateInventoryBean.getFullPath());
        }
        book.setUpdatedDate(new java.util.Date());
        hibernateTemplate.update(book);
        LOGGER.debug(" Book is updated ");
      }
    } catch (SapeStoreSystemException se) {
      LOGGER.fatal("A DB exception occured while inserting the book's information", se);
    }
    LOGGER.debug(" ProductDao.updateBooks method: END ");
  }

  /**
   * Sets details in com.hibernate.entity.Book object from BookVO Spring
   * component
   *
   * @param book the book
   * @return the book vo
   */
  private BookVO setBookDetailBean(Book book) {
    BookVO vo = null;
    if (book != null) {
      vo = new BookVO();
      vo.setIsbn(book.getIsbn());
      vo.setBookTitle(book.getBookTitle());
      vo.setBookAuthor(book.getBookAuthor());
      vo.setBookPrice(book.getBookPrice().toString());
      vo.setThumbPath(book.getBookThumbImage());
      vo.setFullPath(book.getBookFullImage());
      vo.setQuantity(1);
      vo.setAvailableQuantity((book.getQuantity()).toString());
      vo.setRentPrice(book.getRentPrice().toString());
      vo.setBookShortDesc(book.getBookShortDescription());
      vo.setBookDetailDesc(book.getBookDetailDescription());
      vo.setCategoryId(book.getCategoryId().toString());
      vo.setCategoryName(book.getCategoryName());
      vo.setPublisherName(book.getPublisherName());
      vo.setRentAvailable(book.getRentAvailability());
      vo.setType("Purchase");
      vo.setExpectedReturnDate();
    }
    return vo;
  }

  /**
   * Gets the book of corresponding ISBN from database.
   *
   * @param isbn the isbn
   * @return BookVO object
   */
  public BookVO getBookByIsbn(String isbn) {
    LOGGER.error("inside dao");
    String[] namedParams = { "isbn" };
    Object[] paramValues = { isbn };

    @SuppressWarnings("unchecked")
    List<Book> books = (List<Book>) (hibernateTemplate).findByNamedQueryAndNamedParam("Book.getByIsbn", namedParams,
        paramValues);
    BookVO bookvo = setBookDetailBean(books.get(0));
    return bookvo;
  }

  /**
   * Gets the filtered book list.
   *
   * @param bookTitle the book title
   * @param bookAuthor the book author
   * @param isbn the isbn
   * @param categoryId the category id
   * @param publisherName the publisher name
   * @param isSort the is sort
   * @return bookBeanFilteredList
   */
  @SuppressWarnings("unchecked")
  public List<Book> getFilteredBookList(String bookTitle, String bookAuthor, String isbn, int categoryId,
      String publisherName, String isSort) {

    List<Book> bookBeanFilteredList;
    /*
     * if category is not given then first query will be executed otherwise
     *
     *
     **/
    if (categoryId == -1) {
      String name[] = { "bookTitle", "bookAuthor", "isbn", "publisherName" };
      Object value[] = { "%" + bookTitle.toUpperCase() + "%", "%" + bookAuthor.toUpperCase() + "%", isbn,
          "%" + publisherName.toUpperCase() + "%" };

      if (isbn.equalsIgnoreCase("") || isbn == null) {
        value[2] = "%" + isbn + "%";
      }

      bookBeanFilteredList = (List<Book>) hibernateTemplate
          .findByNamedQueryAndNamedParam("Book.bookSearchWithoutCategory", name, value);

    }

    else {
      String name[] = { "bookTitle", "bookAuthor", "isbn", "publisherName", "categoryId" };
      Object value[] = { "%" + bookTitle.toUpperCase() + "%", "%" + bookAuthor.toUpperCase() + "%", isbn,
          "%" + publisherName.toUpperCase() + "%", categoryId };

      if (isbn.equalsIgnoreCase("") || isbn == null) {
        value[2] = "%" + isbn + "%";
      }

      bookBeanFilteredList = (List<Book>) hibernateTemplate.findByNamedQueryAndNamedParam("Book.bookSearchWithCategory",
          name, value);
    }

    if (isSort.equalsIgnoreCase("true") && bookBeanFilteredList.size() > 1) {
      return getSortedBookList(bookBeanFilteredList, isSort);
    }

    return bookBeanFilteredList;
  }

  /**
   * Gets the sorted book list.
   *
   * @param filteredList the filtered list
   * @param isSort the is sort
   * @return filteredList
   */
  public List<Book> getSortedBookList(List<Book> filteredList, String isSort) {

    List<Integer> compareList = new ArrayList<>();
    List<BookRatingComments> reviewVoList = null;
    for (int i = 0; i < filteredList.size(); i++) {
      try {
        reviewVoList = reviewDao.getBookReviews(filteredList.get(i).getIsbn());
        compareList.add(reviewVoList.size());

      } catch (SapeStoreException e) {
        e.printStackTrace();
      }
    }

    @SuppressWarnings("unused")
    int sizefilter = filteredList.size();
    @SuppressWarnings("unused")
    int sizereview = compareList.size();
    for (int i = 0; i < filteredList.size() - 1; i++) {
      for (int j = 0; j < (filteredList.size() - i - 1); j++) {
        if (compareList.get(j) < compareList.get(j + 1)) {
          Collections.swap(filteredList, j, j + 1);
          Collections.swap(compareList, j, j + 1);
        }
      }

    }

    return filteredList;

  }
  
  @SuppressWarnings("unchecked")
  public List<Book> getRecommendList(int categoryId, Object checkMeFromSession, int userId) throws SapeStoreException {
    List<Book> listBook = null;
    
    if (categoryId == 0) {
    	List<Book> listBookTemp = (List<Book>) hibernateTemplate.findByNamedQuery("Book.toprated");
    	if (listBookTemp.size() > 5)
    		listBook =listBookTemp.subList(0, 5);
    	else
    		listBook = listBookTemp;

    } else{
    	List<Book> listBookTemp = (List<Book>) hibernateTemplate.findByNamedQueryAndNamedParam("Book.topratedcategory", "categoryId",
          categoryId);
    	if (listBookTemp.size() > 5)
    		listBook =listBookTemp.subList(0, 5);
    	else
    		listBook = listBookTemp;
    }
    return listBook;
  }
  
	@SuppressWarnings("unchecked")
	public Book getadvBook(int categoryId, Object checkMeFromSession, int userId)
			throws SapeStoreException {
		Book advBook = null;

		if (categoryId == 0) {
			List<Book> listBook = (List<Book>) hibernateTemplate.findByNamedQuery("Book.toprated");
			if (listBook.size() > 0)
				advBook = listBook.get(0);
			else
				advBook = null;

		} else {
			List<Book> listBook = (List<Book>) hibernateTemplate.findByNamedQueryAndNamedParam("Book.topratedcategory",
							"categoryId", categoryId);
			if (listBook.size() > 0)
				advBook = listBook.get(0);
			else
				advBook = null;

		}
		return advBook;
	}

	public Book getisbnBook(String isbn) {

		Book isbnbook =null;
		@SuppressWarnings("unchecked")
		List<Book> isbnbooklist = (List<Book>) hibernateTemplate.findByNamedQueryAndNamedParam("Book.getByIsbn",
				"isbn", isbn);
		isbnbook = isbnbooklist.get(0);
		
		return isbnbook;
	}


}
