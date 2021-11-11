package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.domain.exception.MemberNotFoundException;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.repository.MemberInfoRepository;
import com.project.doubleshop.domain.utils.SHA256EncryptionUtil;
import com.project.doubleshop.web.member.dto.MemberFindResponseDto;
import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import com.project.doubleshop.web.member.dto.PasswordChangeRequestDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @ExtendWith: 기존의 JUnit4에서는 Mockito를 활용하기 위해서
 * 클래스 애노테이션으로 @RunWith(JUnitMockitoRunner.class)를 붙여주어야 했지만,
 * Spring Boot 2.2.0부터 공식적으로 JUnit5를 지원함에 따라 @ExtendWith를 사용해야 결합이 가능하다.
 */
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class MemberServiceImplTest {

    @Mock
    MemberInfoRepository repository;

    @Mock
    SHA256EncryptionUtil encryptionUtil;

    @InjectMocks
    MemberServiceImpl memberService;

    private MemberSaveRequestDto createMemberDto() {
        return MemberSaveRequestDto.builder()
                .userId("testid")
                .password("12345")
                .name("John Doe")
                .phone("01012345678")
                .email("testemail@gmail.com")
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("회원가입 성공")
    void registerMember() {
        MemberSaveRequestDto requestDto = createMemberDto();

        memberService.registerMember(requestDto);

        verify(repository, atLeastOnce()).save(any());
    }

    @Test
    @Order(2)
    @DisplayName("비밀번호 찾기 성공 - 전달받은 객체(아이디)가 회원이라면 비밀번호 변경에 성공한다.")
    void updatePassword() {
        PasswordChangeRequestDto requestDto = PasswordChangeRequestDto.builder()
                .userId("testid")
                .password("123456")
                .build();

        Member member = createMemberDto().toEntity();

        when(repository.findByUserId(requestDto.getUserId()))
                .thenReturn(Optional.of(member));

        memberService.updatePassword(requestDto);

        assertThat(member.getPassword()).isEqualTo(requestDto.getPassword());

        verify(repository, atLeastOnce()).findByUserId(requestDto.getUserId());
    }

    @Test
    @Order(3)
    @DisplayName("가입된 아이디 입력 시 비밀번호 찾기(재설정)에 필요한 리소스를 리턴한다.")
    void getMemberResource() {
        String userId = "testid";
        Member member = createMemberDto().toEntity();

        when(repository.findByUserId(userId)).thenReturn(Optional.of(member));

        MemberFindResponseDto memberResource = memberService.getMemberResource(userId);

        assertThat(memberResource.getEmail()).isEqualTo(member.getEmail());
        assertThat(memberResource.getPhone()).isEqualTo(member.getPhone());
    }

    @Test
    @Order(4)
    @DisplayName("존재하지 않는 아이디 입력 시 비밀번호 찾기(재설정)에 필요한 리소스 리턴에 실패한다.")
    void getMemberResource_failure() {
        String userId = "nonmember";

        when(repository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(MemberNotFoundException.class,
                () -> memberService.getMemberResource("nonmember"));

        verify(repository, atLeastOnce()).findByUserId(userId);
    }

}
