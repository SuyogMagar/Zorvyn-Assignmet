package org.example.suyog_zorvynassignment.service;

import lombok.RequiredArgsConstructor;
import org.example.suyog_zorvynassignment.dto.FinancialRecordDTO;
import org.example.suyog_zorvynassignment.model.FinancialRecord;
import org.example.suyog_zorvynassignment.model.enums.RecordType;
import org.example.suyog_zorvynassignment.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    private final FinancialRecordRepository recordRepository;

    public FinancialRecord createRecord(FinancialRecordDTO dto) {
        FinancialRecord record = FinancialRecord.builder()
                .amount(dto.getAmount())
                .type(dto.getType())
                .category(dto.getCategory())
                .date(dto.getDate())
                .notes(dto.getNotes())
                .build();
        return recordRepository.save(record);
    }

    public List<FinancialRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    public FinancialRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record not found"));
    }

    public FinancialRecord updateRecord(Long id, FinancialRecordDTO dto) {
        FinancialRecord record = getRecordById(id);
        
        record.setAmount(dto.getAmount());
        record.setType(dto.getType());
        record.setCategory(dto.getCategory());
        record.setDate(dto.getDate());
        record.setNotes(dto.getNotes());
        
        return recordRepository.save(record);
    }

    public void deleteRecord(Long id) {
        FinancialRecord record = getRecordById(id);
        recordRepository.delete(record);
    }

    public List<FinancialRecord> filterRecords(RecordType type, String category, LocalDate startDate, LocalDate endDate) {
        return recordRepository.filterRecords(type, category, startDate, endDate);
    }
}
