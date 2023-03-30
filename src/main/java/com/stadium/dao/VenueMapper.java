package com.stadium.dao;

import com.stadium.entity.User;
import com.stadium.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VenueMapper {

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "venue_name", property = "venue_name"),
            @Result(column = "useid", property = "userid"),
    })
    @Select("select * from venue")
    List<Venue> getVenueList_act();


    @Update("update venue_state set useid = #{userid}, state = 0 where state_id = #{id}")
    void SelectVenue(Venue venue);
}
