package com.example.shopapi;

import com.example.shopcore.code.ReviewCode;
import com.example.shopcore.utils.email.EmailDto;
import com.example.shopcore.utils.email.EmailUtil;
import com.example.shopentity.TestService;
import com.example.shopentity.member.entity.Member;
import com.example.shopentity.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    private final TestService testService;


    @GetMapping("/profile-test")
    public void profileTest() {
        testService.testActive();
    }

    @GetMapping("/member")
    public Member callRepository() {
        Member member = new Member();
        member.setName("홍길동");
        memberRepository.save(member);
        Member findMember = memberRepository.findById(1L).get();
        return findMember;
    }

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

    @GetMapping("/api/test/entity")
    public String entityTest() {
        Member member = new Member();
        member.setName("홍길동");
        return member.toString();
    }
}
