package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    private final EmployeeRepository repository = mock(EmployeeRepository.class);
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl(repository);

    @Test
    void should_return_employees_when_get() {
        //given
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        mockedEmployees.add(new Employee(4, 28, "male", "Draymond4", 40));
        mockedEmployees.add(new Employee(5, 28, "female", "Draymond5", 30));
        mockedEmployees.add(new Employee(6, 28, "male", "Draymond6", 20));
        when(repository.findAll()).thenReturn(mockedEmployees);
        //when
        List<Employee> employees = employeeService.getEmployeeList();

        //then
        Assertions.assertEquals(6, employees.size());
    }

    @Test
    void should_return_employee_when_get_given_employeeId() {
        //given
        Integer id = 1;
        when(repository.findById(id)).thenReturn(java.util.Optional.of(new Employee(1, 28, "male", "Draymond1", 1000)));
        //when
        Employee employee = null;
        try {
            employee = employeeService.getEmployeeById(id);
        } catch (NoSuchDataException e) {
            e.printStackTrace();
        }
        //then
        assert employee != null;
        Assertions.assertEquals(id, employee.getId());
    }

    @Test
    void should_return_employees_when_query_by_page_given_page_and_page_size() {
        //given
        int page = 1;
        int pageSize = 5;
        int firstEmployeeIdInPage1 = 1;
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        mockedEmployees.add(new Employee(4, 28, "male", "Draymond4", 40));
        mockedEmployees.add(new Employee(5, 28, "female", "Draymond5", 30));
        when(repository.findAll(PageRequest.of(page, pageSize))).thenReturn(new PageImpl<>(mockedEmployees));
        //when
        List<Employee> employees = employeeService.getEmployeeByPage(page, pageSize).getContent();
        //then
        Assertions.assertEquals(pageSize, employees.size());
        Assertions.assertEquals(firstEmployeeIdInPage1, employees.get(0).getId());
    }

    @Test
    void should_return_employees_when_query_by_gender_given_gender() {
        //given
        String gender = "male";
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        mockedEmployees.add(new Employee(4, 28, "male", "Draymond4", 40));
        when(repository.findByGender(gender)).thenReturn(mockedEmployees);
        //when
        List<Employee> employees = employeeService.getEmployeeByGender(gender);
        //then
        Assertions.assertEquals(4, employees.size());
    }

    @Test
    void should_return_employee_when_add_employees_given_employees() {
        //given
        Employee employee = new Employee(6, 28, "male", "Draymond6", 20);
        when(repository.save(employee)).thenReturn(employee);
        //when
        Employee returnValue = null;
        try {
            returnValue = employeeService.addEmployee(employee);
        } catch (IllegalOperationException e) {
            e.printStackTrace();
        }
        //then
        assert returnValue != null;
        Assertions.assertEquals(employee.getId(), returnValue.getId());
    }

    @Test
    void should_return_updated_employee_when_update_employee_given_employee() {
        //given
        Employee employee = new Employee(6, 28, "male", "Draymond6", 20);
        when(repository.save(employee)).thenReturn(employee);
        given(repository.findById(6)).willReturn(java.util.Optional.of(employee));
        //when
        Employee returnValue = null;
        try {
            returnValue = employeeService.updateEmployeeById(6, employee);
        } catch (IllegalOperationException e) {
            e.printStackTrace();
        }
        //then
        assert returnValue != null;
        Assertions.assertEquals("male", returnValue.getGender());
    }

    @Test
    void should_return_deleted_employee_when_delete_employee_given_employee_id() {
        //given
        EmployeeRepository mockEmployeeReposition = mock(EmployeeRepository.class);
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(mockEmployeeReposition);
        given(mockEmployeeReposition.findById(1)).willReturn(java.util.Optional.of(new Employee(1, 28, "male", "dray", 2222)));
        Integer id = 1;
        //when
        try {
            employeeService.deleteEmployeeByID(id);
        } catch (IllegalOperationException e) {
            e.printStackTrace();
        }
        //then
        Mockito.verify(mockEmployeeReposition).deleteById(1);
    }

    @Test
    void should_throw_no_such_data_exception_when_search_given_no_employee_id() {
        //when then
        Assertions.assertThrows(NoSuchDataException.class, () -> employeeService.getEmployeeById(null));
    }

    @Test
    void should_throw_illegal_exception_when_add_employee_given_no_employee() {
        //when then
        Assertions.assertThrows(IllegalOperationException.class, () -> employeeService.addEmployee(null));
    }

    @Test
    void should_throw_illegal_exception_when_update_employee_given_no_employee_id() {
        //when then
        Assertions.assertThrows(IllegalOperationException.class, () -> employeeService.updateEmployeeById(null, new Employee()));
    }

    @Test
    void should_throw_illegal_exception_when_delete_employee_given_no_employee_id() {
        //when then
        Assertions.assertThrows(IllegalOperationException.class, () -> employeeService.deleteEmployeeByID(null));
    }
}
