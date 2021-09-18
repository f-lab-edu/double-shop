package com.project.doubleshop.controller;

import com.project.doubleshop.dto.MemberSaveRequestDto;
import com.project.doubleshop.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{userId}/exists")
    public boolean checkIdDuplicate(@PathVariable String userId) {
        return memberService.checkIdDuplicate(userId);
    }

    @GetMapping("/{email}/exists")
    public boolean checkEmailDuplicate(@PathVariable String email) {
        return memberService.checkEmailDuplicate(email);
    }

    @PostMapping
    public ResponseEntity<Void> createMember(@Valid @RequestBody MemberSaveRequestDto requestDto) {
        memberService.signUp(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
