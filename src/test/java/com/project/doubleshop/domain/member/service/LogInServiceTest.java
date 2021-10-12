package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.domain.exception.MemberNotFoundException;
import com.project.doubleshop.domain.exception.NotAuthorizedException;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.entity.Status;
import com.project.doubleshop.domain.member.repository.MemberInfoRepository;
import com.project.doubleshop.domain.utils.SHA256EncryptionUtil;
import com.project.doubleshop.web.member.dto.LogInRequestDto;
import com.project.doubleshop.web.member.dto.MemberInfoDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LogInServiceTest {

    @Mock
    MemberInfoRepository repository;

    @Mock
    SHA256EncryptionUtil encryptionUtil;

    @InjectMocks
    LogInService logInService;

    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .userId("testid")
                .password("12345")
                .name("John Doe")
                .phone("01012345678")
                .email("testemail@gmail.com")
                .status(Status.BANNED)
                .build();
    }

    public LogInRequestDto createLogInDto() {
        return LogInRequestDto.builder()
                .userId("testid")
                .password("12345")
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("로그인 성공 - 아이디와 비밀번호 일치")
    void logIn() {
        LogInRequestDto requestDto = createLogInDto();

        when(repository.existsByUserIdAndPassword(requestDto.getUserId(),
                encryptionUtil.encrypt(requestDto.getPassword())))
                .thenReturn(true);

        logInService.checkUserIdAndPassword(requestDto);

        verify(repository, atLeastOnce())
                .existsByUserIdAndPassword(requestDto.getUserId(),
                        encryptionUtil.encrypt(requestDto.getPassword()));
    }

    @Test
    @Order(2)
    @DisplayName("로그인 실패 - 존재하지 않는 아이디를 요청하거나 비밀번호가 일치하지 않을 경우 MemberNotFoundException이 발생한다.")
    void logIn_failure() {
        LogInRequestDto requestDto = createLogInDto();

        when(repository.existsByUserIdAndPassword(requestDto.getUserId(),
                encryptionUtil.encrypt(requestDto.getPassword())))
                .thenReturn(false);

        assertThrows(MemberNotFoundException.class,
                () -> logInService.logIn(requestDto));

        verify(repository, atLeastOnce())
                .existsByUserIdAndPassword(requestDto.getUserId(),
                        encryptionUtil.encrypt(requestDto.getPassword()));
    }

    @Test
    @Order(3)
    @DisplayName("로그인 실패 - BANNED 처리된 사용자는 로그인에 실패하고 NotAuthorizedException이 발생한다.")
    void logIn_banned() {
        LogInRequestDto requestDto = createLogInDto();

        when(repository.existsByUserIdAndPassword(requestDto.getUserId(),
                encryptionUtil.encrypt(requestDto.getPassword())))
                .thenReturn(true);
        when(repository.findByUserId(requestDto.getUserId())).thenReturn(Optional.of(member));

        assertThrows(NotAuthorizedException.class,
                () -> logInService.logIn(requestDto));

        verify(repository, atLeastOnce())
                .existsByUserIdAndPassword(requestDto.getUserId(),
                        encryptionUtil.encrypt(requestDto.getPassword()));
        verify(repository, atLeastOnce()).findByUserId(any());
    }

    @Test
    @Order(4)
    @DisplayName("마이 페이지 - 로그인 이후 myInfo를 요청하면 회원정보가 리턴된다.")
    void getMemberInfo() {
        LogInRequestDto requestDto = createLogInDto();

        when(repository.findByUserId(requestDto.getUserId()))
                .thenReturn(Optional.ofNullable(member));

        MemberInfoDto memberInfoDto = logInService.getCurrentMember(requestDto.getUserId());

        assertThat(memberInfoDto).isNotNull();
        assertThat(memberInfoDto.getUserId()).isEqualTo(member.getUserId());
        assertThat(memberInfoDto.getName()).isEqualTo(member.getName());
        assertThat(memberInfoDto.getPhone()).isEqualTo(member.getPhone());
        assertThat(memberInfoDto.getEmail()).isEqualTo(member.getEmail());

        verify(repository, atLeastOnce()).findByUserId(any());
    }

}
