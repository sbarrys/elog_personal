package com.kry.elog_personal.api;

import com.kry.elog_personal.entity.Board;
import com.kry.elog_personal.entity.User;
import com.kry.elog_personal.repositories.BoardRepository;
import com.kry.elog_personal.repositories.UserRepository;
import com.kry.elog_personal.service.BoardService;
import com.sun.net.httpserver.Authenticator;
import javassist.NotFoundException;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sun.misc.MessageUtils;

import javax.naming.spi.DirStateFactory;
import javax.swing.text.html.parser.Entity;
import javax.xml.transform.Result;
import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class BoardController {
    private BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService= boardService;
    }
     @GetMapping("/boards")
    public List<Board> findAll() throws Exception {
         List<Board> listBoard = boardService.findAllBoards();
         if(listBoard.isEmpty()){
             throw new NotFound();
         }
         else {
             return listBoard;
         }
     }
    @GetMapping("/boards/{id}")
    public HttpEntity<Board> findById(@PathVariable Long id) {
         Optional<Board> optBoard= boardService.findById(id);
        if (optBoard.isPresent()) {
            return ResponseEntity.ok(optBoard.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping(value="/boards",consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Board> save(@RequestBody Board board) {
        Long savedId=boardService.save(board);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();
        return ResponseEntity.created(location).build();
    }
    //result 객체 이용해서 에러 발생시 상황에 맞는 에러 메세지 전달.
    @DeleteMapping(value="/boards/{id}")
    public void delete(@PathVariable Long id){
//        if(로그인안되어있음) {return 실패}
//        if(로그인 유저와 게시글 작성자 동일){ return 실패 }
        boardService.deleteById(id);

    }
}


