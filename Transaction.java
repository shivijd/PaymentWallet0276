package com.capgemini.beans;

import java.math.BigDecimal;

public class Transaction {
int id;
String mobileNo;
BigDecimal Amount;
String TypeOfTrans;
public String getTypeOfTrans() {
	return TypeOfTrans;
}
public void setTypeOfTrans(String typeOfTrans) {
	TypeOfTrans = typeOfTrans;
}
public int  getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMobileNo() {
	return mobileNo;
}
@Override
public String toString() {
	return "Transaction [id=" + id + ", mobileNo=" + mobileNo + ", Amount=" + Amount + ", TypeOfTrans=" + TypeOfTrans
			+ "]";
}
public Transaction(String typeOfTrans) {
	super();
	TypeOfTrans = typeOfTrans;
}
public Transaction(int id, String mobileNo, BigDecimal amount) {
	super();
	this.id = id;
	this.mobileNo = mobileNo;
	Amount = amount;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public BigDecimal getAmount() {
	return Amount;
}
public void setAmount(BigDecimal amount) {
	Amount = amount;
}
}
