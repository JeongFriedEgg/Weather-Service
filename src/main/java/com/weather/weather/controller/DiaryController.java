package com.weather.weather.controller;

import com.weather.weather.domain.Diary;
import com.weather.weather.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @Operation(summary = "일기 작성", description = "일기 텍스트와 날짜를 이용해서 DB에 일기 저장")
    @PostMapping("/diary")
    void createDiary(
            @Parameter(description = "날짜 형식 : yyyy-MM-dd", example = "2025-04-17")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody String text
    ) {
        diaryService.createDiary(date, text);
    }

    @Operation(summary = "일기 조회", description = "선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/diary")
    List<Diary> readDiary(
            @Parameter(description = "날짜 형식 : yyyy-MM-dd", example = "2025-04-17")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return diaryService.readDiary(date);
    }

    @Operation(summary = "기간 조회", description = "선택한 기간의 모든 일기를 가져옵니다.")
    @GetMapping("/diaries")
    List<Diary> readDiaries(
            @Parameter(description = "시작 날짜", example = "2025-04-01")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "끝 날짜", example = "2025-04-17")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return diaryService.readDiaries(startDate,endDate);
    }

    @Operation(summary = "일기 수정", description = "해당 날짜의 일기를 수정합니다.")
    @PutMapping("/diary")
    void updateDiary(
            @Parameter(description = "수정할 날짜", example = "2025-04-17")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody String text
    ) {
        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "일기 삭제", description = "해당 날짜의 일기를 삭제합니다.")
    @DeleteMapping("/diary")
    void deleteDiary(
            @Parameter(description = "삭제할 날짜", example = "2025-04-17")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        diaryService.deleteDiary(date);
    }
}