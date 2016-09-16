
package com.sapestore.controller;

/**
 * Controller class for handling Rent book to cart.
 * @author nkum85
 */
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.service.BookrentService;

import com.sapestore.vo.BookVO;
import com.sapestore.vo.RentedShoppingCartVO;
import com.sapestore.vo.ShoppingCartVO;
import com.sapestore.vo.UserVO;

// TODO: Auto-generated Javadoc
/**
 * The Class BookRentController.
 */
@Controller
@SessionAttributes(value = { "RentedShoppingCart" })

public class BookRentController {
  
  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(BookRentController.class.getName());

  /** The bookrent service. */
  @Autowired(required = true)
  public BookrentService bookrentService;
  
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

  /**
   * Sets the hibernate template.
   *
   * @param hibernateTemplate the new hibernate template
   */
  public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
    this.hibernateTemplate = hibernateTemplate;
  }

  /**
   * Update rent.
   *
   * @param duration the duration
   * @param cost the cost
   * @param modelMap the model map
   * @param session the session
   * @return the string
   */
  @RequestMapping(value = "/rentDetails", method = RequestMethod.GET)
  public String UpdateRent(@RequestParam("duration") Integer duration,
      @RequestParam("cost") Float cost, ModelMap modelMap,
      HttpSession session) {
    LOGGER.error("duration:" + duration);
    LOGGER.error("cost:" + cost);

    Book book = (Book) session.getAttribute("book");

    modelMap.addAttribute("duration", duration);
    modelMap.addAttribute("cost", cost);
    String mode = "Rent";
    modelMap.addAttribute("mode", mode);
    String forwardStr = null;
    forwardStr = "redirect:/addToRentedShoppingCart?categoryId="
        + book.getCategoryId() + "&categoryName=" + book.getCategoryName()
        + "&checkMe=" + session.getAttribute("checkMe") + "&isbn="
        + book.getIsbn() + "&type=" + mode;

    return forwardStr;

  }

  /**
   * Processes the Add to Cart requests.
   *
   * @param categoryId the category id
   * @param categoryName the category name
   * @param checkMe the check me
   * @param isbn the isbn
   * @param type the type
   * @param duration the duration
   * @param cost the cost
   * @param modelMap the model map
   * @param httpServletRequest the http servlet request
   * @param httpSession the http session
   * @return the string
   * @throws Exception the exception
   */

  @RequestMapping(value = "/addToRentedShoppingCart", method = RequestMethod.GET)
  public String addToShoppingCart(@RequestParam("categoryId") int categoryId,
      @RequestParam("categoryName") String categoryName,
      @RequestParam(value = "checkMe", required = false) boolean checkMe,
      @RequestParam("isbn") String isbn, @RequestParam("type") String type,
      @RequestParam("duration") Integer duration,
      @RequestParam("cost") Float cost, ModelMap modelMap,
      HttpServletRequest httpServletRequest, HttpSession httpSession)
          throws Exception {

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("addToShoppingCart method: START");
    }
    LOGGER.error("duration:" + duration);
    // LOGGER.error("cost:"+cost);
    // List<RentedShoppingCartVO> shoppingCart=null;
    RentedShoppingCartVO shoppingCart = null;
    String forwardStr = null;

    try {

      shoppingCart = (RentedShoppingCartVO) modelMap.get("RentedShoppingCart");

      shoppingCart = bookrentService.addRentedBookToCart(shoppingCart, isbn,
          type, duration, cost);
      modelMap.addAttribute("RentedShoppingCart", shoppingCart);
      modelMap.addAttribute("userlogin", new UserVO());

    } catch (SapeStoreSystemException ex) {
      LOGGER.error("addToShoppingCart method: ERROR: " + ex);
      forwardStr = ApplicationConstants.FAILURE;
    }

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("addToShoppingCart method: END");
    }
    LOGGER.error("duration2:" + duration);
    return "RentCart";

  }

  /**
   * Removes the from cart.
   *
   * @param disbn
   *          the disbn
   * @param dquantity
   *          the dquantity
   * @param dtype
   *          the dtype
   * @param map
   *          the map
   * @param httpSession
   *          the http session
   * @return the string
   * @throws Exception
   *           the exception
   */
  @RequestMapping(value = "/removeFromRentedCart")
  public String removeFromCart(@RequestParam("disbn") String disbn,
      @RequestParam("dquantity") String dquantity,
      @RequestParam("dtype") String dtype, ModelMap map,
      HttpSession httpSession) throws Exception {

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("removeFromCart method: START");
    }
    try {

      RentedShoppingCartVO shoppingCart = null;
      shoppingCart = (RentedShoppingCartVO) map.get("RentedShoppingCart");
      shoppingCart = bookrentService.removeRentedBookFromCart(shoppingCart,
          disbn, dtype);
      map.addAttribute("RentedShoppingCart", shoppingCart);
    } catch (SapeStoreSystemException ex) {
      LOGGER.error("Remove from Cart method: ERROR: " + ex);

    }

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("RemoveFromCart method: END");
    }

    return "";
  }

  /**
   * Clear shopping cart.
   *
   * @param httpSession the http session
   * @param map the map
   * @return the string
   */
  @RequestMapping(value = "/clearRentedShoppingCart", method = RequestMethod.GET)
  public String clearShoppingCart(HttpSession httpSession, ModelMap map) {
    RentedShoppingCartVO cart = (RentedShoppingCartVO) map
        .get("RentedShoppingCart");
    List<BookVO> bookvo = cart.getBooksInCart();
    bookvo.clear();
    cart.setTotalPrice((float) 0);
    cart.setTotalQuantity(0);
    map.addAttribute("RentedShoppingCart", cart);
    return "redirect:/welcome?checkMe=false";

  }

  /**
   * Send mail.
   *
   * @param httpSession the http session
   * @param map the map
   * @return the string
   */
  @RequestMapping(value = "/sendrentmail", method = RequestMethod.GET)
  public String sendMail(HttpSession httpSession, ModelMap map) {
    LOGGER.debug("OrderConfirmationDao:SendMail: START");
    String to = "neel@yopmail.com";
    String msg;
    msg = null;
    String from = "admin@sapient.com";
    String host = "inrelaymail.sapient.com";
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    Session session = Session.getDefaultInstance(properties);
    try {

      msg = "Congrats your order has been successfully placed";
      msg += "\nThanks for shopping with us \n";
      msg += "\n\nThanks a lot for shopping with us."
          + "We have received your request."
          + "You can track the order using your order id";
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("SapeStore: Your order Details");
      message.setText(msg);
      Transport.send(message);
      System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
    LOGGER.debug("OrderConfirmationDao:SendMail: END");

    return "rentconfirmation";
  }

}
