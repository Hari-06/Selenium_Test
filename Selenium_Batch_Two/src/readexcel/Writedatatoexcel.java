package readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Writedatatoexcel {
	@Test
	public void readdata() throws IOException {

		// Specify the path of file
		File src=new File("D:\\Batch -2 Workspace\\Selenium_Batch_Two\\TestData\\readexceldemo1.xlsx");
		// load file
		FileInputStream fis=new FileInputStream(src);
		// Load workbook
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		// Load sheet- Here we are loading first sheetonly
		XSSFSheet sh1= wb.getSheetAt(0);
		sh1.getRow(0).createCell(2).setCellValue("pass");
		sh1.getRow(1).createCell(2).setCellValue("fail");
		sh1.getRow(2).createCell(2).setCellValue("20.56");
		FileOutputStream fos=new FileOutputStream(src);
		wb.write(fos);
		wb.close();


	}

}
