package com.sapestore.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.AccountDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.AccountService;
import com.sapestore.service.BookService;
import com.sapestore.service.CommunicationService;
import com.sapestore.vo.ProfileVO;
import com.sapestore.vo.UserVO;
import com.sapestore.vo.ZipCodeValidatorVO;

// TODO: Auto-generated Javadoc
/**
 * This is a controller class for the login functionality.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

/**
 * @author Anshul.
 * @author Prakhar
 * @author Akshay
 */

@Controller
@SessionAttributes(value = { "userId", "username", "cookieId" })
public class AccountController {

	/** The Constant LOGGER. */
	private static final SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(AccountController.class.getName());

	/** The account service. */
	@Autowired
	private AccountService accountService;

	/** The communicate service. */
	@Autowired
	private CommunicationService communicateService;

	/** The book service. */
	/* prakhar - starks - start */
	@Autowired
	private BookService bookService;

	/* prakhar - starks - end */

	/**
	 * Dsc.
	 *
	 * @param modelMap
	 *            modelmap
	 * @return index.jsp
	 * @throws SapeStoreException
	 *             exception.
	 */
	@RequestMapping(value = "/beforelogin", method = RequestMethod.GET)
	public String beforeLogin(ModelMap modelMap) throws SapeStoreException {
		LOGGER.debug(" AccountController.beforeLogin method: START ");
		modelMap.addAttribute("user", new User());
		LOGGER.debug(" AccountController.beforeLogin method: END ");
		return "index";
	}

	/**
	 * Processes the login requests.
	 *
	 * @author
	 * @param email
	 *            the email
	 * @return the string
	 * @throws SapeStoreException
	 *             the sape store exception
	 */

	/**
	 * Validate the emailId entered by the user for password retrieval and
	 * communicate the reset link to registered Id.
	 * 
	 * @author mpuro1
	 * @param email
	 *            contains the email entered by the User.
	 * @return EmailSuccess.jsp on Success and InvalidEmail.jsp on failure
	 * @throws SapeStoreException .
	 */
	@RequestMapping(value = "/Validate", method = RequestMethod.POST)
	public String login(@ModelAttribute("name") String email)
			throws SapeStoreException {
		String forwardStr = null;
		User user = accountService.validateEmail(email);
		forwardStr = communicateService.communicate(user);

		return forwardStr;

	}

	/**
	 * Login.
	 *
	 * @param user
	 *            the user
	 * @param modelMap
	 *            the model map
	 * @param httpServletRequest
	 *            the http servlet request
	 * @param httpSession
	 *            the http session
	 * @param response
	 *            the response
	 * @return the string
	 * @throws SapeStoreException
	 *             the sape store exception
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws InvalidKeySpecException
	 *             the invalid key spec exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") UserVO user, ModelMap modelMap,
			HttpServletRequest httpServletRequest, HttpSession httpSession,
			HttpServletResponse response) throws SapeStoreException,
			NoSuchAlgorithmException, InvalidKeySpecException {
		LOGGER.debug("login method: START");
		String forwardStr = null;
		UserVO localUserlogin = null;
		UserVO userLogin = user;
		String userId = null;
		String referer = httpServletRequest.getHeader("referer");
		localUserlogin = accountService.authenticate(userLogin);
		if (localUserlogin != null && localUserlogin.getAdmin() != null) {
			if (localUserlogin.getAdmin().equalsIgnoreCase("Y")) {
				forwardStr = "redirect:/manageOrders";
				// modelMap.addAttribute("checkMe",true);
			} else {
				if (referer.endsWith("/index.jsp")
						|| referer.endsWith("SapeStore/")) {
					forwardStr = "redirect:/welcome";
				} else {
					forwardStr = "redirect:" + referer;
					modelMap.addAttribute("checkMe", true);

				}

			}
			AccountDao accountDao = new AccountDao();

			userId = localUserlogin.getUserId();
			String cookieId = accountDao.getCookieId(userId);

			// Cookie cookieArray[] = httpServletRequest.getCookies();
			// String userLastCookieId = "";
			// if (cookieArray != null) {
			// for (Cookie ck : cookieArray) {
			// if (ck.getName().toString().equals("userCookieId")) {
			// if (!(ck.getValue().equals(cookieId))) {
			// userLastCookieId = ck.getValue();
			// Cookie cook = new Cookie("userLastCookieId", userLastCookieId);
			// response.addCookie(cook);
			// }
			// }
			// }
			// }

			Cookie usercookie = new Cookie("userCookieId", cookieId);
			Cookie userLastcookie = new Cookie("userLastCookieId", cookieId);

			usercookie.setMaxAge(-1);
			userLastcookie.setMaxAge(Integer.MAX_VALUE);

			response.addCookie(usercookie);
			if (!cookieId.equals("")) {
				response.addCookie(userLastcookie);
			}

			modelMap.addAttribute("userId", userId);
			// modelMap.addAttribute("cookieId", cookieId);
			modelMap.addAttribute("username", localUserlogin.getName());
		} else {
			forwardStr = "loginFailed";
		}
		LOGGER.debug("login method: END");
		return forwardStr;

	}

	/**
	 * Processes the Logout requests.
	 *
	 * @param request
	 *            the request
	 * @param status
	 *            the status
	 * @param modelMap
	 *            the model map
	 * @param httpServletRequest
	 *            the http servlet request
	 * @param httpSession
	 *            the http session
	 * @param response
	 *            the response
	 * @return the string
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(WebRequest request, SessionStatus status,
			ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpSession httpSession, HttpServletResponse response)
			throws SapeStoreException {
		LOGGER.debug("logout method: START");
		status.setComplete();
		request.removeAttribute("userId", WebRequest.SCOPE_SESSION);
		request.removeAttribute("username", WebRequest.SCOPE_SESSION);
		request.removeAttribute("ShoppingCart", WebRequest.SCOPE_SESSION);
		request.removeAttribute("checkMe", WebRequest.SCOPE_SESSION);
		LOGGER.debug("logout method: END");

		Cookie cookie = new Cookie("userCookieId", ""); // Not necessary, but
														// saves bandwidth.
		cookie.setMaxAge(0); // Don't set to -1 or it will become a session
								// cookie!
		response.addCookie(cookie);

		return "redirect:/welcome?checkMe=false";
	}

	/**
	 * Displays user info.
	 *
	 * @param userId
	 *            the user id
	 * @param httpSession
	 *            the http session
	 * @param modelMap
	 *            the model map
	 * @return editProfile jsp
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@RequestMapping(value = "/editInfo", method = RequestMethod.GET)
	public String userDetails(@ModelAttribute("userId") String userId,
			HttpSession httpSession, ModelMap modelMap)
			throws SapeStoreException {
		BasicConfigurator.configure();
		LOGGER.debug("getuserDetails method: START");
		User user = new User();
		Address address;
		ProfileVO profilevo = new ProfileVO();

		user = accountService.getUserDetail(userId);

		address = accountService.getUserAddress(user.getAddressId());

		profilevo.setUserId(user.getUserId());
		profilevo.setName(user.getName());
		profilevo.setPhone(user.getPhone());
		profilevo.setEmailAddress(user.getEmailAddress());
		profilevo.setMobileNumber(user.getMobileNumber());

		profilevo.setPostalCode(address.getPostalCode());
		profilevo.setAddressLine1(address.getAddressLine1());
		profilevo.setAddressLine2(address.getAddressLine2());
		profilevo.setCityId(address.getCityId().toString());
		profilevo.setStateId(address.getStateId().toString());
		List<State> stateList = accountService.getStateList();
		List<City> cityList = accountService.getCityList();

		String[] output = accountService.getCityAndState(cityList, stateList,
				profilevo);

		modelMap.addAttribute("cityName", output[0]);
		modelMap.addAttribute("stateName", output[1]);
		modelMap.addAttribute("profilevo", profilevo);
		modelMap.addAttribute("statelist", stateList);
		modelMap.addAttribute("citylist", cityList);

		LOGGER.debug("getUserDetails method: END");
		return "EditProfile";
	}

	/**
	 * User Information display to be edited.
	 *
	 * @param userId
	 *            String object
	 * @param httpSession
	 *            HttpSession object
	 * @param modelMap
	 *            MOdelMap object
	 * @return updateProfile jsp
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@RequestMapping(value = "/updateProfileform")
	public String updateProfileForm(@ModelAttribute("userId") String userId,
			HttpSession httpSession, ModelMap modelMap)
			throws SapeStoreException {
		BasicConfigurator.configure();
		LOGGER.debug("getuserDetails method: START");
		User user = new User();
		Address address = new Address();
		ProfileVO profilevo = new ProfileVO();

		user = accountService.getUserDetail(userId);

		address = accountService.getUserAddress(user.getAddressId());

		profilevo.setName(user.getName());
		profilevo.setPhone(user.getPhone());
		profilevo.setEmailAddress(user.getEmailAddress());
		profilevo.setMobileNumber(user.getMobileNumber());

		profilevo.setPostalCode(address.getPostalCode());
		profilevo.setAddressLine1(address.getAddressLine1());
		profilevo.setAddressLine2(address.getAddressLine2());

		List<State> stateList = accountService.getStateList();
		List<City> cityList = accountService.getCityList();

		modelMap.addAttribute("userId", userId);
		modelMap.addAttribute("profilevo", profilevo);
		modelMap.addAttribute("statelist", stateList);
		modelMap.addAttribute("citylist", cityList);
		modelMap.addAttribute("address", address);

		LOGGER.debug("getUserDetails method: END");
		return "UpdateProfile";
	}

	/**
	 * Saves updated info in database.
	 *
	 * @param profilevo
	 *            the profilevo
	 * @param bindingResult
	 *            the binding result
	 * @param modelMap
	 *            the model map
	 * @return editprofile jsp
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String userDetailsUpdate(
			@ModelAttribute("profilevo") ProfileVO profilevo,
			BindingResult bindingResult, ModelMap modelMap)
			throws SapeStoreException {
		BasicConfigurator.configure();
		LOGGER.debug("userDetailUpdate method: START");

		if (bindingResult.hasErrors()) {
			return "UpdateProfile";
		} else {

			accountService.updateProfile(profilevo);
			return "redirect:/editInfo";
		}
	}

	/**
	 * Register.
	 *
	 * @param modelMap
	 *            the model map
	 * @return the string
	 * @throws SapeStoreException
	 *             the sape store exception
	 */

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(ModelMap modelMap) throws SapeStoreException {
		LOGGER.debug("register method: START");
		List<State> stateList = accountService.getStateList();
		List<BookCategory> bookcategoryList = bookService.getCategoryList();
		modelMap.addAttribute("stateList", stateList);
		modelMap.addAttribute("catList", bookcategoryList);
		modelMap.addAttribute("profileVO", new ProfileVO());
		return "register";
	}

	/**
	 * Customer register.
	 *
	 * @param profileVO
	 *            the profile vo
	 * @param bindingResult
	 *            the binding result
	 * @param modelMap
	 *            the model map
	 * @return the string
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@RequestMapping(value = "/customerRegister", method = RequestMethod.POST)
	public String customerRegister(
			@ModelAttribute("profileVO") ProfileVO profileVO,
			BindingResult bindingResult, ModelMap modelMap,
			HttpServletResponse response) throws SapeStoreException {
		List<State> stateList = accountService.getStateList();
		List<BookCategory> bookcategoryList = bookService.getCategoryList();
		modelMap.addAttribute("stateList", stateList);
		modelMap.addAttribute("catList", bookcategoryList);
		// zipcode start
		int set = 0;
		RestTemplate template = new RestTemplate();
		String zipurl = "http://zip.getziptastic.com/v2/US/"
				+ profileVO.getPostalCode();
		ZipCodeValidatorVO searchresult = new ZipCodeValidatorVO();
		try {
			searchresult = (ZipCodeValidatorVO) template.getForObject(zipurl,
					ZipCodeValidatorVO.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			set = 0;
		}
		String city = accountService.getCity(profileVO.getCityId());
		// zipcode end
		if (bindingResult.hasFieldErrors()) {
			return "register";
		} else {
			accountService.addCustomer(profileVO);
		}
		modelMap.addAttribute("userId", profileVO.getUserId());
		modelMap.addAttribute("username", profileVO.getName());
		/* return "redirect:/welcome?checkMe=true"; */

		String cookieId = new AccountDao().getCookieId(profileVO.getUserId());
		Cookie usercookie = new Cookie("userCookieId", cookieId);
		Cookie userLastcookie = new Cookie("userLastCookieId", cookieId);

		usercookie.setMaxAge(-1);
		userLastcookie.setMaxAge(Integer.MAX_VALUE);

		response.addCookie(usercookie);
		if (!cookieId.equals("")) {
			response.addCookie(userLastcookie);
		}

		return "redirect:/editInfo";
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
	@RequestMapping(value = "/getCityByState", method = RequestMethod.GET)
	public String getCityByState(
			@RequestParam(value = "stateId") Integer stateId, ModelMap modelMap)
			throws SapeStoreException {
		List<City> cities = accountService.getCityByStateId(stateId);
		modelMap.addAttribute("cities", cities);
		return "CityList";

	}

	/**
	 * Checkuseravail.
	 *
	 * @param inputuser
	 *            the inputuser
	 * @param modelMap
	 *            the model map
	 * @return the string
	 * @throws SapeStoreException
	 *             the sape store exception
	 */
	@RequestMapping(value = "/checkuseravail")
	public String checkuseravail(
			@RequestParam(value = "inputuser") String inputuser,
			ModelMap modelMap) throws SapeStoreException {

		if (inputuser.isEmpty()) {
			modelMap.addAttribute("userIdmessagered", "Please provide User ID");
		} else if (accountService.checkUser(inputuser)) {
			modelMap.addAttribute("userIdmessagered", "User ID already exists");
		} else {
			modelMap.addAttribute("userIdmessagegreen", "User ID available");
		}

		return "checkuseravail";

	}

}
