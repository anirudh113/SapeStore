
package com.sapestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.service.DefaulterEmailService;

// TODO: Auto-generated Javadoc
/**
* The Class EmailController.
*/
@Controller
public class EmailController {

 /** The defaulter email service. */
  @Autowired
  private DefaulterEmailService defaulterEmailService;
  
  /** The Constant LOGGER. */
  private static final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(EmailController.class.getName());

  /**
   * This method is used to send mail.
   * @param ids email id as string
   * @param modelMap modelMap object to pass values to controller.
   * @return String as status
   */
  @RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
  public final String sendEmail(@RequestParam("emailIds") final String ids,
      final ModelMap modelMap) {
    LOGGER.debug("in sendmail function");
    String returnStatus = defaulterEmailService.sendDefaulterNotification(ids);
    return returnStatus;
  }
}
