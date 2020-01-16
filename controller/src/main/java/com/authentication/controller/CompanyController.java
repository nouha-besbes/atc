package com.authentication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.service.ICompanyService;
import com.authentication.service.dto.CompanyDto;
import com.authentication.service.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @PostMapping("/companys")
    public CompanyDto createCompany(@Valid @RequestBody CompanyDto companyDto) {
        return companyService.save(companyDto);
    }

    @PutMapping("/companys/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable(value = "id") Long companyId,
            @Valid @RequestBody CompanyDto companyDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(companyService.update(companyId, companyDetails));
    }

    @GetMapping("/companys/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable(value = "id") Long companyId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(companyService.findDtoById(companyId));
    }

    @DeleteMapping("/companys/{id}")
    public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") Long companyId) throws Exception {
        companyService.deleteById(companyId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/companys")
    public List<CompanyDto> getAllCompanies() {
        return companyService.findAll();
    }

}
