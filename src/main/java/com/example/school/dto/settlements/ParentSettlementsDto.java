package com.example.school.dto.settlements;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ParentSettlementsDto {
    private long parentId;
    private String parentFirstName;
    private String parentLastName;
    private Float chargeSum;
    private List<ChildSettlementsDto> childSettlementsList;

    public void addChildSettlement(ChildSettlementsDto childSettlementsResponse){
        this.childSettlementsList.add(childSettlementsResponse);
    }
}
