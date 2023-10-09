package com.example.thishouse.service;

import com.example.thishouse.domain.Member;
import com.example.thishouse.domain.Notice;
import com.example.thishouse.domain.community.Community;
import com.example.thishouse.mapper.MemberMapper;
import com.example.thishouse.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public List<Notice> noticeList() {
        return this.noticeMapper.noticelist();
    }
    //공지사항 페이징 리스트
    public List<Notice> pg_list(Notice searchVO) {
        return this.noticeMapper.pg_list(searchVO);
    }
    //검색
    public List<Notice> pg_list_search(Notice searchVO) {
        return this.noticeMapper.pg_list_search(searchVO);
    }
    public int pg_listCnt() {
        return this.noticeMapper.pg_listCnt();
    }

    public int pg_list_searchcnt(Notice searchVO) {
        return this.noticeMapper.pg_list_searchcnt(searchVO);
    }
    //공지사항 상세보기
    public Notice view_notice(String notice_num) {
        return noticeMapper.view_notice(notice_num);
    }

    //공지사항 조회수
    @Transactional
    public void update_notice_hitCount(String notice_num) {
        this.noticeMapper.update_notice_hitCount(notice_num);
    }
    //공지사항 등록
    @Transactional
    public void insert_notice(Notice notice) {
        this.noticeMapper.insert_notice(notice);
    }

    @Transactional
    public void update_notice(Notice notice) {
        this.noticeMapper.update_notice(notice);
    }
    @Transactional
    public void delete_notice(String noticeNum) {
        this.noticeMapper.delete_notice(noticeNum);
    }


    //공지사항 수정
    //공지사항 삭제

}
