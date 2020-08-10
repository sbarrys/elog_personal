package com.kry.elog_personal.service;

import com.kry.elog_personal.api.AuthController;
import com.kry.elog_personal.entity.Board;
import com.kry.elog_personal.entity.User;
import com.kry.elog_personal.repositories.BoardRepository;
import com.kry.elog_personal.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private BoardRepository boardRepository;
    private UserRepository userRepository;
    public BoardService(BoardRepository boardRepository, UserRepository userRepository){
        this.boardRepository= boardRepository;
        this.userRepository= userRepository;

    }
    public List<Board> findAllBoards() throws Exception {
        List<Board> boardList= boardRepository.findAll();
        if(boardList==null){

        }
        return boardList;
    }
    public Long save(Board board){

        Board savedBoard=boardRepository.save(board);
        Long savedId =savedBoard.getId();
        return savedId;
    }
    public Optional<Board> findById(Long id){
        Optional<Board> optboard = boardRepository.findById(id);
        return optboard;
    }
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
