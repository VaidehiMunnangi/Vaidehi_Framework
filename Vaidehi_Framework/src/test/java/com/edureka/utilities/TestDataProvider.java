package com.edureka.utilities;

import org.testng.annotations.Test;

public class TestDataProvider {

	@Test(dataProvider = "getData", dataProviderClass = DataProviderUtils.class)
	public void printData(String a, String b, String c) {
		
		System.out.println("data: " + a);
		System.out.println("data: " + b);
		System.out.println("data: " + c);
			
	}
}
