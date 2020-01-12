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

import com.authentication.service.IAffiliateService;
import com.authentication.service.dto.AffiliateDto;
import com.authentication.service.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class AffiliateController {

    @Autowired
    private IAffiliateService affiliateService;

    @PostMapping("/affiliates")
    public AffiliateDto createAffiliate(@Valid @RequestBody AffiliateDto affiliateDto)
            throws ResourceNotFoundException {
        return affiliateService.save(affiliateDto);
    }

    @PutMapping("/affiliates/{id}")
    public ResponseEntity<AffiliateDto> updateAffiliate(@PathVariable(value = "id") Long affiliateId,
            @Valid @RequestBody AffiliateDto affiliateDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(affiliateService.updateAffiliate(affiliateId, affiliateDetails));
    }

    @GetMapping("/affiliates/{id}")
    public ResponseEntity<AffiliateDto> getAffiliateById(@PathVariable(value = "id") Long affiliateId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(affiliateService.findDtoById(affiliateId));
    }

    @DeleteMapping("/affiliates/{id}")
    public Map<String, Boolean> deleteAffiliate(@PathVariable(value = "id") Long affiliateId) throws Exception {
        affiliateService.deleteById(affiliateId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/affiliates")
    public List<AffiliateDto> getAllAffiliates() {
        return affiliateService.findAll();
    }

}
