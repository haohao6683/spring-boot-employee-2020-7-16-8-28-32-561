package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
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
}
