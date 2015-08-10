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
			EmployeeEntity entity = toEmployeeEntity(model);
			if (entity != null) {
				lstEntity.add(entity);
			}
		}
		
		return lstEntity;
	}
	
	public int deleteEmployee(String empId) {
		
		return EmployeeDAO.deleteEmployee(empId);
	}
	
	public int updateEmployee(EmployeeModel model) {
		int result = 0;
		EmployeeEntity entity = toEmployeeEntity(model);
		if (entity != null) {
			EmployeeDAO.updateEmployee(entity);
		}
		return result;
	}
	
	public List<EmployeeModel> getEmployeeList() {
		List<EmployeeEntity> entities = EmployeeDAO.getEmployeeList();
		
		return toEmployeeModel(entities);
	}

	private List<EmployeeModel> toEmployeeModel(List<EmployeeEntity> lstEntity) {
		List<EmployeeModel> lstModel = new ArrayList<EmployeeModel>();
		
		for (EmployeeEntity entity : lstEntity) {
			EmployeeModel model = toEmployeeModel(entity);
			if (model != null) {
				lstModel.add(model);
			}
		}
		
		return lstModel;
	}
	
	public EmployeeModel getEmployeeById(String empId) {
		EmployeeEntity employee = EmployeeDAO.getEmployee(empId);
		
		return toEmployeeModel(employee);
	}
	
	private EmployeeModel toEmployeeModel(EmployeeEntity entity) {
		EmployeeModel model = null;
		if (entity != null) {
			model =  new EmployeeModel();
			model.setEmpId(entity.getId());
			model.setName(entity.getName());
			model.setStartdate(entity.getStartdate());
		}
		return model;
	}
	
	private EmployeeEntity toEmployeeEntity(EmployeeModel model) {
		EmployeeEntity entity = null;
		if (model != null) {
			entity =  new EmployeeEntity();
			entity.setId(model.getEmpId());
			entity.setName(model.getName());
			entity.setStartdate(model.getStartdate());
		}
		return entity;
	}

}