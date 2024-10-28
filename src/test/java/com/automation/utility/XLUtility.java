package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.automation.pages.BasePage;

public class XLUtility {
	
	
	private static Logger mylog=LogManager.getLogger(XLUtility.class);
	
	public void readSingleData(String filePath, String sheetname, int rownum, int colnum) throws IOException {
		String Dirpath = System.getProperty("user.dir");
		FileInputStream fi = new FileInputStream(new File(Dirpath + filePath));
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet sheet = wb.getSheet(sheetname);
		XSSFRow row = sheet.getRow(rownum);

		XSSFCell cell = row.getCell(colnum);

		if (cell.getCellType() == CellType.STRING)
			System.out.println(cell.getStringCellValue());
		else if (cell.getCellType() == CellType.NUMERIC)
			System.out.println(cell.getNumericCellValue());

		wb.close();
		fi.close();

		// ""
	}

	public static  Object[][] readAllDataFromXLToArray(String filePath, String sheetname) {
		Object[][] data = null;
		XSSFWorkbook workbook = null;
		FileInputStream file = null;
		System.out.println("numb or rows ");
		
		try {
			file = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetname);
			int rows = sheet.getPhysicalNumberOfRows();
			//int rows = sheet.getLastRowNum();
			mylog.info("numb or rows "+ rows);
			int cols = sheet.getRow(0).getLastCellNum();
			mylog.info("numb or cols "+ cols);
			data = new Object[rows][cols];
			for (int i = 1; i < rows; i++) {
				mylog.info("row itteration "+ i);
				Row row = sheet.getRow(i);
				for (int j = 0; j < cols; j++) {
					Cell cell = row.getCell(j);
					mylog.info("cell itteration "+ j);
					data[i][j] = cell.toString();
					//mylog.info("data itteration "+ cell.toString());
					mylog.info("data itteration "+ data[i][j]);
				}
			}
		} 
		catch (Exception e) {
			System.out.println("exception caught while reading data from xlsx sheet");
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return data;
	}

}
