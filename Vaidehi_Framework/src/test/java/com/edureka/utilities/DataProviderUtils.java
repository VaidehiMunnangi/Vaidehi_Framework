package com.edureka.utilities;

import org.testng.annotations.DataProvider;

import commonLibs.utilities.ExcelDriver;

public class DataProviderUtils {
	
	private ExcelDriver excelDriver;
	
	@DataProvider
	public Object[][] getData() throws Exception {
		
		String fileName;
		String sheetName;
		Object[][] data;
		fileName = "C:\\Users\\VAIDEHI\\git\\Vaidehi_Framework\\Vaidehi_Framework\\InputFiles\\InputTestFile.xlsx";
		sheetName = "Data";
		excelDriver = new ExcelDriver();
		excelDriver.openAWorkbook(fileName);
		int rowNumber = excelDriver.getRowCountFromASheet(sheetName);
		int cellNumber = excelDriver.getCellCountFromARowOfASheet(sheetName, rowNumber);
		data = new Object[rowNumber][cellNumber];
		for(int r = 0; r < rowNumber; r++)
			for(int c = 0; c < cellNumber; c++)
				data[r][c] = excelDriver.getCellData(sheetName, r, c);	
		
		return data;
		
	}

}
