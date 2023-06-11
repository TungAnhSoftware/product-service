package com.ecom.productservice.service;

import com.ecom.productservice.dto.SmartPhoneResp;
import com.ecom.productservice.exception.SmartPhoneException;
import com.ecom.productservice.mappers.SmartPhoneMapper;
import com.ecom.productservice.model.SmartPhoneModel;
import com.ecom.productservice.dto.SmartPhoneReq;
import com.ecom.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    private final SmartPhoneMapper smartPhoneMapper;

    public List<SmartPhoneResp> getAllSmartphones() {
        List<SmartPhoneModel> smartPhoneModels = productRepository.findAll();
        return smartPhoneModels.stream().map(smartPhoneMapper::ModelToResp).toList();
    }

    public void createSmartPhone(SmartPhoneReq smartPhoneReq) {
        Optional<SmartPhoneModel> storedModel = productRepository.findByName(smartPhoneReq.getName());

        if (storedModel.isPresent()) {
            throw new SmartPhoneException("This SmartPhone already exists", HttpStatus.BAD_REQUEST);
        }

        SmartPhoneModel smartPhoneModel = smartPhoneMapper.ReqToModel(smartPhoneReq);
        productRepository.save(smartPhoneModel);
        log.info("Model " + smartPhoneModel.getName() + " is added to Database!");
    }
}
