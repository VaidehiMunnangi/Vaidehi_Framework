package commonLibs.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {

	// To read from a file or Input device
	private InputStream fileReader;

	// To write to a file or Output device
	private OutputStream fileWriter;

	// ForExcelsheetWorkbook an interface from POI Apache
	private Workbook excelWorkbook;
	
	private Sheet sheet;
	
	private String fileName;

	// Creating empty Workbook
	public void createAWorkbook(String fileName) throws Exception {

		fileName = fileName.trim();
		File file = new File(fileName);
		if (file.exists()) {
			throw new Exception("File already exists... create a new file with different name");
		}
		if (fileName.endsWith(".xls")) {
			excelWorkbook = new HSSFWorkbook();
		} else if (fileName.endsWith(".xlsx")) {
			excelWorkbook = new XSSFWorkbook();
		} else {
			throw new Exception("Invalid file Extension");
		}

		// To create an empty excel sheet
		fileWriter = new FileOutputStream(fileName);
		excelWorkbook.write(fileWriter);

		fileWriter.close();
		excelWorkbook.close();

	}

	public void openAWorkbook(String fileName) throws Exception {

		fileName = fileName.trim();

		this.fileName = fileName;
		
		File file = new File(fileName);
		if (!file.exists()) {
			throw new Exception("File does not exists");
		}
		fileReader = new FileInputStream(fileName);
		excelWorkbook = WorkbookFactory.create(fileReader);
	}
	
	public void createASheet(String sheetName) throws Exception {
		
		sheetName = sheetName.trim();
		sheet = excelWorkbook.getSheet(sheetName);
		if(sheet != null) {
			throw new Exception("Sheet already exists");
		}
		excelWorkbook.createSheet(sheetName);
	}
	
	public int getRowCountFromASheet(String sheetName) throws Exception {
		
		sheetName = sheetName.trim();
		verifyIfSheetIsNullOrNot(sheetName);
		return sheet.getLastRowNum();
		
	}
	
	public int getCellCountFromARowOfASheet(String sheetName,int rowNumber) throws Exception {
		
		sheetName = sheetName.trim();
		verifyIfSheetIsNullOrNot(sheetName);
		
		if(rowNumber <0) {
			throw new Exception("Row number should be >= 0");
		}
		
		Row row;
		row = sheet.getRow(rowNumber);
		if(row == null) {
			return 0;
		}
		else {
		return row.getLastCellNum();
		}
	}
	
	public String getCellData(String sheetName, int rowNumber, int cellNumber) throws Exception {
		
		sheetName = sheetName.trim();
		verifyIfSheetIsNullOrNot(sheetName);
		if(rowNumber < 0 || cellNumber < 0) {
			throw new Exception("Invalid Row or Cell Number");
		}
		Row row = sheet.getRow(rowNumber);
		if(row == null) {
			return "";
		}
			
		Cell cell = row.getCell(cellNumber);
		if(cell == null) {
			return "";
		}
		else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		}
		else {
			return cell.getStringCellValue();
		}
		}
	
	public void setCellData(String sheetName, int rowNumber, int cellNumber, String cellData) throws Exception {
		
		sheetName = sheetName.trim();
		verifyIfSheetIsNullOrNot(sheetName);
		if(rowNumber < 0 || cellNumber < 0) {
			throw new Exception("Invalid Row or Cell Number");
		}
		Row row = sheet.getRow(rowNumber);
		if(row == null) {
			row = sheet.createRow(rowNumber);
			row = sheet.getRow(rowNumber);
		}
		Cell cell = row.getCell(cellNumber);
		if(cell == null) {
			cell = row.createCell(cellNumber);
			cell = row.getCell(cellNumber);
		}
		cell.setCellValue(cellData);
	}
	
	public void saveWorkbook() throws Exception {
		
		fileWriter = new FileOutputStream(fileName);
		excelWorkbook.write(fileWriter);
		fileWriter.close();
	}
	
	public void saveAsWorkbook(String newFileName) throws Exception {
		
		newFileName = newFileName.trim();
		File file = new File(newFileName);
		if(file.exists()) {
			throw new Exception("File already exists");			
		}
		
		fileWriter = new FileOutputStream(newFileName);
		excelWorkbook.write(fileWriter);
		fileWriter.close();
	}
	
	public void closeAWorkbook() throws IOException {
		fileReader.close();
		fileWriter.close();
		excelWorkbook.close();
	}
	
	private void verifyIfSheetIsNullOrNot(String sheetName) throws Exception {
		
		sheet = excelWorkbook.getSheet(sheetName);
		if(sheet == null) {
			throw new Exception("Sheet does not exist");
		}
	}

}