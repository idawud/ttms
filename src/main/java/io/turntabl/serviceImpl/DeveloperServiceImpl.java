package io.turntabl.serviceImpl;

import io.turntabl.domainModel.Employee;
import io.turntabl.domainModel.Project;
import io.turntabl.service.DeveloperService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {
    @Override
    public List<Employee> getAllAvailableDevelopers() {

        // http request
        List<Employee> employees = getAllEmployees();

        List<Employee> employeesPresent = new ArrayList<>();

        for (Employee emp : employees) {
            if (emp.getEmployee_onleave() == true) {
                List<Employee> EmployeesOnLeave;
                // Compute devs on leave time to report
//                if(emp.getProjects())
            }
            else {
                employeesPresent.add(emp);
            }
        }

        // update dev project history
        List<Employee> availableDevelopers = new ArrayList<>();
        for (Employee dev :
                employeesPresent) {
            List<Project> devPresentProjectHistory = dev.getProjects();

            for (Project prjt :
                    devPresentProjectHistory) {
                if (prjt.getProject_end_date().compareTo(LocalDate.now()) > 0) {
                    devPresentProjectHistory.add(prjt);
                }
                dev.setProjects((List<Project>) prjt);
            }

            availableDevelopers.add(dev);

        }


        return null;
    }

    private List<Employee> getAllEmployees() {
        return null;
    }
}
