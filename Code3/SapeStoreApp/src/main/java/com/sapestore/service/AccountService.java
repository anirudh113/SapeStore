package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.ProfileVO;
import com.sapestore.vo.UserVO;

// TODO: Auto-generated Javadoc
/**
 * Service interface for user login functionality.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
/**
 * @author Anshul, Prakhar, Mukesh, Akshay .

 */
public interface AccountService {

  /**
   * Performs user login authentication.
   *
   * @param user the user
   * @return the user vo
   * @throws SapeStoreException the sape store exception
   * @throws SapeStoreSystemException the sape store system exception
   */
  UserVO authenticate(UserVO user) throws SapeStoreException;
  
  /**
   * user details by userid.
   *
   * @param userId the user id
   * @return user
   * @throws SapeStoreException the sape store exception
   */

  public User getUserDetail(String userId) throws SapeStoreException;
  
  /**
   * Retrives address by id.
   *
   * @param addressId the address id
   * @return address
   * @throws SapeStoreException the sape store exception
   */
  public Address getUserAddress(Integer addressId) throws SapeStoreException;

  /**
   * Method for citylist.
   *
   * @return citylist
   */
  public List<City> getCityList();

  /**
   * Gets the state list.
   *
   * @return the state list
   */
  public List<State> getStateList();

  /**
   * Gets the city by state id.
   *
   * @param stateId the state id
   * @return the city by state id
   */
  public List<City> getCityByStateId(Integer stateId);

  /**
   * updates database with user info.
   *
   * @param profilevo the profilevo
   */
  public void updateProfile(ProfileVO profilevo);
  
  /**
   * retrives city and state of user.
   *
   * @param cityList the city list
   * @param stateList the state list
   * @param profilevo the profilevo
   * @return string array with city and state
   */
  public String[] getCityAndState(List<City> cityList, List<State> stateList,
      ProfileVO profilevo);

  /* prakhar - starks - start */

  /**
   * Adds the customer.
   *
   * @param profileVO the profile vo
   */
  void addCustomer(ProfileVO profileVO);

  /**
   * Check user.
   *
   * @param userId the user id
   * @return true, if successful
   */
  boolean checkUser(String userId);

  /**
   * Check email.
   *
   * @param emailAddress the email address
   * @return true, if successful
   */
  boolean checkEmail(String emailAddress);

  /**
   * Validate email.
   *
   * @author Mukesh
   * Validate email .
   * @param email the email
   * @return the user
   * @throws SapeStoreException the sape store exception
   */
  User validateEmail(String email) throws SapeStoreException;

  /**
   * Validate user id.
   *
   * @author Mukesh .
   * Validate user id .
   * @param userid the userid.
   * @return the user
   * @throws SapeStoreException the sape store exception
   */
  User validateUserId(String userid) throws SapeStoreException;

  /**
   * Update password.
   *
   * @author Mukesh
   * Update password .
   * @param userid the userid
   * @param password the password
   * @return the user
   * @throws SapeStoreException the sape store exception
   */
  User updatePassword(String userid, String password) throws SapeStoreException;

  /**
   * Gets the city.
   *
   * @author Mukesh
   * Gets the city.
   * @param cityId the city id
   * @return the city
   */
  String getCity(String cityId);

  /* prakhar - starks - end */

}
