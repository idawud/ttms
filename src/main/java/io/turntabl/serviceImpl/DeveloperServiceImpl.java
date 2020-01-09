package io.turntabl.serviceImpl;

import io.turntabl.domainModel.Employee;
import io.turntabl.service.DeveloperService;

import java.util.ArrayList;
import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {
    @Override
    public List<Employee> getAllAvailableDevelopers() {
        Employee employee = new Employee();

        // http request
        List<Employee> employees = getAllEmployees();

        List<Employee> employeesPrensent = new ArrayList<>();

        for (Employee emp : employees) {
            if (emp.getEmployee_onleave() == true) {
                List<Employee> EmployeesOnLeave;
                // Compute devs on leave time to report
//                if(emp.getProjects())
            }
            else {
                employeesPrensent.add(emp);
            }
        }

        // update dev project history


        return null;
    }

    private List<Employee> getAllEmployees() {
        return null;
    }
}
