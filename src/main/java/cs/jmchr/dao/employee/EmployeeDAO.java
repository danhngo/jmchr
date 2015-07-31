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
	
	public static List<EmployeeEntity> getEmployeeList() {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		
		 List<EmployeeEntity> empList =  session.createCriteria(EmployeeEntity.class).list();
		
		 session.getTransaction().commit();
		 session.close();
		 
		 return empList;
	}
}
