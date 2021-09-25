package com.project.doubleshop.web.member.controller;

import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import com.project.doubleshop.domain.member.service.MemberService;
import com.project.doubleshop.domain.member.service.SmsVerificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    private final SmsVerificationService smsVerificationService;

    // 아이디 중복 체크
    @GetMapping("/{userId}/exists")
    public ResponseEntity<Boolean> checkIdDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok(memberService.isIdDuplicate(userId));
    }

    // 이메일 중복 체크
    @GetMapping("/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(memberService.isEmailDuplicate(email));
    }

    // 회원 등록
    @PostMapping
    public ResponseEntity<Void> createMember(@Valid @RequestBody MemberSaveRequestDto requestDto) {
        memberService.registerMember(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // SMS 인증 번호 전송
    @GetMapping("/verification/sends")
    public ResponseEntity<Void> sendSms(@RequestParam String phoneNum, HttpSession session) {
        smsVerificationService.sendSms("82", phoneNum, session);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 인증 번호 비교
    @GetMapping("/verification/confirms")
    public ResponseEntity<Void> verifySms(@RequestParam String phoneNum, String inputNum, HttpSession session) {
        boolean result = smsVerificationService.verifySms(phoneNum, inputNum, session);

        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
