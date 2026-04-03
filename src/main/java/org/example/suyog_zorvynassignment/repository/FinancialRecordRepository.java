package org.example.suyog_zorvynassignment.repository;

import org.example.suyog_zorvynassignment.model.FinancialRecord;
import org.example.suyog_zorvynassignment.model.enums.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    
    List<FinancialRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<FinancialRecord> findByType(RecordType type);
    
    List<FinancialRecord> findByCategory(String category);

    // Filter by type, category and date manually in service or via specifications, 
    // here we provide flexible query
    @Query("SELECT r FROM FinancialRecord r WHERE " +
           "(:type IS NULL OR r.type = :type) AND " +
           "(:category IS NULL OR r.category = :category) AND " +
           "(:startDate IS NULL OR r.date >= :startDate) AND " +
           "(:endDate IS NULL OR r.date <= :endDate)")
    List<FinancialRecord> filterRecords(
            @Param("type") RecordType type,
            @Param("category") String category,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
            
    // Dashboard aggregations
    @Query("SELECT SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'INCOME'")
    BigDecimal calculateTotalIncome();

    @Query("SELECT SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'EXPENSE'")
    BigDecimal calculateTotalExpense();
    
    @Query("SELECT r.category, SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'EXPENSE' GROUP BY r.category")
    List<Object[]> calculateExpenseByCategory();

    @Query("SELECT MONTH(r.date), SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'EXPENSE' AND YEAR(r.date) = :year GROUP BY MONTH(r.date)")
    List<Object[]> calculateMonthlyExpense(@Param("year") int year);
    
    @Query("SELECT MONTH(r.date), SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'INCOME' AND YEAR(r.date) = :year GROUP BY MONTH(r.date)")
    List<Object[]> calculateMonthlyIncome(@Param("year") int year);
}
