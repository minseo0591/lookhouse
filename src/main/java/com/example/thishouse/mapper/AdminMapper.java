package com.example.thishouse.mapper;

import com.example.thishouse.domain.Inquire;
import com.example.thishouse.domain.Member;
import com.example.thishouse.domain.community.Community;
import com.example.thishouse.domain.community.Community_reply;
import com.example.thishouse.domain.house.House_list;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//interface사용시 오류 (required_bean error)
@Repository
@Mapper
@RequiredArgsConstructor
public class AdminMapper {
    private final SqlSessionTemplate sqlSession;
    private static final String Namespace = "com.example.thishouse.mapper.AdminMapper";

    //문의사항 답변
    public void insert_reply(Inquire inquire) {
        sqlSession.update(Namespace+".insert_reply",inquire);
    }


    public int memberList_cnt() {
        return sqlSession.selectOne(Namespace+".memberList_cnt");
    }

    public List<Member> memberAll(Member searchVO) {
        return sqlSession.selectList(Namespace + ".memberAll", searchVO);
    }

    public int member_search_cnt(String context) {
        return sqlSession.selectOne(Namespace+".member_search_cnt",context);

    }

    public List<Member> member_list_search(Member searchVO) {
        return sqlSession.selectList(Namespace+".member_list_search",searchVO);
    }

    public void member_delete(String user_num) {
        sqlSession.delete(Namespace+".member_delete",user_num);
    }

    public void board_modify_admin(String communityNum) {
        sqlSession.update(Namespace+".board_modify_admin",communityNum);
    }

    public Community view_board(Community_reply community_reply) {
        return sqlSession.selectOne(Namespace+".view_board", community_reply);
    }

    public List<Community_reply> view_reply(Community_reply community_reply) {
        return sqlSession.selectList(Namespace+".view_reply",community_reply);

    }

    public void comment_update_admin(String replyNum) {
        sqlSession.update(Namespace+".comment_update_admin",replyNum);
    }

    public void delete_board_reply(String communityNum) {
        sqlSession.delete(Namespace+".delete_board_reply",communityNum);
    }

    //전체 댓글 리스트 페이징
    public List<Community_reply> reply_list(Community_reply searchVO) {
        return sqlSession.selectList(Namespace+".reply_list",searchVO);
    }

    //전체 댓글 개수
    public int reply_listCnt() {
        return sqlSession.selectOne(Namespace+".reply_listCnt");
    }

    //검색 댓글 개수
    public int reply_list_search_Cnt(Community_reply searchVO) {
        return sqlSession.selectOne(Namespace+".reply_list_search_Cnt",searchVO);
    }

    public List<Community_reply> reply_list_search(Community_reply searchVO) {
        return sqlSession.selectList(Namespace+".reply_list_search",searchVO);
    }

    public List<House_list> re_list(House_list searchVO) {
        return sqlSession.selectList(Namespace + ".re_list", searchVO);
    }

    public int re_list_cnt() {
        return sqlSession.selectOne(Namespace+".re_list_cnt");
    }

    public void approval_ok_house_item(String houseNum) {
        sqlSession.update(Namespace+".approval_ok_house_item",houseNum);    }

    public void approval_ok_house_list(String houseNum) {
        sqlSession.update(Namespace+".approval_ok_house_list",houseNum);
    }

    public void approval_no_house_item(String houseNum) {
        sqlSession.update(Namespace+".approval_no_house_item",houseNum);
    }

    public void approval_no_house_list(String houseNum) {
        sqlSession.update(Namespace+".approval_no_house_list",houseNum);
    }

    public List<House_list> no_re_list(House_list searchVO) {
        return sqlSession.selectList(Namespace + ".no_re_list", searchVO);    }

    public int no_re_list_cnt() {
        return sqlSession.selectOne(Namespace+".no_re_list_cnt");
    }

    public void no_approval_ok_house_item(String houseNum) {
        sqlSession.update(Namespace+".approval_ok_house_item",houseNum);

    }

    public void no_approval_ok_house_list(String houseNum) {
        sqlSession.update(Namespace+".approval_ok_house_list",houseNum);

    }
}
