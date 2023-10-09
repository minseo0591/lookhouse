package com.example.thishouse.service;

import com.example.thishouse.domain.contract.Lessoer;
import com.example.thishouse.domain.house.*;
import com.example.thishouse.mapper.RealEstateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RealEstateService {

    private final RealEstateMapper realEstateMapper;
    //시퀸스 생성
    public int sequence() {
        int result = realEstateMapper.sequence();
        return result;
    }
    //매물등록*
    //매물 기본
    @Transactional
    public void insert_house_item(House_item houseItem) {
        this.realEstateMapper.insert_house_item(houseItem);
    }

    //매물내용
    @Transactional
    public void insert_house_type(House_type house_type) {
        System.out.println("TEST CON SER================");
        this.realEstateMapper.insert_house_type(house_type);
    }

    @Transactional
    public void insert_house_location(House_location house_location) {
        this.realEstateMapper.insert_house_location(house_location);
    }

    @Transactional
    public void insert_house_deal(House_deal house_deal) {
        this.realEstateMapper.insert_house_deal(house_deal);
    }

    @Transactional
    public void insert_house_info(House_info house_info) {
        this.realEstateMapper.insert_house_info(house_info);
    }

    @Transactional
    public void insert_house_addinfo(House_addinfo house_addinfo) {
        this.realEstateMapper.insert_house_addinfo(house_addinfo);
    }

    @Transactional
    public void insert_house_option(House_option house_option) {
        this.realEstateMapper.insert_house_option(house_option);
    }

    @Transactional
    public void insert_house_detail(House_detail house_detail) {
        this.realEstateMapper.insert_house_detail(house_detail);
    }

    @Transactional
    public void insert_picture(House_picture house_picture) {
        this.realEstateMapper.insert_picture(house_picture);
    }
    //매물요약
    @Transactional
    public void insert_house_list(House_list house_list) {
        this.realEstateMapper.insert_house_list(house_list);
    }

    //매물 사진

    public List<House_list> view_house_list() {
        return this.realEstateMapper.view_house_list();
    }

    public List<House_picture> getHousePictures() {
        return this.realEstateMapper.getHousePictures();
    }

    public Resource loadFileAsResource(String filePath) throws FileNotFoundException {
        try {
            Path file = Paths.get(filePath);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Could not read file: " + filePath);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filePath);
        }
    }

    public List<House_list> view_house_list_one(String house_num) {
            return this.realEstateMapper.view_house_list_one(house_num);
    }
    public List<House_item> list_house_item(String house_num) {
        return this.realEstateMapper.list_house_item(house_num);
    }

    public List<House_addinfo> add_info_list(String houseNum) {
        return this.realEstateMapper.add_info_list(houseNum);
    }

    public List<House_detail> house_detail_list(String houseNum) {
        return this.realEstateMapper.house_detail_list(houseNum);
    }

    public List<House_option> house_option_list(String houseNum) {
        return this.realEstateMapper.house_option_list(houseNum);

    }

    public List<House_info> house_info_list(String houseNum) {
        return this.realEstateMapper.house_info_list(houseNum);
    }

    public List<House_type> house_type_list(String houseNum) {
        return this.realEstateMapper.house_type_list(houseNum);
    }

    public String road_address(String houseNum) {
        return this.realEstateMapper.road_address(houseNum);
    }

    public List<House_picture> house_picture_list(String houseNum) {
        return this.realEstateMapper.house_picture_list(houseNum);
    }

    public List<House_list> house_list_pg(House_list searchVO) {
        return this.realEstateMapper.house_list_pg(searchVO);
    }

    public int house_list_pg_cnt() {
        return this.realEstateMapper.house_list_pg_cnt();
    }

    public List<House_list> house_search_pg(House_list searchVO) {
        return this.realEstateMapper.house_search_pg(searchVO);
    }

    public int house_search_pg_cnt(House_list searchVO) {
        return this.realEstateMapper.house_search_pg_cnt(searchVO);
    }

    @Transactional
    public void house_hit_coount(String houseNum) {
        this.realEstateMapper.house_hit_coount(houseNum);
    }

    @Transactional
    public void insert_lessoer_info(Lessoer lessoer) {
        this.realEstateMapper.insert_lessoer_info(lessoer);

    }

    public String deal_type(String houseNum) {
        return this.realEstateMapper.deal_type(houseNum);
    }

    public List<House_location> house_location(String houseNum) {
        return this.realEstateMapper.house_location(houseNum);
    }

    public String house_type(String houseNum) {
        return this.realEstateMapper.house_type(houseNum);
    }
}
