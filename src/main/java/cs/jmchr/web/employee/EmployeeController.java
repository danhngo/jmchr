package cs.jmchr.web.employee;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cs.jmchr.model.employee.EmployeeModel;
import cs.jmchr.service.employee.EmployeeService;

@Controller("employee")
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value = "/employee/import", method = RequestMethod.GET)
	public String showImport(Map<String, Object> model) {
		//model.put("importInfoForm", new ImportInfoForm()); 
		logger.info("showImport() is executed");
		
		return "employee/import";
	}
	@RequestMapping(value = "/employee/importexcel", method = RequestMethod.POST)
	public ModelAndView importEmployee(@RequestBody String filePath) {

		logger.info("importexcel() is executed");
		
		//filePath = "/home/danhngo/Projects/Jmchr/Employee.xlsx";
		filePath = "D:/1.Projects/Jmchr.git/jmchr/build/employee.xls";
		
		List<EmployeeModel> lstEmployee = readEmployeeProfile(filePath);
		EmployeeService.importEmployee(lstEmployee);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("employee/list");		
		
		return model;
	}
	
	
	
	private List<EmployeeModel> readEmployeeProfile(String filePath) {
		List<EmployeeModel> lstEmployee = new ArrayList<EmployeeModel>();
		
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
			Sheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            
            
            int posId = -1;
            int posName = -1;
            int posStartDate = -1;
            boolean isStarted = false;
            
            
            while (rowIterator.hasNext())
            {
            	EmployeeModel employee =  new EmployeeModel();
            	
            	Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    
                    if (isStarted) {
                    	if (cell.getColumnIndex() == posId) {
                    		employee.setId(cell.getStringCellValue());
                    	} else if (cell.getColumnIndex() == posName) {
                    		employee.setName(cell.getStringCellValue());
                    	} else if (cell.getColumnIndex() == posStartDate) {
                    		employee.setStartDate(cell.getStringCellValue());
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
                    
                    if (posId == -1 && "Id".equals(cell.getStringCellValue())) {
                    	posId =  cell.getColumnIndex();
                    }
                    if (posName == -1 && "Name".equals(cell.getStringCellValue())) {
                    	posName =  cell.getColumnIndex();
                    } 
                    if (posStartDate == -1 && "Startdate".equals(cell.getStringCellValue())) {
                    	posStartDate =  cell.getColumnIndex();
                    } 
                    
                    if (posId == -1 || posName == -1 || posStartDate == -1) {
                    	continue;
                    } else {
                    	isStarted = true;
                    }
                }
                if (employee.getId() != null) {
                	lstEmployee.add(employee);
                }
                
            }
            file.close();
            workbook.close();
            
            
        }
        catch (Exception e)
        {
        	logger.debug("Exception readEmployeeProfile: " + e.getMessage());        	
        }
		
		return lstEmployee;
	}
	
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		logger.debug("index() is executed!");

		//model.put("title", employeeService.getTitle(""));
		//model.put("msg", employeeService.getDesc());
		
		return "index";
	}

	/*@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		logger.debug("hello() is executed - $name {}", name);

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		
		model.addObject("title", employeeService.getTitle(name));
		model.addObject("msg", employeeService.getDesc());
		
		return model;

	}*/

}