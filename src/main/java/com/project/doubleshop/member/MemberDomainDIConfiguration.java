package com.project.doubleshop.member;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.project.doubleshop.member.infrastructure.MemberInfoFinder;
import com.project.doubleshop.member.infrastructure.MemberInfoManager;
import com.project.doubleshop.member.infrastructure.MemberRegisterManager;
import com.project.doubleshop.member.infrastructure.MemberRegisterProcessor;
import com.project.doubleshop.member.infrastructure.MemberRegisterVerifier;
import com.project.doubleshop.member.infrastructure.jpa.MemberRepositoryAdapter;
import com.project.doubleshop.member.infrastructure.token.TokenMemberAuthProcessor;

@Import({
	MemberRepositoryAdapter.class,
	TokenMemberAuthProcessor.class,
	MemberRegisterManager.class,
	MemberRegisterProcessor.class,
	MemberRegisterVerifier.class,
	MemberInfoFinder.class,
	MemberInfoManager.class
})
@Configuration
public class MemberDomainDIConfiguration {
}
