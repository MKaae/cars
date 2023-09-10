package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1",
                "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email2", "fn2",
                "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(true);
        assertEquals(2, memberResponses.size());
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNotNull(time, "Expect date to be set when true is passed");
    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(false);
        assertEquals(2, memberResponses.size());
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNull(time, "Expect date not to be set when false is passed");
    }

    @Test
    void testFindByIdFound() {
        MemberResponse re = memberService.findById("user1");
        assertEquals("user1", re.getUsername());
        assertEquals("email1", re.getEmail());
        assertEquals("fn1", re.getFirstName());
        assertEquals("ln1", re.getLastName());
        assertEquals("street1", re.getStreet());
        assertEquals("city1", re.getCity());
        assertEquals("zip1", re.getZip());
    }

    @Test
    void testFindByIdNotFound() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.findById("I dont exist"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void testAddMember_UserDoesNotExist() {
        MemberRequest request = MemberRequest.builder().
                username("user3").
                email("email3").
                password("123").
                firstName("fn3").
                lastName("ln3").
                street("street3").
                city("city3").
                zip("zip3").
                build();
        MemberResponse res = memberService.addMember(request);
        assertEquals("user3", res.getUsername());
        assertEquals("fn3", res.getFirstName());
        assertEquals("ln3", res.getLastName());
        assertTrue(memberRepository.existsById("user3"));
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        MemberRequest request = new MemberRequest();
        request.setUsername("user1");
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.addMember(request));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        MemberRequest request = new MemberRequest(m1);
        request.setFirstName("New first name");
        request.setLastName("New last name");
        memberService.editMember(request, "user1");
        MemberResponse response = memberService.findById("user1");

        assertEquals("user1", response.getUsername());
        assertEquals("New first name", response.getFirstName());
        assertEquals("New last name", response.getLastName());
        assertEquals("email1", response.getEmail());
        assertEquals("street1", response.getStreet());
        assertEquals("city1", response.getCity());
        assertEquals("zip1", response.getZip());
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        MemberRequest request = new MemberRequest();
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.editMember(request, "I dont exist"));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        MemberRequest request = new MemberRequest(m1);
        request.setUsername("New Username");
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.editMember(request, "user1"));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        assertEquals("Cannot change username", ex.getReason());
    }

    @Test
    void testSetRankingForUser() {
        memberService.setRankingForUser("user1", 3);
        MemberResponse response = memberService.findById("user1");
        assertEquals(3, response.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.setRankingForUser("I dont exist", 3));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
    @Test
    void testDeleteMemberByUsername() {
        memberService.deleteMemberByUsername("user1");
        assertFalse(memberRepository.existsById("user1"));
    }

    @Test
    void testDeleteMember_ThatDontExist() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.deleteMemberByUsername("I dont exist"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
}
