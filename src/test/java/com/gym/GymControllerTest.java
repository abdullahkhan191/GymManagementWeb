package com.gym;

import com.gym.model.Member;
import com.gym.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GymControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testMemberSave() {
        Member member = new Member();
        member.setName("Test User");
        member.setEmail("test@gym.com");
        member.setPhone("1234567890");
        Member saved = memberRepository.save(member);
        assertNotNull(saved.getId());
        memberRepository.delete(saved);
    }

    @Test
    public void testMemberNotNull() {
        Member member = new Member();
        member.setName("Abdullah");
        member.setEmail("abdullah@gym.com");
        member.setPhone("0300000000");
        assertNotNull(member);
        assertEquals("Abdullah", member.getName());
    }
}