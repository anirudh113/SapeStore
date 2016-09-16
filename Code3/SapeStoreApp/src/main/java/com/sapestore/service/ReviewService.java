package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.vo.BookRatingCommentVO;

// TODO: Auto-generated Javadoc
/**
 * Service interface for rating and comment functionality.
 * 
 * @author shadab
 * 
 */
public interface ReviewService {

  /**
   * Performs adding reviews.
   *
   * @param reviewVO the review vo
   * @throws SapeStoreException           Handling exception
   */
  public void addBookReview(BookRatingCommentVO reviewVO) throws SapeStoreException;

  /**
   * Performs adding AVERAGE rating.
   *
   * @param bookRating the book rating
   * @param isbn the isbn
   * @throws SapeStoreException           Handling exception
   */
  public void addAverageBookRating(Integer bookRating, String isbn) throws SapeStoreException;

  /**
   * Returns list of reviews of the book.
   *
   * @param isbn the isbn
   * @return ReviewList
   * @throws SapeStoreException           Handling exception
   */
  public List<BookRatingComments> getReviewlist(String isbn) throws SapeStoreException;

  /**
   * Returns list of three reviews of the book.
   *
   * @param isbn the isbn
   * @return threeReviewList
   * @throws SapeStoreException           Handling exception
   */
  public List<BookRatingComments> getThreeReviewslist(String isbn) throws SapeStoreException;
}
