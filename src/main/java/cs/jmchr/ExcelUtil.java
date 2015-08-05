package cs.jmchr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtil {
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
	
	public static List<Object[]> readExcelFile(MultipartFile file,int sheetIndex, String[] fields) {
		List<Object[]> lstModel = new ArrayList<Object[]>();
		try
        {
			//File inputFile = new File(filePath);
			//FileInputStream file = new FileInputStream(inputFile);
            //Create Workbook instance holding reference to .xlsx file
			InputStream inputStream = file.getInputStream();
			
			String fileName = file.getOriginalFilename();
			
			Workbook workbook = null;
			String ext = fileName.substring(fileName.length() - 3);
			if ("xls".equals(ext)) {
				workbook = new HSSFWorkbook(inputStream);
			} else {
				workbook = new XSSFWorkbook(inputStream);
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
            inputStream.close();
            workbook.close();
            
        }
        catch (Exception e)
        {
        	logger.debug("Exception readEmployeeProfile: " + e.getMessage());        	
        }
		
		return lstModel;
	}
	
	
	
	
	public static void extractData(MultipartFile file) throws Exception {
		
			//String outSheetName = "Sheet1";//name of sheet

			//XSSFWorkbook outWb = new XSSFWorkbook();
			//XSSFSheet outSheet = outWb.createSheet(outSheetName) ;
			
			
			InputStream inputStream = file.getInputStream();
			
			String fileName = file.getOriginalFilename();
			
			Workbook workbook = null;
			String ext = fileName.substring(fileName.length() - 3);
			if ("xls".equals(ext)) {
				workbook = new HSSFWorkbook(inputStream);
			} else {
				workbook = new XSSFWorkbook(inputStream);
			}
			//Get first/desired sheet from the workbook
			Sheet sheet = workbook.getSheetAt(0);
			
            //Iterate through each rows one by one
                        
			int minRowIx = sheet.getFirstRowNum();
            int maxRowIx = sheet.getLastRowNum();
            for(int rowIx=minRowIx; rowIx<maxRowIx; rowIx++) {
        		Row row = sheet.getRow(rowIx);
        		
        		 if(row == null) {
                    continue;
                  }
        	
        		short minColIx = row.getFirstCellNum();
                //short maxColIx = row.getLastCellNum();
              
                Cell cell = row.getCell(minColIx);
                
                double workingHourSum = 0;
                double workingDate = 0;
                
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                	 	                    		
            		for (int step = 1; step < 32; step++) {
            			
            			int workingIndex = minColIx + 2 * step + 1;
            			int OTIndex = minColIx + 2 * step + 2;
            			
            			Cell timeInCell = row.getCell(workingIndex);
                		Cell timeOutCell = row.getCell(OTIndex);
                		
                		if (HSSFDateUtil.isCellDateFormatted(timeInCell) & HSSFDateUtil.isCellDateFormatted(timeOutCell)) {
                			Date timeIn = timeInCell.getDateCellValue();
                    		Date timeOut = timeOutCell.getDateCellValue();
                    		if (timeOut != null && timeIn != null) {
                    			long secs = (timeOut.getTime() - timeIn.getTime()) / 1000;
	                    		double hours = secs / 3600;    
	                    		secs = secs % 3600;
	                    		double mins = secs / 60;
	                    		double hourext = mins/60;
	                    		double realHour = hours + hourext;
	                    		  			                    		
	                    		Row nextRow = sheet.getRow(rowIx+1);
	                    		
	                    		//Cell workingHourCell = nextRow.getCell(workingIndex);
	                    		
	                    		/*boolean isOk = false;
	                    		
	                    		double workingHourValue = 0;
	                    		if (workingHourCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	                    			isOk = true;
	                    			workingHourValue = workingHourCell.getNumericCellValue();
	                    		} else if (workingHourCell.getCellType() == Cell.CELL_TYPE_STRING) {
	                    			String tempValue = workingHourCell.getStringCellValue();
	                    			workingHourValue = toNumeric(tempValue);
	                    			
	                    			if (workingHourValue != -1) {
	                    				isOk = true;
	                    				
	                    			}
	                    		} else if (workingHourCell.getCellType() == Cell.CELL_TYPE_BLANK) {
	                    			workingHourValue = 8;
	                    				isOk = true;
	                    		}*/
	                    		
	                    			double workingHourValue = 8;
	                    		
	                    			Cell workingHourCell = nextRow.getCell(workingIndex);
	                    			Cell OTHourCell = nextRow.getCell(OTIndex);
	                    			
	                    			long iPart = (long)realHour;
	                    			
	                    			double fPart = realHour - iPart;
	                    			
	                    			double outValue = realHour;
	                    			if (fPart <= 0.2) {
	                    				outValue = iPart;
	                    			} else if (fPart <= 0.7) {
	                    				outValue = iPart + 0.5;
	                    			}  else {
	                    				outValue = iPart + 1;
	                    			}
	                    			
	                    			
	                    			double otHour =  outValue - workingHourValue;
	                    			
	                    			if (otHour < 0) {
	                    				workingHourValue = outValue;
	                    				otHour = 0;
	                    			} 
	                    			
	                    			if (workingIndex == 11 || workingIndex == 25 || workingIndex == 39 || workingIndex == 53) {
	                    				//Weekend
	                    				workingHourValue = 0;
	                    				otHour = outValue;
	                    			}
	                    			
	                    			workingHourSum = workingHourSum + workingHourValue + otHour;
	                    			
	                    			workingHourCell.setCellValue(workingHourValue);
	                    			OTHourCell.setCellValue(otHour);
	                    			
	                    			CellStyle style = workbook.createCellStyle();
	                    			
	                    		/*	Font font = workbook.createFont();
	                    			font.setColor(HSSFColor.RED.index);
	                    			style.setFont(font);
	                    	    
	                    	        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
	                    	    
	                    	        style.setFillPattern(CellStyle.SOLID_FOREGROUND);*/
	                    	        
	                    			workingHourCell.setCellStyle(style);
	                    	        OTHourCell.setCellStyle(style);
	                    		
                    		}
                    		
                		}
            		}
            		
            		
            		//Set sum values
            		int workingHourSumIx = minColIx + 2 * 32 + 1;
            		int workingDateSumIx = minColIx + 2 * 32 + 2;
            		
            		Row nextRow = sheet.getRow(rowIx+1);
            		
            		Cell workingHourSumCell = nextRow.getCell(workingHourSumIx);
            		Cell workingDateSumCell = nextRow.getCell(workingDateSumIx);
            		
            		CellStyle style = workbook.createCellStyle();
        			    
            		workingHourSumCell.setCellStyle(style);
            		workingDateSumCell.setCellStyle(style);
            		
            		workingHourSumCell.setCellValue(workingHourSum);
            		double dateSum = workingHourSum / 8;
            		workingDateSumCell.setCellValue(dateSum);
                	
                 }
                  
                      
            inputStream.close();
            //workbook.close();
            
        	//write this workbook to an Outputstream.
			//outWb.write(fileOut);
			//outWb.close();
            
            //String excelFileName = "D:/temp/out.xls";
            String excelFileName = "/home/danhngo/Projects/Jmchr/jmchr.git/Sources/outnew.xls";
           
			FileOutputStream fileOut = new FileOutputStream(excelFileName);
			
			workbook.write(fileOut);
			workbook.close();
			
			fileOut.flush();
			fileOut.close();
            
        }

	}
	
	public static double toNumeric(String str)  
	{  
	  try  
	  {  
		  str = str.replace(",",".");
		  double d = Double.parseDouble(str);  
		  return d;
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return -1;  
	  }  
	 
	}
	
	
	
	
}
