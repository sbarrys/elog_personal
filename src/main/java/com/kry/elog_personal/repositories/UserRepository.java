package com.kry.elog_personal.repositories;

import com.kry.elog_personal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);   //email 을 통해 이미 가입된 사용자인지 확인 하기 위함.
                                                //가입이 안되어있다면  create해주면 된다.
    Optional<User> findBySub(Long sub);
}
