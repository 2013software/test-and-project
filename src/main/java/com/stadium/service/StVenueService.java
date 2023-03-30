package com.stadium.service;

import com.stadium.entity.Course;
import com.stadium.entity.StVenue;

import java.util.List;

public interface StVenueService {
    List<StVenue> getStVenueList(int userid);

    void cancelVenue(String id);
    void selectVenue(StVenue stVenue);
}
