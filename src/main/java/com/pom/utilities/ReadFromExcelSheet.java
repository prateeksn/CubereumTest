package com.pom.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.mysql.jdbc.StandardSocketFactory; 


public class ReadFromExcelSheet
{
	// Method to read the values from the excel 
	public static  Map<String,Map<String,String>> readFromExcel(String filePath, String fileName, String[] columns)
	{
		String configPath = "", expRowValues = "";
		String allRowValues = "";
		String fileVar = "", fileSend = "";
		// To get canonical path..
		try {
				configPath = StringUtils.stripEnd(filePath, "/\\" );
				configPath = (new File(filePath)).getCanonicalPath();
			}
		 catch (IOException e1)
		{
			e1.printStackTrace();
		}
		fileVar = configPath + "\\";
		fileSend = fileVar + fileName;
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(fileSend));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		// Create Workbook instance holding reference to .xlsx file
		org.apache.poi.ss.usermodel.Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(file);
		} 
		catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// Iterate through each rows one by one
		Sheet sheet = workbook.getSheetAt(0);
		Map<String,Map<String,String>> excelData = new HashMap<>();
		sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator1 = sheet.iterator();

		String rowValue1 = "";

		if (rowIterator1.hasNext())
			rowIterator1.next();
		try {
			while (rowIterator1.hasNext()) {
				Row row1 = rowIterator1.next();
				Map<String,String> filednamevalueMap=new HashMap<>();
				// For each row, iterate through all the columns
				for(int column = 0;column < columns.length ; column++) {
					String headerColumn = "";
					
					if(column == 0) {
						Cell cell = row1.getCell(column);
						headerColumn = cell.getStringCellValue();
						excelData.put(headerColumn,filednamevalueMap);
						continue;
					}
					Cell cell = row1.getCell(column);
					rowValue1=switchCondition(cell);
					filednamevalueMap.put(columns[column], rowValue1);
					if(headerColumn.trim()!="" && headerColumn !=null)
					  excelData.put(headerColumn,filednamevalueMap);
				}			
			}
			
		} 
		
		catch (Exception e) {
			// no exception
		}
		
		
		return excelData;
	
	}
	
	private static String switchCondition(Cell cell)
	{
		String rowValue1="";
		try {
		
		if(cell!=null)
		{
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				rowValue1 = String.valueOf(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				rowValue1 = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				rowValue1 = cell.getStringCellValue();
				break;
			
			default:
			rowValue1 = cell.getStringCellValue();
			break;
			}
		}
	}
		catch(Exception e)
		{
			String trace= e.getStackTrace().toString();
			//System.out.println(trace);
		}
		return rowValue1;
	}
	
 }
