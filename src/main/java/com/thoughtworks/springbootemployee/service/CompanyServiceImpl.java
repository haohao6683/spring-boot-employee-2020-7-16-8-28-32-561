package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.List;

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
    public Company findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Company> getCompaniesByPage(int page, int pageSize) {
        return repository.findAllByPage(page, pageSize);
    }

    @Override
    public Company addCompany(Company company) {
        return repository.save(company);
    }

    @Override
    public Company updateCompany(Company company) {
        return repository.updateCompany(company);
    }

    @Override
    public Company deleteEmployeeByID(Integer id) {
        return repository.deleteById(id);
    }
}
