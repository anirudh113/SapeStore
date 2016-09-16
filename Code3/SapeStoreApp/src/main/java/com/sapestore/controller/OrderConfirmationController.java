package com.sapestore.controller;

import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.OrderConfirmationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * The Class OrderConfirmationController.
 */
@Controller
public class OrderConfirmationController {

  /** The orderconfirmation service object. */
  @Autowired
  OrderConfirmationService orderconfirmation;

  /**
   * Order Confirmation controller which calls the order confirmation service.
   * 
   * @author vsaara
   * @author unatar
   * @param username
   *          The existing username or the changed one (as per the users input).
   * @param map
   *          The model map object
   * @param session
   *          The HTTP session object
   * @return the string
   */
  @RequestMapping(value = "/showDetails", method = RequestMethod.GET)
  public String orderConfirmation(@RequestParam("newuser") String username,
      ModelMap map, HttpSession session) {
    User user = (User) orderconfirmation
        .getCustomerDetails(session.getAttribute("userId").toString());
    Address address = (Address) orderconfirmation
        .getCustomerAddress(session.getAttribute("userId").toString());
    int cityId = address.getCityId();
    City city = (City) orderconfirmation.getCustomerCity(cityId);
    int stateId = city.getStateId();
    List<OrderItemInfo> orderitems = (List<OrderItemInfo>) orderconfirmation
        .getorderItemDetails(
            Integer.parseInt(session.getAttribute("orderId").toString()));
    OrderInfo orderlist = orderconfirmation.getOrdersInfo(
        Integer.parseInt(session.getAttribute("orderId").toString()));
    orderconfirmation.sendMail(username, user.getEmailAddress(), orderlist,
        orderitems);
    State state = (State) orderconfirmation.getCustomerState(stateId);
    map.addAttribute("user", user);
    map.addAttribute("address", address);
    map.addAttribute("city", city);
    map.addAttribute("state", state);
    map.addAttribute("userName", username);
    map.addAttribute("orderitems", orderitems);
    map.addAttribute("orderlist", orderlist);

    return "OrderConfirmation";
  }

}
