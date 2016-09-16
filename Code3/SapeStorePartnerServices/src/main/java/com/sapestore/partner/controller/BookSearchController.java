package com.sapestore.partner.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapestore.partner.services.SSPartnerBooksListBean;
import com.sapestore.partner.services.SSPartnerWebService;

// TODO: Auto-generated Javadoc
/**
 * This is rest controller for partner sape store which allows acces for sape
 * store controllers.
 *
 * @author pramac (Pavithran)
 * @version 1.
 */

@RestController
// @Controller
public class BookSearchController {

  /** The ss partner web service. */
  @Autowired
  private SSPartnerWebService ssPartnerWebService;

  /**
   * Gets the ss partner web service.
   *
   * @return the ss partner web service
   */
  public SSPartnerWebService getSsPartnerWebService() {
    return ssPartnerWebService;
  }

  /**
   * Sets the ss partner web service.
   *
   * @param ssPartnerWebService
   *          the new ss partner web service
   */
  public void setSsPartnerWebService(SSPartnerWebService ssPartnerWebService) {
    this.ssPartnerWebService = ssPartnerWebService;
  }

  /*
   * @RequestMapping(value = "/bookSearchFormSS" ,method = RequestMethod.GET)
   * public List<SSPartnerBooksListBean> bookSearch(@PathVariable("bookTitle")
   * String bookTitle,
   * 
   * @PathVariable("bookAuthor") String bookAuthor, @PathVariable("isbn") String
   * isbn,
   * 
   * @PathVariable("publisherName") String
   * publisherName, @PathVariable("categoryId") Integer categoryId, ModelMap
   * modelMap)
   */

  /**
   * This is book search controller for partner sape store.
   *
   * @author pramac
   * @version 1.
   * @param modelMap
   *          the model map
   * @param request
   *          the request
   * @return the list
   */
  @RequestMapping(value = "/bookSearchFormSS", method = RequestMethod.GET)
  public List<SSPartnerBooksListBean> bookSearch(ModelMap modelMap,
      HttpServletRequest request) {
    String bookAuthor = request.getParameter("bookAuthor");
    String bookTitle = request.getParameter("bookTitle");
    Integer categoryId = Integer.valueOf(request.getParameter("categoryId"));
    String publisherName = request.getParameter("publisherName");
    String isbn = request.getParameter("isbn");
    List<SSPartnerBooksListBean> bookSearchResult = new ArrayList<SSPartnerBooksListBean>();

    if (categoryId == -1) {
      bookSearchResult = ssPartnerWebService.getSearchBookListwithoutID(
          bookTitle, bookAuthor, isbn, publisherName);
    } else {

      bookSearchResult = ssPartnerWebService.getSearchBookList(bookTitle,
          bookAuthor, isbn, publisherName, categoryId);
    }

    return bookSearchResult;
  }

  /**
   * This is for getting book by category.
   *
   * @author pramac
   * @version 1.
   * @param categoryId
   *          the category id
   * @param bookTitle
   *          the book title
   * @param modelMap
   *          the model map
   * @return the list
   */
  @RequestMapping(value = "/bookBYCategoryId", method = RequestMethod.GET)
  public List<SSPartnerBooksListBean> bookSearchtrial(
      @PathParam("categoryId") Integer categoryId,
      @PathParam("bookTitle") String bookTitle, ModelMap modelMap) {

    List<SSPartnerBooksListBean> bookSearchResult = new ArrayList<SSPartnerBooksListBean>();

    bookSearchResult = ssPartnerWebService.getBookList(categoryId);

    modelMap.addAttribute("bookArr", bookSearchResult);

    if (bookSearchResult.size() == 0) {
      System.out.println("empty list");
    }

    for (SSPartnerBooksListBean ssPartnerBooksListBean : bookSearchResult) {
      System.out.println(ssPartnerBooksListBean);
    }

    return bookSearchResult;

  }
}