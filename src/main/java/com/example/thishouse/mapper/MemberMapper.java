package com.example.thishouse.mapper;

import com.example.thishouse.domain.Member;
import com.example.thishouse.domain.Inquire;
import com.example.thishouse.domain.Report;
import com.example.thishouse.domain.community.Community;
import com.example.thishouse.domain.community.Community_reply;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//interface사용시 오류 (required_bean error)
@Repository
@Mapper
@RequiredArgsConstructor
public class MemberMapper {
    private final SqlSessionTemplate sqlSession;
    private static final String Namespace = "com.example.thishouse.mapper.MemberMapper";
    //회원가입 기능
    public void sign_up(Member member) {
        sqlSession.insert(Namespace+".sign_up",member);
    }
    //로그인 기능
    public int loginMember(Member member) {
        int result = sqlSession.selectOne(Namespace+".loginMember",member);
        return result;
    }
    //아이디 중복
    public int idCk(String user_id) {
        int result = sqlSession.selectOne(Namespace+".idCk",user_id);
        return result;
    }
    //회원 상세정보 조회
    public Member findInputMember(String user_id) {
        return sqlSession.selectOne(Namespace+".findInputMember", user_id);
    }
    //회원 정보 수정
    public void updateMember(Member member) {
        sqlSession.update(Namespace+".updateMember", member);
    }
    //회원 정보 삭제
    public void deleteMember(String user_id) {
        sqlSession.delete(Namespace+".deleteMember", user_id);
    }
    //회원 문의 내열 리스트 조회
    public List<Inquire> findInputMemberInquire(String user_id) {
        return sqlSession.selectList(Namespace+".findInputMemberInquire",user_id);
    }

    public void modify_user(String userId) {

    }
    //회원 게시글 내역 조회
    public List<Community> my_community(String user_id) {
        return sqlSession.selectList(Namespace+".my_community",user_id);
    }
    //회원 신고 내역 조회
    public List<Report> findInputMemberReport(String user_id) {
        return sqlSession.selectList(Namespace+".findInputMemberReport", user_id);
    }

    public void inquire_insert(Inquire inquire) {
        sqlSession.insert(Namespace+".inquire_insert",inquire);
    }

    public void community_reply(Community_reply reply) {
        sqlSession.insert(Namespace+".community_reply",reply);
    }

    public List<Community> my_board_list(String userId) {
        return sqlSession.selectList(Namespace+".my_board_list",userId);
    }

    public List<Inquire> my_inquire_one(String userId) {
        return sqlSession.selectList(Namespace+".my_inquire_one",userId);
    }
//
//    public int memberList_cnt() {
//        return sqlSession.selectOne(Namespace+".memberList_cnt");
//    }
//
//    public List<Member> memberAll(Member searchVO) {
//        return sqlSession.selectList(Namespace + ".memberAll", searchVO);
//    }
//
//    public int member_search_cnt(Member searchVO) {
//        return sqlSession.selectOne(Namespace+".member_search_cnt");
//
//    }
//
//    public List<Member> member_list_search(Member searchVO) {
//        return sqlSession.selectOne(Namespace+".member_list_search");
//    }
}
