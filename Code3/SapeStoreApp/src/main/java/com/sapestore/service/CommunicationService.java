
package com.sapestore.service;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommunicationService.
 */
public interface CommunicationService {

  /**
   * @author Mukesh Communicate update.
   *
   * @param user
   *          the user
   * @throws SapeStoreException
   *           the sape store exception
   */
  void communicateUpdate(User user) throws SapeStoreException;

  /**
   * Communicate register.
   *
   * @param user
   *          the user
   * @throws SapeStoreException
   *           the sape store exception
   */
  void communicateRegister(User user) throws SapeStoreException;

  /**
   * Communicate.
   * 
   * @author Mukesh
   *
   * @param user
   *          the user
   * @return the string
   * @throws SapeStoreException
   *           the sape store exception
   */
  String communicate(User user) throws SapeStoreException;

  /**
   * Communicate update password.
   * 
   * @author Mukesh
   *
   * @param user
   *          the user
   * @return the string
   * @throws SapeStoreException
   *           the sape store exception
   */
  String communicateUpdatePassword(User user) throws SapeStoreException;

}
