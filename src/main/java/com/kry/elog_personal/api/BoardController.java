package com.kry.elog_personal.api;

import com.kry.elog_personal.entity.Board;
import com.kry.elog_personal.entity.User;
import com.kry.elog_personal.repositories.BoardRepository;
import com.kry.elog_personal.repositories.UserRepository;
import com.kry.elog_personal.service.BoardService;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sun.misc.MessageUtils;

import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/boards")
    public List<Board> findAll() throws Exception {

        List<Board> board = boardRepository.findAll();
        return board;
    }

    @GetMapping("/boards/{id}")
    public HttpEntity<Board> findById(@PathVariable Long id) {

        Optional<Board> optboard = boardRepository.findById(id);
        if (optboard.isPresent()) {
            return ResponseEntity.ok(optboard.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);

        }
    }

    @PostMapping(value="/boards",consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Board> save(@RequestBody Board board) {

        Optional<User> user= userRepository.findById(1L);//나중에 세션에서 가져올것.

        board.setUser(user.get());
        Board savedboard=boardRepository.save(board);
        Long savedId = savedboard.getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();
        return ResponseEntity.created(location).build();

    }

}

//        List<Board> listBoard = boardService.findAll();
//        if(listBoard==null){
//            return null;
//            throw new NullPointerException("not founduser");
//        }
//        else {
//            return listBoard;
//        }
//    }

