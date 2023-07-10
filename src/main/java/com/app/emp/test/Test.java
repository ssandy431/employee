package com.app.emp.test;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Test {

	public static void main(String[] args) {
		double payment = 90000.9;
		
//		Locale locale = new Locale("en","IN");
		Currency currency = Currency.getInstance("INR");
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
//		nf.setCurrency(currency);
		System.out.println(nf.format(payment));
	}

}
