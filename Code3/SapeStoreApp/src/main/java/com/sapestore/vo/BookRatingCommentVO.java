
package com.sapestore.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

// TODO: Auto-generated Javadoc
/**
 * Bean class for adding a review and comment.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
public class BookRatingCommentVO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The comment id. */
  @NotNull
  private Integer commentId;

  /** The isbn. */
  @NotEmpty
  private String isbn;

  /** The user id. */
  @NotEmpty
  private String userId;

  /** The book comments. */
  @NotEmpty
  private String bookComments;

  /** The book comment date. */
  private Date bookCommentDate;

  /** The book rating. */
  @NotNull
  private Integer bookRating;

  /**
   * Gets the comment id.
   *
   * @return the comment id
   */
  public Integer getCommentId() {
    return commentId;
  }

  /**
   * Sets the comment id.
   *
   * @param commentId the new comment id
   */
  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  /**
   * Gets the isbn.
   *
   * @return the isbn
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * Sets the isbn.
   *
   * @param isbn the new isbn
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * Gets the user id.
   *
   * @return the user id
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Sets the user id.
   *
   * @param userId the new user id
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * Gets the book comments.
   *
   * @return the book comments
   */
  public String getBookComments() {
    return bookComments;
  }

  /**
   * Sets the book comments.
   *
   * @param bookComments the new book comments
   */
  public void setBookComments(String bookComments) {
    this.bookComments = bookComments;
  }

  /**
   * Gets the book comment date.
   *
   * @return the book comment date
   */
  public Date getBookCommentDate() {
    return bookCommentDate;
  }

  /**
   * Sets the book comment date.
   *
   * @param bookCommentDate the new book comment date
   */
  public void setBookCommentDate(Date bookCommentDate) {
    this.bookCommentDate = bookCommentDate;
  }

  /**
   * Gets the book rating.
   *
   * @return the book rating
   */
  public Integer getBookRating() {
    return bookRating;
  }

  /**
   * Sets the book rating.
   *
   * @param bookRating the new book rating
   */
  public void setBookRating(Integer bookRating) {
    this.bookRating = bookRating;
  }

}
