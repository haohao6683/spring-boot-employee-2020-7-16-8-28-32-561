package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyList();

    Company findById(Integer id);

    List<Company> getCompaniesByPage(int page, int pageSize);

    Company addCompany(Company company);

    Company updateCompany(Company company);

    Company deleteEmployeeByID(Integer id);
}
