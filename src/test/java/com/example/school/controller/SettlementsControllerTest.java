package com.example.school.controller;

import com.example.school.DatabaseCleaner;
import liquibase.exception.LiquibaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SettlementsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    public void setup() throws LiquibaseException {
        databaseCleaner.cleanUp();
    }

    @Test
    public void testGetSchoolSettlements_happyPath_isOk() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(get("/settlements/school/1/month/8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolId").value(1))
                .andExpect(jsonPath("$.schoolName").value("school1"))
                .andExpect(jsonPath("$.parentSettlements.[0].parentId").value("1"))
                .andExpect(jsonPath("$.parentSettlements.[0].parentFirstName").value("Jan"))
                .andExpect(jsonPath("$.parentSettlements.[0].parentLastName").value("Kowalski"))
                .andExpect(jsonPath("$.parentSettlements.[0].chargeSum").value(30.0f))
                .andExpect(jsonPath("$.parentSettlements.[0].childSettlementsList.[0].childName").value("Marcin"))
                .andExpect(jsonPath("$.parentSettlements.[0].childSettlementsList.[0].childSurname").value("Kowalski"))
                .andExpect(jsonPath("$.parentSettlements.[0].childSettlementsList.[0].charge").value(30.0f))
                .andExpect(jsonPath("$.parentSettlements.[0].childSettlementsList.[0].timeSpent").value(3));
    }

    @Test
    public void testGetParentSettlements_happyPath_isOk() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(get("/settlements/parent/1/month/8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.parentId").value("1"))
                .andExpect(jsonPath("$.parentFirstName").value("Jan"))
                .andExpect(jsonPath("$.parentLastName").value("Kowalski"))
                .andExpect(jsonPath("$.chargeSum").value(30.0f))
                .andExpect(jsonPath("$.childSettlementsList.[0].childName").value("Marcin"))
                .andExpect(jsonPath("$.childSettlementsList.[0].childSurname").value("Kowalski"))
                .andExpect(jsonPath("$.childSettlementsList.[0].charge").value(30.0f))
                .andExpect(jsonPath("$.childSettlementsList.[0].timeSpent").value(3));
    }
}