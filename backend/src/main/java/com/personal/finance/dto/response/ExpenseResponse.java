package com.personal.finance.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ExpenseResponse(Long id, String description, BigDecimal amount, String category, String merchant,
                              LocalDate expenseDate, BigDecimal confidence, Boolean aiClassified) {
}
