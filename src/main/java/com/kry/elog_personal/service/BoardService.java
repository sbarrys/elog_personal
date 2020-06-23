package com.kry.elog_personal.service;

import com.kry.elog_personal.entity.Board;
import com.kry.elog_personal.repositories.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardService {

    private BoardRepository boardRepository;
    public void BoardService(BoardRepository boardRepository){
        this.boardRepository= boardRepository;
    }

    @Transactional
    public List<Board> findAllBoards() throws Exception {

        List<Board> boardList= boardRepository.findAll();
        if(boardList==null){

        }
        return boardList;
    }
    @Transactional
    public Long save(Board board){
        Board savedBoard = boardRepository.save(board);
        Long savedId =savedBoard.getId();
        return savedId;
    }
}
