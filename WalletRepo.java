package com.capgemini.payment.bean.Customer;

import com.capgemini.beans.Customer;


public interface WalletRepo {
	public boolean save(Customer c);
	public Customer findByOne(String mobileNo);

}
