package com.kry.elog_personal.api;

import com.kry.elog_personal.entity.Board;
import com.kry.elog_personal.service.BoardService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="/api")
public class BoardController {
    private BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService= boardService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/boards")
    public ResponseEntity<?> findAll() throws Exception {

         List<Board> listBoard = boardService.findAllBoards();

         if(listBoard.isEmpty()){
             throw new NotFound();
         }
         else {
            return ResponseEntity.status(HttpStatus.OK).body(listBoard);
         }
     }
    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> findById(@PathVariable Long id) {
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
        boardService.deleteById(id);

    }
}


