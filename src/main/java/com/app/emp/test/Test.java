package com.app.emp.test;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Test {

	public static void main(String[] args) {
//		double payment = 90000.9;
		String regex ="^[a-zA-Z0-9]+";
		System.out.println("ST0001".matches(regex));
		
//		Locale locale = new Locale("en","IN");
//		Currency currency = Currency.getInstance("INR");
//		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
////		nf.setCurrency(currency);
//		System.out.println(nf.format(payment));
	}

}
