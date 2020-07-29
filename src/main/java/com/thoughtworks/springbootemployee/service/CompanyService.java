package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyList();

    Company findById(Integer id);

    List<Employee> findEmployeesByCompanyId(Integer id);

    Page<Company> getCompaniesByPage(int page, int pageSize);

    Company addCompany(Company company);

    Company updateCompanyByID(Integer id, Company newCompany);

    void deleteCompanyByID(Integer id);
}
