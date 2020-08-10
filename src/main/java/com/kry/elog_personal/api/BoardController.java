package com.kry.elog_personal.api;
import com.kry.elog_personal.entity.User;
import com.kry.elog_personal.repositories.UserRepository;
import com.kry.elog_personal.service.UserService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kry.elog_personal.entity.Board;
import com.kry.elog_personal.service.BoardService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping(path="/api")
public class BoardController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);
    private BoardService boardService;
    private UserService userService;

    public BoardController(BoardService boardService, UserService userService){
        this.userService= userService;
        this.boardService= boardService;
    }
    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> findById(@PathVariable Long id) {
        logger.debug("여기들어와?@22222");
        Optional<Board> optBoard= boardService.findById(id);
        if (optBoard.isPresent()) {
            return ResponseEntity.ok(optBoard.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
    @GetMapping("/boards")
    public ResponseEntity<?> findAll() throws Exception {
        logger.debug("여기들어와?@1111111");

         List<Board> listBoard = boardService.findAllBoards();

         if(listBoard.isEmpty()){
             throw new NotFound();
         }
         else {

            return ResponseEntity.status(HttpStatus.OK).body(listBoard);
         }
     }


    @PostMapping(value="/boards")
    public HttpEntity<Board> save(@RequestBody BoardDto boardDto)throws Exception {
        User user= userService.findByEmail(boardDto.getWriter()).get();
        logger.debug("1111",user);

        if(user==null){
            throw new NotFound();
        }else if( boardDto.getWriter()==null){
            throw new NotFound();
        }
        else{
            Board board=new Board();
            board.setUser(user);
            board.setContent(boardDto.getContent());
            board.setTitle(boardDto.getTitle());
            boardService.save(board);
            return ResponseEntity.status(200).body(board);

        }
    }
    //result 객체 이용해서 에러 발생시 상황에 맞는 에러 메세지 전달.
    @DeleteMapping(value="/boards/{id}")
    public void delete(@PathVariable Long id){
        boardService.deleteById(id);

    }
}


