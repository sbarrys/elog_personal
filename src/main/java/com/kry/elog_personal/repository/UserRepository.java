package com.kry.elog_personal.repository;

import com.kry.elog_personal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
