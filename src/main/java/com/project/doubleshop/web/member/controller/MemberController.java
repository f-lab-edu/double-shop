package com.project.doubleshop.web.member.controller;

import com.project.doubleshop.domain.member.service.EmailVerificationService;
import com.project.doubleshop.domain.member.service.LogInService;
import com.project.doubleshop.web.member.annotation.CurrentMember;
import com.project.doubleshop.web.member.annotation.LogInCheck;
import com.project.doubleshop.web.member.dto.*;
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

    private final EmailVerificationService emailVerificationService;

    private final LogInService logInService;

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
    @GetMapping("/sms-verification/sends")
    public ResponseEntity<Void> sendSms(@RequestParam String phoneNum, HttpSession session) {
        smsVerificationService.sendSms("82", phoneNum, session);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 인증 번호 비교
    @GetMapping("/sms-verification/confirms")
    public ResponseEntity<Void> verifySms(@RequestParam String phoneNum, String inputNum, HttpSession session) {
        boolean result = smsVerificationService.verifySms(phoneNum, inputNum, session);

        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 로그인
    @PostMapping("/logIn")
    public ResponseEntity<Void> logIn(@RequestBody LogInRequestDto requestDto) {
        logInService.existsByUserIdAndPassword(requestDto);
        logInService.logIn(requestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 로그아웃
    @LogInCheck
    @GetMapping("/logOut")
    public ResponseEntity<Void> logOut() {
        logInService.logOut();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 마이 페이지
    @LogInCheck
    @GetMapping("/myInfo")
    public ResponseEntity<MemberInfoDto> getMemberInfo(@CurrentMember String userId) {
        MemberInfoDto member = logInService.getCurrentMember(userId);

        return ResponseEntity.ok(member);
    }

    /**
     * 비밀번호 찾기: 입력받은 아이디가 존재한다면 휴대폰/이메일 인증 중 한 가지를 선택하도록 구현
     * - 휴대폰 인증: sendSms, verifySms
     * - 이메일 인증: sendEmail, verifyEmail
     */

    @GetMapping("find/{userId}")
    public ResponseEntity<MemberFindResponseDto> findMember(@PathVariable String userId) {
        MemberFindResponseDto responseDto = memberService.getMemberResource(userId);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("email-verification/sends")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailVerificationRequestDto requestDto, HttpSession session) {
        emailVerificationService.sendEmail(requestDto.getEmail(), session);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("email-verification/confirms")
    public ResponseEntity<Void> verifyEmail(@RequestBody EmailVerificationRequestDto requestDto, HttpSession session) {
        boolean result = emailVerificationService.verifyEmail(requestDto, session);

        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("password")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequestDto requestDto) {
        memberService.updatePassword(requestDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
