package com.project.doubleshop.member.presentation;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.common.ApiResult;
import com.project.doubleshop.member.application.MemberFacade;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.application.JoinRequest;
import com.project.doubleshop.member.presentation.response.MemberJoinResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MemberJoinApi {
	private final MemberFacade memberFacade;

	@PostMapping("v2/member/join")
	public ApiResult<MemberJoinResponse> join(@RequestBody @Valid JoinRequest joinRequest) {
		Member member = memberFacade.join(joinRequest);
		return ApiResult.OK(new MemberJoinResponse(member));
	}
}
