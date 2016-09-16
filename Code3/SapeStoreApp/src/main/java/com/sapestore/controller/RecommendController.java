package com.sapestore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

@Controller
@SessionAttributes("RecommendList")
public class RecommendController {
	
	/** The recommend list. */
	private List<Book> recommendList;

	public List<Book> getRecommendList() {
		return recommendList;
	}

	public void setRecommendList(List<Book> recommendList) {
		this.recommendList = recommendList;
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
	@RequestMapping(value = "/getRecommendBooks", method = RequestMethod.GET)
	public ModelAndView getRecommendBooks(@RequestParam("dsessionId") String dsessionId,
			ModelMap modelMap, HttpSession httpSession) throws Exception {

		// populate recommendList
		recommendList = getRecommendsList(0, false, 0);
		this.setRecommendList(recommendList);
		
		System.out.println(dsessionId);
		System.out.println("inside recommendBooks method");
		
		// set recommendList in jsp
		modelMap.addAttribute("RecommendList", this.getRecommendList());

		return new ModelAndView("newRecommBook");

	}
	*/
	
	@RequestMapping(value = "/getRecommendBooks", method = RequestMethod.GET)
	public ModelAndView getRecommendBooks(@RequestParam("dsessionId") String dsessionId,
			ModelMap modelMap, HttpSession httpSession) throws Exception {
		
		 RestTemplate template = new RestTemplate();
		 //get the string returned from API
		 String fromapi=template.getForObject("http://10.150.232.32:5000/re", String.class);
		 System.out.println(fromapi);
		 
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
		 
		 Book book1=bookfromisbn(isbn1);
		 Book book2=bookfromisbn(isbn2);
		 
		 List<Book> recommendListlocal=new ArrayList<Book>();
		 recommendListlocal.add(book1);
		 recommendListlocal.add(book2);
		 
		 this.setRecommendList(recommendListlocal);
		// set recommendList in jsp
		 modelMap.addAttribute("RecommendList", this.getRecommendList());

		return new ModelAndView("newRecommBook");
	}
	
	
	/*
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
*/
	
	private Book bookfromisbn(String isbn) throws SapeStoreException{
		
		Book book=null;
		book=bookService.getisbnBook(isbn);
		
		return book;
		
	}


}
