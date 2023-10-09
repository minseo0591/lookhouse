package com.example.thishouse.mapper;

import com.example.thishouse.domain.Member;
import com.example.thishouse.domain.Notice;
import com.example.thishouse.domain.community.Community;
import com.example.thishouse.domain.community.Community_reply;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.example.thishouse.domain.community.Community;

import java.util.List;

//interface사용시 오류 (required_bean error)
@Repository
@Mapper
@RequiredArgsConstructor
public class BoardMapper {
    private final SqlSessionTemplate sqlSession;
    private static final String Namespace = "com.example.thishouse.mapper.BoardMapper";

    //게시판 목록 조회
    public List<Community> select_board_list() {
        return sqlSession.selectList(Namespace + ".select_board_list");
    }

    //게시판 목록 페이징 추가
    public List<Community> bd_list(Community searchVO) { return sqlSession.selectList(Namespace + ".bd_list", searchVO);}

    public int bd_listCnt() { return sqlSession.selectOne(Namespace + ".bd_listCnt");}
    //게시판 생성
    public void insert_board(Community community) {
        sqlSession.insert(Namespace+".insert_board",community);
    }

    //게시판 댓글 생성
    public void insert_reply(Community_reply cm) {
        sqlSession.insert(Namespace+".insert_reply",cm);
    }

    //게시판 상세정보 조회
    public Community view_board(String community_num) {
        return sqlSession.selectOne(Namespace+".view_board", community_num);
    }
    //게시판 상세정보 댓글 조회
    public List<Community_reply> view_reply(String community_num) {
        return sqlSession.selectList(Namespace+".view_reply",community_num);
    }
    //게시판 삭제
    public void delete_board(String community_num) {
        sqlSession.delete(Namespace+".delete_board",community_num);
    }
    //게시판 수정
    public void update_board(Community community) {
        sqlSession.update(Namespace+".update_board",community);
    }
    //게시판 조회수
    public void update_board_hitCount(String community_num) {
        sqlSession.update(Namespace + ".update_board_hitCount", community_num);
    }
    //게시판이랑 댓글 같이 삭제
    public void delete_reply(String community_num) {
        sqlSession.insert(Namespace+".delete_reply",community_num);
    }

    public List<Community> bd_list_search(Community searchVO) {
        return sqlSession.selectList(Namespace + ".bd_list_search", searchVO);
    }

    public int bd_list_search_Cnt(Community searchVO) {
        return sqlSession.selectOne(Namespace + ".bd_list_search_Cnt", searchVO);
    }

    //댓글
    public List<Community_reply> reply_list(Community_reply reply){
        return sqlSession.selectList(Namespace + ".reply_list" + reply);
    }

}
