
package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.service.BookService;
import com.sapestore.service.WishListService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.WishListVO;

// TODO: Auto-generated Javadoc
/**
 * This is a controller class for WishList i.e. addition of books to WishList
 * and then to Cart.
 * 
 * @author lborah,pramachandran
 * @version 1.0
 *
 *          CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 02-11-2015 SAPIENT
 *          Initial version
 */

@Controller
public class WishListController {

  /**
   * This is to autowire WishListService.
   */
  @Autowired
  private WishListService wishListService;
  
  /** The book service. */
  @Autowired
  private BookService bookService;

  /**
   * This is for Logger.
   *
   * 
   */
  private static final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(ProductController.class.getName());

  /**
   * This method is for adding books to wish list for the current logged-in
   * user.
   * 
   * @param isbn
   *          - book identifier
   * @param modelMap
   *          - map object for passing information to the controller
   * @param httpServletRequest
   *          - http request object
   * @param httpSession
   *          - session object
   * @return String - list as string
   * @throws SapeStoreException
   *           - SapeStoreException, in case there is issue with database.
   */

  @RequestMapping(value = "/addToWishList", method = RequestMethod.GET)
  public String addToWishList(@RequestParam("isbn") final String isbn,
      final ModelMap modelMap, final HttpServletRequest httpServletRequest,
      final HttpSession httpSession) throws SapeStoreException {

    WishListVO wishListVO = null;
    BasicConfigurator.configure();
    LOGGER.error(isbn);
    String userId = (String) httpSession.getAttribute("userId");
    if (userId == null) {
      return "login";
    } else {
      Book book = wishListService.getBookByISBN(isbn);
      BookVO bookVO = new BookVO();
      bookVO.setActive(book.getIsActive());
      bookVO.setBookAuthor(book.getBookAuthor());
      bookVO.setBookDetailDesc(book.getBookDetailDescription());
      bookVO.setBookPrice(book.getBookPrice().toString());
      bookVO.setBookDetailDesc(book.getBookDetailDescription());
      bookVO.setBookTitle(book.getBookTitle());
      bookVO.setCategoryId(book.getCategoryId().toString());
      bookVO.setCategoryName(book.getCategoryName());
      bookVO.setIsbn(book.getIsbn());
      bookVO.setOldIsbn(book.getIsbn());
      bookVO.setPublisherName(book.getPublisherName());
      bookVO.setQuantity(book.getQuantity());
      bookVO.setRentAvailable(book.getRentAvailability());
      bookVO.setRentPrice(book.getRentPrice().toString());
      bookVO.setThumbPath(book.getBookThumbImage());
      wishListVO = wishListService.bookVOToWishListVO(bookVO, userId);
      wishListService.addToWishList(wishListVO, userId);
      List<WishListVO> arrList = wishListService.getAllWishListVO(userId);
      modelMap.addAttribute("wishListArr", arrList);
      return "WishList";
    }
  }

  /**
   * Display wish list.
   *
   * @param modelMap          - map object for passing information to the controller
   * @param httpServletRequest          - http request object
   * @param httpSession          - session object
   * @return String
   * @throws SapeStoreException           -SapeStoreException, in case there is issue with database.
   */

  @RequestMapping(value = "/displayWishList")
  public String displayWishList(final ModelMap modelMap,
      final HttpServletRequest httpServletRequest,
      final HttpSession httpSession) throws SapeStoreException {

    String userId = (String) httpSession.getAttribute("userId");
    if (userId == null) {
      return "login";
    } else {
      List<WishListVO> arrList = wishListService.getAllWishListVO(userId);

      for (WishListVO wishListVO : arrList) {
        System.out.println(wishListVO);
      }

      httpSession.setAttribute("catList", bookService.getCategoryList());
      modelMap.addAttribute("wishListArr", arrList);
      return "WishList";
    }

  }

  /**
   * Adds the to cart from wish list.
   *
   * @param wishId          - WishList Identifier
   * @param modelMap          - map object for passing information to the controller
   * @param httpServletRequest          - http request object
   * @param httpSession          - session object
   * @return String
   * @throws SapeStoreException - SapeStoreException, in case there is issue with database.
   */

  @RequestMapping(value = "/addToCartFromWishList")
  public final String addToCartFromWishList(
      @RequestParam(value = "wishId") final Integer wishId,
      final ModelMap modelMap, final HttpServletRequest httpServletRequest,
      final HttpSession httpSession) throws SapeStoreException {
    BasicConfigurator.configure();

    WishListVO wishListVO = wishListService.getWishListVO(wishId);
    BookVO bookVO = wishListService.WishListVOToBookVO(wishListVO);
    modelMap.addAttribute("bookitem", bookVO);
    String isbn = bookVO.getIsbn();
    Book book = wishListService.getBookByISBN(isbn);
    Integer categoryId = book.getCategoryId();
    String categoryName = wishListService.getCategoryNameById(categoryId);
    wishListService.deleteFromWishList(wishId);
    return "redirect:/addToShoppingCart?categoryId=" + categoryId
        + "&categoryName=" + categoryName + "&checkMe=false&isbn=" + isbn
        + "&type=Purchase";
  }
}
