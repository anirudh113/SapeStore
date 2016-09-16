package com.sapestore.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.service.ReviewService;
import com.sapestore.vo.BookRatingCommentVO;

// TODO: Auto-generated Javadoc
/**
 * This is a controller class for the review functionality.
 * 
 * @author shadab
 * @version 1.0
 */

@Controller
public class ReviewController {
  
  /** The Constant LOGGER. */
  private static final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(ReviewController.class.getName());

  /** The forward str. */
  String forwardStr = null;
  
  /** The book rating comment list. */
  private List<BookRatingComments> bookRatingCommentList;

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
   * @param bookRatingCommentList the new book rating comment list
   */
  public void setBookRatingCommentList(List<BookRatingComments> bookRatingCommentList) {
    this.bookRatingCommentList = bookRatingCommentList;
  }

  /** The review service. */
  @Autowired
  private ReviewService reviewService;

  /**
   * This is a controller method is for adding the review functionality.
   *
   * @param comments the comments
   * @param rating Rating of the book
   * @param session Http session object
   * @param modelMap ModelMap object
   * @return bookDetails.jsp
   * @throws SapeStoreException Handling exception
   */
  @RequestMapping(value = "/addReviewController", method = RequestMethod.POST)
  public String addReview(@RequestParam("bookComments") @Valid String comments,
      @RequestParam("rating-input-1") @Valid String rating, HttpSession session, ModelMap modelMap)
          throws SapeStoreException {

    LOGGER.debug("addReviews method: START");
    Book book = (Book) session.getAttribute("book");
    String isbn = book.getIsbn();
    BookRatingCommentVO addReview = new BookRatingCommentVO();
    addReview.setIsbn(isbn);
    addReview.setBookComments(comments);
    addReview.setBookCommentDate(new Date());
    Integer bookRating = Integer.parseInt(rating);
    addReview.setBookRating(bookRating);
    String userId = (String) session.getAttribute("userId");
    addReview.setUserId(userId);
    reviewService.addBookReview(addReview);
    reviewService.addAverageBookRating(bookRating, isbn);
    LOGGER.debug(bookRating.toString());
    LOGGER.debug("addReviews method: END");
    forwardStr = "redirect:/bookDetailsController?isbn=" + isbn;
    return forwardStr;

  }

  /**
   * This is a controller method is for landing on the home page.
   * 
   * @return adminHome.jsp
   */
  @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
  public String getHome() {
    return "AdminHome";
  }

  /**
   * This is a controller method is for landing on index.
   * 
   * @return index.jsp
   */
  @RequestMapping(value = "/bookdetails", method = RequestMethod.GET)
  public String getBookDetailsPage() {
    LOGGER.debug("getBookDetailsPage method: START");
    LOGGER.debug("getBookDetailsPage method: END");
    return "index";
  }
}
