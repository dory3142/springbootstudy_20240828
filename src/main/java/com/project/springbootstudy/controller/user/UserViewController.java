package com.project.springbootstudy.controller.user;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewController {

    @Autowired
    UserService userService;

    @GetMapping("/user/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/user/join")
    public String join() {
        return "user/join";
    }

    /********** 로그인 로직 **********/

    // 사용자가 userId, password 데이터로 함께 요청
    // 적절한 결과 응답
    @PostMapping("login-process")
    public String loginCheck(@RequestParam(name="userId") String userId
                           , @RequestParam(name="password") String password
                           , Model model) {
        //이 실행의 결과물만 신경쓰면 됨
        // 컨트롤러 만들기 (true -> 로직 구현)
        // 서비스 만들기 (서비스: 일단 출력값만 알려줄게) -> true, false
        boolean isValidUser = userService.login(userId, password);

        if (isValidUser) {
            return "home";
        }

        // 동적 화면 구현하고 싶을 때 사용
        model.addAttribute("error", "없는 회원입니다. 회원가입을 해주세요~");
        return "user/login";
    }

    //컨트롤러 : 사용자 요청
    //응답 : 화면이나 데이터 반환

    //추가적인 요구사항
    // 예) 회원가입 -> 비밀번호 8자리 이상~ 10자리 이하
    // 중복된 아이디는 가입할 수 있다.

    //사용자 회원가입 처리에 대한 화면 반환
    @PostMapping("join-process")
    public String joinProcess(@ModelAttribute User user, Model model) {

        //사용자 요청을 받았다!!
        // 근데 필요한 로직이 발생했다 (사용자 요구사항)
        // 이걸 컨트롤러는 하지 않고 service에 맡긴다.!

        int result = userService.join(user);

        if(result == 1) {
            System.out.println("회원가입성공");
            return "redirect:/user/login";
        } else {
            model.addAttribute("error", "회원가입 실패!! 다시 해보세요~");
            return "/user/join";
        }


    }

}
