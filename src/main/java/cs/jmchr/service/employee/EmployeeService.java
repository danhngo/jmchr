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

	public int importEmployee(List<EmployeeModel> lstEmployee) {
		return EmployeeDAO.importEmployee(toEmployeeEntity(lstEmployee));
	}
	
	private List<EmployeeEntity> toEmployeeEntity(List<EmployeeModel> lstEmployee) {
		List<EmployeeEntity> lstEntity = new ArrayList<EmployeeEntity>();
		
		for (EmployeeModel model : lstEmployee) {
			EmployeeEntity entity = new EmployeeEntity();
			entity.setId(model.getId());
			entity.setName(model.getName());
			entity.setStartdate(model.getStartdate());
			
			lstEntity.add(entity);
		}
		
		return lstEntity;
	}
	
	public List<EmployeeModel> getEmployeeList() {
		List<EmployeeEntity> entities = EmployeeDAO.getEmployeeList();
		
		return toEmployeeModel(entities);
	}

	private List<EmployeeModel> toEmployeeModel(List<EmployeeEntity> lstEntity) {
		List<EmployeeModel> lstModel = new ArrayList<EmployeeModel>();
		
		for (EmployeeEntity entity : lstEntity) {
			EmployeeModel model = new EmployeeModel();
			model.setId(entity.getId());
			model.setName(entity.getName());
			model.setStartdate(entity.getStartdate());
			
			lstModel.add(model);
		}
		
		return lstModel;
	}

}