package com.stadium.service;

import com.stadium.dao.UserMapper;
import com.stadium.dao.VenueMapper;
import com.stadium.entity.User;
import com.stadium.entity.Venue;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.stadium.utils.MybatisUtil.factory;

public interface VenueService {
    List<Venue> getVenueList_act();

    void selectVenue(Venue venue);
}
