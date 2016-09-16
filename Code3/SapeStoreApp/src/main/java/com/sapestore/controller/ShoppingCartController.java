
package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.service.BookService;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.ShoppingCartVO;
import com.sapestore.vo.UserVO;

// TODO: Auto-generated Javadoc
/**
 * This is a controller class for handling Shopping cart i.e. addition and
 * removal of books in cart CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.1
 * 23-11-2015 Added functionalities
 * 
 * @author ngupta79
 * @author cgoyal
 * @author ntrip6
 */

@Controller
@SessionAttributes("ShoppingCart")
public class ShoppingCartController {

  /** The Constant LOGGER. */
  private  static final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(ShoppingCartController.class.getName());

  /** The shopping cart service. */
  @Autowired(required = true)
  public ShoppingCartService shoppingCartService;
  
  /** The hibernate template. */
  @Autowired
  private HibernateTemplate hibernateTemplate;

  /**
   * Gets the hibernate template.
   *
   * @return the hibernate template
   */
  public HibernateTemplate getHibernateTemplate() {
    return hibernateTemplate;
  }
  
  @Autowired
  private BookService bookService;

  /**
   * Sets the hibernate template.
   *
   * @param hibernateTemplate the new hibernate template
   */
  public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
    this.hibernateTemplate = hibernateTemplate;
  }

  /**
   * This is a method for addition of books in cart CHANGE LOG VERSION DATE
   * AUTHOR MESSAGE 1.1 23-10-2015 Added parameter type
   * @param categoryId the category id
   * @param categoryName the category name
   * @param checkMe the check me
   * @param isbn the isbn
   * @param type the type
   * @param modelMap the model map
   * @param httpServletRequest the http servlet request
   * @param httpSession the http session
   * @return the string
   * @throws Exception the exception
   */
  @RequestMapping(value = "/addToShoppingCart", method = RequestMethod.GET)
  public String addToShoppingCart(@RequestParam("categoryId") int categoryId,
      @RequestParam("categoryName") String categoryName,
      @RequestParam(value = "checkMe", required = false) boolean checkMe,
      @RequestParam("isbn") String isbn, @RequestParam("type") String type,
      ModelMap modelMap, HttpServletRequest httpServletRequest,
      HttpSession httpSession) throws Exception {

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("addToShoppingCart method: START");
    }

    ShoppingCartVO shoppingCart = null;
    String forwardStr = null;

    try {

      shoppingCart = (ShoppingCartVO) modelMap.get("ShoppingCart");

      shoppingCart = shoppingCartService.addBookToCart(shoppingCart, isbn,
          type);
      modelMap.addAttribute("ShoppingCart", shoppingCart);
      modelMap.addAttribute("userlogin", new UserVO());
      if (categoryName.equalsIgnoreCase("Top Rated")) {
        forwardStr = "redirect:/bookListByCat?categoryId=0&categoryName=Top Rated&checkMe="
            + httpSession.getAttribute("checkMe");
      } else {
        forwardStr = "redirect:/bookListByCat?categoryId=" + categoryId
            + "&categoryName=" + categoryName + "&checkMe="
            + httpSession.getAttribute("checkMe");
      }
    } catch (SapeStoreSystemException ex) {
      LOGGER.error("addToShoppingCart method: ERROR: " + ex);
      forwardStr = ApplicationConstants.FAILURE;
    }

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("addToShoppingCart method: END");
    }

    return forwardStr;

  }

  /**
   * This is a method for Remove from Cart requests. CHANGE LOG VERSION DATE
   * AUTHOR MESSAGE 1.0 24-10-2015 Added this method
   * @param disbn the disbn
   * @param dquantity the dquantity
   * @param dtype the dtype
   * @param map the map
   * @param httpSession the http session
   * @return the string
   * @throws Exception the exception
   */

  @RequestMapping(value = "/removeFromCart")
  public String removeFromCart(@RequestParam("disbn") String disbn,
      @RequestParam("dquantity") String dquantity,
      @RequestParam("dtype") String dtype, ModelMap map,
      HttpSession httpSession) throws Exception {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("removeFromCart method: START");
    }
    try {

      ShoppingCartVO shoppingCart = null;
      shoppingCart = (ShoppingCartVO) map.get("ShoppingCart");
      shoppingCart = shoppingCartService.removeFromCart(shoppingCart, disbn,
          dtype);
      map.addAttribute("ShoppingCart", shoppingCart);
    } catch (SapeStoreSystemException ex) {
      LOGGER.error("Remove from Cart method: ERROR: " + ex);

    }

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("RemoveFromCart method: END");
    }

    return "";
  }

  /**
   * This is a method for Update Cart requests. CHANGE LOG VERSION DATE AUTHOR
   * MESSAGE 1.0 24-10-2015 Added this method
   * @param uisbn the uisbn
   * @param uquantity the uquantity
   * @param utype the utype
   * @param oldtype the oldtype
   * @param httpSession the http session
   * @param map the map
   * @return the string
   */
  @RequestMapping(value = "/updateController", method = RequestMethod.GET)
  public String update(@RequestParam("uisbn") String uisbn,
      @RequestParam("uquantity") String uquantity,
      @RequestParam("utype") String utype,
      @RequestParam("oldtype") String oldtype, HttpSession httpSession,
      ModelMap map) {
    ShoppingCartVO shoppingCart = null;
    shoppingCart = (ShoppingCartVO) map.get("ShoppingCart");
    shoppingCart = shoppingCartService.updateCart(shoppingCart, uisbn, utype,
        uquantity, oldtype);
    map.addAttribute("ShoppingCart", shoppingCart);
    return "";

  }

  /**
   * Processes the Clear Cart requests.
   *
   * @param httpSession the http session
   * @param map the map
   * @return the string
   */
  @RequestMapping(value = "/clearShoppingCart", method = RequestMethod.GET)
  public String clearShoppingCart(HttpSession httpSession, ModelMap map) {
    ShoppingCartVO cart = (ShoppingCartVO) map.get("ShoppingCart");
    List<BookVO> bookvo = cart.getBooksInCart();
    bookvo.clear();
    cart.setTotalPrice(0);
    cart.setTotalQuantity(0);
    map.addAttribute("ShoppingCart", cart);
    return "redirect:/welcome?checkMe=false";

  }
  
  @RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
  public String shoppingCart(ModelMap modelMap, HttpServletRequest httpServletRequest,
      HttpSession httpSession) throws SapeStoreException {

   
    
    	httpSession.setAttribute("catList", bookService.getCategoryList());
    	return "ShoppingCart";
    
   
  }

}
