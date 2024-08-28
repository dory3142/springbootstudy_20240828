package com.project.springbootstudy.repository;

import com.project.springbootstudy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 이 녀석은 JPA 기술을 사용하는 놈입니다~
// extends JpaRepository<User, String>
// 상속을 받는데, User는 사용할 엔터티, String은 이 User엔터티의 PK
public interface UserRepository extends JpaRepository<User, String> {

    //    User findByUserIdAndPassword(@Param(value="userId") String userId , @Param(value="password") String password);

    @Query(value = "select * from tb_user where user_id = :userId and password = :password" , nativeQuery = true)
    User findByUserIdAndPassword(@Param(value="userId") String userId , @Param(value="password") String password);

    //    아까는 nativeQuery 방식을 써서 특정 DB에 맞게 쿼리를 작성했다면
    //    이번에  는 어느 DB에서도 사용할 수 있는 쿼리 방식 (nativeQuery = false)

    //    @Query(value = "SELECT m FROM user ")
    //    User find()
}
