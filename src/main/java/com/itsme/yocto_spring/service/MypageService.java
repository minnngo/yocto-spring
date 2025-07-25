package com.itsme.yocto_spring.service;

import com.itsme.yocto_spring.domain.Feedback;
import com.itsme.yocto_spring.domain.History;
import com.itsme.yocto_spring.repository.FeedbackRepository;
import com.itsme.yocto_spring.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final FeedbackRepository feedbackRepository;
    private final HistoryRepository historyRepository; // 주간/월간용(이미 프로젝트에 있는 것으로 가정)

    /* 3. 말하기 리포트 (voice) */
    public Map<String, Object> getVoiceReport(String userId, int year, int month, int day) {
        return buildReport(userId, "voice", year, month, day);
    }

    /* 4. 쓰기 리포트 (image) */
    public Map<String, Object> getImageReport(String userId, int year, int month, int day) {
        return buildReport(userId, "image", year, month, day);
    }

    private Map<String, Object> buildReport(String userId, String type, int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);

        List<Feedback> list = feedbackRepository.findDailyByUserIdAndType(userId, type, start, end);

        List<Map<String, Object>> report = list.stream()
                .map(f -> Map.<String, Object>of(
                        "date", f.getCreatedAt().toLocalDate().toString(),
                        "text", f.getTranscribedText(),
                        "corrected_text", f.getCorrectedText()
                ))
                .collect(Collectors.toList());

        return Map.of("report", report);
    }

    /* 5. 학습 진도 조회(주간)
       - week: 1~4 (1주차: 1~7일, 2주차: 8~14일, 3주차: 15~21일, 4주차: 22일~말일) */
    public Map<String, Object> getWeeklyProgress(String userId, int year, int month, int week) {
        int startDay = (week - 1) * 7 + 1;
        int lastDayOfMonth = YearMonth.of(year, month).lengthOfMonth();
        int endDay = (week < 4) ? (startDay + 6) : lastDayOfMonth;

        List<Integer> studyTime = new ArrayList<>();
        List<String> levelHistory = new ArrayList<>();

        for (int d = startDay; d <= endDay; d++) {
            LocalDate date = LocalDate.of(year, month, d);
            Optional<History> opt = historyRepository.findByUserIdAndDate(userId, date);

            if (opt.isPresent()) {
                History h = opt.get();
                studyTime.add(h.getStudyTime());
                levelHistory.add(h.getLevel().name());
            } else {
                studyTime.add(0);
                levelHistory.add("Unknown");
            }
        }

        return Map.of(
                "study_time", studyTime,
                "level_history", levelHistory
        );
    }

    /* 6. 학습 진도 조회(월간) - 1일부터 말일까지 */
    public Map<String, Object> getMonthlyProgress(String userId, int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        int last = ym.lengthOfMonth();

        List<Integer> studyTime = new ArrayList<>(last);
        List<String> levelHistory = new ArrayList<>(last);

        for (int d = 1; d <= last; d++) {
            LocalDate date = LocalDate.of(year, month, d);
            Optional<History> opt = historyRepository.findByUserIdAndDate(userId, date);

            if (opt.isPresent()) {
                History h = opt.get();
                studyTime.add(h.getStudyTime());
                levelHistory.add(h.getLevel().name());
            } else {
                studyTime.add(0);
                levelHistory.add("Unknown");
            }
        }

        return Map.of(
                "study_time", studyTime,
                "level_history", levelHistory
        );
    }
}
