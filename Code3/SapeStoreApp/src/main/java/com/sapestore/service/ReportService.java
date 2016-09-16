
package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.vo.AdminDefaulterReport;
import com.sapestore.vo.ReportVO;

/**
 * Service interface for Admin Report functionality.
 * 
 * @author hsin59
 * @version 1.0
 */

public interface ReportService {

  /**
   * Prepares admin report
   * @return
   * @throws SapeStoreSystemException .
   */
  List<ReportVO> getAdminReport() throws SapeStoreException;

  /**
   * Prepares Purchased/Rented report
   * @return
   * @throws SapeStoreSystemException .
   */
  public List<ReportVO> getPurchasedRentedAdminReport()
      throws SapeStoreException;

  /**
   * Prepares the defaulters report.
   * @return
   * @throws SapeStoreSystemException .
   */
  public List<AdminDefaulterReport> getDefaultersAdminReport()
      throws SapeStoreException;

}
