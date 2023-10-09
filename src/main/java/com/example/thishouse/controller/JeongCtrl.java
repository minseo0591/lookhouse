package com.example.thishouse.controller;
import com.example.thishouse.domain.Notice;
import com.example.thishouse.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JeongCtrl {

    private final NoticeService noticeService;

    @RequestMapping("/notice_list1")
    public String test(Model model){
        List<Notice> noticeList = noticeService.noticeList();
        model.addAttribute("noticeList" , noticeList);
        return "notice/notice_list";
    }

    //페이징 처리
    @RequestMapping("/notice_list")
    public String test2(@ModelAttribute("searchVO") Notice searchVO, HttpServletRequest request, Model model) {
        PageCtrl pagination  = new PageCtrl();
        pagination.setCurrentPageNo(searchVO.getPageIndex());
        pagination.setRecordCountPerPage(searchVO.getPageUnit());
        pagination.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(pagination.getFirstRecordIndex());
        searchVO.setRecordCountPerPage(pagination.getRecordCountPerPage());
        System.out.println("펄스트인덱스 : " + searchVO.getFirstIndex());

        String search = request.getParameter("search");
        String context = request.getParameter("searchValue");
        System.out.println(search + " " + context);

        if(context == null){
            List<Notice> pg_list = noticeService.pg_list(searchVO);
            model.addAttribute("pg_list" , pg_list);
            int totCnt = noticeService.pg_listCnt();
            model.addAttribute("totCnt",totCnt);
            System.out.println("전체 게시글 수 : " + totCnt);

            pagination.setTotalRecordCount(totCnt);

            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
            searchVO.setPrev(pagination.getXprev());
            searchVO.setNext(pagination.getXnext());
            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
            model.addAttribute("pagination",pagination);
        }
        else if(context != null && context == ""){
            List<Notice> pg_list = noticeService.pg_list(searchVO);
            model.addAttribute("pg_list" , pg_list);
            int totCnt = noticeService.pg_listCnt();
            model.addAttribute("totCnt",totCnt);
            System.out.println("전체 게시글 수 : " + totCnt);

            pagination.setTotalRecordCount(totCnt);

            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
            searchVO.setPrev(pagination.getXprev());
            searchVO.setNext(pagination.getXnext());
            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
            model.addAttribute("pagination",pagination);
        }
        else{
            System.out.println("제대로 검색실행");
            searchVO.setSearch_name(search);
            searchVO.setSearch_content(context);
            List<Notice> pg_list = noticeService.pg_list_search(searchVO);
            int totCnt = noticeService.pg_list_searchcnt(searchVO);

            System.out.println("전체 게시글 수 : " + totCnt);

            pagination.setTotalRecordCount(totCnt);

            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
            searchVO.setPrev(pagination.getXprev());
            searchVO.setNext(pagination.getXnext());
            model.addAttribute("pg_list" , pg_list);
            model.addAttribute("totCnt",totCnt);
            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
            model.addAttribute("pagination",pagination);
        }


//        int totCnt = noticeService.pg_listCnt();
//        model.addAttribute("totCnt",totCnt);
//        System.out.println("전체 게시글 수 : " + totCnt);
//
//        pagination.setTotalRecordCount(totCnt);

//        searchVO.setEndDate(pagination.getLastPageNoOnPageList());
//        searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
//        searchVO.setPrev(pagination.getXprev());
//        searchVO.setNext(pagination.getXnext());

//        model.addAttribute("pg_list" , pg_list);
        //model.addAttribute("totCnt",totCnt);
//        model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
//        model.addAttribute("pagination",pagination);
        return "notice/notice_list";
    }

    @RequestMapping("notice_detail")
    public String NoticeDetail(Model model, String notice_num) {
        System.out.println(notice_num);
        noticeService.update_notice_hitCount(notice_num);
        model.addAttribute("Notice", noticeService.view_notice(notice_num));
        return "notice/notice_detail";
    }
    //공지사항 추가view
    @GetMapping("notice_add")
    public String NoardAddView() {
        return "notice/notice_add";
    }

    //공지사항 등록 기능
    @PostMapping("notice_add")
    public String BoardAdd(Notice notice) {
        noticeService.insert_notice(notice);
        System.out.println(notice);
        return "redirect:/notice_list";
    }

    //공지사항 수정 View
    @GetMapping("notice_edit")
    public String NoticeUpdateView(Model model, String notice_num) {
        model.addAttribute("Notice", noticeService.view_notice(notice_num));
        return "notice/notice_edit";
    }

    //공지사항 수정 기능
    @PostMapping("notice_edit")
    public String NoticeUpdate(@ModelAttribute Notice notice) {
        noticeService.update_notice(notice);
        return "redirect:/notice_list";
    }
    //공지사항 삭제 기능
    @RequestMapping("notice_delete")
    public String NoticeDelete(String notice_num) {
        noticeService.delete_notice(notice_num);
        return "redirect:/notice_list";
    }



    //testCtrl
    @RequestMapping("/jtest")
    public String test1(){
        return "z_JeongTest/Jtest";
    }

    @RequestMapping("/jtestmap")
    public String test2(){
        return "z_JeongTest/map1";
    }

    @RequestMapping("/jtestinput")
    public String test3(){
        return "z_JeongTest/putTest";
    }

    @PostMapping("/jjtest")
    public void test4(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("test 내용 : " + request.getParameter("test"));

    }
}
