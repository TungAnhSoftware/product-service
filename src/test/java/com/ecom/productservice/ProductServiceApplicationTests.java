package com.ecom.productservice;

import com.ecom.productservice.dto.CreateProductArgs;
import com.ecom.productservice.dto.GetProductDetailArgs;
import com.ecom.productservice.dto.GetProductListArgs;
import com.ecom.productservice.dto.UpdateProductArgs;
import com.ecom.productservice.model.Product;
import com.ecom.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceApplicationTests extends AbstractContainerBaseTest {
    private static final Logger logger =
            LoggerFactory.getLogger(ProductServiceApplicationTests.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void createProductTest() throws Exception {
        logger.info("createProductTest");

        //Create 1st object - expect: 201 Created
        CreateProductArgs reqProduct1 = new CreateProductArgs("iPhone 13",
                BigDecimal.valueOf(1200), "testUrl1", "#ffffff",
                BigDecimal.valueOf(15));
        String reqString = objectMapper.writeValueAsString(reqProduct1);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product" +
                "/createProduct").contentType(MediaType.APPLICATION_JSON).content(reqString)).andExpect(status().isCreated());

        //Create 2nd object - expect: 201 Created
        CreateProductArgs reqProduct2 = new CreateProductArgs("iPhone 14",
                BigDecimal.valueOf(1500), "testUrl2", "#ff3333",
                BigDecimal.valueOf(18));
        String reqString2 = objectMapper.writeValueAsString(reqProduct2);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product" +
                "/createProduct").contentType(MediaType.APPLICATION_JSON).content(reqString2)).andExpect(status().isCreated());

        //Create 1st object 2nd time - expect: Bad request
        //        mockMvc.perform(MockMvcRequestBuilders.post("/api/product" +
        //                "/createProduct").contentType(MediaType
        //                .APPLICATION_JSON)
        //                .content(reqString)).andExpect(status()
        //                .is5xxServerError());
    }

    @Test
    public void updateProductTest() throws Exception {
        logger.info("updateProductTest");

        Product storedProduct = productRepository.findAll().get(0);
        logger.info("storedProduct " + storedProduct);

        UpdateProductArgs req =
                new UpdateProductArgs(storedProduct.getId().toString(),
                        "iPhone 17", BigDecimal.valueOf(1200), "testUrl",
                        "#000000", BigDecimal.valueOf(15));
        String reqString = objectMapper.writeValueAsString(req);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product" +
                "/updateProduct").contentType(MediaType.APPLICATION_JSON).content(reqString)).andExpect(status().isOk());
    }

    @Test
    public void getProductDetailTest() throws Exception {
        logger.info("getProductDetailTest");

        Product storedProduct = productRepository.findAll().get(0);
        logger.info("storedProduct " + storedProduct);

        GetProductDetailArgs req =
                new GetProductDetailArgs(storedProduct.getId().toString());

        String reqString = objectMapper.writeValueAsString(req);
        String expectedJsonPath = "$.name";
        String expectedJson = storedProduct.getName();
        mockMvc.perform(MockMvcRequestBuilders.get("/api" + "/product" +
                "/getProductDetail").contentType(MediaType.APPLICATION_JSON).content(reqString)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath(expectedJsonPath).value(expectedJson));
    }

    @Test
    public void getProductListTest() throws Exception {
        logger.info("getProductListTest");

        GetProductListArgs req = new GetProductListArgs(0, 100, 0, 0, "");
        String reqString = objectMapper.writeValueAsString(req);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product" +
                "/getProductList").contentType(MediaType.APPLICATION_JSON).content(reqString)).andExpect(status().isOk());
    }
}
