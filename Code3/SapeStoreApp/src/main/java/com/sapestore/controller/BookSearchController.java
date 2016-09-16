package com.sapestore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.sapestore.dao.ReviewDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.service.BookService;
import com.sapestore.vo.BookVO;

// TODO: Auto-generated Javadoc
/**
 * The Class BookSearchController.
 */
@Controller
@SessionAttributes(value = { "userId", "username" })
public class BookSearchController {

  /** The book list. */
  private List<Book> bookList;

  /** The cat list. */
  private List<BookCategory> catList;

  /** The category name. */
  private String categoryName;

  /** The check me. */
  private boolean checkMe;

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
   *          the new book list
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
   *          the new cat list
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
   *          the new category name
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
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
   *          the new check me
   */
  public void setCheckMe(boolean checkMe) {
    this.checkMe = checkMe;
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
   *          the new book service
   */
  public void setBookService(BookService bookService) {
    this.bookService = bookService;
  }

  /**
   * This is a controller for AutoSuggest Appear LOG.
   *
   * @author maro20
   * @version 1.0
   * @param request
   *          the request
   * @param title
   *          the title
   * @return the auto suggest
   */
  @RequestMapping(value = "autoSuggest", method = RequestMethod.GET)
  @ResponseBody
  public String getAutoSuggest(HttpServletRequest request,
      @RequestParam("title") String title) {

    List<Book> bookList = bookService.getFilteredBooks(title, "", "", -1, "",
        "false");
    StringBuffer suggestions = new StringBuffer("");
    /* suggestions.append("<ul>"); */

    for (int count = 0; count < bookList.size(); count++) {
      if (count == 5) {
        break;
      }
      /*
       * suggestions.append("<li>"+bookList.get(count).getBookTitle()+
       * "</li><br>");
       */
      suggestions.append("<div><input name='title' id='sugg" + count
          + "' type='text' style='width : 330px;' value='"
          + bookList.get(count).getBookTitle() + "' readonly onclick='sugg"
          + "(this.value)'></div>");

    }
    /* suggestions.append("</ul>"); */
    return suggestions.toString();
  }

  /**
   * Gets the book search form.
   *
   * @param modelMap
   *          the model map
   * @return the book search form
   */
  @RequestMapping(value = "getBookSearchForm", method = RequestMethod.GET)
  public String getBookSearchForm(ModelMap modelMap) {

    List<BookCategory> categoryList = null;
    try {
      categoryList = bookService.getCategoryList();
    } catch (SapeStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    modelMap.addAttribute("catList", categoryList);
    return "BookSearch";
  }

  /**
   * Book search.
   *
   * @param bookTitle
   *          the book title
   * @param bookAuthor
   *          the book author
   * @param isbn
   *          the isbn
   * @param publisherName
   *          the publisher name
   * @param categoryName
   *          the category name
   * @param isSort
   *          the is sort
   * @param modelMap
   *          the model map
   * @param session
   *          the session
   * @return the string
   */
  @RequestMapping(value = "bookSearchForm", method = RequestMethod.GET)
  public String bookSearch(@RequestParam("bookTitle") String bookTitle,
      @RequestParam("bookAuthor") String bookAuthor,
      @RequestParam("isbn") String isbn,
      @RequestParam("publisherName") String publisherName,
      @RequestParam("categoryName") String categoryName,
      @RequestParam(value = "isSort", defaultValue = "false") String isSort,
      ModelMap modelMap, HttpSession session) {
    int categoryId = -1;
    ArrayList<BookCategory> bookCategoryBeanList;
    boolean checkMe = (boolean) session.getAttribute("checkMe");
    try {
      bookCategoryBeanList = (ArrayList<BookCategory>) bookService
          .getCategoryList();

      /*
       * searching the category id for the given category name
       * 
       * @param categoryId
       */

      for (BookCategory bookCategory : bookCategoryBeanList) {
        if (bookCategory.getCategoryName().equalsIgnoreCase(categoryName)) {
          categoryId = bookCategory.getCategoryId();
        }
      }
      ArrayList<Book> bookBeanFilteredList = (ArrayList<Book>) bookService
          .getFilteredBooks(bookTitle, bookAuthor, isbn, categoryId,
              publisherName, isSort);
      if (checkMe) {
        RestTemplate template = new RestTemplate();
        // String
        // bookurl="http://10.209.231.21:8080/SapeStorePartnerServicesTrial/bookSearchFormSS?bookTitle="+bookTitle+"&bookAuthor="+bookAuthor+"&isbn="+isbn+"&publisherName="+publisherName+"&categoryId="+categoryId;
        String bookurl = "http://localhost:8080/SapeStorePartnerServicesTrial/bookSearchFormSS?bookTitle="
            + bookTitle + "&bookAuthor=" + bookAuthor + "&isbn=" + isbn
            + "&publisherName=" + publisherName + "&categoryId=" + categoryId;
        bookBeanFilteredList.addAll(
            (ArrayList<Book>) template.getForObject(bookurl, List.class));
      }
      modelMap.addAttribute("bookBeanFilteredList", bookBeanFilteredList);
    } catch (SapeStoreException e) {

      e.printStackTrace();
    }

    return "BookSearch";
  }
}
