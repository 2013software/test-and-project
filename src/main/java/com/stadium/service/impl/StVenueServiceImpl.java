package com.stadium.service.impl;

import com.stadium.dao.StVenueMapper;
import com.stadium.entity.StVenue;
import com.stadium.service.StVenueService;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StVenueServiceImpl implements StVenueService {

    @Override
    public List<StVenue> getStVenueList(int userid) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            StVenueMapper mapper = sqlSession.getMapper(StVenueMapper.class);
            return mapper.getStVenueList(userid);
        }
    }

    @Override
    public void cancelVenue(String id) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            StVenueMapper mapper = sqlSession.getMapper(StVenueMapper.class);
            mapper.CancelVenue(id);
        }
    }

    @Override
    public void selectVenue(StVenue stVenue) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            StVenueMapper mapper = sqlSession.getMapper(StVenueMapper.class);
            mapper.SelectVenue(stVenue);
        }
    }
}
