package io.turntabl.serviceImpl;

import io.turntabl.domainModel.Employee;
import io.turntabl.domainModel.Leave;
import io.turntabl.domainModel.Project;
import io.turntabl.service.DeveloperService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeveloperServiceImpl     {
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM d, yyyy", Locale.ENGLISH);

    public List<Employee> getAllAvailableDevelopers(Date projectStartDate, Date projectEndDate) {
        // http request
        List<Employee> employees = getAllEmployees();

        List<Employee> employeesAvailable = new ArrayList<>();

        // filter out unavailable employees
        Stream<Employee> employeesReturningFromLeave = employees.stream().filter(Employee::getEmployee_onleave).filter(
                x -> {
                    Leave leave = getLeave(x.getEmployee_id());
                    return dateIsBefore(projectStartDate, leave);
                }
        );
        Stream<Employee> employeesWithNoLeave = employees.stream().filter( x -> !x.getEmployee_onleave());
        Stream<Employee> availableEmployees = Stream.concat(employeesReturningFromLeave, employeesWithNoLeave);

        // filter out old projects
         availableEmployees.forEach( x -> {
            List<Project> projects = x.getProjects();
            List<Project> currentProjs = projects.stream().filter(project -> project.getProject_end_date().after(getDateNow())).collect(Collectors.toList());
            x.setProjects(currentProjs);
        });

         // employees to consider for this date duration
        return availableEmployees.filter( emp -> fitEmployee(emp.getProjects(), projectStartDate, projectEndDate)).collect(Collectors.toList());
    }

    private boolean fitEmployee(List<Project> projects, Date projectStartDate, Date projectEndDate) {
        Iterator<Project> iterator = projects.iterator();
        Project current;

        do{
            current = iterator.next();
            if(current != null && projectStartDate.after(current.getProject_end_date())){
                if ( iterator.hasNext() && projects.get(projects.indexOf(current) + 1).getProject_start_date().before(projectEndDate)){
                    return true;
                }
                else if( !iterator.hasNext() ){
                    return true;
                }
            }
        }while (iterator.hasNext());

        return false;
    }

    private boolean dateIsBefore(Date projectStartDate, Leave leave) {
        boolean isBefore =  false;
        try {
            Date leaveEndDate = DATE_FORMAT.parse(leave.getEndDate());
            isBefore = leaveEndDate.before(projectStartDate);
        } catch (ParseException e) {  e.printStackTrace();  }
        return isBefore;
    }


    private Date getDateNow(){
        SimpleDateFormat formatter= new SimpleDateFormat("MM d, yyyy");
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    private Leave getLeave(int employee_id) {
        return new Leave();
    }

    private List<Employee> getAllEmployees() {
        return (List<Employee>) new Employee();
    }
}
