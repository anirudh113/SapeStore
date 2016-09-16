package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.service.BookService;
import com.sapestore.vo.HomeVO;
import com.sapestore.vo.UserVO;

// TODO: Auto-generated Javadoc
/**
 * This is a controller class for landing and post customer login pages. CHANGE
 * LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Controller
@SessionAttributes("checkMe")
public class HomePageController {

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

	/** The book service. */
	@Autowired
	private BookService bookService;

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

	/** The Constant LOGGER. */
	private static final SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(HomePageController.class.getName());

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
	 * Processes the home page requests.
	 *
	 * @param map
	 *            the map
	 * @param session
	 *            the session
	 * @param request
	 *            the request
	 * @param httpSession
	 *            the http session
	 * @return .
	 * @throws Exception
	 *             the exception
	 * @throws SapeStoreSystemException .
	 */
	@RequestMapping(value = "/togglecheckMe", method = RequestMethod.GET)
	public Object toggleCheckMe(ModelMap map, HttpSession session,
			HttpServletRequest request, HttpSession httpSession)
			throws Exception {
		boolean checkMe = (boolean) session.getAttribute("checkMe");
		map.addAttribute("checkMe", !checkMe);
		String referer = request.getHeader("referer");
		return "redirect:/getBookSearchForm";
	}

	/**
	 * Returnindex.
	 *
	 * @return the object
	 */
	@RequestMapping(value = "/welcome1", method = RequestMethod.GET)
	public Object returnindex() {

		return "index";
	}

	/**
	 * Sets the check me true.
	 *
	 * @param map
	 *            the map
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String setCheckMeTrue(ModelMap map) throws Exception {

		// map.addAttribute("checkMe",false);
		return "redirect:/welcome";
	}

	/**
	 * Welcome.
	 *
	 * @param checkMe
	 *            the check me
	 * @param modelMap
	 *            the model map
	 * @param httpServletRequest
	 *            the http servlet request
	 * @param httpSession
	 *            the http session
	 * @return the string
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcome(
			@RequestParam(value = "checkMe", required = false) boolean checkMe,
			ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpSession httpSession) throws SapeStoreException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: START");
		}

		List<Book> bookList = null;
		List<Book> recommendList = null;
		Book adbook = null;

		try {
			this.setCatList(getCategoryList());
			Object checkMeFromSession = httpSession.getAttribute("checkMe");
			bookList = getBooksList(checkMeFromSession);

			if (checkMeFromSession != null && (boolean) checkMeFromSession) {
				bookList.addAll(bookService.getBookFromWebService(0));
			}
			this.setBookList(bookList);

			// populate recommendList
			recommendList = getRecommendsList(0, checkMeFromSession, 0);
			this.setRecommendList(recommendList);
			// set recommendList in jsp
			modelMap.addAttribute("recommendList", this.getRecommendList());
			
			// get adBook 
			adbook = getAdBooks(0, checkMeFromSession, 0);
			this.setAdbook(adbook);
			// set adBook in jsp
			modelMap.addAttribute("adbook", this.getAdbook());

			this.setCategoryName("Top Rated");
			modelMap.addAttribute("bookList", this.getBookList());

			modelMap.addAttribute("catList", this.getCatList());
			if (httpSession.getAttribute("checkMe") != null) {
				modelMap.addAttribute("checkMe",
						httpSession.getAttribute("checkMe"));
			} else {
				modelMap.addAttribute("checkMe", false);
			}
			modelMap.addAttribute("categoryName", getCategoryName());
			modelMap.addAttribute("userlogin", new UserVO());
			modelMap.addAttribute("categoryId", 0);
			modelMap.addAttribute("welcome", new HomeVO());

		} catch (SapeStoreSystemException e) {
			LOGGER.error("welcome method: ERROR: " + e);
			modelMap.addAttribute("errorMessage",
					"Error in opening the welcome page.");
			return "redirect:/errorPage";
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: ModelMap: " + modelMap);
			LOGGER.debug("welcome method: END");
		}

		return "index";
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

	/**
	 * Gets the books list.
	 *
	 * @param checkMeFromSession
	 *            the check me from session
	 * @return the books list
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	private List<Book> getBooksList(Object checkMeFromSession)
			throws SapeStoreException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksList method: START");
		}
		List<Book> bookList = null;
		try {
			try {
				bookList = bookService.getBookList(0, checkMeFromSession);
			} catch (SapeStoreSystemException e) {
				LOGGER.error("getBooksList method: ERROR: " + e);
			}
			this.setBookList(bookList);
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("welcome method: ERROR: " + ex);
			return null;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksList method: END");
		}
		return bookList;
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
			LOGGER.error("getRecommendsList method: ERROR: " + ex);
			return null;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getRecommendsList method: END");
		}
		return recommendList;
	}

	/**
	 * Processes the requests to pull book list by category.
	 *
	 * @param welcome
	 *            the welcome
	 * @param categoryId
	 *            the category id
	 * @param categoryName
	 *            the category name
	 * @param modelMap
	 *            the model map
	 * @return the books list by cat
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/bookListByCat", method = RequestMethod.GET)
	public String getBooksListByCat(@ModelAttribute("welcome") HomeVO welcome,
			@RequestParam("categoryId") int categoryId,
			@RequestParam("categoryName") String categoryName, ModelMap modelMap)
			throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksListByCat method: END");
		}

		List<Book> list = null;
		List<Book> recommendlist = null;
		Book adBook = null;
		
		try {
			this.setCheckMe(welcome.isCheckMe());
			list = bookService.getBookList(categoryId, this.isCheckMe());
			if (this.isCheckMe()) {
				list.addAll(bookService.getBookFromWebService(categoryId));
			}
			this.setBookList(list);
			this.setCatList(getCategoryList());
			// populate recommendList
			recommendlist = getRecommendsList(categoryId, this.isCheckMe(), 0);
			this.setRecommendList(recommendlist);
			
			// get adBook
			adbook = getAdBooks(categoryId, this.isCheckMe(), 0);
			this.setAdbook(adbook);
			// set adBook in jsp
			modelMap.addAttribute("adbook", this.getAdbook());
			

			
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("getBooksListByCat method: END" + ex);
			modelMap.addAttribute("errorMessage",
					"Error in getting book list by category");
			return "redirect:/errorPage";
		}
		
		// set recommendList in jsp
		modelMap.addAttribute("recommendList", this.getRecommendList());
		
		modelMap.addAttribute("bookList", list);
		modelMap.addAttribute("catList", this.getCatList());
		modelMap.addAttribute("categoryName", categoryName);
		modelMap.addAttribute("checkMe", this.checkMe);
		modelMap.addAttribute("userlogin", new UserVO());
		modelMap.addAttribute("categoryId", categoryId);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksListByCat method: END");
		}
		return "CategoryPage";
	}

	/**
	 * This method returns the category of books.
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

}
