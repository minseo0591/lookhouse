package com.example.thishouse.controller;

import com.example.thishouse.domain.Member;
import com.example.thishouse.domain.Notice;
import com.example.thishouse.domain.Report;
import com.example.thishouse.domain.community.Community;
import com.example.thishouse.domain.community.Community_reply;
import com.example.thishouse.domain.house.*;
import com.example.thishouse.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestKongController {
    private final BoardService boardService;
    private final AdminService adminService;
    private final NoticeService noticeService;
    private final RealEstateService realEstateService;
    private final ReportService reportService;



    //게시판 목록
//    @RequestMapping("board_list")
//    public String BoardList(Model model) {
//        List<Community> list = boardService.select_board_list();
//        model.addAttribute("list", list);
//        return "board/board_list";
//    }

    @RequestMapping("board_list")
        public String BoardList(@ModelAttribute("searchVO") Community searchVO, HttpServletRequest request, Model model) {
        PageCtrl pagination  = new PageCtrl();
        pagination.setCurrentPageNo(searchVO.getPageIndex());
        pagination.setRecordCountPerPage(searchVO.getPageUnit());
        pagination.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(pagination.getFirstRecordIndex());
        searchVO.setRecordCountPerPage(pagination.getRecordCountPerPage());
        System.out.println("펄스트인덱스 : " + searchVO.getFirstIndex());

        String search = request.getParameter("searchName");
        String context = request.getParameter("searchValue");
        System.out.println(search + " " + context);

        if(context == null){
            List<Community> bd_list = boardService.bd_list(searchVO);
            model.addAttribute("bd_list" , bd_list);
            int totCnt = boardService.bd_listCnt();
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
            List<Community> bd_list = boardService.bd_list(searchVO);
            model.addAttribute("bd_list" , bd_list);
            int totCnt = boardService.bd_listCnt();
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
            List<Community> pg_list = boardService.bd_list_search(searchVO);
            int totCnt = boardService.bd_list_search_Cnt(searchVO);

            System.out.println("전체 게시글 수 : " + totCnt);

            pagination.setTotalRecordCount(totCnt);

            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
            searchVO.setPrev(pagination.getXprev());
            searchVO.setNext(pagination.getXnext());
            model.addAttribute("bd_list" , pg_list);
            model.addAttribute("totCnt",totCnt);
            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
            model.addAttribute("pagination",pagination);
        }
        return "board/board_list";
    }


    //게시판 상세 조회
    @RequestMapping("board_detail")
    public String BoardDetail(Model model, String community_num) {
        System.out.println("이것은 " + community_num);
        boardService.update_board_hitCount(community_num);
        model.addAttribute("Board", boardService.view_board(community_num));

        //댓글
        model.addAttribute("reply", boardService.view_reply(community_num));
        return "board/board_detail";
    }

    @RequestMapping("reply_add")
    public String ReplyAdd(HttpServletRequest request){
        String x = request.getParameter("reply_text");
        String id = request.getParameter("user_id");
        String num = request.getParameter("community_num");
        Community_reply cr = new Community_reply();
        cr.setCommunity_num(Integer.parseInt(num));
        cr.setReply_contents(x);
        cr.setUser_id(id);

        if(x.equals("")){
            System.out.println("빈칸이라서 댓글 추가 x");
        }
        else{
            boardService.insert_reply(cr);
        }
        System.out.println("텍스트 : "+ x);
        System.out.println("작성자 : "+ id);
        System.out.println("커뮤니티번호 : "+ num);

        return "redirect:/board_detail?community_num=" + num;
    }

    //게시판 등록 View
    @GetMapping("board_add")
    public String BoardAddView() {
        return "board/board_add";
    }

    //게시판 등록 기능
    @PostMapping("board_add")
    public String BoardAdd(Community community) {
        boardService.insert_board(community);
        System.out.println(community);
        return "redirect:/board_list";
    }
    //게시판 수정 View
    @GetMapping("board_edit")
    public String BoardUpdateView(Model model, String community_num) {
        model.addAttribute("Board", boardService.view_board(community_num));
        return "board/board_edit";
    }

    //게시판 수정 기능
    @PostMapping("board_edit")
    public String BoardUpdate(@ModelAttribute Community community) {
        boardService.update_board(community);
        return "redirect:/board_list";
    }

    @RequestMapping("board_delete")
    public String BoardDelete(String community_num) {
        boardService.delete_board(community_num);
        return "redirect:/board_list";
    }

    @GetMapping("Contract_Details")
    public String Contract_Details() {
        return "Admin_Dashboard/Contract_Details";
    }

    @GetMapping("Event_Check")
    public String Event_Check() {
        return "Admin_Dashboard/Event_Check";
    }

    @GetMapping("Event_Registration")
    public String Event_Registration() {
        return "Admin_Dashboard/Event_Registration";
    }

    @GetMapping("Member_Control")
    public String Member_Control() {
        return "Admin_Dashboard/Member_Control";
    }

    @GetMapping("Member_Withdrawal")
    public String Member_Withdrwal() {
        return "Admin_Dashboard/Member_Withdrawal";
    }


    @GetMapping("Members_Search")
    public String Members_Search(@ModelAttribute("searchVO") Member searchVO, HttpServletRequest request, Model model) {
        PageCtrl pagination  = new PageCtrl();
        pagination.setCurrentPageNo(searchVO.getPageIndex());
        pagination.setRecordCountPerPage(searchVO.getPageUnit());
        pagination.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(pagination.getFirstRecordIndex());
        searchVO.setRecordCountPerPage(pagination.getRecordCountPerPage());
        System.out.println("펄스트인덱스 : " + searchVO.getFirstIndex());

        String context = request.getParameter("searchValue");

        if(context == null){
            List<Member> memberList = adminService.memberAll(searchVO);
            model.addAttribute("memberList" , memberList);
            int totCnt = adminService.memberList_cnt();
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
            List<Member> memberList = adminService.memberAll(searchVO);
            model.addAttribute("memberList" , memberList);
            int totCnt = adminService.memberList_cnt();
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
            searchVO.setSearch_content(context);
            System.out.println("c : "+searchVO.getSearch_content());

            int totCnt = adminService.member_search_cnt(context);
            System.out.println("전체 게시글 수 : " + totCnt);
            System.out.println("검색내용 : " + context);

            List<Member> member_list_search = adminService.member_list_search(searchVO);
            pagination.setTotalRecordCount(totCnt);

            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
            searchVO.setPrev(pagination.getXprev());
            searchVO.setNext(pagination.getXnext());
            model.addAttribute("memberList" , member_list_search);
            model.addAttribute("totCnt",totCnt);
            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
            model.addAttribute("pagination",pagination);
        }
        return "Admin_Dashboard/Members_Search";
    }

    @RequestMapping("member_delete")
    public String member_delete(String user_num, @ModelAttribute("searchVO") Member searchVO, HttpServletRequest request, Model model) {
        adminService.member_delete(user_num);
        return  Members_Search(searchVO,request,model);
    }
    @GetMapping("Statistics_Chart")
    public String Statistics_Chart() {
        return "Admin_Dashboard/Statistics_Chart";
    }

    @GetMapping("Notice_Control")
    public String Notice_Control(@ModelAttribute("searchVO") Notice searchVO, HttpServletRequest request, Model model) {
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
        else {
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
            model.addAttribute("pg_list", pg_list);
            model.addAttribute("totCnt", totCnt);
            model.addAttribute("totalPageCnt", (int) Math.ceil(totCnt / (double) searchVO.getPageUnit()));
            model.addAttribute("pagination", pagination);
        }
        return "Admin_Dashboard/Notice_Control";
    }

    @GetMapping("Community_Control")
    public String Community_Control(@ModelAttribute("searchVO") Community searchVO, HttpServletRequest request, Model model) {
        PageCtrl pagination  = new PageCtrl();
        pagination.setCurrentPageNo(searchVO.getPageIndex());
        pagination.setRecordCountPerPage(searchVO.getPageUnit());
        pagination.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(pagination.getFirstRecordIndex());
        searchVO.setRecordCountPerPage(pagination.getRecordCountPerPage());
        System.out.println("펄스트인덱스 : " + searchVO.getFirstIndex());

        String search = request.getParameter("searchName");
        String context = request.getParameter("searchValue");
        System.out.println(search + " " + context);

        if(context == null){
            List<Community> bd_list = boardService.bd_list(searchVO);
            model.addAttribute("bd_list" , bd_list);
            int totCnt = boardService.bd_listCnt();
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
            List<Community> bd_list = boardService.bd_list(searchVO);
            model.addAttribute("bd_list" , bd_list);
            int totCnt = boardService.bd_listCnt();
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
            searchVO.setSearch_name(search);
            searchVO.setSearch_content(context);
            int totCnt = boardService.bd_list_search_Cnt(searchVO);
            System.out.println("CC : " + totCnt);

            List<Community> pg_list = boardService.bd_list_search(searchVO);
            pagination.setTotalRecordCount(totCnt);

            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
            searchVO.setPrev(pagination.getXprev());
            searchVO.setNext(pagination.getXnext());
            model.addAttribute("bd_list" , pg_list);
            model.addAttribute("totCnt",totCnt);
            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
            model.addAttribute("pagination",pagination);
        }
        return "Admin_Dashboard/Community_Control";
    }
    //관리자 게시글 삭제
    @RequestMapping("board_delete_admin")
    public String board_delete_admin(String community_num,@ModelAttribute("searchVO") Community searchVO, HttpServletRequest request, Model model) {
        boardService.delete_board(community_num);
        adminService.delete_board_reply(community_num);
        return Community_Control(searchVO,request,model);
    }
    //관리자 게시글 수정 페이지
    @RequestMapping("board_modify_admin")
    public String board_modify_admin(String community_num, Model model) {
        model.addAttribute("Board", boardService.view_board(community_num));
        model.addAttribute("reply", boardService.view_reply(community_num));
        return "Admin_Dashboard/Community_Modify";
    }
    //게시글 수정
    @RequestMapping("board_update_admin")
    public String board_modify_admin(Community community) {
        boardService.update_board(community);
        return "redirect:/Community_Control";
    }

    @RequestMapping("comment_update_admin")
    public String comment_update_admin(HttpServletRequest request, Model model) {
        System.out.println("댓글필터");
        String reply_num = request.getParameter("reply_num");
        String community_num = request.getParameter("community_num");
        System.out.println(reply_num + " " + community_num);
        adminService.comment_update_admin(reply_num);
        return board_modify_admin(community_num,model);
    }

    @GetMapping("Report_List")
    public String Report_List(@ModelAttribute("searchVO") Report searchVO, Model model) {
        PageCtrl pagination  = new PageCtrl();
        pagination.setCurrentPageNo(searchVO.getPageIndex());
        pagination.setRecordCountPerPage(searchVO.getPageUnit());
        pagination.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(pagination.getFirstRecordIndex());
        searchVO.setRecordCountPerPage(pagination.getRecordCountPerPage());

        List<Report> re_list = reportService.report_all(searchVO);
        model.addAttribute("re_list" , re_list);
        int totCnt = reportService.report_all_cnt();
        model.addAttribute("totCnt",totCnt);
        model.addAttribute("totCnt",totCnt);
        System.out.println("전체 게시글 수 : " + totCnt);

        pagination.setTotalRecordCount(totCnt);

        searchVO.setEndDate(pagination.getLastPageNoOnPageList());
        searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
        searchVO.setPrev(pagination.getXprev());
        searchVO.setNext(pagination.getXnext());
        model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
        model.addAttribute("pagination",pagination);


        return "Admin_Dashboard/Report_List";
    }

    @GetMapping("Approval_List")
    public String Approval_List(@ModelAttribute("searchVO") House_list searchVO, HttpServletRequest request, Model model) {

        PageCtrl pagination  = new PageCtrl();
        pagination.setCurrentPageNo(searchVO.getPageIndex());
        pagination.setRecordCountPerPage(searchVO.getPageUnit());
        pagination.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(pagination.getFirstRecordIndex());
        searchVO.setRecordCountPerPage(pagination.getRecordCountPerPage());
        System.out.println("펄스트인덱스 : " + searchVO.getFirstIndex());

        String search = request.getParameter("searchName");
        String context = request.getParameter("searchValue");
        System.out.println(search + " " + context);

        if(context == null){
            List<House_list> re_list = adminService.re_list(searchVO);
            model.addAttribute("re_list" , re_list);
            int totCnt = adminService.re_list_cnt();
            model.addAttribute("totCnt",totCnt);
            System.out.println("전체 매물 수 : " + totCnt);

            pagination.setTotalRecordCount(totCnt);

            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
            searchVO.setPrev(pagination.getXprev());
            searchVO.setNext(pagination.getXnext());
            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
            model.addAttribute("pagination",pagination);
        }
        else if(context != null && context == ""){
            List<House_list> re_list = adminService.re_list(searchVO);
            model.addAttribute("re_list" , re_list);
            int totCnt = adminService.re_list_cnt();
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
            //검색
//            searchVO.setSearch_name(search);
//            searchVO.setSearch_content(context);
//            int totCnt = boardService.bd_list_search_Cnt(searchVO);
//            System.out.println("CC : " + totCnt);
//
//            List<Community> pg_list = boardService.bd_list_search(searchVO);
//            pagination.setTotalRecordCount(totCnt);
//
//            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
//            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
//            searchVO.setPrev(pagination.getXprev());
//            searchVO.setNext(pagination.getXnext());
//            model.addAttribute("bd_list" , pg_list);
//            model.addAttribute("totCnt",totCnt);
//            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
//            model.addAttribute("pagination",pagination);
        }

        return "Admin_Dashboard/Approval_List";
    }

    @RequestMapping("no_approval_ok")
    public String no_approval_ok(String house_num, Model model) {
        adminService.no_approval_ok_house_item(house_num);
        adminService.no_approval_ok_house_list(house_num);
        return "redirect:/No_Approval_List";
    }

    @RequestMapping("approval_ok")
    public String approval_ok(String house_num, Model model) {
        adminService.approval_ok_house_item(house_num);
        adminService.approval_ok_house_list(house_num);

        return "redirect:/Approval_List";
    }

    @RequestMapping("approval_no")
    public String approval_no(String house_num, Model model) {
        adminService.approval_no_house_item(house_num);
        adminService.approval_no_house_list(house_num);

        return "redirect:/Approval_List";
    }

    @GetMapping("No_Approval_List")
    public String No_Approval_List(@ModelAttribute("searchVO") House_list searchVO, HttpServletRequest request, Model model) {

        PageCtrl pagination  = new PageCtrl();
        pagination.setCurrentPageNo(searchVO.getPageIndex());
        pagination.setRecordCountPerPage(searchVO.getPageUnit());
        pagination.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(pagination.getFirstRecordIndex());
        searchVO.setRecordCountPerPage(pagination.getRecordCountPerPage());
        System.out.println("펄스트인덱스 : " + searchVO.getFirstIndex());

        String search = request.getParameter("searchName");
        String context = request.getParameter("searchValue");
        System.out.println(search + " " + context);

        if(context == null){
            List<House_list> re_list = adminService.no_re_list(searchVO);
            model.addAttribute("re_list" , re_list);
            int totCnt = adminService.re_list_cnt();
            model.addAttribute("totCnt",totCnt);
            System.out.println("미승인 매물 수 : " + totCnt);

            pagination.setTotalRecordCount(totCnt);

            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
            searchVO.setPrev(pagination.getXprev());
            searchVO.setNext(pagination.getXnext());
            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
            model.addAttribute("pagination",pagination);
        }
        else if(context != null && context == ""){
            List<House_list> re_list = adminService.no_re_list(searchVO);
            model.addAttribute("re_list" , re_list);
            int totCnt = adminService.no_re_list_cnt();
            model.addAttribute("totCnt",totCnt);
            System.out.println("미승인 매물 수 : " + totCnt);

            pagination.setTotalRecordCount(totCnt);

            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
            searchVO.setPrev(pagination.getXprev());
            searchVO.setNext(pagination.getXnext());
            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
            model.addAttribute("pagination",pagination);
        }
        else{
            //검색
//            searchVO.setSearch_name(search);
//            searchVO.setSearch_content(context);
//            int totCnt = boardService.bd_list_search_Cnt(searchVO);
//            System.out.println("CC : " + totCnt);
//
//            List<Community> pg_list = boardService.bd_list_search(searchVO);
//            pagination.setTotalRecordCount(totCnt);
//
//            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
//            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
//            searchVO.setPrev(pagination.getXprev());
//            searchVO.setNext(pagination.getXnext());
//            model.addAttribute("bd_list" , pg_list);
//            model.addAttribute("totCnt",totCnt);
//            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
//            model.addAttribute("pagination",pagination);
        }

        return "Admin_Dashboard/No_Approval_List";
    }


    //댓글 관리 (삭제->필터) 검색(작성자,내용)
    @GetMapping("Reply_Control")
    public String Reply_Control(@ModelAttribute("searchVO") Community_reply searchVO, HttpServletRequest request, Model model)
    {
        PageCtrl pagination  = new PageCtrl();
        pagination.setCurrentPageNo(searchVO.getPageIndex());
        pagination.setRecordCountPerPage(searchVO.getPageUnit());
        pagination.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(pagination.getFirstRecordIndex());
        searchVO.setRecordCountPerPage(pagination.getRecordCountPerPage());
        System.out.println("펄스트인덱스 : " + searchVO.getFirstIndex());

        String search = request.getParameter("searchName");
        String context = request.getParameter("searchValue");
        System.out.println(search + " " + context);

        if(context == null){
            List<Community_reply> reply = adminService.reply_list(searchVO);
            model.addAttribute("reply" , reply);
            int totCnt = adminService.reply_listCnt();
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
            List<Community_reply> reply = adminService.reply_list(searchVO);
            model.addAttribute("reply" , reply);
            int totCnt = adminService.reply_listCnt();
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
            searchVO.setSearch_name(search);
            searchVO.setSearch_content(context);
            System.out.println("Parameter ==============");
            System.out.println(searchVO.getSearch_name());
            System.out.println(searchVO.getSearch_content());

            int totCnt = adminService.reply_list_search_Cnt(searchVO);
            System.out.println("댓글검색 : " + totCnt);
            List<Community_reply> reply = adminService.reply_list_search(searchVO);
            pagination.setTotalRecordCount(totCnt);

            searchVO.setEndDate(pagination.getLastPageNoOnPageList());
            searchVO.setStartDate(pagination.getFirstPageNoOnPageList());
            searchVO.setPrev(pagination.getXprev());
            searchVO.setNext(pagination.getXnext());
            model.addAttribute("reply" , reply);
            model.addAttribute("totCnt",totCnt);
            model.addAttribute("totalPageCnt",(int)Math.ceil(totCnt / (double)searchVO.getPageUnit()));
            model.addAttribute("pagination",pagination);
        }

        return "Admin_Dashboard/Reply_Control";
    }

    @RequestMapping("comment_update_admin_reply")
    public String comment_update_admin(@ModelAttribute("searchVO") Community_reply searchVO, HttpServletRequest request, Model model) {
        String reply_num = request.getParameter("reply_num");
        adminService.comment_update_admin(reply_num);
        return Reply_Control(searchVO,request,model);
    }

    @RequestMapping("house_info")
    public String house_info (String house_num,Model model) {

        List<House_list> house_list = realEstateService.view_house_list_one(house_num);
        List<House_item> house_item = realEstateService.list_house_item(house_num);
        List<House_addinfo> house_addinfo = realEstateService.add_info_list(house_num);
        List<House_detail> house_detail = realEstateService.house_detail_list(house_num);
        List<House_option> house_option = realEstateService.house_option_list(house_num);
        List<House_info> house_info = realEstateService.house_info_list(house_num);
        List<House_type> house_type = realEstateService.house_type_list(house_num);
        String road_address = realEstateService.road_address(house_num);
        List<House_picture> housePictures = realEstateService.house_picture_list(house_num);


        model.addAttribute("house_list",house_list);
        model.addAttribute("house_item",house_item);
        model.addAttribute("house_addinfo",house_addinfo);
        model.addAttribute("house_detail",house_detail);
        model.addAttribute("house_option",house_option);
        model.addAttribute("house_info",house_info);
        model.addAttribute("house_type",house_type);
        model.addAttribute("road_address",road_address);
        model.addAttribute("housePictures",housePictures);
        return "Admin_Dashboard/house_info";
    }

    }
