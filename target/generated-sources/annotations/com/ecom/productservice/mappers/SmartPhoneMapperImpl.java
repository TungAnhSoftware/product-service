package com.ecom.productservice.mappers;

import com.ecom.productservice.dto.SmartPhoneReq;
import com.ecom.productservice.dto.SmartPhoneResp;
import com.ecom.productservice.model.SmartPhoneModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-07T17:46:00+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class SmartPhoneMapperImpl implements SmartPhoneMapper {

    @Override
    public SmartPhoneResp ModelToResp(SmartPhoneModel smartPhoneModel) {
        if ( smartPhoneModel == null ) {
            return null;
        }

        SmartPhoneResp.SmartPhoneRespBuilder smartPhoneResp = SmartPhoneResp.builder();

        smartPhoneResp.id( smartPhoneModel.getId() );
        smartPhoneResp.name( smartPhoneModel.getName() );
        smartPhoneResp.price( smartPhoneModel.getPrice() );

        return smartPhoneResp.build();
    }

    @Override
    public SmartPhoneModel ReqToModel(SmartPhoneReq smartPhoneReq) {
        if ( smartPhoneReq == null ) {
            return null;
        }

        SmartPhoneModel smartPhoneModel = new SmartPhoneModel();

        smartPhoneModel.setName( smartPhoneReq.getName() );
        smartPhoneModel.setPrice( smartPhoneReq.getPrice() );

        return smartPhoneModel;
    }
}
