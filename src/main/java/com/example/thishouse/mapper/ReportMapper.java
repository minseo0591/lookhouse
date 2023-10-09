package com.example.thishouse.mapper;

import com.example.thishouse.domain.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {

    public void insertReport(Report report);

    public List<Report> report_list(String house_num);


    List<Report> report_all(Report searchVO);

    int report_all_cnt();
}
