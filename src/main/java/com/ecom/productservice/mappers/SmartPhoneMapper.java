package com.ecom.productservice.mappers;

import com.ecom.productservice.dto.SmartPhoneResp;
import com.ecom.productservice.model.SmartPhoneModel;
import com.ecom.productservice.dto.SmartPhoneReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SmartPhoneMapper {

    SmartPhoneResp ModelToResp(SmartPhoneModel smartPhoneModel);

    @Mapping(target = "name")
    SmartPhoneModel ReqToModel(SmartPhoneReq smartPhoneReq);

}
