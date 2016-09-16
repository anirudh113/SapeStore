package com.sapestore.vo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * Bean class for keeping logged in or logging in user information.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

public class UserVO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 6344881547419789459L;

  /** The user id. */
  private String userId;

  /** The password. */
  private String password;

  /** The name. */
  private String name;

  /** The user status. */
  private String userStatus;

  /**
   * Gets the gender id.
   *
   * @return the gender id
   */
  public int getGenderId() {
    return genderId;
  }

  /**
   * Sets the gender id.
   *
   * @param genderId
   *          the new gender id
   */
  public void setGenderId(int genderId) {
    this.genderId = genderId;
  }

  /**
   * Gets the email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email.
   *
   * @param email
   *          the new email
   */
  public void setEmail(String email) {
    this.email = email;
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

  /** The admin. */
  private String admin;

  /** The active. */
  private String active;

  /** The gender id. */
  private int genderId;

  /** The email. */
  private String email;

  /** The phone. */
  private String phone;

  /** The mobile number. */
  private String mobileNumber;

  /**
   * Gets the user status.
   *
   * @return the user status
   */
  public String getUserStatus() {
    return userStatus;
  }

  /**
   * Sets the user status.
   *
   * @param userStatus
   *          the new user status
   */
  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
  }

  /**
   * Gets the admin.
   *
   * @return the admin
   */
  public String getAdmin() {
    return admin;
  }

  /**
   * Sets the admin.
   *
   * @param admin
   *          the new admin
   */
  public void setAdmin(String admin) {
    this.admin = admin;
  }

  /**
   * Gets the active.
   *
   * @return the active
   */
  public String getActive() {
    return active;
  }

  /**
   * Sets the active.
   *
   * @param active
   *          the new active
   */
  public void setActive(String active) {
    this.active = active;
  }

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

}
