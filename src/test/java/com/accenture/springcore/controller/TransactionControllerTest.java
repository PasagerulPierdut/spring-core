package com.accenture.springcore.controller;

import com.accenture.model.Product;
import com.accenture.model.Transaction;
import com.accenture.springcore.model.Dto.SortCriteriaInfo;
import com.accenture.springcore.model.TransactionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TransactionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
//    @WithMockUser(roles="ADMIN")
    void testIfFindAllMethodReturnsWithSortCriteria() throws Exception {

        SortCriteriaInfo sortCriteriaInfo = new SortCriteriaInfo();
        sortCriteriaInfo.setMaxAmount(30.0);
        String json = mapper.writeValueAsString(sortCriteriaInfo);
        mockMvc.perform(get("/transactions")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testAddNewTransactionAuthenticated() throws Exception {

        ObjectMapper newMapper = new ObjectMapper();
        newMapper.registerModule(new JSR310Module());
        String json = newMapper.writeValueAsString(new Transaction(3,TransactionType.BUY, 25.0,
                LocalDateTime.of(2022, 05, 28, 18, 11, 21, 00001), true, new Product("test", "test",
                LocalDateTime.of(2022, 05, 28, 18, 11, 20, 00001), LocalDateTime.of(2022, 05, 28, 18, 11, 21, 00001))));

        mockMvc.perform(post("/transactions")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isForbidden());
    }
}