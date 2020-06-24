package com.kry.elog_personal.service;

import com.kry.elog_personal.entity.Board;
import com.kry.elog_personal.entity.User;
import com.kry.elog_personal.repositories.BoardRepository;
import com.kry.elog_personal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardService {
    private BoardRepository boardRepository;
    private UserRepository userRepository;
    public BoardService(BoardRepository boardRepository, UserRepository userRepository){
        this.boardRepository= boardRepository;
        this.userRepository= userRepository;

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
        Optional<User> user= userRepository.findById(1L);//나중에 세션에서 가져올것.
        board.setUser(user.get());
        Board savedBoard=boardRepository.save(board);
        Long savedId =savedBoard.getId();
        return savedId;
    }
    @Transactional
    public Optional<Board> findById(Long id){
        Optional<Board> optboard = boardRepository.findById(id);
        return optboard;
    }
    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
