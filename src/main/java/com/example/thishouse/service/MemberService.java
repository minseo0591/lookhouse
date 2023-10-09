package com.example.thishouse.service;

import com.example.thishouse.domain.Member;
import com.example.thishouse.domain.Inquire;
import com.example.thishouse.domain.Report;
import com.example.thishouse.domain.community.Community;
import com.example.thishouse.domain.community.Community_reply;
import com.example.thishouse.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    //테이블 Members(회원)

    private final MemberMapper memberMapper;

    //@Transactional => return없이 mapper내용 실행(void함수)
    //로그인 회원가입 기능
    @Transactional
    public void sign_up(Member member) {
        this.memberMapper.sign_up(member);
    }
    //회원 로그인 기능
    public int loginMember(Member member) {
        int result = this.memberMapper.loginMember(member);
        if(result == 1) {
            return result;
        }
        return 0;
    }
    //회원 아이디 중복체크
    public int idCk(String user_id) {
        int result = memberMapper.idCk(user_id);
        return result;
    }

    //회원 상세정보 조회
    public Member findInputMember(String user_id) {
        return memberMapper.findInputMember(user_id);
    }

    //회원 정보 수정
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    //회원 정보 삭제
    public void deleteMember(String user_id) {
        memberMapper.deleteMember(user_id);
    }


    //회원 문의내역
    public List<Inquire> findInputMemberInquire(String user_id) {
        return memberMapper.findInputMemberInquire(user_id);
    }

    //내가 쓴 게시글
    public List<Community> my_community(String user_id) {
        return memberMapper.my_community(user_id);
    }
    //회원 신고내역
    public List<Report> findInputMemberReport(String user_id) {
        return memberMapper.findInputMemberReport(user_id);
    }
    //문의하기
    @Transactional
    public void inquire_insert (Inquire inquire) {
        memberMapper.inquire_insert(inquire);
    }

    //댓글 작성
    @Transactional
    public void community_reply (Community_reply reply) {
        memberMapper.community_reply(reply);
    }

    public List<Community> my_board_list(String userId) {
        return memberMapper.my_board_list(userId);
    }

    public List<Inquire> my_inquire_one(String userId) {
        return memberMapper.my_inquire_one(userId);
    }

//    public int memberList_cnt() {
//        return memberMapper.memberList_cnt();
//    }
//
//    public List<Member> memberAll(Member searchVO) {
//        return this.memberMapper.memberAll(searchVO);
//    }
//
//    public int member_search_cnt(Member searchVO) {
//        return this.memberMapper.member_search_cnt(searchVO);
//    }
//
//    public List<Member> member_list_search(Member searchVO) {
//        return this.memberMapper.member_list_search(searchVO);
//    }


    //회원 관심매물 내역
    //회원 거래상황
    //회원 등록한 매물


}
