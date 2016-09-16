package com.sapestore.service;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;

// TODO: Auto-generated Javadoc
/**
 * Service interface for editing static content like Contacts Us and Privacy
 * Policy.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

public interface PageService {

  /**
   * Returns Contact Us text.
   * @return the contact us
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  String getContactUs() throws SapeStoreException;

  /**
   * Sets new Contact Us text.
   * @param contactText the new contact us
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  void setContactUs(String contactText) throws SapeStoreException;

  /**
   * Returns Privacy policy text.
   * @return the policy
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  String getPolicy() throws SapeStoreException;

  /**
   * Sets new Privacy Policy text.
   * @param policyText the new policy
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  void setPolicy(String policyText) throws SapeStoreException;

}
