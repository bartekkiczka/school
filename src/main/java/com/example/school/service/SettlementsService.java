package com.example.school.service;

import com.example.school.model.Attendance;
import com.example.school.model.Child;
import com.example.school.model.Parent;
import com.example.school.model.School;
import com.example.school.dto.settlements.ChildSettlementsDto;
import com.example.school.dto.settlements.ParentSettlementsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementsService {

    private final int FREE_HOUR_LIMIT_START = 7;
    private final int FREE_HOUR_LIMIT_END = 12;

    private final AttendanceService attendanceService;
    private final SchoolService schoolService;
    private final ParentService parentService;

    public List<ParentSettlementsDto> calculateSchoolSettlements(List<Child> children, School school, int monthNumber) {
        List<ParentSettlementsDto> parentSettlementsResponses = new ArrayList<>();
        children.forEach(child -> {
            if (child.getSchool().getId() != school.getId()) {
                return;
            }

            handleCreateNewParentSettlements(child, parentSettlementsResponses);

            int timeSpent = calculateChildAttendancesTime(child, monthNumber);
            float chargeSum = timeSpent * school.getHourPrice();

            handleChildSettlementAndUpdateParentSettlement(child, parentSettlementsResponses, chargeSum, timeSpent);
        });

        return parentSettlementsResponses;
    }

    public ParentSettlementsDto calculateParentSettlements(List<Child> children, long parentId, int monthNumber) {
        Parent parent = parentService.findById(parentId);
        ParentSettlementsDto response = ParentSettlementsDto.builder()
                .parentId(parentId)
                .parentFirstName(parent.getFirstName())
                .parentLastName(parent.getLastName())
                .chargeSum(0f)
                .childSettlementsList(new ArrayList<>())
                .build();
        children.forEach(child -> {
            School school = schoolService.findById(child.getSchool().getId());
            int timeSpent = calculateChildAttendancesTime(child, monthNumber);
            float chargeSum = timeSpent * school.getHourPrice();
            ChildSettlementsDto childSettlementsResponse = ChildSettlementsDto.builder()
                    .childName(child.getFirstName())
                    .childSurname(child.getLastName())
                    .charge(chargeSum)
                    .timeSpent(timeSpent)
                    .build();
            response.addChildSettlement(childSettlementsResponse);
            response.setChargeSum(response.getChargeSum() + chargeSum);
        });
        return response;
    }

    private boolean parrentOccursOnList(List<ParentSettlementsDto> list, long parentId) {
        return list.stream().anyMatch(e -> e.getParentId() == parentId);
    }

    private int calculateChildAttendancesTime(Child child, int monthNumber) {
        List<Attendance> attendances = attendanceService.findByChildId(child.getId())
                .stream()
                .filter(attendance -> attendance.getEntryDate().getMonth() == Month.of(monthNumber)
                        && attendance.getExitDate().getMonth() == Month.of(monthNumber))
                .toList();

        int timeSpent = 0;
        for (Attendance attendance : attendances) {
            long durationHours = ChronoUnit.HOURS.between(attendance.getEntryDate(), attendance.getExitDate());
            for (long i = 0; i < durationHours; i++) {
                LocalDateTime currentHour = attendance.getEntryDate().plusHours(i);
                if (currentHour.getHour() >= FREE_HOUR_LIMIT_START && currentHour.getHour() < FREE_HOUR_LIMIT_END) {
                    continue;
                }
                timeSpent++;
            }
        }
        return timeSpent;
    }

    private void handleChildSettlementAndUpdateParentSettlement(
            Child child, List<ParentSettlementsDto> parentSettlementsResponses, float chargeSum, int timeSpent) {
        ChildSettlementsDto childSettlementsResponse = ChildSettlementsDto.builder()
                .childName(child.getFirstName())
                .childSurname(child.getLastName())
                .charge(chargeSum)
                .timeSpent(timeSpent)
                .build();

        ParentSettlementsDto response = parentSettlementsResponses.stream()
                .filter(e -> e.getParentId() == child.getParent().getId()).toList().get(0);
        response.addChildSettlement(childSettlementsResponse);
        response.setChargeSum(response.getChargeSum() + chargeSum);
    }

    private void handleCreateNewParentSettlements(Child child, List<ParentSettlementsDto> parentSettlementsResponses) {
        ParentSettlementsDto parentSettlementsResponse = ParentSettlementsDto.builder()
                .parentId(child.getParent().getId())
                .parentFirstName(child.getParent().getFirstName())
                .parentLastName(child.getParent().getLastName())
                .chargeSum(0f)
                .childSettlementsList(new ArrayList<>())
                .build();
        if (!parrentOccursOnList(parentSettlementsResponses, child.getParent().getId())) {
            parentSettlementsResponses.add(parentSettlementsResponse);
        }
    }
}
