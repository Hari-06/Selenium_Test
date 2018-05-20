package readexcel;

import lib.ExcelDataConfig;

public class ReadExcelfromLib {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExcelDataConfig excel=new ExcelDataConfig("D:\\Batch -2 Workspace\\Selenium_Batch_Two\\TestData\\readexceldemo1.xlsx");
		System.out.println(excel.getData(0, 0, 0));
	}

}
