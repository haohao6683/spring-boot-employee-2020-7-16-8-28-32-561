package com.thoughtworks.springbootemployee.service;

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
    public Company findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findEmployeesByCompanyId(Integer id) {
        return findById(id).getEmployees();
    }

    @Override
    public Page<Company> getCompaniesByPage(int page, int pageSize) {
        return repository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Company addCompany(Company company) {
        return repository.save(company);
    }

    @Override
    public Company updateCompanyByID(Integer id, Company newCompany) {
        Company company = this.findById(id);
        if (company != null) {
            BeanUtils.copyProperties(newCompany, company);
            return repository.save(company);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteCompanyByID(Integer id) {
        Company company = this.findById(id);
        if (company != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
