import com.stadium.dao.VenueMapper;
import com.stadium.entity.Venue;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestVenue {
    @Test
    public void  GetVenue(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            VenueMapper mapper = sqlSession.getMapper(VenueMapper.class);
            Venue venue=mapper.getVenueList_act().get(0);
            Assertions.assertNotNull(venue, "getting venue data has failed");
        }
    }

    @Test
    public void  SelectVenue(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            VenueMapper mapper = sqlSession.getMapper(VenueMapper.class);
            Venue venue=mapper.getVenueList_act().get(0);
            venue.setUserid("2");
            mapper.SelectVenue(venue);
            Assertions.assertEquals("2", venue.getUserid(),"selecting venue has failed");
        }
    }


}
