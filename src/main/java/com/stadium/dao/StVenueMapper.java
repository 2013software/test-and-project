package com.stadium.dao;

import com.stadium.entity.Course;
import com.stadium.entity.StVenue;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StVenueMapper {
    @Results({
            @Result(column = "state_id", property = "id"),
            @Result(column = "time", property = "time"),
            @Result(column = "venue_name", property = "location")
    })
    @Select("select * from venue_state, time, venue " +
            "where venue_state.venueid = venue.id " +
            "and venue_state.timeid = time.id " +
            "and venue_state.useid = #{userid} ")
    List<StVenue> getStVenueList(int userid);

    @Delete("delete from venue_state where state_id = #{id}")
    void CancelVenue(String id);

    @Insert("insert into venue_state(venueid,timeid,useid) value(#{venueid}, #{timeid}, #{useid})")
    void SelectVenue(StVenue stVenue);
}
