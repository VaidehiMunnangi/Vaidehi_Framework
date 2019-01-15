package designPattern;

import commonLibs.utilities.ExcelDriver;

public class DemoExcelDriver {

	public static void main(String[] args) throws Exception {

		String fileName = "C:\\Users\\VAIDEHI\\git\\Vaidehi_Framework\\Vaidehi_Framework\\OutputFolder\\ExcelInsertDataTest.xls";
		String sheetName = "Data";
		ExcelDriver excelDriver = new ExcelDriver();
		excelDriver.createAWorkbook(fileName);
		excelDriver.openAWorkbook(fileName);
		excelDriver.createASheet(sheetName);
		excelDriver.setCellData(sheetName, 0, 0, "A");
		excelDriver.setCellData(sheetName, 0, 1, "B");
		excelDriver.setCellData(sheetName, 0, 2, "C");
		excelDriver.setCellData(sheetName, 1, 0, "D");
		excelDriver.setCellData(sheetName, 1, 1, "E");
		excelDriver.setCellData(sheetName, 1, 2, "F");
		excelDriver.saveWorkbook();
		excelDriver.closeAWorkbook();
		
	}

}
