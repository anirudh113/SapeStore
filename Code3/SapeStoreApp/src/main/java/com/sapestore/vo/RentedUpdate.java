package com.sapestore.vo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * Bean class for Rented books status.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
public class RentedUpdate implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The dispatch status. */
  private boolean dispatchStatus;

  /** The return status. */
  private boolean returnStatus;

  /**
   * Instantiates a new rented update.
   */
  public RentedUpdate() {
  }

  /**
   * Instantiates a new rented update.
   *
   * @param dispatchStatus
   *          the dispatch status
   * @param returnStatus
   *          the return status
   */
  public RentedUpdate(boolean dispatchStatus, boolean returnStatus) {
    this.dispatchStatus = dispatchStatus;
    this.returnStatus = returnStatus;
  }

  /**
   * Gets the dispatch status.
   *
   * @return book dispatch status(dispatched/pending)
   */
  public boolean getDispatchStatus() {
    return dispatchStatus;
  }

  /**
   * Sets the dispatch status.
   *
   * @param dispatchStatus
   *          sets book dispatch status(dispatched/pending)
   */
  public void setDispatchStatus(boolean dispatchStatus) {
    this.dispatchStatus = dispatchStatus;
  }

  /**
   * Gets the return status.
   *
   * @return book return status(returned/pending)
   */
  public boolean getReturnStatus() {
    return returnStatus;
  }

  /**
   * Sets the return status.
   *
   * @param returnStatus
   *          sets book return status(returned/pending)
   */
  public void setReturnStatus(boolean returnStatus) {
    this.returnStatus = returnStatus;
  }
}