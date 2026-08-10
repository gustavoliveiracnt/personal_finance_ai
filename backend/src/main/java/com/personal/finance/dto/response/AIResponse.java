package com.personal.finance.dto.response;

import java.math.BigDecimal;

public record AIResponse(String merchant, BigDecimal value, String category, BigDecimal confidence) {
}
