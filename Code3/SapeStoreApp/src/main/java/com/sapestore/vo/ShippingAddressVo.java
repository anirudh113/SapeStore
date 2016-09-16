package com.sapestore.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class ShippingAddressVo.
 */
@Component
public class ShippingAddressVo implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The address line1. */
  String addressLine1;

  /** The address line2. */
  String addressLine2;

  /** The state id. */
  Integer stateId;

  /** The city id. */
  Integer cityId;

  /** The postal code. */
  String postalCode;

  /** The phone. */
  String phone;

  /** The mobile number. */
  String mobileNumber;

  /** The secretcity. */
  Integer secretcity;

  /**
   * Gets the secretcity.
   *
   * @return the secretcity
   */
  public Integer getSecretcity() {
    return secretcity;
  }

  /**
   * Sets the secretcity.
   *
   * @param secretcity
   *          the new secretcity
   */
  public void setSecretcity(Integer secretcity) {
    this.secretcity = secretcity;
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
  public Integer getStateId() {
    return stateId;
  }

  /**
   * Sets the state id.
   *
   * @param stateId
   *          the new state id
   */
  public void setStateId(Integer stateId) {
    this.stateId = stateId;
  }

  /**
   * Gets the city id.
   *
   * @return the city id
   */
  public Integer getCityId() {
    return cityId;
  }

  /**
   * Sets the city id.
   *
   * @param cityId
   *          the new city id
   */
  public void setCityId(Integer cityId) {
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

}