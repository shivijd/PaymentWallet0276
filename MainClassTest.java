package com.capgemini.testt;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import com.capgemini.payment.bean.Customer.WalletRepo;
import com.capgemini.payment.bean.Customer.WalletRepoImplementation;
import com.capgemini.payment.bean.Excep.InvalidAmountPresentException;
import com.capgemini.payment.bean.Excep.InvalidNameException;
import com.capgemini.payment.bean.Excep.InvalidPhoneNumberException;
import com.capgemini.payment.bean.Excep.MobileNumberAlreadyExistException;
import com.capgemini.payment.bean.Service.WalletService;
import com.capgemini.payment.bean.Service.WalletServiceImplementation;

public class MainClassTest {
	WalletService  wa; 
	@Before
	public void setUp() throws Exception {
		  WalletRepo repo=new WalletRepoImplementation();
	 wa=new WalletServiceImplementation(repo);
	}

	@Test(expected=com.capgemini.payment.bean.Excep.InvalidAmountPresentException.class)
	public void testValidAmountIsNotPresentThrowException() throws InvalidAmountPresentException, InvalidPhoneNumberException, InvalidNameException, MobileNumberAlreadyExistException
	{
	  wa.createAccount("shivi", "7500725707", new BigDecimal("0.000"));
	}
	   @Test
	   public void testwhenValidAmountIsPresent()throws InvalidAmountPresentException,InvalidPhoneNumberException,InvalidNameException, MobileNumberAlreadyExistException
	   {
		   //Customer c1=new Customer(null, null, null);		   
		   wa.createAccount("shivi", "7500725707", new BigDecimal("200.01"));
		 //  assertEquals(wa.createAccount("shivi", "7500725707", new BigDecimal("200.01")).getWallet().getBalance().intValue(),new BigDecimal("200.01").intValue());
	   }
   @Test(expected=com.capgemini.payment.bean.Excep.MobileNumberAlreadyExistException.class)
   public void testMobileNumberIsAlreadyPresent()throws MobileNumberAlreadyExistException, InvalidPhoneNumberException, InvalidNameException, InvalidAmountPresentException
   {
	   wa.createAccount("shivi", "7500725707", new BigDecimal("5967.896"));
	   wa.createAccount("vishnu","7500725707",new BigDecimal("10983.56"));
   }
   @Test
   public void testMobileNumberIsNotPresent()throws MobileNumberAlreadyExistException, InvalidPhoneNumberException, InvalidNameException, InvalidAmountPresentException
   {
	   wa.createAccount("shivi", "7500725707", new BigDecimal("5967.896")); 
	   wa.createAccount("vishnu", "9917110325",  new BigDecimal("5685.96"));
   }
   @Test(expected=com.capgemini.payment.bean.Excep.InvalidAmountPresentException.class)
   public void testifAmountIsNotPresentForWithdraw() throws InvalidAmountPresentException, InvalidPhoneNumberException, InvalidNameException, MobileNumberAlreadyExistException
   {  wa.createAccount("shivi", "7500725707",  new BigDecimal("0.000"));
	   wa.withdrawAmount(1, "7500725707",  new BigDecimal("50.000"));
   }
  @Test
  public void testifAmountIsPresentWithdrawDoneSuccessfully()throws InvalidAmountPresentException,InvalidPhoneNumberException,InvalidNameException,MobileNumberAlreadyExistException
  {
	  wa.createAccount("shivi", "7500725707",new BigDecimal("233145.45") );
	  wa.withdrawAmount(2, "7500725707", new BigDecimal("3744.54"));

  }
  @Test(expected=com.capgemini.payment.bean.Excep.InvalidPhoneNumberException.class)
 public void testifAmountGetTransferToWrongMobileNumber() throws InvalidPhoneNumberException, InvalidNameException, InvalidAmountPresentException, MobileNumberAlreadyExistException
 {
	 wa.createAccount("shivi", "7500725707", new BigDecimal("3744.54"));
	 wa.withdrawAmount(2, "9450033127",new BigDecimal("37.54") );
  }
  @Test(expected=com.capgemini.payment.bean.Excep.InvalidPhoneNumberException.class)
  public void testifAmountTransferToWrongMobileNumber() throws InvalidPhoneNumberException, InvalidNameException, InvalidAmountPresentException, MobileNumberAlreadyExistException
  {
	wa.createAccount("shivi", "7500725707", new BigDecimal("3744.54"))  ;
	wa.depositAmount(2, "9450033127", new BigDecimal("37.54"));
  }
  @Test
  public void testifAmountIsPresentDepositDoneSuccessfully()throws InvalidAmountPresentException,InvalidPhoneNumberException,InvalidNameException,MobileNumberAlreadyExistException
  {
		wa.createAccount("shivi", "7500725707", new BigDecimal("3744.54"))  ;
		wa.depositAmount(2, "7500725707", new BigDecimal("3744.54"));

  }
}
