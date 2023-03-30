package com.stadium.service.impl;

import com.stadium.dao.VenueMapper;
import com.stadium.entity.Venue;
import com.stadium.service.VenueService;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class VenueServiceImpl implements VenueService {

    @Override
    public List<Venue> getVenueList_act() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            VenueMapper mapper = sqlSession.getMapper(VenueMapper.class);
            return  mapper.getVenueList_act();
        }
    }


    @Override
    public void selectVenue(Venue venue) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            VenueMapper mapper = sqlSession.getMapper(VenueMapper.class);
            mapper.SelectVenue(venue);
        }
    }

}
