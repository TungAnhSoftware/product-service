package com.ecom.productservice.repository;

import com.ecom.productservice.model.SmartPhoneModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<SmartPhoneModel, Integer> {
    Optional<SmartPhoneModel> findByName(String name);
}

