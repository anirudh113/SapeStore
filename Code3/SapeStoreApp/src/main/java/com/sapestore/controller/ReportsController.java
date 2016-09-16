

package com.sapestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.ReportService;
import com.sapestore.vo.ReportVO;


// TODO: Auto-generated Javadoc
/**
   * This is a controller class for the admin report.
   * 
   @author hsin59
   * @version 1.0
   */

@Controller
public class ReportsController {

  /** The Constant LOGGER. */
  private static final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(ReportsController.class.getName());

  /** The report service. */
  @Autowired
  private ReportService reportService;

  /** The admin report list. */
  private List<ReportVO> adminReportList;

  /**
   * Gets the admin report list.
   *
   * @return the admin report list
   */
  public List<ReportVO> getAdminReportList() {
    return adminReportList;
  }

  /**
   * Sets the admin report list.
   *
   * @param adminReportList the new admin report list
   */
  public void setAdminReportList(List<ReportVO> adminReportList) {
    this.adminReportList = adminReportList;
  }

  /**
   * Processes the requests for Defaulter report.
   *
   * @param modelMap model map.
   * @return DefaulterReport
   * @throws SapeStoreException the sape store exception
   */
  @RequestMapping(value = "/defaultersReport", method = RequestMethod.GET)
  public String defaultersReport(ModelMap modelMap) throws SapeStoreException {
    LOGGER.debug("defaultersReport method: START");
    modelMap.addAttribute("adminReportsList", reportService.getDefaultersAdminReport());
    LOGGER.debug("defaultersReport method: END");
    return "DefaulterReport";
  }

  /**
   * Processes the requests for Admin report.
   *
   * @param modelMap the model map
   * @return AdminHome
   * @throws SapeStoreException the sape store exception
   */
  @RequestMapping(value = "/adminReport", method = RequestMethod.GET)
  public String adminReport(ModelMap modelMap) throws SapeStoreException {
    LOGGER.debug("adminReport method: START");
    this.setAdminReportList(reportService.getAdminReport());
    LOGGER.debug("adminReport method: END");
    return "AdminHome";
  }

  /**
   * Processes the Purchased/Rented report requests.
   *
   * @param modelMap model map.
   * @return PurchasedRentedReport
   * @throws SapeStoreException the sape store exception
   */
  @RequestMapping(value = "/purchasedRentedReport", method = RequestMethod.GET)
  public String purchasedRentedReport(ModelMap modelMap) throws SapeStoreException {
    LOGGER.debug("purchasedRentedReport method: START");
    modelMap.addAttribute("adminReportsList", reportService.getPurchasedRentedAdminReport());
    LOGGER.debug("purchasedRentedReport method: END");
    return "PurchasedRentedReport";
  }

}
