package com.personal.finance.service;

import com.personal.finance.client.AIClient;
import com.personal.finance.dto.mapper.ExpenseMapper;
import com.personal.finance.dto.request.AIRequest;
import com.personal.finance.dto.request.CreateExpenseRequest;
import com.personal.finance.dto.request.UpdateExpenseRequest;
import com.personal.finance.dto.response.AIResponse;
import com.personal.finance.dto.response.ExpenseResponse;
import com.personal.finance.entity.Category;
import com.personal.finance.entity.Expense;
import com.personal.finance.entity.Merchant;
import com.personal.finance.exception.ResourceNotFoundException;
import com.personal.finance.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryService categoryService;
    private final MerchantService merchantService;
    private final AIClient aiClient;
    private final ExpenseMapper expenseMapper;


    public ExpenseResponse createExpense(CreateExpenseRequest request) {
        AIResponse aiResponse = aiClient.classify(new AIRequest(request.description()));

        Category category = categoryService.findOrCreate(aiResponse.category());

        Merchant merchant = merchantService.findOrCreate(aiResponse.merchant());

        Expense expense = buildExpense(request, aiResponse, category, merchant);

        expenseRepository.save(expense);

        return expenseMapper.toResponse(expense);

    }

    public List<ExpenseResponse> findAll() {
        return expenseMapper.toResponseList(expenseRepository.findAll());
    }

    public List<ExpenseResponse> findByPeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null) {
            return expenseMapper.toResponseList(expenseRepository.findByExpenseDateBetween(startDate, endDate));
        }

        return findAll();
    }

    public ExpenseResponse updateExpense(Long id, UpdateExpenseRequest request) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id " + id));

        expense.setDescription(request.description());
        expense.setExpenseDate(request.expenseDate());
        expense.setAmount(request.amount());

        expenseRepository.save(expense);
        return expenseMapper.toResponse(expense);
    }

    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id " + id));

        expenseRepository.delete(expense);
    }

    private Expense buildExpense(CreateExpenseRequest request, AIResponse aiResponse, Category category, Merchant merchant) {
        return Expense.builder()
                .description(request.description())
                .expenseDate(request.expenseDate())
                .amount(aiResponse.value())
                .confidence(aiResponse.confidence())
                .category(category)
                .merchant(merchant)
                .aiClassified(true)
                .build();

    }
}
