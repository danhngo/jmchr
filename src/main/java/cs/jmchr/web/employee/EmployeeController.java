package cs.jmchr.web.employee;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cs.jmchr.model.employee.Employee;
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
	public String importEmployee(@RequestBody String filePath) {

		logger.info("importexcel() is executed");
		
		List<Employee> lstEmployee = readEmployeeProfile(filePath);
				
		return "employee/list";
	}
	
	private List<Employee> readEmployeeProfile(String filePath) {
		List<Employee> lstEmployee = new ArrayList<Employee>();
		
		try
        {
			Employee employee = new Employee();
			
			FileInputStream file = new FileInputStream(new File(filePath));
            //Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            
                            break;
                        case Cell.CELL_TYPE_STRING:
                            
                            break;
                            
                    }
                }
                
            }
            file.close();
            workbook.close();
            
            lstEmployee.add(employee);
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

		model.put("title", employeeService.getTitle(""));
		model.put("msg", employeeService.getDesc());
		
		return "index";
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		logger.debug("hello() is executed - $name {}", name);

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		
		model.addObject("title", employeeService.getTitle(name));
		model.addObject("msg", employeeService.getDesc());
		
		return model;

	}

}