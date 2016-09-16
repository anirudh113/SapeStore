package com.sapestore.vo;

import java.io.Serializable;

/**
 * BRAAVOS
 * @author asiraj
 * 
 *
 */
public class PurchasedUpdate implements Serializable {

	private boolean dispatchStatus;
	private boolean paymentStatus;

	public PurchasedUpdate(boolean dispatchStatus, boolean paymentStatus) {
		this.dispatchStatus = dispatchStatus;
		this.paymentStatus = paymentStatus;
	}

	public PurchasedUpdate() {
	}

	public boolean getDispatchStatus() {
		return dispatchStatus;
	}

	public void setDispatchStatus(boolean dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}

	public boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
