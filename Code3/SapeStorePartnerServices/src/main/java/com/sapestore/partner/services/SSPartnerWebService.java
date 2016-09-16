package com.sapestore.partner.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

/**
 * This is service DAO for partner sape store
 * 
 * @author pramac (Pavithran)
 * @version 1.0
 * 
 *          SERVICE DAO For SapeStore Partner BookStore which does three
 *          functionalities
 * 
 *          getBookList(Integer catId) Gets book list based on category
 *
 *          getSearchBookList(String title, String author, String isbn, String
 *          publisher, Integer catId) Gets book list based on category id and
 *          other search fields
 * 
 *          getSearchBookListwithoutID(String title, String author, String isbn,
 *          String publisher) Gets book list based on search fields without
 *          category id
 */

@Service
@Transactional
public class SSPartnerWebService {

  private Logger LOGGER = Logger.getLogger(SSPartnerWebService.class.getName());
  // JDBC driver name and database URL
  static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
  static final String DB_URL = "jdbc:oracle:thin:@bangvmpllcai02.sapient.com:1521:caidb2";

  // Database credentials
  static final String USER = "BLR_SAPESTORE_BATCH_B";
  static final String PASS = "BLR_SAPESTORE_BATCH_B";

  /**
   * This is for getting book by category id
   * 
   * @author pramac
   * @version 1.
   */
  public List<SSPartnerBooksListBean> getBookList(Integer catId) {
    List<SSPartnerBooksListBean> finalList = new ArrayList<SSPartnerBooksListBean>();
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      // STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 4: Execute a query
      System.out.println("Creating statement.....");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT * FROM SAPESTORE_BOOKS WHERE CATEGORY_ID=" + catId
          + " AND IS_FROM_PARTNER_STORE='Y'";
      ResultSet rs = stmt.executeQuery(sql);

      // STEP 5: Extract data from result set
      while (rs.next()) {
        // Retrieve by column name

        SSPartnerBooksListBean transDO = new SSPartnerBooksListBean();
        transDO.setIsbn(rs.getString("ISBN"));
        transDO.setPublisherName(rs.getString("PUBLISHER_NAME"));
        transDO.setCategoryIdpr(rs.getInt("CATEGORY_ID"));
        transDO.setBookTitle(rs.getString("BOOK_TITLE"));
        transDO.setQuantity(rs.getInt("QUANTITY"));
        transDO.setBookAuthor(rs.getString("BOOK_AUTHOR"));
        transDO.setThumbImageUrl(rs.getString("BOOK_THUMB_IMAGE"));
        transDO.setFullImageUrl(rs.getString("BOOK_FULL_IMAGE"));
        transDO.setBookPrice(rs.getInt("BOOK_PRICE"));
        transDO.setBookShortDesc(rs.getString("BOOK_SHORT_DESCRIPTION"));
        transDO.setBookDetailDesc(rs.getString("BOOK_DETAIL_DESCRIPTION"));
        transDO.setActive(rs.getString("IS_ACTIVE"));
        finalList.add(transDO);
      }
      // STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException se) {
      LOGGER.debug("SQLException in partner services is " + se);
      // Handle errors for JDBC
      se.printStackTrace();
    } catch (Exception e) {
      // Handle errors for Class.forName
      LOGGER.debug("SQLException in partner services is " + e);
      e.printStackTrace();
    } finally {
      // finally block used to close resources
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se2) {
        LOGGER.error("SQL is not opened ");
      } // nothing we can do
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      } // end finally try
    } // end try
    return finalList;
  }// end main here

  /**
   * This is for searching book by title, author, isbn, publisher name and
   * category id
   * 
   * @author pramac
   * @version 1.
   */
  public List<SSPartnerBooksListBean> getSearchBookList(String title,
      String author, String isbn, String publisher, Integer catId) {
    List<SSPartnerBooksListBean> finalList = new ArrayList<SSPartnerBooksListBean>();
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      // STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 4: Execute a query
      System.out.println("Creating statement.....");
      stmt = conn.createStatement();
      String sql;

      sql = "SELECT * FROM SAPESTORE_BOOKS WHERE UPPER(BOOK_TITLE) LIKE '%"
          + title.toUpperCase() + "%' AND UPPER(BOOK_AUTHOR) LIKE '%"
          + author.toUpperCase() + "%' AND ISBN LIKE '%" + isbn
          + "%' AND UPPER(PUBLISHER_NAME) LIKE '%" + publisher.toUpperCase()
          + "%' AND CATEGORY_ID LIKE '%" + catId
          + "%' AND IS_FROM_PARTNER_STORE = 'Y' ";
      System.out.println(sql);
      ResultSet rs = stmt.executeQuery(sql);

      // STEP 5: Extract data from result set
      while (rs.next()) {
        // Retrieve by column name

        SSPartnerBooksListBean transDO = new SSPartnerBooksListBean();
        transDO.setIsbn(rs.getString("ISBN"));
        transDO.setPublisherName(rs.getString("PUBLISHER_NAME"));
        transDO.setCategoryIdpr(rs.getInt("CATEGORY_ID"));
        transDO.setBookTitle(rs.getString("BOOK_TITLE"));
        transDO.setQuantity(rs.getInt("QUANTITY"));
        transDO.setBookAuthor(rs.getString("BOOK_AUTHOR"));
        transDO.setThumbImageUrl(rs.getString("BOOK_THUMB_IMAGE"));
        transDO.setFullImageUrl(rs.getString("BOOK_FULL_IMAGE"));
        transDO.setBookPrice(rs.getInt("BOOK_PRICE"));
        transDO.setBookShortDesc(rs.getString("BOOK_SHORT_DESCRIPTION"));
        transDO.setBookDetailDesc(rs.getString("BOOK_DETAIL_DESCRIPTION"));
        transDO.setActive(rs.getString("IS_ACTIVE"));
        finalList.add(transDO);
      }
      // STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException se) {
      LOGGER.debug("SQLException in partner services is " + se);
      // Handle errors for JDBC
      se.printStackTrace();
    } catch (Exception e) {
      // Handle errors for Class.forName
      LOGGER.debug("SQLException in partner services is " + e);
      e.printStackTrace();
    } finally {
      // finally block used to close resources
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se2) {
        LOGGER.error("SQL is not opened ");
      } // nothing we can do
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      } // end finally try
    } // end try
    return finalList;
  }// end main here

  /**
   * This is for searching book by title, author, isbn and publisher name
   * 
   * @author pramac
   * @version 1.
   */
  public List<SSPartnerBooksListBean> getSearchBookListwithoutID(String title,
      String author, String isbn, String publisher) {
    List<SSPartnerBooksListBean> finalList = new ArrayList<SSPartnerBooksListBean>();
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      // STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 4: Execute a query
      System.out.println("Creating statement.....");
      stmt = conn.createStatement();
      String sql;

      sql = "SELECT * FROM SAPESTORE_BOOKS WHERE UPPER(BOOK_TITLE) LIKE '%"
          + title.toUpperCase() + "%' AND UPPER(BOOK_AUTHOR) LIKE '%"
          + author.toUpperCase() + "%' AND ISBN LIKE '%" + isbn
          + "%' AND UPPER(PUBLISHER_NAME) LIKE '%" + publisher.toUpperCase()
          + "%' AND IS_FROM_PARTNER_STORE = 'Y' ";
      System.out.println(sql);
      ResultSet rs = stmt.executeQuery(sql);

      // STEP 5: Extract data from result set
      while (rs.next()) {
        // Retrieve by column name

        SSPartnerBooksListBean transDO = new SSPartnerBooksListBean();
        transDO.setIsbn(rs.getString("ISBN"));
        transDO.setPublisherName(rs.getString("PUBLISHER_NAME"));
        transDO.setCategoryIdpr(rs.getInt("CATEGORY_ID"));
        transDO.setBookTitle(rs.getString("BOOK_TITLE"));
        transDO.setQuantity(rs.getInt("QUANTITY"));
        transDO.setBookAuthor(rs.getString("BOOK_AUTHOR"));
        transDO.setThumbImageUrl(rs.getString("BOOK_THUMB_IMAGE"));
        transDO.setFullImageUrl(rs.getString("BOOK_FULL_IMAGE"));
        transDO.setBookPrice(rs.getInt("BOOK_PRICE"));
        transDO.setBookShortDesc(rs.getString("BOOK_SHORT_DESCRIPTION"));
        transDO.setBookDetailDesc(rs.getString("BOOK_DETAIL_DESCRIPTION"));
        transDO.setActive(rs.getString("IS_ACTIVE"));
        finalList.add(transDO);
      }
      // STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException se) {
      LOGGER.debug("SQLException in partner services is " + se);
      // Handle errors for JDBC
      se.printStackTrace();
    } catch (Exception e) {
      // Handle errors for Class.forName
      LOGGER.debug("SQLException in partner services is " + e);
      e.printStackTrace();
    } finally {
      // finally block used to close resources
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se2) {
        LOGGER.error("SQL is not opened ");
      } // nothing we can do
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      } // end finally try
    } // end try

    for (SSPartnerBooksListBean ssPartnerBooksListBean : finalList) {
      System.out.println(ssPartnerBooksListBean);
    }

    return finalList;
  }// end main here

}