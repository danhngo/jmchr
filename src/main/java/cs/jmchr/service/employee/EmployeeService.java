package cs.jmchr.service.employee;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cs.jmchr.dao.employee.EmployeeDAO;
import cs.jmchr.entity.employee.EmployeeEntity;
import cs.jmchr.model.employee.EmployeeModel;

@Service
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	public static int importEmployee(List<EmployeeModel> lstEmployee) {
		return EmployeeDAO.importEmployee(toEmployeeEntity(lstEmployee));
	}
	
	private static List<EmployeeEntity> toEmployeeEntity(List<EmployeeModel> lstEmployee) {
		List<EmployeeEntity> lstEntity = new ArrayList<EmployeeEntity>();
		
		for (EmployeeModel model : lstEmployee) {
			EmployeeEntity entity = new EmployeeEntity();
			entity.setId(model.getId());
			entity.setName(model.getName());
			entity.setStartdate(model.getStartDate());
			
			lstEntity.add(entity);
		}
		
		return lstEntity;
	}

	

}