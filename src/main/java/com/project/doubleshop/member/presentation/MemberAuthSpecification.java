package com.project.doubleshop.member.presentation;

import org.springframework.web.bind.annotation.RequestBody;

import com.project.doubleshop.common.ApiResult;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.infrastructure.AuthenticationResult;
import com.project.doubleshop.member.presentation.request.AuthenticationRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface MemberAuthSpecification {

	@Operation(summary = "Authenticate member with valid id, password")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Authentication success",
			content = { @Content(mediaType = "application/json") }),
		@ApiResponse(responseCode = "400", description = "Autentication fail",
			content = @Content),
		@ApiResponse(responseCode = "404", description = "Cannot found member",
			content = @Content) })
	ApiResult<AuthenticationResult> authentication(AuthenticationRequest authRequest);
}
