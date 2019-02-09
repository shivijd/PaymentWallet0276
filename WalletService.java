package com.capgemini.payment.bean.Service;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Transaction;
import com.capgemini.payment.bean.Excep.InvalidAmountPresentException;
import com.capgemini.payment.bean.Excep.InvalidNameException;
import com.capgemini.payment.bean.Excep.InvalidPhoneNumberException;
import com.capgemini.payment.bean.Excep.MobileNumberAlreadyExistException;

public interface WalletService {
        public Customer createAccount(String name,String mobileNo,BigDecimal Amount) throws InvalidPhoneNumberException, InvalidNameException,InvalidAmountPresentException, MobileNumberAlreadyExistException;
        public Customer showBalance(int id,String mobileNo);
        public Customer depositAmount(int id,String mobileNo,BigDecimal Amount) throws InvalidPhoneNumberException;
        public Customer withdrawAmount(int id,String mobileNo,BigDecimal Amount) throws InvalidAmountPresentException, InvalidPhoneNumberException;
        public boolean validAmount(BigDecimal Amount) throws InvalidAmountPresentException;
        public Customer fundTransfer(int id,String sourceMobNo,String Target,BigDecimal Amount);
        public boolean validate(Customer c) throws InvalidPhoneNumberException, InvalidNameException;
        public List<Transaction> showTransaction(String mobileNo);
}
