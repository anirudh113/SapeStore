package com.sapestore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.OrderDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.service.OrderService;
import com.sapestore.vo.DispatchSlip;
import com.sapestore.vo.RentedUpdate;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderController.
 *
 * @author Siddharth
 * @author Afreen
 * @author Vipul Garg
 * @author Dhina
 */
@Controller
@SessionAttributes("dispatchList")
public class OrderController {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = 
      SapeStoreLogger.getLogger(OrderController.class.getName());

  /** The order dao. */
  @Autowired
  private OrderDao orderDao;

  /** The order service. */
  @Autowired
  private OrderService orderService;

  /** The dispatch slip beans. */
  private List<DispatchSlip> dispatchSlipBeans;

  /**
   * Gets the dispatch slip beans.
   *
   * @return the dispatch slip beans
   */
  public List<DispatchSlip> getDispatchSlipBeans() {
    return dispatchSlipBeans;
  }

  /**
   * Sets the dispatch slip beans.
   *
   * @param dispatchSlipBeans the new dispatch slip beans
   */
  public void setDispatchSlipBeans(List<DispatchSlip> dispatchSlipBeans) {
    this.dispatchSlipBeans = dispatchSlipBeans;
  }

  /** The rented updates. */
  private static List<RentedUpdate> rentedUpdates = new ArrayList<RentedUpdate>();

  /**
   * Redirects to manage orders page.
   *
   * @param modelMap the model map
   * @return ManageOrders.jsp
   */
  @RequestMapping(value = "/manageOrders", method = RequestMethod.GET)
  public String manageOrders(ModelMap modelMap) {
    LOGGER.debug(" OrderController.manageOrders method: START ");
    return "ManageOrders";
  }

  /**
   * Gets rented orders from database.
   *
   * @param modelMap the model map
   * @return RentedOrders.jsp
   * @throws Exception the exception
   */
  @RequestMapping(value = "/rentedOrders", method = RequestMethod.GET)
  public String getRentedOrders(ModelMap modelMap) throws Exception {
    LOGGER.debug(" OrderController.getRentedOrders method: START ");
    modelMap.addAttribute("rentedOrdersList", orderDao.getRentedOrders());
    LOGGER.debug(" OrderController.getRentedOrders method: END ");
    return "RentedOrders";

  }

  /**
   * Gets purchased orders from database.
   *
   * @param modelMap the model map
   * @return PurchasedOrders.jsp
   * @throws Exception the exception
   */
  @RequestMapping(value = "/purchasedOrders", method = RequestMethod.GET)
  public String getPurchasedOrders(ModelMap modelMap) throws Exception {
    LOGGER.debug(" OrderController.getPurchasedOrders method: START ");
    modelMap.addAttribute("purchasedOrdersList", orderDao.getPurchasedOrders());
    LOGGER.debug(" OrderController.getPurchasedOrders method: END ");
    return "PurchasedOrders";

  }

  /**
   * Processes the rent updation requests.
   *
   * @param orderString the order string
   * @param modelMap the model map
   * @return redirect:/dispatchSlip
   * @throws Exception the exception
   */
  @RequestMapping(value = "/updateRentDispatch", method = RequestMethod.GET)
  public String dispatchOrder(@ModelAttribute("orderList") String orderString,
      ModelMap modelMap) throws Exception {
    BasicConfigurator.configure();
    List<Integer> dispatchedOrderItemIds = new ArrayList<>();
    String[] orderStrArr = orderString.split("\\$");
    for (int i = 1; i < orderStrArr.length; i++) {
      dispatchedOrderItemIds.add(Integer.parseInt(orderStrArr[i]));
    }

    List<Integer> dispatchedOrders = orderService.updateDispatch(dispatchedOrderItemIds);

    modelMap.addAttribute("dispatchList", dispatchedOrders);
    return "redirect:/dispatchSlip";

  }

  /**
   * Processes the requests for Dispatch Slip.
   *
   * @param dispatchList the dispatch list
   * @param modelMap the model map
   * @return DispatchResult.jsp
   * @throws SapeStoreException the sape store exception
   */
  @RequestMapping(value = "/dispatchSlip", method = RequestMethod.GET)
  public String dispatchSlip(@ModelAttribute("dispatchList") List<Integer> dispatchList, 
      ModelMap modelMap)
      throws SapeStoreException {
    LOGGER.debug("dispatchSlip method: START");
    List<Integer> list = dispatchList;
    List<DispatchSlip> dispatchSlips = null;

    try {
      dispatchSlips = orderService.getDispatchedOrders(list);
    } catch (SapeStoreSystemException e) {
      LOGGER.error("dispatchSlip method: ERROR: " + e);
      return ApplicationConstants.FAILURE;
    }

    modelMap.addAttribute("dispatchSlips", dispatchSlips);
    this.setDispatchSlipBeans(dispatchSlips);

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("dispatchSlip method: END");
    }
    return "DispatchResult";
  }

  /**
   * Gets ReturnTrack.jsp to enter orderId
   *
   * @param modelMap the model map
   * @return ReturnTrack.jsp
   */
  @RequestMapping(value = "/trackReturn", method = RequestMethod.GET)
  public String getReturnStatus(ModelMap modelMap) {
    modelMap.addAttribute("dispatch", true);
    modelMap.addAttribute("canupdate", true);
    return "ReturnTrack";

  }

  /**
   * Updates return information.
   *
   * @param orderId the order id
   * @param modelMap the model map
   * @return ManageOrders.jsp on success
   */
  @RequestMapping(value = "/updateTrack", method = RequestMethod.POST)
  public String updateReturnStatus(@RequestParam("orderId") Integer orderId, ModelMap modelMap) {

    List<Boolean> checks = null;

    try {
      checks = orderService.updateReturnOrder(orderId);

    } catch (SapeStoreException e) {
      LOGGER.error("uodateReturnStatus method: ERROR: " + e);
      e.printStackTrace();
    }
    if (checks.get(1) == true && checks.get(0) == true) {
      orderService.updateReturnCustomer(orderId);
      return "ManageOrders";
    } else {
      modelMap.addAttribute("dispatch", checks.get(0));
      modelMap.addAttribute("canupdate", checks.get(1));
      return "ReturnTrack";
    }

  }

  /**
   * Gets PaymentTrack.jsp to enter orderId
   *
   * @param modelMap the model map
   * @return PaymentTrack.jsp
   */
  @RequestMapping(value = "/trackPayment", method = RequestMethod.GET)
  public String getPaymentStatus(ModelMap modelMap) {

    modelMap.addAttribute("dispatch", true);
    modelMap.addAttribute("canupdate", true);

    return "PaymentTrack";
  }

  /**
   * Updates payment information.
   *
   * @param orderId the order id
   * @param modelMap the model map
   * @return ManageOrders.jsp on success
   */
  @RequestMapping(value = "/updatePayment", method = RequestMethod.POST)
  public String updatePaymentStatus(@RequestParam("orderId") Integer orderId, ModelMap modelMap) {

    List<Boolean> checks = null;
    try {
      checks = orderService.updatePaymentOrder(orderId);
    } catch (SapeStoreException e) {
      LOGGER.error("updatePaymentStatus method: ERROR: " + e);
      e.printStackTrace();
    }
    if (checks.get(1) == true && checks.get(0) == true) {
      orderService.updateCustomer(orderId);
      return "ManageOrders";
    } else {
      modelMap.addAttribute("dispatch", checks.get(0));
      modelMap.addAttribute("canupdate", checks.get(1));

      return "PaymentTrack";
    }

  }

  /**
   * Get transaction history.
   *
   * @param modelMap the model map
   * @param httpServletRequest the http servlet request
   * @param httpSession the http session
   * @return TransactionHistory.jsp
   * @throws SapeStoreException the sape store exception
   */
  @RequestMapping(value = "/transactionHistory", method = RequestMethod.GET)
  public String showTrasactionPage(ModelMap modelMap, HttpServletRequest httpServletRequest,
      HttpSession httpSession)
      throws SapeStoreException {

    String userId = (String) httpSession.getAttribute("userId");
    LOGGER.error("User ID:" + userId);
    List<OrderItemInfo> userOrderList = orderService.getOrderItemDetailByUserId(userId);
    modelMap.addAttribute("userOrderList", userOrderList);
    return "TransactionHistory";
  }

}
