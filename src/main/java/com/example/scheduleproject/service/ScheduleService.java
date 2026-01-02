package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.*;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    // - 사용자가 입력한 요청 DTO를 Schedule 엔티티로 변환
    // - DB에 저장
    // - 응답 DTO로 변환하여 반환
    @Transactional
    public ScheduleCreateResponse save(ScheduleCreateRequest request) {
        // 요청값 -> 엔티티 객체로 변환
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getWriter(),
                request.getPassword()
        );

        // DB에 저장
        Schedule saved = scheduleRepository.save(schedule);

        // 응답 DTO로 반환 (password 제외)
        return new ScheduleCreateResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getWriter(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }

    // 전체 일정 조회
    // - writer가 존재하면 해당 작성자의 일정만 조회
    // - 없으면 전체 일정 조회
    // - 항상 수정일 기준 내림차순 정렬
    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> findAll(String writer) {
        // writer 조건에 따라 조회 방식 분기
        List<Schedule> schedules = (writer == null || writer.isBlank())
                ? scheduleRepository.findAllByOrderByModifiedAtDesc()
                : scheduleRepository.findAllByWriterOrderByModifiedAtDesc(writer);

        // 엔티티 리스트 -> 응답 DTO로 반환 (password 제외)
        return schedules.stream()
                .map(s -> new ScheduleGetResponse(
                        s.getId(),
                        s.getTitle(),
                        s.getContent(),
                        s.getWriter(),
                        s.getCreatedAt(),
                        s.getModifiedAt()
                ))
                .toList();
    }

    // 단건 일정 조회
    // - ID로 특정 일정 1개 조회
    @Transactional(readOnly = true)
    public ScheduleGetResponse findOne(Long scheduleId) {
        // DB에서 ID로 일정 조회
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("없는 일정입니다."));

        // 응답 DTO로 반환 (password 제외)
        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 수정
    // - title, content만 수정 가능
    // - 수정 시 modifiedAt 자동 갱신됨
    @Transactional
    public ScheduleUpdateResponse update(Long scheduleId, ScheduleUpdateRequest request) {
        // 수정할 일정 조회
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("없는 일정입니다."));

        // 비밀번호 검증
        schedule.checkPassword(request.getPassword());

        // 제목과 내용 수정
        schedule.update(request.getTitle(), request.getContent());

        // 수정 결과를 응답 DTO로 반환 (password 제외)
        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 삭제
    @Transactional
    public void delete(Long scheduleId, ScheduleDeleteRequest request) {
        // 삭제할 일정 조회
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("없는 일정입니다."));

        // 비밀번호 검증
        schedule.checkPassword(request.getPassword());

        // DB에서 일정 삭제
        scheduleRepository.delete(schedule);
    }
}
