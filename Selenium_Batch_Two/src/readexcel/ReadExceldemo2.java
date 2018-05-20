package readexcel;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExceldemo2 {
	@Test
	public void readdata() {
		
		try {
		  // Specify the path of file
		  File src=new File("D:\\Batch -2 Workspace\\Selenium_Batch_Two\\TestData\\readexceldemo1.xlsx");
		 
		   // load file
		   FileInputStream fis=new FileInputStream(src);
		 
		   // Load workbook
		   XSSFWorkbook wb=new XSSFWorkbook(fis);
		   
		   // Load sheet- Here we are loading first sheetonly
		      XSSFSheet sh1= wb.getSheetAt(0);
		     int rowcount= sh1.getLastRowNum();
		 		System.out.println("Total row count is"+rowcount);
		 		for(int i=0;i<rowcount;i++) {
		 			String data0=sh1.getRow(i).getCell(0).getStringCellValue();
		 			System.out.println("Data from Row "+i+" is "+data0);
		 			String data1=sh1.getRow(i).getCell(1).getStringCellValue();
		 			System.out.println("Data from Row "+i+" is "+data1);
		 		
		 		}
		
		 
		  } catch (Exception e) {
		 
		   System.out.println(e.getMessage());
		 
		  }
		  
		 }
		 
}
