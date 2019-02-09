package com.capgemini.payment.bean.Service;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Transaction;
import com.capgemini.beans.Wallet;
import com.capgemini.payment.bean.Customer.WalletRepo;
import com.capgemini.payment.bean.Excep.InvalidAmountPresentException;
import com.capgemini.payment.bean.Excep.InvalidNameException;
import com.capgemini.payment.bean.Excep.InvalidPhoneNumberException;
import com.capgemini.payment.bean.Excep.MobileNumberAlreadyExistException;

public class WalletServiceImplementation implements WalletService {
	static int count=1;
	WalletRepo wa ;
	public WalletServiceImplementation(WalletRepo wa2) {
	super();
	this.wa=wa2;
	}

	@Override
	public Customer createAccount(String name, String mobileNo, BigDecimal Amount) throws InvalidPhoneNumberException, InvalidNameException, InvalidAmountPresentException, MobileNumberAlreadyExistException{		
	Wallet wall=new Wallet(Amount);
		Customer customer=new Customer(name, mobileNo, wall);
	    if(validate(customer))
		if(validAmount(Amount))
		if(name==" ")
			throw new InvalidNameException();
	    if(repeatedAcc(mobileNo))
		if(wa.save(customer))
		return customer;	
	return null;
	}

	public Customer showBalance(int id,String mobileNo) {
		Customer customer=wa.findByOne(mobileNo);
		customer.getWallet().getBalance();
		Transaction trans=new Transaction(id, mobileNo,  customer.getWallet().getBalance());
		trans.setId(count++);
		trans.setTypeOfTrans("Show Balance");
		customer.getLi().add(trans);
		return customer;
	}

	@Override
	public Customer depositAmount(int id,String mobileNo, BigDecimal Amount)throws InvalidPhoneNumberException {
		Customer customer=wa.findByOne(mobileNo);
		if(customer==null)
			throw new InvalidPhoneNumberException();
	customer.getWallet().setBalance(customer.getWallet().getBalance().add(Amount));
	Transaction trans=new Transaction(id, mobileNo,  customer.getWallet().getBalance());
	trans.setId(count++);
	trans.setTypeOfTrans("Deposit");
	customer.getLi().add(trans);
		return customer;
	}

	@Override
	public Customer withdrawAmount(int id,String mobileNo, BigDecimal Amount)throws InvalidAmountPresentException,InvalidPhoneNumberException {
		if(validAmount(Amount))
			throw new InvalidAmountPresentException();
		
		Customer customer=wa.findByOne(mobileNo);
		if(customer==null)
			throw new InvalidPhoneNumberException();
		customer.getWallet().setBalance(customer.getWallet().getBalance().subtract(Amount));
		Transaction trans=new Transaction(id, mobileNo, customer.getWallet().getBalance());
		trans.setId(count++);
		trans.setTypeOfTrans("Withdraw");
		customer.getLi().add(trans);
		return customer;
	}

	@Override
	public Customer fundTransfer(int id,String sourceMobNo, String Target, BigDecimal Amount) {
		Customer customer=wa.findByOne(sourceMobNo);
		Customer customer1=wa.findByOne(Target);
		customer1.getWallet().setBalance(customer1.getWallet().getBalance().add(Amount));
		
		//FUND GET RECIEVED TO OTHER ACCOUNT
		
		Transaction trans=new Transaction(id, Target,customer1.getWallet().getBalance());
		trans.setId(count++);
		trans.setTypeOfTrans("Recieved");
		customer1.getLi().add(trans);
		customer1.getWallet().setBalance(customer.getWallet().getBalance().subtract(Amount));
		
		//FUND TRANSFER FROM FIRST ACCOUNT
		
		Transaction trans1=new Transaction(id, sourceMobNo,customer.getWallet().getBalance() );
		trans1.setId(count++);
		trans1.setTypeOfTrans("Transfer");
		customer.getLi().add(trans1);
		return customer1;
	}

	@Override
	public boolean validate(Customer c) throws InvalidPhoneNumberException, InvalidNameException{
		if (!(c.getMobileNo().matches("[7-9][0-9]{9}"))) 
			throw new InvalidPhoneNumberException();
		 if(!(c.getName().matches("^[a-zA-Z]{3,9}")))
		   throw new InvalidNameException();	
		 return true;
	
			
	
	}
	@Override
public boolean validAmount(BigDecimal Amount)throws InvalidAmountPresentException
{
	// if(!((c.getWallet().getBalance().compareTo(BigDecimal.ZERO)>0)))
	if(!(Amount.compareTo(BigDecimal.ZERO)>0))
			   throw new InvalidAmountPresentException();
	 return false;
}
public boolean repeatedAcc(String mobileNo)throws MobileNumberAlreadyExistException
{
	Customer temp=null;
	temp=wa.findByOne(mobileNo);
	if(temp!=null)
	throw new MobileNumberAlreadyExistException();
	return true;
			
}

@Override
public List<Transaction> showTransaction(String mobileNo) {	
	return wa.findByOne(mobileNo).getLi();
}
}
