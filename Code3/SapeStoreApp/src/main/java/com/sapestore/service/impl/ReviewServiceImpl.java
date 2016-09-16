package com.sapestore.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ReviewDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.service.ReviewService;
import com.sapestore.vo.BookRatingCommentVO;

// TODO: Auto-generated Javadoc
/**
 * Service class for adding and fetching book reviews.
 * 
 * @author shadab
 * 
 */
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

  /** The Constant LOGGER. */
  private static final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(ReviewServiceImpl.class.getName());

  /** The review dao. */
  @Autowired
  private ReviewDao reviewDao;

  /**
   * Add new review to book.
   *
   * @param reviewVO
   *          the review vo
   * @throws SapeStoreException
   *           the sape store exception
   * @throws SapeStoreSystemException
   *           the sape store system exception
   */
  @Override
  public void addBookReview(BookRatingCommentVO reviewVO) throws SapeStoreException {
    LOGGER.debug("addBookReview method: START");
    if (reviewVO != null) {
      reviewDao.addBookReview(reviewVO);
    }
    LOGGER.debug("addBookReview method: END");
  }

  /**
   * Returns list of reviews of the book.
   *
   * @param isbn
   *          the isbn
   * @return the reviewlist
   * @throws SapeStoreException
   *           the sape store exception
   */
  @Override
  public List<BookRatingComments> getReviewlist(String isbn) throws SapeStoreException {
    LOGGER.debug("getReviewlist method: START");
    List<BookRatingComments> bookReviewBeanList = null;
    bookReviewBeanList = reviewDao.getBookReviews(isbn);
    if (bookReviewBeanList != null && bookReviewBeanList.size() > 0) {
      Collections.sort(bookReviewBeanList, new Comparator<BookRatingComments>() {
        @Override
        public int compare(final BookRatingComments o1, final BookRatingComments o2) {
          return o2.getBookCommentDate().compareTo(o1.getBookCommentDate());
        }
      });
    }
    LOGGER.debug("getReviewlist method: END");
    return bookReviewBeanList;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.sapestore.service.ReviewService#getThreeReviewslist(java.lang.String)
   */
  @Override
  public List<BookRatingComments> getThreeReviewslist(String isbn) throws SapeStoreException {

    LOGGER.debug("getReviewlist method: START");
    List<BookRatingComments> bookReviewBeanList = null;
    List<BookRatingComments> bookThreeReviewsBeanList = new ArrayList<BookRatingComments>();
    bookReviewBeanList = reviewDao.getBookReviews(isbn);
    if (bookReviewBeanList != null && bookReviewBeanList.size() > 0) {
      Collections.sort(bookReviewBeanList, new Comparator<BookRatingComments>() {
        @Override
        public int compare(final BookRatingComments o1, final BookRatingComments o2) {
          return o2.getBookCommentDate().compareTo(o1.getBookCommentDate());
        }
      });
    }
    if (bookReviewBeanList.size() < 3) {
      bookThreeReviewsBeanList.addAll(bookReviewBeanList);
    } else {
      for (int count = 0; count < 3; count++) {

        bookThreeReviewsBeanList.add(bookReviewBeanList.get(count));
      }
    }
    LOGGER.debug("getReviewlist method: END");
    return bookThreeReviewsBeanList;

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.sapestore.service.ReviewService#addAverageBookRating(java.lang.Integer,
   * java.lang.String)
   */
  @Override
  public void addAverageBookRating(Integer bookRating, String isbn) throws SapeStoreException {
    LOGGER.debug("addAverageBookRating method: START");
    if (bookRating != 0) {
      reviewDao.addAverageBookRating(bookRating, isbn);
    }
    LOGGER.debug("addAverageBookRating method: END");
  }

}
