package com.example.thishouse.mapper;

import com.example.thishouse.domain.Member;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.example.thishouse.domain.Member;
import com.example.thishouse.domain.PageVO;
import com.example.thishouse.domain.Notice;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

//interface사용시 오류 (required_bean error)
@Repository
@Mapper
@RequiredArgsConstructor
public class NoticeMapper {
    private final SqlSessionTemplate sqlSession;
    private static final String Namespace = "com.example.thishouse.mapper.NoticeMapper";

    public List<Notice> noticelist() {
        return sqlSession.selectList(Namespace+ ".notice_list");
    }
    //공지사항 페이징 리스트
    public List<Notice> pg_list(Notice searchVO) { return sqlSession.selectList(Namespace + ".pg_list", searchVO);}

    public int pg_listCnt() { return sqlSession.selectOne(Namespace + ".pg_listCnt");}

    //공지사항 상세보기
    public Notice view_notice(String notice_num) {
        return sqlSession.selectOne(Namespace+".view_notice", notice_num);
    }

    public void update_notice_hitCount(String noticeNum) {
        sqlSession.update(Namespace + ".update_notice_hitCount", noticeNum);
    }

    public void insert_notice(Notice notice) {
        sqlSession.insert(Namespace+".insert_notice",notice);
    }

    public void update_notice(Notice notice) {
        sqlSession.update(Namespace+".update_notice",notice);
    }

    public void delete_notice(String noticeNum) {
        sqlSession.delete(Namespace+".delete_notice",noticeNum);
    }

    public List<Notice> pg_list_search(Notice searchVO) {
        return sqlSession.selectList(Namespace + ".pg_list_search", searchVO);
    }

    public int pg_list_searchcnt(Notice searchVO) {
        return sqlSession.selectOne(Namespace + ".pg_list_searchcnt", searchVO);
    }

}
