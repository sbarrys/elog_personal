package com.kry.elog_personal.repositories;

import com.kry.elog_personal.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
