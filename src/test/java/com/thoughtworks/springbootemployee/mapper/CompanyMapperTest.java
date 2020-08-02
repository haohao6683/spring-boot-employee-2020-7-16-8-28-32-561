package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyMapperTest {
    private final CompanyMapper companyMapper = new CompanyMapper();

    @Test
    void should_transform_to_company_when_given_companyRequest() {
        //given
        CompanyRequest companyRequest = new CompanyRequest(1, "OOCL", 1, null);

        //when
        Company company = companyMapper.toCompany(companyRequest);

        //then
        assertEquals(companyRequest.getId(), company.getId());
        assertEquals(companyRequest.getCompanyName(), company.getCompanyName());
        assertEquals(companyRequest.getEmployeeNumber(), company.getEmployeeNumber());
        assertEquals(companyRequest.getEmployees(), company.getEmployees());
    }

    @Test
    void should_transform_to_companyResponse_when_given_company() {
        //given
        Company company = new Company(1, "OOCL", 1, null);

        //when
        CompanyResponse companyResponse = companyMapper.toCompanyResponse(company);

        //then
        assertEquals(company.getId(), companyResponse.getId());
        assertEquals(company.getCompanyName(), companyResponse.getCompanyName());
        assertEquals(company.getEmployeeNumber(), companyResponse.getEmployeeNumber());
        assertEquals(company.getEmployees(), companyResponse.getEmployees());
    }
}
