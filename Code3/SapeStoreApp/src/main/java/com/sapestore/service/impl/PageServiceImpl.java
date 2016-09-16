package com.sapestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.PagesDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.PageService;

// TODO: Auto-generated Javadoc
/**
 * Service class for editing static content like Contacts Us and Privacy Policy.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Service("pageService")
public class PageServiceImpl implements PageService {

  /** The Constant LOGGER. */
  private static  final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(PageServiceImpl.class.getName());

  /** The pages dao. */
  @Autowired
  private PagesDao pagesDao;

  /* (non-Javadoc)
   * @see com.sapestore.service.PageService#getContactUs()
   */
  @Override
  public String getContactUs() throws SapeStoreException {
    LOGGER.debug("getContactUs method: START");
    String contactUsText = null;
    contactUsText = pagesDao.getContactUs();
    LOGGER.debug("getContactUs method: END");
    return contactUsText;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.PageService#setContactUs(java.lang.String)
   */
  @Override
  public void setContactUs(String contactText) throws SapeStoreException {
    pagesDao.setContactUs(contactText);
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.PageService#getPolicy()
   */
  @Override
  public String getPolicy() throws SapeStoreException {
    String policyText = null;
    policyText = pagesDao.getPolicy();
    return policyText;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.PageService#setPolicy(java.lang.String)
   */
  @Override
  public void setPolicy(String policyText) throws SapeStoreException {
    pagesDao.setPolicy(policyText);
  }

}
