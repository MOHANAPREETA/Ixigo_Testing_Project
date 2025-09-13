package com.utils;

public class TestDataContext {
	public static String[][] excelData;
	public static int currentRow = 0;

	public static void loadExcelData() {
		if (excelData == null) {
			Exceldata_provider.excel();
			excelData = Exceldata_provider.excelData;
		}
	}
}
