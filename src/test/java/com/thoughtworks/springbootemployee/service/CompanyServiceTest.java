package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompanyServiceTest {
    private CompanyRepository repository = mock(CompanyRepository.class);
    private CompanyServiceImpl companyService = new CompanyServiceImpl(repository);
    @Test
    void should_return_companies_when_get() {
        //given
        List<Company> mockedCompanies = new ArrayList<>();
        mockedCompanies.add(new Company(1,
                "OOCL",
                1,
                Arrays.asList(
                        new Employee(1, 28, "male", "OOCL1", 1000),
                        new Employee(2, 28, "male", "OOCL2", 1000),
                        new Employee(3, 28, "male", "OOCL3", 1000)
                )
        ));
        mockedCompanies.add(new Company(2,
                "OOCL2",
                1,
                Arrays.asList(
                        new Employee(1, 28, "male", "OOCL1", 1000),
                        new Employee(2, 28, "male", "OOCL2", 1000),
                        new Employee(3, 28, "male", "OOCL3", 1000)
                )
        ));
        mockedCompanies.add(new Company(3,
                "OOCL3",
                1,
                Arrays.asList(
                        new Employee(1, 28, "male", "OOCL1", 1000),
                        new Employee(2, 28, "male", "OOCL2", 1000),
                        new Employee(3, 28, "male", "OOCL3", 1000)
                )
        ));
        when(repository.findAll()).thenReturn(mockedCompanies);

        //when
        List<Company> companies = companyService.getCompanyList();

        //then
        Assertions.assertEquals(3, companies.size());
    }

    @Test
    void should_return_company_when_get_given_companyId() {
        //given
        Integer id = 1;
        when(repository.findById(id)).thenReturn(new Company(1,
                "OOCL",
                1,
                Arrays.asList(
                        new Employee(1, 28, "male", "OOCL1", 1000),
                        new Employee(2, 28, "male", "OOCL2", 1000),
                        new Employee(3, 28, "male", "OOCL3", 1000)
                )
        ));
        //when
        Company company = companyService.findById(id);
        //then
        Assertions.assertEquals(id, company.getId());
    }

    @Test
    void should_return_companies_when_query_by_page_given_page_and_page_size() {
        //given
        int page = 1;
        int pageSize = 2;
        int firstCompanyIdInPage1 = 1;
        List<Company> mockedCompanies = new ArrayList<>();
        mockedCompanies.add(new Company(1,
                "OOCL",
                1,
                Arrays.asList(
                        new Employee(1, 28, "male", "OOCL1", 1000),
                        new Employee(2, 28, "male", "OOCL2", 1000),
                        new Employee(3, 28, "male", "OOCL3", 1000)
                )
        ));
        mockedCompanies.add(new Company(2,
                "OOCL2",
                1,
                Arrays.asList(
                        new Employee(1, 28, "male", "OOCL1", 1000),
                        new Employee(2, 28, "male", "OOCL2", 1000),
                        new Employee(3, 28, "male", "OOCL3", 1000)
                )
        ));
        when(repository.findAllByPage(page, pageSize)).thenReturn(mockedCompanies);
        //when
        List<Company> companies = companyService.getCompaniesByPage(page, pageSize);
        //then
        Assertions.assertEquals(pageSize, companies.size());
        Assertions.assertEquals(firstCompanyIdInPage1, companies.get(0).getId());
    }

    @Test
    void should_return_employees_when_get_given_companyId() {
        //given
        Integer id = 1;
        when(repository.findById(id)).thenReturn(new Company(1,
                "OOCL",
                1,
                Arrays.asList(
                        new Employee(1, 28, "male", "OOCL1", 1000),
                        new Employee(2, 28, "male", "OOCL2", 1000),
                        new Employee(3, 28, "male", "OOCL3", 1000)
                )
        ));
        //when
        Company company = companyService.findById(id);
        //then
        Assertions.assertEquals(3, company.getEmployees().size());
    }

    @Test
    void should_return_company_when_add_companies_given_company() {
        //given
        Company company = new Company(1,
                "OOCL",
                1,
                Arrays.asList(
                        new Employee(1, 28, "male", "OOCL1", 1000),
                        new Employee(2, 28, "male", "OOCL2", 1000),
                        new Employee(3, 28, "male", "OOCL3", 1000)
                )
        );
        when(repository.save(company)).thenReturn(company);
        //when
        Company returnValue = companyService.addCompany(company);
        //then
        Assertions.assertEquals(company.getId(), returnValue.getId());
    }

    @Test
    void should_return_updated_company_when_update_company_given_company() {
        //given
        Company company = new Company(1,
                "OOCL",
                1,
                Arrays.asList(
                        new Employee(1, 28, "male", "OOCL1", 1000),
                        new Employee(2, 28, "male", "OOCL2", 1000),
                        new Employee(3, 28, "male", "OOCL3", 1000)
                )
        );
        when(repository.updateCompany(company)).thenReturn(company);
        //when
        Company returnValue = companyService.updateCompany(company);
        //then
        Assertions.assertEquals(1, returnValue.getId());
    }

    @Test
    void should_return_deleted_company_when_delete_company_given_company_id() {
        //given
        Integer id = 1;
        Company company = new Company(1,
                "OOCL",
                1,
                Arrays.asList(
                        new Employee(1, 28, "male", "OOCL1", 1000),
                        new Employee(2, 28, "male", "OOCL2", 1000),
                        new Employee(3, 28, "male", "OOCL3", 1000)
                )
        );
        when(repository.deleteById(id)).thenReturn(company);
        //when
        Company returnValue = companyService.deleteEmployeeByID(id);
        //then
        Assertions.assertEquals(id, returnValue.getId());
    }
}