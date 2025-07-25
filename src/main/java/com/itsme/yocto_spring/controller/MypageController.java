package com.itsme.yocto_spring.controller;

import com.itsme.yocto_spring.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    // 3. 말하기 리포트(SP) - 하루 단위
    @GetMapping("/mypage/report/voice")
    public Map<String, Object> getVoiceReport(
            @RequestParam("user_id") String userId,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day
    ) {
        return mypageService.getVoiceReport(userId, year, month, day);
    }

    // 4. 쓰기 리포트(WT) - 하루 단위
    @GetMapping("/mypage/report/image")
    public Map<String, Object> getImageReport(
            @RequestParam("user_id") String userId,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day
    ) {
        return mypageService.getImageReport(userId, year, month, day);
    }

    // 5. 학습 진도 조회(주간)
    @GetMapping("/mypage/progress/weekly")
    public Map<String, Object> getWeekly(
            @RequestParam("user_id") String userId,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int week
    ) {
        return mypageService.getWeeklyProgress(userId, year, month, week);
    }

    // 6. 학습 진도 조회(월간)
    @GetMapping("/mypage/progress/monthly")
    public Map<String, Object> getMonthly(
            @RequestParam("user_id") String userId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return mypageService.getMonthlyProgress(userId, year, month);
    }
}
