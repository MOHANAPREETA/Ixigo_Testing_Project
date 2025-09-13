package com.utils;

import com.parameters.Excel_reader;

public class Exceldata_provider {

	public static String[][] excelData;
	public static int currentrow = 0;

	public static void excel() {

		try {
			excelData = Excel_reader.readData();
		} catch (Exception e) {
			e.printStackTrace();
			// Handle the exception appropriately, e.g., by throwing a more specific
			// exception
			throw new RuntimeException("Failed to read data from Excel file.", e);
		}

	}
}