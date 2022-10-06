package com.sparta.springcore.service;

import com.sparta.springcore.dto.BoardDto;
import com.sparta.springcore.dto.BoardPasswordDto;
import com.sparta.springcore.entity.Board;
import com.sparta.springcore.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long update(Long id, BoardDto boardDto){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        board.update(boardDto);
        return board.getId();
    }

    public Boolean deleteBoard(Long id, BoardPasswordDto boardPasswordDto){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        if(board.getPassword().equals(boardPasswordDto.getPassword())){
            //Objects.equals(board.getPassword(), boardPasswordDto.getPassword())
            boardRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

}
