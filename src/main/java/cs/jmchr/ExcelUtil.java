package cs.jmchr;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtil {
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
	
	public static List<Object[]> readExcelFile(String filePath,int sheetIndex, String[] fields) {
		List<Object[]> lstModel = new ArrayList<Object[]>();
		try
        {
			File inputFile = new File(filePath);
			FileInputStream file = new FileInputStream(inputFile);
            //Create Workbook instance holding reference to .xlsx file
			Workbook workbook = null;
			String ext = filePath.substring(filePath.length() - 3);
			if ("xls".equals(ext)) {
				workbook = new HSSFWorkbook(file);
			} else {
				workbook = new XSSFWorkbook(file);
			}
			//Get first/desired sheet from the workbook
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
                        
            int[] indexs = new int[fields.length];
            for (int i = 0; i < indexs.length; i++) indexs[i] = -1;
            
            boolean isStarted = false;
                        
            while (rowIterator.hasNext())
            {
            	
            	Object[] objList = new Object[fields.length];
            	
            	Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    if (isStarted) {
                    	
                    	for (int i = 0; i < indexs.length; i++) {
                    		if (cell.getColumnIndex() == indexs[i]) {
                    			objList[i] = cell.getStringCellValue();
                    		}
                    	}
                    	
	                   /* //Check the cell type and format accordingly
	                    switch (cell.getCellType())
	                    {
	                        case Cell.CELL_TYPE_NUMERIC:
	                            
	                            break;
	                        case Cell.CELL_TYPE_STRING:
	                            
	                            break;
	                            
	                    }*/
                    }
                    
                    for (int i = 0; i < fields.length; i ++) {
                    	String field = fields[i];
                    	int index = indexs[i];
                    	if (index == -1) {
                    		if (field.equals(cell.getStringCellValue())) {
                    			indexs[i] = cell.getColumnIndex();
                    		}
                    	}
                    }
                    boolean ok = true;
                    for (int i = 0; i < indexs.length; i++) {
                    	if (indexs[i] == -1) {
                    		ok = false;
                    	}
                    }
                    if (ok) isStarted = true;
                  
                }
                
                if (objList[0] != null) {
                	lstModel.add(objList);
                }
                
            }
            file.close();
            workbook.close();
            
        }
        catch (Exception e)
        {
        	logger.debug("Exception readEmployeeProfile: " + e.getMessage());        	
        }
		
		return lstModel;
	}
	
	
	
}
