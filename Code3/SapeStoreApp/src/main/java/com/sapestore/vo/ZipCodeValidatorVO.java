package com.sapestore.vo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ZipCodeValidatorVO.
 */
public class ZipCodeValidatorVO implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The postal_code. */
  private String city, state, county, country, state_short, postal_code;

  /**
   * Gets the city.
   *
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city.
   *
   * @param city
   *          the new city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Gets the state.
   *
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the state.
   *
   * @param state
   *          the new state
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Gets the county.
   *
   * @return the county
   */
  public String getCounty() {
    return county;
  }

  /**
   * Sets the county.
   *
   * @param county
   *          the new county
   */
  public void setCounty(String county) {
    this.county = county;
  }

  /**
   * Gets the country.
   *
   * @return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the country.
   *
   * @param country
   *          the new country
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Gets the state_short.
   *
   * @return the state_short
   */
  public String getState_short() {
    return state_short;
  }

  /**
   * Sets the state_short.
   *
   * @param state_short
   *          the new state_short
   */
  public void setState_short(String state_short) {
    this.state_short = state_short;
  }

  /**
   * Gets the postal_code.
   *
   * @return the postal_code
   */
  public String getPostal_code() {
    return postal_code;
  }

  /**
   * Sets the postal_code.
   *
   * @param postal_code
   *          the new postal_code
   */
  public void setPostal_code(String postal_code) {
    this.postal_code = postal_code;
  }

}
