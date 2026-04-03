package org.example.suyog_zorvynassignment.service;

import lombok.RequiredArgsConstructor;
import org.example.suyog_zorvynassignment.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FinancialRecordRepository recordRepository;

    public BigDecimal getTotalIncome() {
        BigDecimal income = recordRepository.calculateTotalIncome();
        return income != null ? income : BigDecimal.ZERO;
    }

    public BigDecimal getTotalExpense() {
        BigDecimal expense = recordRepository.calculateTotalExpense();
        return expense != null ? expense : BigDecimal.ZERO;
    }

    public BigDecimal getNetBalance() {
        return getTotalIncome().subtract(getTotalExpense());
    }

    public Map<String, BigDecimal> getCategoryWiseExpense() {
        List<Object[]> results = recordRepository.calculateExpenseByCategory();
        Map<String, BigDecimal> categoryMap = new HashMap<>();
        for (Object[] result : results) {
            categoryMap.put((String) result[0], (BigDecimal) result[1]);
        }
        return categoryMap;
    }

    public Map<Integer, BigDecimal> getMonthlyExpense(int year) {
        List<Object[]> results = recordRepository.calculateMonthlyExpense(year);
        Map<Integer, BigDecimal> monthlyMap = new HashMap<>();
        for (Object[] result : results) {
            monthlyMap.put((Integer) result[0], (BigDecimal) result[1]);
        }
        return monthlyMap;
    }
    
    public Map<Integer, BigDecimal> getMonthlyIncome(int year) {
        List<Object[]> results = recordRepository.calculateMonthlyIncome(year);
        Map<Integer, BigDecimal> monthlyMap = new HashMap<>();
        for (Object[] result : results) {
            monthlyMap.put((Integer) result[0], (BigDecimal) result[1]);
        }
        return monthlyMap;
    }
}
