package com.member.service;

import com.member.domain.Member;
import com.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStor();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring1");

        // when
        Long savdId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(savdId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void joinVilidaionCase() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        // when
        memberService.join(member1);
        IllegalStateException msg = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(msg.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        // then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}