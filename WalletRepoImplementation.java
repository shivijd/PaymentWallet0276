package com.capgemini.payment.bean.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.capgemini.beans.Customer;

public class WalletRepoImplementation implements WalletRepo {
 Map<String,Customer> map=new HashMap<>();
	@Override
	public boolean save(Customer c) {
	if(map.containsKey(c.getMobileNo()))
		return false;
	map.put(c.getMobileNo(), c);
	return true;
	}

	@Override
	public Customer findByOne(String mobileNo) {		
		for(Entry<String, Customer> entry:map.entrySet())
			if(entry.getKey().equals(mobileNo))
			 return (entry.getValue());
		return null;
	
	}
//@Override
//	public boolean validate(Customer c) {
//			String input=c.getMobileNo();
//			Pattern pattern=Pattern.compile("^[7-9]{10}");
//			Matcher m=pattern.matcher(input);
//			if(!m.find())
//				return false;
//		return true;
//	}


}
