package com.project.springbootstudy.service.user;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //메서드
    public int join(User user) {

        //사용자ID는 최대 10자리다.
        if(user.getUserId().length() > 10) {
            System.out.println("사용자ID는 최대 10자리여야 합니다.");
            return 0; // 0이면 비정상, 1이면 정상
        }

        //비밀번호는 최소 8자리, 최대 10자리이다.
        if(user.getPassword().length() < 8 || user.getPassword().length() > 10) {
            System.out.println("사용자 비밀번호는 8~10 자리여야 합니다.");
            return 0;
        }

        userRepository.save(user);

        return 1;
    }

    public boolean login(String userId, String password) throws RuntimeException {

        User userInfo = userRepository.findByUserIdAndPassword(userId, password);

        if(userInfo != null) {
            return true; //사용자가 있습니다.
        } else {
            return false; //사용자가 없습니다.
        }

    }
}
