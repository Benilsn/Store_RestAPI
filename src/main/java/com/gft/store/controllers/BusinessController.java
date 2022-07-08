package com.gft.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gft.store.models.forms.PurchaseOrder;
import com.gft.store.models.forms.SaleOrder;
import com.gft.store.services.BusinessService;

@RestController
// @PreAuthorize(value = "hasAuthorities('ADMIN', 'USER')")
public class BusinessController {

    @Autowired
    private BusinessService service;

    @PostMapping("/v1/auth/purchases/")
    public ResponseEntity<?> buy(@RequestBody PurchaseOrder order) {
        return ResponseEntity.ok().body(service.makeBuy(order));
    }

    @GetMapping("/v1/auth/purchases/")
    public ResponseEntity<?> getPurchases(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(service.getAllPurchases(pageable));
    }

    @PostMapping("/auth/sales/")
    public ResponseEntity<?> sell(@RequestBody SaleOrder order) {
        return ResponseEntity.ok().body(service.makeSale(order));
    }

    @GetMapping("/auth/sales/")
    public ResponseEntity<?> getSales(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(service.getAllSales(pageable));
    }

}
