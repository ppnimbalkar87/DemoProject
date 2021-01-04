package com.demosite.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {
	XSSFWorkbook wb;
	XSSFSheet sh;
	public ExcelDataProvider()
	{
		File src=new File("./TestData/LoginData.xlsx");
		try 
		{
			FileInputStream fin=new FileInputStream(src);				
			wb=new XSSFWorkbook(fin);
			sh=wb.getSheet("Login");
		} catch (Exception e) {
			System.out.println("Unable to read Excel File "+e.getMessage() );
		}
	}

	public String getStringData(String sheetName,int rowNo,int colNo) 
	{
		return wb.getSheet(sheetName).getRow(rowNo).getCell(colNo).getStringCellValue();
	}
	
	public int getTotalRows(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}
	
	public int getTotalCoulums(String SheetName)
	{
		return wb.getSheet(SheetName).getRow(0).getLastCellNum();
		
	}
	
	
   @DataProvider(name="DataInput")
   public Iterator fetchData()
   {
        ArrayList myData = new ArrayList();
        int numOfRows=sh.getLastRowNum();
        String userName, pass;
        for(int i=0; i<=numOfRows; i++)
        {
            userName = sh.getRow(i).getCell(0).getStringCellValue();
            pass = sh.getRow(i).getCell(1).getStringCellValue();
            myData.add(new Object[]{userName,pass});
        }
        return myData.iterator();
    }
	
	
	
}
