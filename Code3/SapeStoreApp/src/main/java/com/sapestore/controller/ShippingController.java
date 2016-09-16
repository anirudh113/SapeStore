package com.sapestore.controller;

import com.sapestore.dao.ShippingAddressDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.OrderConfirmationService;
import com.sapestore.service.ShippingAddressService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.ShippingAddressVo;

import com.sapestore.vo.ShoppingCartVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

// TODO: Auto-generated Javadoc
/**
 * The Class ShippingController.
 */
@Controller
@SessionAttributes(value = { "orderId" })
public class ShippingController {

	/** The shipping address. */
	@Autowired
	ShippingAddressService shippingAddress;

	/** The orderconfirmation. */
	@Autowired
	OrderConfirmationService orderconfirmation;

	/** The shipping dao. */
	@Autowired
	private ShippingAddressDao shippingDao;

	/**
	 * Shipping address.Intializing all the attribute .
	 * 
	 * @param map
	 *            ModelMap
	 * @param session
	 *            the session
	 * @return shippingAddress
	 */
	@RequestMapping(value = "/shippingAddress", method = RequestMethod.GET)
	public String shippingAddress(ModelMap map, HttpSession session) {
		// String userId=(String)session.getAttribute("userId");
		User user = shippingAddress.getCustomerDetails(session.getAttribute("userId").toString());
		Address address = getAddress(user.getUserId(), session);
		List<State> state = getStateList();
		map.addAttribute("stateList", state);
		// map.addAttribute("cityList",city);
		map.addAttribute("user", user);
		map.addAttribute("customeraddress", address);
		return "shippingAddress";
	}

	/**
	 * Gets the state list.
	 *
	 * @return the state list
	 */
	private List<State> getStateList() {
		List<State> state = shippingAddress.getAllState();
		return state;
	}

	/**
	 * Gets the address.
	 *
	 * @param string
	 *            the string
	 * @param session
	 *            the session
	 * @return the address
	 */
	public Address getAddress(String string, HttpSession session) {
		// TODO Auto-generated method stub
		Address address = shippingAddress.getCustomerAddress(session.getAttribute("userId").toString());
		return address;
	}

	/**
	 * Gets the city by state.
	 *
	 * @param stateId
	 *            the state id
	 * @param modelMap
	 *            the model map
	 * @return the city by state
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@RequestMapping(value = "/getCityByStateIdcityId", method = RequestMethod.GET)
	public String getCityByState(@RequestParam(value = "stateId") Integer stateId, ModelMap modelMap)
			throws SapeStoreException {
		List<City> cities = shippingAddress.getCityByStateId(stateId);
		modelMap.addAttribute("cities", cities);
		return "GetCityList";
	}

	/**
	 * Gets the intial city.
	 *
	 * @param stateId
	 *            the state id
	 * @param cityId
	 *            the city id
	 * @param modelMap
	 *            the model map
	 * @return the intial city
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@RequestMapping(value = "/getIntialCityList", method = RequestMethod.GET)
	public String getIntialCity(@RequestParam(value = "stateId") Integer stateId,
			@RequestParam(value = "cityId") Integer cityId, ModelMap modelMap) throws SapeStoreException {
		// System.out.println("inside fn - "+stateId);
		List<City> cities = shippingAddress.getCityByStateId(stateId);
		modelMap.addAttribute("cities", cities);
		modelMap.addAttribute("cityNa", cityId);
		return "GetCityList";
	}

	/**
	 * Adding details to db.
	 *
	 * @param shippingAddressVo
	 *            the shipping address vo
	 * @param username
	 *            the username
	 * @param modelMap
	 *            the model map
	 * @param session
	 *            the session
	 * @return the string
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@RequestMapping(value = "/addOrderDetailsToDb", method = RequestMethod.POST)
	public String addingDetailsToDb(@ModelAttribute("shippingAddressVo") ShippingAddressVo shippingAddressVo,
			@RequestParam("name") String username, ModelMap modelMap, HttpSession session) throws SapeStoreException {
		User user = shippingAddress.getCustomerDetails(session.getAttribute("userId").toString());
		user.setMobileNumber(shippingAddressVo.getMobileNumber());
		user.setPhone(shippingAddressVo.getPhone());
		Address address = getAddress(user.getUserId(), session);
		address.setAddressLine1(shippingAddressVo.getAddressLine1());
		address.setAddressLine2(shippingAddressVo.getAddressLine2());
		address.setPostalCode(shippingAddressVo.getPostalCode());
		address.setCityId(shippingAddressVo.getSecretcity());
		address.setStateId(shippingAddressVo.getStateId());
		List<OrderItemInfo> orderitems = (List<OrderItemInfo>) shippingAddress.getorderIds();
		int orderId = 1;
		int orderItemId = 1;
		for (OrderItemInfo orderItemInfo : orderitems) {
			if (orderItemInfo.getOrderId() > orderId) {
				orderId = orderItemInfo.getOrderId();
			}
		}
		for (OrderItemInfo orderItemInfo : orderitems) {
			if (orderItemInfo.getOrderItemId() > orderItemId) {
				orderItemId = orderItemInfo.getOrderItemId();
			}
		}
		orderId = orderId + 1;
		orderItemId = orderItemId + 1;
		OrderInfo orderinfo = new OrderInfo();
		orderinfo.setOrderId(orderId);
		orderinfo.setUserId(user.getUserId());
		Date orderDate = new Date();
		Date createdDate = new Date();
		Date updatedDate = new Date();
		orderinfo.setOrderDate(orderDate);
		
		orderinfo.setCreatedDate(createdDate);
		orderinfo.setUpdatedDate(updatedDate);
		ShoppingCartVO shoppingcart = (ShoppingCartVO) session.getAttribute("ShoppingCart");
		orderinfo.setTotalPayment(shoppingcart.getTotalPrice());
		orderinfo.setPaymentMode("COD");
		orderinfo.setOrderStatus("Not Dispatched");
		orderinfo.setIsActive("Y");
		List<OrderItemInfo> orderItem = new ArrayList<OrderItemInfo>();
		List<BookVO> bookvo = shoppingcart.getBooksInCart();
		for (BookVO bookVO2 : bookvo) {
			OrderItemInfo info = new OrderItemInfo();
			info.setOrderId(orderId);
			info.setOrderItemId(orderItemId);
			info.setIsbn(bookVO2.getIsbn());
			info.setBookPrice(Integer.parseInt(bookVO2.getBookPrice().toString()));
			info.setOrderQuantity(bookVO2.getQuantity());
			info.setIsActive("Y");
			info.setPurchaseType(bookVO2.getType());
			if (bookVO2.getType().equalsIgnoreCase("Rent")) {
				info.setExpectedReturnDate(bookVO2.getExpectedReturnDate());
			}

			info.setCreatedDate(createdDate);
			info.setUpdatedDate(updatedDate);
			orderItemId++;
			orderItem.add(info);
		}
		for (BookVO bookVO2 : bookvo) {
			BookVO bookvo3 = shippingDao.getBookByIsbn(bookVO2.getIsbn());
			if (bookvo3.getQuantity() < bookVO2.getQuantity()) {
				modelMap.addAttribute("bookvo", bookVO2);
				return "OrderFailed";
			}

		}
		orderinfo.setOrderItemInfoList(orderItem);
		session.removeAttribute("ShoppingCart");

		boolean orderio = shippingAddress.updateOrderInfo(orderinfo, orderItem, bookvo);

		State state = shippingAddress.getStateById(address.getStateId());
		City city = shippingAddress.getCityById(address.getCityId());
		modelMap.addAttribute("username", user);
		modelMap.addAttribute("custadddress", address);
		modelMap.addAttribute("state", state);
		modelMap.addAttribute("city", city);
		modelMap.addAttribute("orderId", orderId);
		boolean inserted = shippingAddress.updateCustomerInfomation(user, address);
		if (inserted) {
			return "redirect:/showDetails?newuser=" + username;
		} else {
			return "shippingAddress";
		}

	}
}
