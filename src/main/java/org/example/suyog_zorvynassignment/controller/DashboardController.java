package org.example.suyog_zorvynassignment.controller;

import lombok.RequiredArgsConstructor;
import org.example.suyog_zorvynassignment.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/summary")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/total-income")
    public ResponseEntity<BigDecimal> getTotalIncome() {
        return ResponseEntity.ok(dashboardService.getTotalIncome());
    }

    @GetMapping("/total-expense")
    public ResponseEntity<BigDecimal> getTotalExpense() {
        return ResponseEntity.ok(dashboardService.getTotalExpense());
    }

    @GetMapping("/net-balance")
    public ResponseEntity<BigDecimal> getNetBalance() {
        return ResponseEntity.ok(dashboardService.getNetBalance());
    }

    @GetMapping("/category-wise")
    public ResponseEntity<Map<String, BigDecimal>> getCategoryWiseExpense() {
        return ResponseEntity.ok(dashboardService.getCategoryWiseExpense());
    }

    @GetMapping("/monthly")
    public ResponseEntity<Map<Integer, BigDecimal>> getMonthlyExpense(@RequestParam(required = false) Integer year) {
        int targetYear = (year != null) ? year : LocalDate.now().getYear();
        return ResponseEntity.ok(dashboardService.getMonthlyExpense(targetYear));
    }
    
    @GetMapping("/monthly-income")
    public ResponseEntity<Map<Integer, BigDecimal>> getMonthlyIncome(@RequestParam(required = false) Integer year) {
        int targetYear = (year != null) ? year : LocalDate.now().getYear();
        return ResponseEntity.ok(dashboardService.getMonthlyIncome(targetYear));
    }
}
