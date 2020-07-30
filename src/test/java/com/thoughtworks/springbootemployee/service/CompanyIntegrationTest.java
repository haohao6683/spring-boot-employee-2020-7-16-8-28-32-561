package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void should_return_companies_when_find_all_companies_given_null() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1, 28, "male", "OOCL1", 1000),
                new Employee(2, 28, "male", "OOCL2", 1000),
                new Employee(3, 28, "male", "OOCL3", 1000)
        );
        Company company = new Company(1,
                "OOCL",
                1,
                employees
        );
        companyRepository.save(company);

        //when then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].companyName").value("OOCL"))
                .andExpect(jsonPath("$[0].employeeNumber").value(1))
                .andExpect(jsonPath("$[0].employees[0].name").value("OOCL1"));

    }

    @Test
    void should_return_certain_company_when_find_company_by_id_given_id() throws Exception {
        //given
        Integer id = 1;
        List<Employee> employees = Arrays.asList(
                new Employee(1, 28, "male", "OOCL1", 1000),
                new Employee(2, 28, "male", "OOCL2", 1000),
                new Employee(3, 28, "male", "OOCL3", 1000)
        );
        Company company = new Company(1,
                "OOCL",
                1,
                employees
        );
        Company savedCompany = companyRepository.save(company);

        //when then
        mockMvc.perform(get("/companies/" + savedCompany.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.companyName").value("OOCL"))
                .andExpect(jsonPath("$.employeeNumber").value(1))
                .andExpect(jsonPath("$..employees[0].name").value("OOCL1"));
    }
}
