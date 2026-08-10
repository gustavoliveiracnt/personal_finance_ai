package com.personal.finance.service;

import com.personal.finance.entity.Merchant;
import com.personal.finance.repository.MerchantRepository;
import com.personal.finance.utils.ToolsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public Merchant findOrCreate(String merchantName) {
        String normalizedName = ToolsUtil.normalize(merchantName);
        return merchantRepository.findByNameIgnoreCase(merchantName)
                .orElseGet(() -> create(normalizedName));
    }

    private Merchant create(String merchantName) {
        Merchant merchant = Merchant.builder()
                .name(merchantName.trim())
                .build();

        return merchantRepository.save(merchant);
    }
}
