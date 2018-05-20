package lib;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	XSSFWorkbook wb;
	XSSFSheet sh1;
	public ExcelDataConfig(String excelpath) {
		try {

			File src=new File(excelpath);

			FileInputStream fis=new FileInputStream(src);

			wb=new XSSFWorkbook(fis);

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public String getData(int sheetnum ,int row,int col) {
		sh1= wb.getSheetAt(sheetnum);
		String data=sh1.getRow(row).getCell(col).getStringCellValue();
		return data;
	}
	public int getRowCount(int sheetIndex) {
		int row=wb.getSheetAt(sheetIndex).getLastRowNum();
		row=row+1;
		return row;
	}
}

