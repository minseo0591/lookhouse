package com.example.thishouse.service;

import com.example.thishouse.domain.Member;
import com.example.thishouse.domain.Notice;
import com.example.thishouse.domain.community.Community;
import com.example.thishouse.domain.community.Community_reply;
import com.example.thishouse.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    //community(커뮤니티), community_reply(커뮤니티 댓글)

    private final BoardMapper boardMapper;

    //게시판 리스트 조회 select_board_list
    public List<Community> select_board_list() {
        return this.boardMapper.select_board_list();
    }

    //게시판 리스트 + 페이징 조회
    public List<Community> bd_list(Community searchVO) {
        return this.boardMapper.bd_list(searchVO);
    }

    public int bd_listCnt() {
        return this.boardMapper.bd_listCnt();
    }
    //게시글 작성 insert_board
    @Transactional
    public void insert_board(Community community) {
        this.boardMapper.insert_board(community);
    }
    //댓글작성 insert_reply
    @Transactional
    public void insert_reply(Community_reply cm) {
        this.boardMapper.insert_reply(cm);
    }

    //상세내용보기 view_board
    public Community view_board(String community_num) {
        return boardMapper.view_board(community_num);
    }
    //view_reply
    public List<Community_reply> view_reply(String community_num) {
        return this.boardMapper.view_reply(community_num);
    }
    //글 삭제 delete_board
    @Transactional
    public void delete_board(String community_num){
        this.boardMapper.delete_board(community_num);
    }
    //글 수정 update_board
    @Transactional
    public void update_board(Community community){
        this.boardMapper.update_board(community);
    }
    //댓글 삭제 delete_reply
    @Transactional
    public void delete_reply(String community_num){
        this.boardMapper.delete_reply(community_num); //쿼리 설정X
    }

    //게시판 조회수 update_board_hitCount
    @Transactional
    public void update_board_hitCount(String community_num) {
        this.boardMapper.update_board_hitCount(community_num);
    }

    public List<Community> bd_list_search(Community searchVO) {
        return this.boardMapper.bd_list_search(searchVO);
    }

    public int bd_list_search_Cnt(Community searchVO) {
        return this.boardMapper.bd_list_search_Cnt(searchVO);
    }
    // List 검색 search
    // List 페이징 리스트 page_list

}
