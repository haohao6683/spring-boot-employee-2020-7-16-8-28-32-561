package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Company> getCompaniesByPage(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page-1, pageSize));
    }

    @Override
    public Company addCompany(Company company) {
        return repository.save(company);
    }

    @Override
    public Company updateCompanyByID(Integer id, Company newCompany) {
        Company company = this.findById(id);
        company.setEmployees(newCompany.getEmployees());
        company.setCompanyName(newCompany.getCompanyName());
        company.setEmployeeNumber(newCompany.getEmployeeNumber());
        return repository.save(company);
    }

    @Override
    public void deleteCompanyByID(Integer id) {
        repository.deleteById(id);
    }
}
