package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.State;
import com.sapestore.service.AccountService;
import com.sapestore.service.BookService;
import com.sapestore.vo.ProfileVO;
import com.sapestore.vo.ShoppingCartVO;
import com.sapestore.vo.UserVO;
import com.sapestore.vo.HomeVO;

@Controller
@SessionAttributes("userId")
public class Client360 {

	/** The Constant LOGGER. */
	private static final SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(AccountController.class.getName());

	@RequestMapping(value = "/clientanalytics", method = RequestMethod.GET)
	public String analytics(@ModelAttribute("user") UserVO user,
			ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpSession httpSession, HttpServletResponse response)
			throws SapeStoreException {
		LOGGER.debug("clientanalytics method: START");

		Cookie cookieArray[] = httpServletRequest.getCookies();
		String jsessionid = "";
		String clientId = "";
		if (cookieArray != null) {
			for (Cookie ck : cookieArray) {
				if (ck.getName().toString().equals("JSESSIONID")) {

					jsessionid = ck.getValue();
				}
				if (ck.getName().toString().equals("s_fid")) {
					clientId = ck.getValue();
				}

			}
		}

		long creationtime = httpSession.getCreationTime();
		long lasttime = httpSession.getLastAccessedTime();
		long duration = lasttime - creationtime;
		modelMap.addAttribute("duration", duration / 1000);
		modelMap.addAttribute("jsessionid", jsessionid);
		modelMap.addAttribute("clientId", clientId);

		LOGGER.debug("clientanalytics method: END");
		return "ClientAnalytics";
	}

	@RequestMapping(value = "/unauthUserProfile", method = RequestMethod.GET)
	public String unauthUserProfile(ModelMap modelMap)
			throws SapeStoreException {
		LOGGER.debug("unauthUserProfile method: START");

		LOGGER.debug("unauthUserProfile method: END");
		return "unauthprofile";
	}

	@ResponseBody
	@RequestMapping(value = "/recommend")
	public String recommendnewBook(
			@RequestParam("dsessionId") String dsessionId, ModelMap modelMap,
			HttpSession httpSession) throws Exception {
		
		String returnvalue = "ajax_called";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("recommend method: START");
		}
		try {
			
			if (dsessionId.equalsIgnoreCase("abc")) {
				returnvalue = "ajax_success";
			}

		} catch (SapeStoreSystemException ex) {
			LOGGER.error("recommend method: ERROR: " + ex);

		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("recommend method: END");
		}
		return returnvalue;
	}

}
