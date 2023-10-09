package com.example.thishouse.service;

import com.example.thishouse.domain.Inquire;
import com.example.thishouse.domain.Member;
import com.example.thishouse.domain.community.Community;
import com.example.thishouse.domain.community.Community_reply;
import com.example.thishouse.domain.house.House_list;
import com.example.thishouse.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
    private final AdminMapper adminMapper;
    //table 전체
    //공지사항 작성
    @Transactional
    public void insert_reply(Inquire inquire) {
        this.adminMapper.insert_reply(inquire);
    }
    //문의사항 답변

    public int memberList_cnt() {
        return adminMapper.memberList_cnt();
    }

    public List<Member> memberAll(Member searchVO) {
        return this.adminMapper.memberAll(searchVO);
    }

    public int member_search_cnt(String context) {
        return this.adminMapper.member_search_cnt(context);
    }

    public List<Member> member_list_search(Member searchVO) {
        return this.adminMapper.member_list_search(searchVO);
    }

    @Transactional
    public void member_delete(String userNum) {
        this.adminMapper.member_delete(userNum);
    }
    @Transactional
    public void board_modify_page(String communityNum) {
        this.adminMapper.board_modify_admin(communityNum);
    }
    @Transactional
    public void comment_update_admin(String replyNum) {
        this.adminMapper.comment_update_admin(replyNum);
    }

    public Community view_board(Community_reply community_num) {
        return adminMapper.view_board(community_num);
    }
    //view_reply
    public List<Community_reply> view_reply(Community_reply community_num) {
        return this.adminMapper.view_reply(community_num);
    }
    @Transactional
    public void delete_board_reply(String communityNum) {
        this.adminMapper.delete_board_reply(communityNum);
    }

    //댓글 리스트 페이징
    public List<Community_reply> reply_list(Community_reply searchVO) {
        return this.adminMapper.reply_list(searchVO);
    }
    //댓글 개수
    public int reply_listCnt() {
        return adminMapper.reply_listCnt();
    }

    //검색 댓글 개수
    public int reply_list_search_Cnt(Community_reply searchVO) {
        return this.adminMapper.reply_list_search_Cnt(searchVO);
    }

    //검색 댓글 리스트
    public List<Community_reply> reply_list_search(Community_reply searchVO) {
        return this.adminMapper.reply_list_search(searchVO);
    }

    public List<House_list> re_list(House_list searchVO) {
        return this.adminMapper.re_list(searchVO);
    }

    public int re_list_cnt() {
        return this.adminMapper.re_list_cnt();
    }

    @Transactional
    public void approval_ok_house_item(String houseNum) {
        this.adminMapper.approval_ok_house_item(houseNum);
    }

    @Transactional
    public void approval_ok_house_list(String houseNum) {
        this.adminMapper.approval_ok_house_list(houseNum);

    }
    @Transactional
    public void approval_no_house_item(String houseNum) {
        this.adminMapper.approval_no_house_item(houseNum);
    }
    @Transactional
    public void approval_no_house_list(String houseNum) {
        this.adminMapper.approval_no_house_list(houseNum);
    }

    public List<House_list> no_re_list(House_list searchVO) {
        return this.adminMapper.no_re_list(searchVO);
    }

    public int no_re_list_cnt() {
        return this.adminMapper.no_re_list_cnt();
    }

    @Transactional
    public void no_approval_ok_house_item(String houseNum) {
        this.adminMapper.no_approval_ok_house_item(houseNum);
    }
    @Transactional
    public void no_approval_ok_house_list(String houseNum) {
        this.adminMapper.no_approval_ok_house_list(houseNum);
    }
}
