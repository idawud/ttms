package io.turntabl.service;

import io.turntabl.models.Employee;

import java.util.Date;
import java.util.List;

public interface IAvailableDeveloper {
    List<Employee> getAllAvailableDevelopers(Date projectStartDate, Date projectEndDate);
}
