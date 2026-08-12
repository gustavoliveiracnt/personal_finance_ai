package com.personal.finance.dto.mapper;

import com.personal.finance.dto.response.ExpenseResponse;
import com.personal.finance.entity.Expense;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class ExpenseMapper {

    public ExpenseResponse toResponse(Expense expense) {
        if (Objects.isNull(expense))
            return null;

        return new ExpenseResponse(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCategory().getName(),
                expense.getMerchant().getName(),
                expense.getExpenseDate(),
                expense.getConfidence(),
                expense.getAiClassified()
        );
    }

    public List<ExpenseResponse> toResponseList(List<Expense> expenses) {
        if (Objects.isNull(expenses) || expenses.isEmpty()) {
            return Collections.emptyList();
        }

        return expenses.stream()
                .map(this::toResponse)
                .toList();
    }
}
