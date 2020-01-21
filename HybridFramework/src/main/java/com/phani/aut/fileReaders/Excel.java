package com.phani.aut.fileReaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	private static Map<String, String[]> map = new HashMap<>();

	public static String[] getTestData(String key) {
		return map.get(key);
	}

	public static void readFile(String path) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFWorkbook book = null;
		try {
			book = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = book.getSheetAt(0);

		int rows = sheet.getLastRowNum() + 1;
		int cells = sheet.getRow(0).getLastCellNum();

		for (int i = 1; i < rows; i++) {
			Row row = sheet.getRow(i);
			String[] str = new String[cells - 1];
			String key = null;
			for (int j = 1; j < cells; j++) {
				Cell cell = row.getCell(j);
				if (j == 1) {
					key = row.getCell(0).getStringCellValue();
				}
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					str[j - 1] = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					str[j - 1] = Long.toString(Math.round(cell.getNumericCellValue()));
					break;
				case Cell.CELL_TYPE_BLANK:
					str[j - 1] = "";
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					str[j - 1] = Boolean.toString(cell.getBooleanCellValue());
					break;
				}
			}
			map.put(key, str);
		}
	}

}
