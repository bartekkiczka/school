package com.example.school.service;

import com.example.school.DatabaseCleaner;
import com.example.school.dto.settlements.ParentSettlementsDto;
import com.example.school.model.Child;
import com.example.school.model.School;
import liquibase.exception.LiquibaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class SettlementsServiceTest {

    @Autowired
    private SettlementsService settlementsService;

    @Autowired
    private ChildService childService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    public void setup() throws LiquibaseException {
        databaseCleaner.cleanUp();
    }

    @Test
    public void testCalculateSchoolSettlements_happyPath_isOk(){
        //given
        List<Child> children = childService.findAll(PageRequest.of(0, 3)).getContent();
        School school = schoolService.findById(1);
        final int monthNumber = 8;

        //when
        List<ParentSettlementsDto> dtoList = settlementsService.calculateSchoolSettlements(children, school, monthNumber);

        //then
        assertEquals(dtoList.size(), 1);
        assertEquals(dtoList.get(0).getParentId(), 1);
        assertEquals(dtoList.get(0).getChargeSum(), 30.0f);
        assertEquals(dtoList.get(0).getParentFirstName(), "Jan");
        assertEquals(dtoList.get(0).getParentLastName(), "Kowalski");
        assertEquals(dtoList.get(0).getChildSettlementsList().size(), 1);
        assertEquals(dtoList.get(0).getChildSettlementsList().get(0).getChildName(), "Marcin");
        assertEquals(dtoList.get(0).getChildSettlementsList().get(0).getChildSurname(), "Kowalski");
        assertEquals(dtoList.get(0).getChildSettlementsList().get(0).getTimeSpent(), 3);
        assertEquals(dtoList.get(0).getChildSettlementsList().get(0).getCharge(), 30.0f);
    }

    @Test
    public void testCalculateParentSettlements_happyPath_isOk(){
        //given
        List<Child> children = childService.findAll(PageRequest.of(0, 3)).getContent();
        final long parentId = 1L;
        final int monthNumber = 8;

        //when
        ParentSettlementsDto dto = settlementsService.calculateParentSettlements(children, parentId, monthNumber);

        //then
        assertEquals(dto.getParentId(), 1);
        assertEquals(dto.getParentFirstName(), "Jan");
        assertEquals(dto.getParentLastName(), "Kowalski");
        assertEquals(dto.getChargeSum(), 30.0f);
        assertEquals(dto.getChildSettlementsList().size(), 1);
        assertEquals(dto.getChildSettlementsList().get(0).getChildName(), "Marcin");
        assertEquals(dto.getChildSettlementsList().get(0).getChildSurname(), "Kowalski");
        assertEquals(dto.getChildSettlementsList().get(0).getTimeSpent(), 3);
        assertEquals(dto.getChildSettlementsList().get(0).getCharge(), 30.0f);
    }

}