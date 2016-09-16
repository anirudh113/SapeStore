package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.vo.BookRatingCommentVO;

// TODO: Auto-generated Javadoc
/**
 * DAO class for adding the book's review to the database.
 * 
 * @author shadab
 * @version 1.0
 */
@Repository
public class ReviewDao {

  /** The hibernate template. */
  @Autowired
  private HibernateTemplate hibernateTemplate;

  private static final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(ReviewDao.class.getName());

  /**
   * Method to add a new book to the database.
   *
   * @param reviewVO
   *          the review vo
   * @throws SapeStoreException
   *           the sape store exception
   */
  public void addBookReview(BookRatingCommentVO reviewVO)
      throws SapeStoreException {
    LOGGER.debug(" ReviewDao.addBookReview method: START");

    try {
      BookRatingComments bookRatingComment = new BookRatingComments();
      bookRatingComment.setIsbn(reviewVO.getIsbn());
      bookRatingComment.setUserId(reviewVO.getUserId());
      bookRatingComment.setBookRating(reviewVO.getBookRating());
      bookRatingComment.setBookComments(reviewVO.getBookComments());
      bookRatingComment.setBookCommentDate(reviewVO.getBookCommentDate());
      hibernateTemplate.save(bookRatingComment);
    } catch (SapeStoreSystemException se) {
      LOGGER.fatal("A DB exception occured while inserting the book's review",
          se);
    }
    LOGGER.debug(" ReviewDao.addBookReview method: END ");
  }

  /**
   * Method to get book review from the database.
   *
   * @param isbn
   *          the isbn
   * @return ReviewVo List
   * @throws SapeStoreException
   *           the sape store exception
   */
  @SuppressWarnings("unchecked")
  public List<BookRatingComments> getBookReviews(String isbn)
      throws SapeStoreException {
    LOGGER.debug(" ReviewDao.getBookReviews method: START");
    List<BookRatingComments> reviewVoList = null;

    try {
      reviewVoList = (List<BookRatingComments>) hibernateTemplate
          .findByNamedQueryAndNamedParam("BookRatingComments.findByIsbn",
              "isbn", isbn);
    } catch (SapeStoreSystemException se) {
      LOGGER.fatal("A DB exception occured while retriving the book's review",
          se);
    }
    return reviewVoList;
  }

  /**
   * Method to add a average book rating to the database.
   *
   * @param bookRating
   *          the book rating
   * @param isbn
   *          the isbn
   * @throws SapeStoreException
   *           the sape store exception
   */
  public void addAverageBookRating(Integer bookRating, String isbn)
      throws SapeStoreException {
    LOGGER.debug(" ReviewDao.addAverageBookRating method: START");
    List<BookRatingComments> bookRatingList = null;
    bookRatingList = getBookReviews(isbn);
    Integer averageRating = 0;
    Integer sumRating = 0;
    int countRating = 0;
    for (BookRatingComments ratingList : bookRatingList) {
      sumRating += ratingList.getBookRating();
      countRating++;
    }
    try {

      if (countRating != 0) {
        Book book = hibernateTemplate.get(Book.class, isbn);
        averageRating = Math.round(sumRating / countRating);
        book.setAverageRating(new BigDecimal(averageRating));
        hibernateTemplate.update(book);
      }

    } catch (SapeStoreSystemException se) {
      LOGGER.fatal(
          "A DB exception occured while inserting the book's average rating",
          se);
    }
    LOGGER.debug("ReviewDao.addAverageBookRating method: END ");
  }

}
