package com.sapestore.vo;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Bean class for updating rent informatio..
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

public class RentedUpdateForm {

  /** The rented update list. */
  List<RentedUpdate> rentedUpdateList;

  /**
   * Gets the rented update list.
   *
   * @return the rented update list
   */
  public List<RentedUpdate> getRentedUpdateList() {
    return rentedUpdateList;
  }

  /**
   * Sets the rented update list.
   *
   * @param rentedUpdateList
   *          the new rented update list
   */
  public void setRentedUpdateList(List<RentedUpdate> rentedUpdateList) {
    this.rentedUpdateList = rentedUpdateList;
  }

}
