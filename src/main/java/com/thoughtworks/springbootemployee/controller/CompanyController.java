package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyServiceImpl companyService;

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getCompanyList();
    }

    @GetMapping("/{id}")
    public Company getCompaniesById(@PathVariable int id) throws NoSuchDataException {
        return companyService.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) throws NoSuchDataException {
        return companyService.findEmployeesByCompanyId(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Company> getCompaniesByPage(int page, int pageSize) {
        return companyService.getCompaniesByPage(page, pageSize);
    }

    @PostMapping
    public Company addCompany(@RequestBody Company newCompany) throws IllegalOperationException {
        return companyService.addCompany(newCompany);
    }

    @PutMapping("/{id}")
    public Company updateCompanyByID(@PathVariable int id, @RequestBody Company newCompany) throws IllegalOperationException {
        return companyService.updateCompanyById(id, newCompany);
    }

    @DeleteMapping("/{id}")
    public void deleteAllEmployeesOfTheCompanyByID(@PathVariable int id) throws IllegalOperationException {
        companyService.deleteCompanyById(id);
    }
}
