package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.service.BookService;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.ShoppingCartVO;
import com.sapestore.vo.UserVO;

import org.springframework.http.HttpStatus;

@Controller
@SessionAttributes("AdBook")
public class AdBookController {

	/** The adBook list. */
	private Book adbook;

	public Book getAdbook() {
		return adbook;
	}

	public void setAdbook(Book adbook) {
		this.adbook = adbook;
	}

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

	/*
	 * @RequestMapping(value = "/getAdvBook", method = RequestMethod.GET,
	 * produces = "application/json")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public @ResponseBody Book
	 * getAdBook(@RequestParam("dsessionId") String dsessionId, ModelMap
	 * modelMap, HttpSession httpSession) throws Exception {
	 * 
	 * //Book adBook = null; //adBook = (Book) modelMap.get("AdBook");
	 * System.out.println("inside ajax");
	 * 
	 * // get adBook adbook = getAdBooks(0, false, 0); this.setAdbook(adbook);
	 * // set adBook in jsp modelMap.addAttribute("AdBook", this.getAdbook());
	 * 
	 * return adbook;
	 * 
	 * }
	 */

	@RequestMapping(value = "/getAdvBook", method = RequestMethod.GET)
	public ModelAndView getSubView(
			@RequestParam("dsessionId") String dsessionId, ModelMap modelMap,
			HttpSession httpSession) throws Exception {

		RestTemplate template = new RestTemplate();
		 //get the string returned from API
		 String fromapi=template.getForObject("http://10.150.232.32:5000/re", String.class);
		 
		 //split the string
		 String[] parts= fromapi.split("=");
		 String part1=parts[0];
		 String part2=parts[1]; // list of isbn numbers
		 
		//split the isbn numbers
		 parts=part2.split(",");
		 part1=parts[0];//for isbn1
		 part2=parts[1];//for isbn2
		 
		 //for isbn1
		 String[] isbnparts=part1.split(" ");
		 String isbn1=isbnparts[0];
		
		 //for isbn2
		 isbnparts=part2.split("\"");
		 String isbn2=isbnparts[0];
		 System.out.println(isbn1);
		 System.out.println(isbn2);
		 
		// get adBook
		 Book adbook=bookfromisbn(isbn1);
		
		// get adBook
		this.setAdbook(adbook);
		// set adBook in jsp

		modelMap.addAttribute("AdBook", this.getAdbook());
		return new ModelAndView("newAdBook");
	}

	private Book bookfromisbn(String isbn) throws SapeStoreException {

		Book book = null;
		book = bookService.getisbnBook(isbn);

		return book;

	}

	/*
	 * private Book getAdBooks(int categoryId, Object checkMeFromSession, int
	 * userId) throws SapeStoreException {
	 * 
	 * if (LOGGER.isDebugEnabled()) { LOGGER.debug("getAdBooks method: START");
	 * } Book adBook = null; try { try { adBook =
	 * bookService.getadBook(categoryId, checkMeFromSession, userId); } catch
	 * (SapeStoreSystemException e) { LOGGER.error("getAdBooks method: ERROR: "
	 * + e); } this.setAdbook(adBook); } catch (SapeStoreSystemException ex) {
	 * LOGGER.error("getAdBooks method: ERROR: " + ex); return null; }
	 * 
	 * if (LOGGER.isDebugEnabled()) { LOGGER.debug("getAdBooks method: END"); }
	 * return adBook; }
	 */

}
