package com.capgemini.mainis;
import java.math.BigDecimal;
import java.util.List;

import com.capgemini.beans.Transaction;
import com.capgemini.payment.bean.Customer.WalletRepo;
import com.capgemini.payment.bean.Customer.WalletRepoImplementation;
import com.capgemini.payment.bean.Excep.InvalidAmountPresentException;
import com.capgemini.payment.bean.Excep.InvalidNameException;
import com.capgemini.payment.bean.Excep.InvalidPhoneNumberException;
import com.capgemini.payment.bean.Excep.MobileNumberAlreadyExistException;
import com.capgemini.payment.bean.Service.WalletService;
import com.capgemini.payment.bean.Service.WalletServiceImplementation;

public class MainCla {
public static void main(String[] args) throws InvalidPhoneNumberException, InvalidNameException, InvalidAmountPresentException, MobileNumberAlreadyExistException {
	WalletRepo wa=new WalletRepoImplementation();
	WalletService ws=new WalletServiceImplementation(wa);
	
System.out.println("initially when account is created balance is"+" "+ws.createAccount("Shivi", "7500725707",new BigDecimal("198.176")));
	System.out.println(ws.createAccount("Abhishek", "9917110325", new BigDecimal("200.00")));
	System.out.println();
	ws.withdrawAmount(0, "7500725707", new BigDecimal("40.66"));
	ws.depositAmount(0, "7500725707", new BigDecimal("53.43"));
	 ws.fundTransfer(0, "9917110325", "7500725707", new BigDecimal("5.6"));
    ws.showBalance(0, "7500725707");
   
	List<Transaction> list=ws.showTransaction("7500725707");
	
	for(Transaction i:list)
	{
		System.out.println(list);
	}
	
}
}
