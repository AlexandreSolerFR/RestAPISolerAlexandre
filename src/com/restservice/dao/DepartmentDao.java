package com.restservice.dao;

import java.util.List;

public class DepartmentDao {
	
	
	public List<Department> getAllDepartments() {
	      List<Department> departmentList = VirtualDatabase.INSTANCE.getDepartments();
	      System.out.println(departmentList.size());
	      return departmentList;
	   }
	
	public int createDepartment(Department dep) {
		Department department = getDepartment(dep.getId());
		if (department == null) {
			List<Department> depList = getAllDepartments();
			depList.add(dep);
			saveDepartmentList(depList);
			return 1;
		}
		return updateDepartment(dep);
	}
	
	public int updateDepartment(Department dep) {
		List<Department> depList = getAllDepartments();
		
		for (Department department : depList) {
			if (department.getId() == dep.getId()) {
				int index = depList.indexOf(department);
				depList.set(index, dep);
				saveDepartmentList(depList);
				return 1;
			}
		}
		return 0;
	}

   private void saveDepartmentList(List<Department> departmentList){
	   VirtualDatabase.INSTANCE.setDepartments(departmentList);
   }
   
   public Department getDepartment(int id) {
	   List<Department> dep = getAllDepartments();
		for (Department department : dep) {
			if (department.getId() == id)
				return department;
		}
		return null;
   }

	public Department getDepartment(String value) {
		List<Department> dep = getAllDepartments();
		for (Department department : dep) {
			if (department.getName().equals(value))
				return department;
		}
		return null;
	}

	public int deleteDepartment(int depId) {
		Department dep = getDepartment(depId);
		if (dep != null) {
			List<Department> listDep = getAllDepartments();
			listDep.remove(dep);
			saveDepartmentList(listDep);
			return 1;
		}
		return 0;
	}   

}
