package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{
    private CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Company> getCompanyList() {
        return repository.findAll();
    }

    @Override
    public Company findById(Integer id) throws NoSuchDataException {
        Company company = repository.findById(id).orElse(null);
        if (company == null) {
            throw new NoSuchDataException();
        }
        return company;
    }

    @Override
    public List<Employee> findEmployeesByCompanyId(Integer id) throws NoSuchDataException {
        Company company = findById(id);
        if (company == null) {
            throw new NoSuchDataException();
        }
        return company.getEmployees();
    }

    @Override
    public Page<Company> getCompaniesByPage(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Company addCompany(Company company) throws IllegalOperationException {
        if (company == null) {
            throw new IllegalOperationException();
        }
        return repository.save(company);
    }

    @Override
    public Company updateCompanyByID(Integer id, Company newCompany) throws IllegalOperationException {
        Company company = null;
        try {
            company = this.findById(id);
        } catch (NoSuchDataException e) {
            throw new IllegalOperationException();
        }
        BeanUtils.copyProperties(newCompany, company);
        return repository.save(company);
    }

    @Override
    public void deleteCompanyByID(Integer id) throws IllegalOperationException {
        try {
            Company company = this.findById(id);
        } catch (NoSuchDataException e) {
            throw new IllegalOperationException();
        }
        repository.deleteById(id);
    }
}
