package exceldemo;

import java.io.File;
import java.io.FileInputStream;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Read {
	
	public  String[][] credentials() throws Exception {
	
		String[][] data = null;
		
		//Open the Excel File(specify the Path)
		File src = new File(".//Test Data//Login.xlsx");
		FileInputStream fis = new FileInputStream(src);
		
		//OpenWorkBook
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		//Enter into the Sheet(Using Sheet Name)
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		//Row Count
		int rowCount = sheet.getLastRowNum();
		System.out.println(":: Total Rows :: "+rowCount);

		//Column Count
		int columnCount = sheet.getRow(0).getLastCellNum();
		System.out.println(":: Total Cols :: "+columnCount);
		
		
		data = new String[rowCount][columnCount];
		
		//for Loop (Rows)
		for (int i = 0; i <rowCount; i++) 
		{
			//Enter into the rows
			XSSFRow row = sheet.getRow(i);			

			//for Loop (columns)
			for (int j = 1; j <columnCount; j++) 
			{
				//Enter into the Column
				XSSFCell cell = row.getCell(j);
				XSSFCell TC  = row.getCell(0);
				String verify = "tc_01";
				String TestCase = TC.getStringCellValue();
				
				//if(TestCase.equalsIgnoreCase(verify))
				//{
				//Read the String Value
				//System.out.print(cell.getStringCellValue()+" || ");
				data[i][j] = cell.getStringCellValue();
				System.out.print(data[i][j]+" || ");
				
				//}
				
				
				
			}
		System.out.println();
		}
		
				wb.close();
				return data;
		

	}
	public static void main(String[] args) throws Exception {
		Read obj = new Read();
		obj.credentials();
	}

	}
