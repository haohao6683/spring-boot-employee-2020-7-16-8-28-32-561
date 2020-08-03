package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyList();

    Company findById(Integer id) throws NoSuchDataException;

    List<Employee> findEmployeesByCompanyId(Integer id) throws NoSuchDataException;

    Page<Company> getCompaniesByPage(int page, int pageSize);

    Company addCompany(Company company) throws IllegalOperationException;

    Company updateCompanyById(Integer id, Company newCompany) throws Exception;

    void deleteCompanyById(Integer id) throws Exception;
}
