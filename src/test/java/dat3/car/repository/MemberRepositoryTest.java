package dat3.car.repository;

import dat3.car.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    boolean isInitialized = false;
    @BeforeEach
    void setUp() {
        if(!isInitialized){
            memberRepository.deleteAll();
            memberRepository.save(new Member("farblossom", "123456", "abc@123.dk", "Jens",
                    "Jensen", "Hvidovrevej 5", "Hvidovre", "2500"));
            memberRepository.save(new Member("eggbasket", "password123", "email@email.dk", "Hans", "Hansen",
                    "Folehaven 1", "Valby", "2500"));
            isInitialized = true;
        }
    }
    @Test
    public void deleteAll(){
        memberRepository.deleteAll();
        assertEquals(0, memberRepository.count());
    }
    @Test
    public void testAll(){
        Long count = memberRepository.count();
        assertEquals(2, count);
    }
}
