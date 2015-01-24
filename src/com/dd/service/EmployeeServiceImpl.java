package com.dd.service;

import com.dd.domain.Employee;
import com.dd.util.SqlHelper;

public class EmployeeServiceImpl {
	public boolean checkEmployee(Employee employee) {
		//String sql = "select * from employee where name=?";
		String sql = "select * from students where name = ?";
		String[] params = {employee.getName()};

		return SqlHelper.executeQuery(sql, params);
	}
}
