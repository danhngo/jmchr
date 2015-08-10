package cs.jmchr.dao.employee;

import java.util.List;

import org.hibernate.Session;

import cs.jmchr.HibernateUtil;
import cs.jmchr.entity.employee.EmployeeEntity;

public class EmployeeDAO {
	
	public static int importEmployee(List<EmployeeEntity> lstEmp) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 
		 for (EmployeeEntity item : lstEmp) {
			 Object tempEmp =   session.get(EmployeeEntity.class, item.getId());
			 if (tempEmp == null) {
				 session.save(item);
			 }
		 }
		 
		 session.getTransaction().commit();
		 session.close();
		
		return 0;
	}
	
	public static int deleteEmployee(String empId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		
		 EmployeeEntity employee = (EmployeeEntity) session.get(EmployeeEntity.class, empId);
		
		 session.delete(employee);
		
		 session.getTransaction().commit();
		 session.close();
		 
		 return 1;
	}
	
	public static List<EmployeeEntity> getEmployeeList() {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		
		 List<EmployeeEntity> empList =  session.createCriteria(EmployeeEntity.class).list();
		
		 session.getTransaction().commit();
		 session.close();
		 
		 return empList;
	}
	
	public static EmployeeEntity getEmployee(String empId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		
		 EmployeeEntity tempEmp =   (EmployeeEntity)session.get(EmployeeEntity.class, empId);
		 
		 session.close();
		 
		 return tempEmp;
	}
	
	public static int updateEmployee(EmployeeEntity entity) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		
		 session.saveOrUpdate(entity);
		 
		 session.getTransaction().commit();
		 session.close();
		 
		 return 1;
	}
	
	
	
}
