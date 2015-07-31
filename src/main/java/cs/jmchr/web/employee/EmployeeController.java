package cs.jmchr.web.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cs.jmchr.ExcelUtil;
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
	public String importEmployee(@RequestBody String filePath) {

		logger.info("importexcel() is executed");
		
		filePath = "/home/danhngo/Projects/Jmchr/Employee.xlsx";
		//filePath = "D:/1.Projects/Jmchr.git/jmchr/build/employee.xls";
		
		//List<EmployeeModel> lstEmployee = ExcelUtil.readEmployeeProfile(filePath);
		String[] fields = new String[] {"Id","Name","Startdate"};
		List<Object[]> lstModel = ExcelUtil.readExcelFile(filePath, 0, fields);
		
		List<EmployeeModel> lstEmployee = new ArrayList<EmployeeModel>();
		for (Object[] objList : lstModel) {
			EmployeeModel model = new EmployeeModel();
			model.setId((String)objList[0]);
			model.setName((String)objList[1]);
			model.setStartdate((String)objList[2]);
			
			lstEmployee.add(model);
		}
				
		employeeService.importEmployee(lstEmployee);
		
		/*ModelAndView model = new ModelAndView();
		model.setViewName("employee/listxxx");*/		
		
		return "redirect:/employee/list";
	}
	
	@RequestMapping(value = "/employee/list", method = RequestMethod.GET)
	public ModelAndView employeeList() {
		//model.put("importInfoForm", new ImportInfoForm()); 
		logger.info("employeeList() is executed");
		
		List<EmployeeModel> employeeList = employeeService.getEmployeeList();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("employee/list");
		
		model.addObject("employeeList", employeeList);
				
		return model;
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