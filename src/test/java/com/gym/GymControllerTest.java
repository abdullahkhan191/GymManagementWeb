package com.gym;

import com.gym.model.Member;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GymControllerTest {

    @Test
    public void testMemberNotNull() {
        Member member = new Member();
        member.setId(8888);
        member.setFull_name("Abdullah");
        member.setPhone("0300000000");
        assertNotNull(member);
        assertEquals("Abdullah", member.getFull_name());
    }

    @Test
    public void testMemberFields() {
        Member member = new Member();
        member.setId(9999);
        member.setFull_name("Test User");
        member.setPhone("1234567890");
        member.setGender("Male");
        member.setAge(25);
        member.setStatus("Active");
        assertNotNull(member);
        assertEquals("Test User", member.getFull_name());
        assertEquals("Male", member.getGender());
        assertEquals(25, member.getAge());
    }
}