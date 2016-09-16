package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.BookDetailsDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.service.BookService;
import com.sapestore.service.ReviewService;

// TODO: Auto-generated Javadoc
/**
 * This is a controller class for landing book details and reviews page. CHANGE
 * LOG
 * 
 * @author aaga47
 * @version 1.0
 */

@Controller
@SessionAttributes(value = { "book", "bookRatingCommentList", "bookVo" })
public class BookDetailsController {

	/** The book list. */
	private List<Book> bookList;

	/** The recommend list. */
	private List<Book> recommendList;

	public List<Book> getRecommendList() {
		return recommendList;
	}

	public void setRecommendList(List<Book> recommendList) {
		this.recommendList = recommendList;
	}
	
	/** The adBook list. */
	private Book adbook;

	public Book getAdbook() {
		return adbook;
	}

	public void setAdbook(Book adbook) {
		this.adbook = adbook;
	}


	/** The cat list. */
	private List<BookCategory> catList;

	/** The category name. */
	private String categoryName;

	/** The check me. */
	private boolean checkMe;

	/** The book rating comment list. */
	private List<BookRatingComments> bookRatingCommentList;

	/** The book service. */
	@Autowired
	private BookService bookService;

	/** The book details. */
	@Autowired
	BookDetailsDao bookDetails;

	/** The review service. */
	@Autowired
	private ReviewService reviewService;

	/**
	 * Gets the book service.
	 *
	 * @return the book service
	 */
	public BookService getBookService() {
		return bookService;
	}

	/**
	 * Sets the book service.
	 *
	 * @param bookService
	 *            the new book service
	 */
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * Checks if is check me.
	 *
	 * @return true, if is check me
	 */
	public boolean isCheckMe() {
		return checkMe;
	}

	/**
	 * Sets the check me.
	 *
	 * @param checkMe
	 *            the new check me
	 */
	public void setCheckMe(boolean checkMe) {
		this.checkMe = checkMe;
	}

	/**
	 * Gets the book list.
	 *
	 * @return the book list
	 */
	public List<Book> getBookList() {
		return bookList;
	}

	/**
	 * Sets the book list.
	 *
	 * @param bookList
	 *            the new book list
	 */
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	/**
	 * Gets the cat list.
	 *
	 * @return the cat list
	 */
	public List<BookCategory> getCatList() {
		return catList;
	}

	/**
	 * Sets the cat list.
	 *
	 * @param catList
	 *            the new cat list
	 */
	public void setCatList(List<BookCategory> catList) {
		this.catList = catList;
	}

	/**
	 * Gets the category name.
	 *
	 * @return the category name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Sets the category name.
	 *
	 * @param categoryName
	 *            the new category name
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Gets the book rating comment list.
	 *
	 * @return the book rating comment list
	 */
	public List<BookRatingComments> getBookRatingCommentList() {
		return bookRatingCommentList;
	}

	/**
	 * Sets the book rating comment list.
	 *
	 * @param bookRatingCommentList
	 *            the new book rating comment list
	 */
	public void setBookRatingCommentList(
			List<BookRatingComments> bookRatingCommentList) {
		this.bookRatingCommentList = bookRatingCommentList;
	}

	/**
	 * Gets the book.
	 *
	 * @return the book
	 */
	public BookDetailsDao getBook() {
		return bookDetails;
	}

	/**
	 * Sets the book.
	 *
	 * @param bookDetails
	 *            the new book
	 */
	public void setBook(BookDetailsDao bookDetails) {
		this.bookDetails = bookDetails;
	}

	/** The Constant LOGGER. */
	private static final SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(BookDetailsController.class.getName());

	/**
	 * This is a controller method for landing book details and reviews page.
	 * CHANGE LOG
	 *
	 * @author aaga47
	 * @version 1.0
	 * @param isbn
	 *            the isbn
	 * @param modelMap
	 *            the model map
	 * @param session
	 *            the session
	 * @return the string
	 * @throws SapeStoreException
	 *             the sape store exception
	 */

	@RequestMapping(value = "/bookDetailsController", method = RequestMethod.GET)
	public String beforeLogin(@RequestParam("isbn") String isbn,
			ModelMap modelMap, HttpSession session) throws SapeStoreException {

		LOGGER.debug(" BookDetailsController.bookDetails method: START ");
		this.setCatList(getCategoryList());
		Book book = bookDetails.getBookDetails(isbn);
		session.setAttribute("bookVo", book);
		modelMap.addAttribute("book", book);
		modelMap.addAttribute("catList", this.getCatList());
		modelMap.addAttribute("categoryName", book.getCategoryName());
		List<BookRatingComments> bookReviewList = null;
		bookReviewList = getReviewsListByIsbn(isbn);
		this.setBookRatingCommentList(bookReviewList);
		modelMap.addAttribute("bookRatingCommentList", bookReviewList);
		LOGGER.debug("BookDetailsController.bookDetails method: END ");
		LOGGER.error(book.getBookDetailDescription());
		
		// populate recommendList
		List <Book> recommendList = getRecommendsList(book.getCategoryId(), isCheckMe(), 0);
		this.setRecommendList(recommendList);
		// set recommendList in jsp
		modelMap.addAttribute("recommendList", this.getRecommendList());
		
		// get adBook
		Book adbook = getAdBooks(book.getCategoryId(), isCheckMe(), 0);
		this.setAdbook(adbook);
		// set adBook in jsp
		modelMap.addAttribute("adbook", this.getAdbook());
		
		//addbookdetailsascookie
		

		return "bookDetails";
	}

	/**
	 * This is a getReviewsListByIsbn method for landing book details and
	 * reviews page. CHANGE LOG
	 *
	 * @author aaga47
	 * @version 1.0
	 * @param isbn
	 *            the isbn
	 * @return the reviews list by isbn
	 * @throws SapeStoreException
	 *             the sape store exception
	 */

	public List<BookRatingComments> getReviewsListByIsbn(String isbn)
			throws SapeStoreException {
		LOGGER.debug("getReviewsListByIsbn method: START");
		List<BookRatingComments> bookReviewList = null;
		bookReviewList = reviewService.getThreeReviewslist(isbn);
		LOGGER.debug("getReviewsListByIsbn method: END");
		return bookReviewList;
	}

	/**
	 * Gets the category list.
	 *
	 * @return the category list
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	private List<BookCategory> getCategoryList() throws SapeStoreException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getCategoryList method: START");
		}

		List<BookCategory> bookCategoryList = null;

		try {
			bookCategoryList = bookService.getCategoryList();

		} catch (SapeStoreSystemException ex) {
			LOGGER.error("getCategoryList method: ERROR: " + ex);
			return null;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getCategoryList method: END");
		}
		return bookCategoryList;
	}

	private List<Book> getRecommendsList(int categoryId,
			Object checkMeFromSession, int userId) throws SapeStoreException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getRecommendsList method: START");
		}
		List<Book> recommendList = null;
		try {
			try {
				recommendList = bookService.getRecommendList(categoryId,
						checkMeFromSession, userId);
			} catch (SapeStoreSystemException e) {
				LOGGER.error("getRecommendsList method: ERROR: " + e);
			}
			this.setRecommendList(recommendList);
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("welcome method: ERROR: " + ex);
			return null;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksList method: END");
		}
		return recommendList;
	}
	
	private Book getAdBooks(int categoryId,
			Object checkMeFromSession, int userId) throws SapeStoreException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdBooks method: START");
		}
		Book adBook = null;
		try {
			try {
				adBook = bookService.getadBook(categoryId,
						checkMeFromSession, userId);
			} catch (SapeStoreSystemException e) {
				LOGGER.error("getAdBooks method: ERROR: " + e);
			}
			this.setAdbook(adBook);
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("getAdBooks method: ERROR: " + ex);
			return null;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdBooks method: END");
		}
		return adBook;
	}

}
