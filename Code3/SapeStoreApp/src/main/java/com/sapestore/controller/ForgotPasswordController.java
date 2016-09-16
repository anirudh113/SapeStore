package com.sapestore.controller;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.AccountService;
import com.sapestore.service.CommunicationService;

// TODO: Auto-generated Javadoc
/**
 * The Class ForgotPasswordController.
 *
 * @author mpuro1
 */

@Controller
@SessionAttributes(value = { "userId", "username" })
public class ForgotPasswordController {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(ForgotPasswordController.class.getName());

  /** The account service. */
  @Autowired
  private AccountService accountService;
  
  /** The communicate service. */
  @Autowired
  private CommunicationService communicateService;
  
  /**
   * @author Mukesh
   * Process: It validates whether the link entered by the user is valid 
   * and redirects to change password page.
   * Forgot.
   *
   * @param userId the user id
   * @param lincdate It contains the date in the reset password link
   * @param request the request
   * @param modelMap the model map
   * @param httpServletRequest the http servlet request
   * @param httpSession the http session
   * @param response the response
   * @param status the status
   * @return on success it returns ChangePswd.jsp and on failure it will return error.jsp
   * @throws SapeStoreException the sape store exception
   * @throws NoSuchAlgorithmException the no such algorithm exception
   */

  @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
  public String forgot(@RequestParam("userId") String userId,
      @RequestParam("date") String lincdate, WebRequest request,
      ModelMap modelMap, HttpServletRequest httpServletRequest,
      HttpSession httpSession, HttpServletResponse response,
      SessionStatus status)
          throws SapeStoreException, NoSuchAlgorithmException {
    String forwardStr = null;

    DateFormat df = new SimpleDateFormat("dd/MM/yy");
    Date dateobj = new Date();
    String date = df.format(dateobj);
    byte[] asBytes = null;
    byte[] dateasBytes = null;

    try {
      asBytes = Base64.getDecoder().decode(userId);
      dateasBytes = Base64.getDecoder().decode(lincdate);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return "error";
    }

    String user = new String(asBytes);
    String linkdate = new String(dateasBytes);

    User testuser = accountService.validateUserId(user);
    if (testuser != null && date.equals(linkdate)) {
      modelMap.addAttribute("userId", user);
      modelMap.addAttribute("username", testuser.getName());
      forwardStr = "ChangePswd";

    } else {
      status.setComplete();
      request.removeAttribute("userId", WebRequest.SCOPE_SESSION);
      request.removeAttribute("username", WebRequest.SCOPE_SESSION);
      request.removeAttribute("ShoppingCart", WebRequest.SCOPE_SESSION);
      request.removeAttribute("checkMe", WebRequest.SCOPE_SESSION);

      forwardStr = "error";
    }

    return forwardStr;

  }

  /**
   * Login.
   * @author Mukesh 
   *  Process: It taks the password from the user and updates in the database and also sends email.
   * @param newpassword it is the new password entered by the user.
   * @param request the request
   * @param modelMap the model map
   * @param httpServletRequest the http servlet request
   * @param httpSession the http session
   * @param response the response
   * @param status the status
   * @return updatePswdSucss.jsp on succes.
   * @throws SapeStoreException the sape store exception
   * @throws NoSuchAlgorithmException the no such algorithm exception
   */
  @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
  public String login(@RequestParam("newpassword") String newpassword,
      WebRequest request, ModelMap modelMap,
      HttpServletRequest httpServletRequest, HttpSession httpSession,
      HttpServletResponse response, SessionStatus status)
          throws SapeStoreException, NoSuchAlgorithmException {

    System.out.println(newpassword);
    String userid = (String) modelMap.get("userId");
    User user = accountService.updatePassword(userid, newpassword);
    String forwardStr = communicateService.communicateUpdatePassword(user);
    status.setComplete();
    request.removeAttribute("userId", WebRequest.SCOPE_SESSION);
    request.removeAttribute("username", WebRequest.SCOPE_SESSION);
    request.removeAttribute("ShoppingCart", WebRequest.SCOPE_SESSION);
    request.removeAttribute("checkMe", WebRequest.SCOPE_SESSION);

    return forwardStr;

  }
}
