package com.example.thishouse.service;

import com.example.thishouse.domain.Report;
import com.example.thishouse.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {


        private final ReportMapper reportMapper;

        public void insertReport(Report report) {
            reportMapper.insertReport(report);
        }
        public List<Report> report_list(String house_num) {
            return reportMapper.report_list(house_num);
        }

    public List<Report> report_all(Report searchVO) {
        return reportMapper.report_all(searchVO);
    }

    public int report_all_cnt() {
        return reportMapper.report_all_cnt();
    }
}
