package com.example.shopapi;

import com.example.shopcore.code.ReviewCode;
import com.example.shopcore.utils.email.EmailDto;
import com.example.shopcore.utils.email.EmailUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final EmailUtil emailUtil;

    @GetMapping("/code-title/delete")
    public String getCode() {
        return ReviewCode.DELETE.getTitle();
    }


    @GetMapping("/api/test/email")
    public ResponseEntity sendEmail(
        @RequestParam("from") String from,
        @RequestParam("to") String to,
        @RequestParam("title") String title,
        @RequestParam("text") String text
    ) {
        emailUtil.sendEmail(EmailDto.builder()
            .from(from)
            .to(List.of(to))
            .cc(List.of(to))
            .title(title)
            .text(text)
            .build()
        );
        return ResponseEntity.ok("Success send email");
    }

}
