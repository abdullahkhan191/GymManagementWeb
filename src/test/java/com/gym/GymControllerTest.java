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
        member.setId(9999);
        member.setFull_name("Test User");
        member.setPhone("1234567890");
        member.setGender("Male");
        member.setAge(25);
        member.setStatus("Active");
        Member saved = memberRepository.save(member);
        assertNotNull(saved.getId());
        memberRepository.delete(saved);
    }

    @Test
    public void testMemberNotNull() {
        Member member = new Member();
        member.setId(8888);
        member.setFull_name("Abdullah");
        member.setPhone("0300000000");
        assertNotNull(member);
        assertEquals("Abdullah", member.getFull_name());
    }
}