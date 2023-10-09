package com.example.thishouse.mapper;

import com.example.thishouse.domain.Marker;
import com.example.thishouse.domain.house.House_list;
import com.example.thishouse.domain.house.MapVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
@RequiredArgsConstructor
public class MarkerMapper {
    private final SqlSessionTemplate sqlSession;

    private static final String Namespace = "com.example.thishouse.mapper.MarkerMapper";

    public void insertMarker(Marker marker) {
        sqlSession.insert(Namespace+".insertMarker", marker);
    }

    public List<MapVO> getMarkers() {
        System.out.println("맵 마커!!");
        System.out.println(sqlSession.selectList(Namespace+".getMarkers"));
        return sqlSession.selectList(Namespace+".getMarkers"

        );
    }

    public List<MapVO> map_filter(House_list houseList) {
        System.out.println(sqlSession.selectList(Namespace+".map_filter", houseList));
        return sqlSession.selectList(Namespace+".map_filter", houseList);

    }

    public List<MapVO> all_map() {
        return sqlSession.selectList(Namespace+".all_map");
    }

    public List<MapVO> map_all_filter(House_list houseList) {
        return sqlSession.selectList(Namespace+".map_all_filter", houseList);
    }

    public List<MapVO> map_all_house_type_filter(House_list houseList) {
        return sqlSession.selectList(Namespace+".map_all_house_type_filter", houseList);
    }

    public List<MapVO> map_all_deal_type_filter(House_list houseList) {
        return sqlSession.selectList(Namespace+".map_all_deal_type_filter", houseList);
    }
}
