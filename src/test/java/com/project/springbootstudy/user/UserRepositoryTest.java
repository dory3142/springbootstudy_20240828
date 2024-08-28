package com.project.springbootstudy.user;

// Service 테스트 : 딱 Service 클래스 하나만 테스트 했음 (다른 의존성, 클래스 필요 없었음) -> 단위테스트
// Repository 테스트 : 실제 DB연동 필요!! 실제 스프링 띄울거다 -> 통합테스트

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest // //통합테스트, 실제로 스프링이 뜹니다. 컨테이너 활동을 한다. DB연동
public class UserRepositoryTest {

    UserRepository userRepository; // 필드

    @Autowired
    public UserRepositoryTest( UserRepository userRepository ) {
        this.userRepository = userRepository;
    } //생성자 주입 방식

    @Test
    public void 회원가입_테스트() { //테스트는 -> 무조건 void, 이름 한글로 써도 무방

        User user = new User();
        user.setUserId("taewoo");
        user.setPassword("12345");
        user.setAge(20);
        user.setGender("Male");
        user.setPhone("01012341234");
        // homeAddr

        userRepository.save(user); // 해당 PK가 없으면 INSERT, 있으면 UPDATE
    }

    @Test
    public void 회원수정_테스트() { //마이페이지
        User user = new User();
        user.setUserId("taewoo");
        user.setPassword("ㅁㄵㅅ42342");
        user.setAge(30);
        user.setGender("Female");
        user.setPhone("3333333");
        user.setHomeAddr("한강이 보이는 곳..");
        userRepository.save(user); // userId PK체크 어 있네? 그럼 업데이트
    }

    @Test
    public void 특정_회원조회_테스트() {
        String userId = "taewoo";
        Optional<User> foundUser = userRepository.findById(userId); //pk로 쓰이는 값
        // Optional -> null 처리를 쉽게 해주는 놈
        // foundUser.orElse(null); //사용자 있으면 사용자 출력하고, 없으면 null 혹은 지정한 값 출력 (기본값)
        // foundUser.orElseThrow(); // 사용자 있으면 사용자 출력, 없으면 예외처리
        System.out.println(foundUser.orElse(null));
    }

    @Test
    public void 로그인_테스트() {
        // select * from tb_user where user_id = 'taewoo' and password = 'eiwf332';
        // 있으면 로그인 성공! 없으면 로그인 실패
        String userId = "taewoo";
        String password = "ㅁㄵㅅ42342";

        User user = userRepository.findByUserIdAndPassword(userId, password);
        // JPA의 기능 중 하나 : 메서드명을 이용해서 원하는 쿼리를 작성할 수 있음!
        System.out.println(user);
    }


}
