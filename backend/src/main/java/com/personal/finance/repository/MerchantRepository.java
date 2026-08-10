package com.personal.finance.repository;

import com.personal.finance.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository  extends JpaRepository<Merchant, Long> {

    Optional<Merchant> findByNameIgnoreCase(String name);
}
