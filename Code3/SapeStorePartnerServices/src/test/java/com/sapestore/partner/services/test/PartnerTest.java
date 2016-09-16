package com.sapestore.partner.services.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sapestore.partner.services.SSPartnerBooksListBean;
import com.sapestore.partner.services.SSPartnerWebService;

/**
 * This is test for partner sape store
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

public class PartnerTest {

  SSPartnerWebService ssPartnerWebService;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    ssPartnerWebService = new SSPartnerWebService();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testGetBookList() {
    List<SSPartnerBooksListBean> bookList = new ArrayList<SSPartnerBooksListBean>();
    bookList = ssPartnerWebService.getBookList(3);
    boolean check = true;

    for (SSPartnerBooksListBean ssPartnerBooksListBean : bookList) {
      if (ssPartnerBooksListBean.getCategoryIdpr() != 3) {
        check = false;
        break;
      }
    }
    assertTrue(check);
  }

  @Test
  public void testGetSearchBookList() {
    List<SSPartnerBooksListBean> bookList = new ArrayList<SSPartnerBooksListBean>();
    bookList = ssPartnerWebService.getSearchBookList("", "Dan Brown", "", "",
        7);
    boolean check = false;

    for (SSPartnerBooksListBean ssPartnerBooksListBean : bookList) {
      if (ssPartnerBooksListBean.getBookAuthor().equals("Dan Brown")) {
        check = true;
        break;
      }
    }
    assertTrue(check);
  }

  @Test
  public void testGetSearchBookListwithoutID() {
    List<SSPartnerBooksListBean> bookList = new ArrayList<SSPartnerBooksListBean>();
    bookList = ssPartnerWebService.getSearchBookListwithoutID("", "Dan Brown",
        "", "");
    boolean check = false;

    for (SSPartnerBooksListBean ssPartnerBooksListBean : bookList) {
      if (ssPartnerBooksListBean.getBookAuthor().equals("Dan Brown")) {
        check = true;
        break;
      }
    }
    assertTrue(check);
  }

}
