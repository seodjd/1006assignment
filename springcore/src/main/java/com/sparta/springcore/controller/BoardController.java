package com.sparta.springcore.controller;


import com.sparta.springcore.dto.BoardDto;
import com.sparta.springcore.dto.BoardPasswordDto;
import com.sparta.springcore.entity.Board;
import com.sparta.springcore.repository.BoardRepository;
import com.sparta.springcore.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;


    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardDto boardDto) {
        Board board = new Board(boardDto);
        return boardRepository.save(board);
    }

    @GetMapping("/api/boards")
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/boards/{id}")
    public Board getBoardById(@PathVariable Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
    }

    @DeleteMapping("/api/boards/{id}")
    public boolean deleteBoard(@PathVariable Long id, @RequestBody BoardPasswordDto boardPasswordDto) {
        if(boardService.deleteBoard(id, boardPasswordDto)){
            return true;
        }else{
            return false;
        }
    }

    @DeleteMapping("/api/boards")
    public void deleteAll() {
        boardRepository.deleteAll();
    }

    @PutMapping("/api/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        return boardService.update(id, boardDto);
    }


}
