package com.sapestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.service.BookService;
import com.sapestore.service.PageService;

// TODO: Auto-generated Javadoc
/**
 * This is a controller class for the Manage Pages page.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
@Controller
public class PagesController {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(PagesController.class.getName());

  /** The page service. */
  @Autowired
  private PageService pageService;

  /** The book service. */
  @Autowired
  private BookService bookService;

  /**
   * Redirects to ManagePages section for the administrator.
   *
   * @param modelMap the model map
   * @return the string
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  @RequestMapping(value = "/managePages", method = RequestMethod.GET)
  public String managePages(ModelMap modelMap) throws SapeStoreException {
    return "ManagePages";
  }

  /**
   * Redirects to Contact Us tab for the admin.
   *
   * @param modelMap the model map
   * @return the string
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  @RequestMapping(value = "/contactUsEdit", method = RequestMethod.GET)
  public String contactUsEdit(ModelMap modelMap) throws SapeStoreException {
    LOGGER.debug("contactUsEdit method: START");
    modelMap.addAttribute("contactText", pageService.getContactUs());
    LOGGER.debug("contactUsEdit method: END");
    return "ContactUsAdmin";
  }

  /**
   * Saves the Contact Us text.
   *
   * @param contactText the contact text
   * @param modelMap the model map
   * @return the string
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  @RequestMapping(value = "/contactUs", method = RequestMethod.GET)
  public String setContactUs(@RequestParam("contactText") String contactText,
      ModelMap modelMap) throws SapeStoreException {
    pageService.setContactUs(contactText);
    return "redirect:/managePages";
  }

  /**
   * Policy edit.
   *
   * @param modelMap the model map
   * @return the string
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  @RequestMapping(value = "/policyEdit", method = RequestMethod.GET)
  public String policyEdit(ModelMap modelMap) throws SapeStoreException {
    modelMap.addAttribute("policyText", pageService.getPolicy());
    return "PolicyAdmin";
  }

  /**
   * Sets the policy.
   *
   * @param policyText the policy text
   * @param modelMap the model map
   * @return the string
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  @RequestMapping(value = "/policy", method = RequestMethod.GET)
  public String setPolicy(@RequestParam("policyText") String policyText,
      ModelMap modelMap) throws SapeStoreException {
    pageService.setPolicy(policyText);
    return "redirect:/managePages";
  }

  /**
   * Returns the privacy policy text for the customer.
   *
   * @param modelMap the model map
   * @param httpServletRequest the http servlet request
   * @param httpSession the http session
   * @return the string
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  @RequestMapping(value = "/policyCustomer", method = RequestMethod.GET)
  public String policyCustomer(ModelMap modelMap,
      HttpServletRequest httpServletRequest, HttpSession httpSession)
          throws SapeStoreException {
    LOGGER.debug("policyCustomer method: START");
    modelMap.addAttribute("catList", bookService.getCategoryList());
    modelMap.addAttribute("policyText", pageService.getPolicy());
    modelMap.addAttribute("checkMe", httpSession.getAttribute("checkMe"));
    LOGGER.debug("policyCustomer method: END");
    return "PolicyCustomer";
  }

  /**
   * Processes the request for Contact Us page for the customer.
   *
   * @param modelMap the model map
   * @param httpServletRequest the http servlet request
   * @param httpSession the http session
   * @return the string
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  @RequestMapping(value = "/contactUsCustomer", method = RequestMethod.GET)
  public String contactUsCustomer(ModelMap modelMap,
      HttpServletRequest httpServletRequest, HttpSession httpSession)
          throws SapeStoreException {
    LOGGER.debug("contactUsCustomer method: START");
    modelMap.addAttribute("catList", bookService.getCategoryList());
    modelMap.addAttribute("contactUsText", pageService.getContactUs());
    modelMap.addAttribute("checkMe", httpSession.getAttribute("checkMe"));
    LOGGER.debug("contactUsCustomer method: END");
    return "ContactUsCustomer";
  }

}
