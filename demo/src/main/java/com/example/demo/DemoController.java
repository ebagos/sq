package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/collatz")
public class DemoController {

    @GetMapping("/{maxValue}")
    public Map<String, Long> getCollatz(@PathVariable Long maxValue) {
        Long longestLength = 0L;
        Long longestIndex = 0L;

        for (Long i = 1L; i <= maxValue; i++) {
            Long length = calculateCollatzLength(i);
            if (length > longestLength) {
                longestLength = length;
                longestIndex = i;
            }
        }

        Map<String, Long> result = new HashMap<>();
        result.put("index", longestIndex);
        result.put("length", longestLength);
        return result;
    }

    private Long calculateCollatzLength(Long n) {
        Long length = 1L;
        while (n != 1L) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
            length++;
        }
        return length;
    }
}
