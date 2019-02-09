package com.capgemini.beans;

import java.util.LinkedList;

public class Customer {
private String name;
private LinkedList<Transaction> li=new LinkedList();
private String mobileNo;
//public LinkedList<Transaction> Transaction();
private Wallet wallet;
public LinkedList<Transaction> getLi() {
	return li;
}
public void setLi(LinkedList<Transaction> li) {
	this.li = li;
}
public Customer(String name,String mobileNo,Wallet wallet)
{
	this.mobileNo=mobileNo;
	this.name=name;
	this.wallet=wallet;
}
public Wallet getWallet() {
	return wallet;
}
public void setWallet(Wallet wallet) {
	this.wallet = wallet;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "Customer [name=" + name + ", mobileNo=" + mobileNo + ", wallet=" + wallet + "]";
}
}
