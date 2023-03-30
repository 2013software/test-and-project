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
//    @Select("select * from venue_state, venue, time where venue_state.venueid = venue.id and venue_state.timeid = time.id")
//    @Select("select * from venue")
//    List<Venue> getVenueList_act();
//    Venue getVenue(@Param("venue_name") String venue_name, @Param("state") String state);

//
//    @Select("select * from venue, venue_state, user, time where venue_state.timeid = time.id and venue_state.venueid = venue.id and venue_state.useid = user.userid and ")
//    User getUser(@Param("username") String username, @Param("password") String password, @Param("identity") String identity);


    @Update("update venue_state set useid = #{userid}, state = 0 where state_id = #{id}")
    void SelectVenue(Venue venue);
}
