package com.personal.finance.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateExpenseRequest(@NotNull String description, @NotNull @Positive BigDecimal amount,
                                   @NotNull Long categoryId, @NotNull Long merchantId,
                                   @NotNull LocalDate expenseDate) {
}
