package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.service.OrderService;
import com.sapestore.service.OrderTrackService;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderTrackController.
 */
@Controller
public class OrderTrackController {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(OrderTrackController.class.getName());

  /** The ordertrackservice. */
  @Autowired
  OrderTrackService ordertrackservice;

  /** The order service. */
  @Autowired
  OrderService orderService;

  /**
   * Order track.
   *
   * @param orderId the order id
   * @param map the map
   * @param session the session
   * @return the string
   */
  @RequestMapping(value = "/ordertrack", method = RequestMethod.GET)
  public String orderTrack(@RequestParam("orderId") String orderId,
      ModelMap map, HttpSession session) {
    BasicConfigurator.configure();
    LOGGER.debug(" OrderTrackController.orderTrack method: START ");
    OrderInfo orderinfo = null;
    OrderItemInfo orderItemInfo = null;
    List<OrderItemInfo> orderItemList = null;
    String userId = (String) session.getAttribute("userId");
    try {
      int orderId1 = Integer.parseInt(orderId);
      orderinfo = ordertrackservice.orderTracking(orderId1, userId);
      orderItemInfo = ordertrackservice.orderRentPurchase(orderId1);
      orderItemList = orderService.getOrderItemDetailByUserId(userId);
    } catch (Exception e) {
      e.printStackTrace();
    }

    map.addAttribute("orderinfo", orderinfo);
    map.addAttribute("orderItemInfo", orderItemInfo);
    map.addAttribute("orderItemList", orderItemList);

    return "orderTrackingReport";
  }
}
