package com.sapestore.vo;

import java.io.Serializable;

import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.Country;
import com.sapestore.hibernate.entity.Gender;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;

// TODO: Auto-generated Javadoc
/**
 * Bean class for keeping logged in or logging in user information.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

public class ProfileVO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The user id. */

  // private static final long serialVersionUID = 6344881547419789459L;

  private String userId;

  /** The password. */
  private String password;

  /** The name. */
  private String name;

  /** The retype password. */

  private String retypePassword;

  /** The email address. */
  private String emailAddress;

  /** The address line1. */
  private String addressLine1;

  /** The address line2. */
  private String addressLine2;

  /** The state id. */
  private String stateId;

  /** The city id. */
  private String cityId;

  /** The gender id. */
  private String genderId;

  /** The postal code. */
  private String postalCode;

  /** The phone. */
  private String phone;

  /** The mobile number. */
  private String mobileNumber;

  /**
   * Gets the user id.
   *
   * @return the user id
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Sets the user id.
   *
   * @param userId
   *          the new user id
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password.
   *
   * @param password
   *          the new password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name
   *          the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the retype password.
   *
   * @return the retype password
   */
  public String getRetypePassword() {
    return retypePassword;
  }

  /**
   * Sets the retype password.
   *
   * @param retypePassword
   *          the new retype password
   */
  public void setRetypePassword(String retypePassword) {
    this.retypePassword = retypePassword;
  }

  /**
   * Gets the email address.
   *
   * @return the email address
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * Sets the email address.
   *
   * @param emailAddress
   *          the new email address
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
   * Gets the address line1.
   *
   * @return the address line1
   */
  public String getAddressLine1() {
    return addressLine1;
  }

  /**
   * Sets the address line1.
   *
   * @param addressLine1
   *          the new address line1
   */
  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  /**
   * Gets the address line2.
   *
   * @return the address line2
   */
  public String getAddressLine2() {
    return addressLine2;
  }

  /**
   * Sets the address line2.
   *
   * @param addressLine2
   *          the new address line2
   */
  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  /**
   * Gets the state id.
   *
   * @return the state id
   */
  public String getStateId() {
    return stateId;
  }

  /**
   * Sets the state id.
   *
   * @param stateId
   *          the new state id
   */
  public void setStateId(String stateId) {
    this.stateId = stateId;
  }

  /**
   * Gets the city id.
   *
   * @return the city id
   */
  public String getCityId() {
    return cityId;
  }

  /**
   * Sets the city id.
   *
   * @param cityId
   *          the new city id
   */
  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  /**
   * Gets the postal code.
   *
   * @return the postal code
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * Sets the postal code.
   *
   * @param postalCode
   *          the new postal code
   */
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  /**
   * Gets the phone.
   *
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets the phone.
   *
   * @param phone
   *          the new phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Gets the mobile number.
   *
   * @return the mobile number
   */
  public String getMobileNumber() {
    return mobileNumber;
  }

  /**
   * Sets the mobile number.
   *
   * @param mobileNumber
   *          the new mobile number
   */
  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  /**
   * Gets the gender id.
   *
   * @return the gender id
   */
  public String getGenderId() {
    return genderId;
  }

  /**
   * Sets the gender id.
   *
   * @param genderId
   *          the new gender id
   */
  public void setGenderId(String genderId) {
    this.genderId = genderId;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ProfileVO [userId=" + userId + ", password=" + password + ", name="
        + name + ", retypePassword=" + retypePassword + ", emailAddress="
        + emailAddress + ", addressLine1=" + addressLine1 + ", addressLine2="
        + addressLine2 + ", stateId=" + stateId + ", cityId=" + cityId
        + ", genderId=" + genderId + ", postalCode=" + postalCode + ", phone="
        + phone + ", mobileNumber=" + mobileNumber + "]";
  }

}
