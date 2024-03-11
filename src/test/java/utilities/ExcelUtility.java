package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	String filePath= "F:\\Mphasis Practice Eclipse\\TricentisPracticeExercise\\src\\test\\resources\\TestData.xlsx";
	Workbook book;
	Sheet sheet;
	
	public Object[][] getTestData(String sheetName) throws EncryptedDocumentException, IOException {
		
		FileInputStream file=new FileInputStream(filePath);
		
		book=WorkbookFactory.create(file);
		sheet=book.getSheet(sheetName);
		
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(i).getLastCellNum();j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				System.out.println(sheet.getRow(i+1).getCell(j).toString());
			}
		}
		return data;
	}
	
	
}
