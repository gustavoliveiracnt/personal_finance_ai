package com.personal.finance.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateExpenseRequest(@NotBlank(message = "Description is required") String description,
                                   @NotNull(message = "Expense date is required") LocalDate expenseDate) {
}
