package com.dd.action;

import com.dd.domain.Employee;
import com.dd.form.UserForm;
import com.dd.service.EmployeeServiceImpl;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;
		String username = userForm.getUsername();
		String password = userForm.getPassword();
		System.out.println("---> password: " + password);

		if ("root".equalsIgnoreCase(password)) {
			EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
			Employee employee = new Employee();
			employee.setName(username);
			if (employeeService.checkEmployee(employee)) {
				request.setAttribute("username", userForm.getUsername());
				return mapping.findForward("ok");
			} else {
				return mapping.findForward("error");
			}
		} else {
			return mapping.findForward("error");
		}
	}
}
