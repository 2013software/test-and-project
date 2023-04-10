import com.stadium.dao.StVenueMapper;
import com.stadium.entity.StVenue;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStVenue {
    @Test
    public void  GetStVenueList(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            StVenueMapper mapper = sqlSession.getMapper(StVenueMapper.class);
            StVenue venue= mapper.getStVenueList(13).get(0);
            Assertions.assertNotNull(venue, "getting venue selected has failed");
        }
    }

    @Test
    public void SelectVenue(){
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            StVenueMapper mapper = sqlSession.getMapper(StVenueMapper.class);
            StVenue venue=new StVenue();
            venue.setVenueid(4);
            venue.setTimeid(6);
            venue.setUseid(13);
            venue.setId(5);
            mapper.SelectVenue(venue);
            StVenue venue1= mapper.getStVenueList(13).get(0);
            Assertions.assertNotNull(venue1,"selecting venue has failed");
        }
    }
    @Test
    public void CancelVenue() throws Exception{
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            StVenueMapper mapper = sqlSession.getMapper(StVenueMapper.class);
            mapper.CancelVenue("5");
            int i=0;
            while(i<mapper.getStVenueList(13).size())
            {
                StVenue venue= mapper.getStVenueList(13).get(i);
                if(venue.getId()==5){
                    throw new Exception("canceling the venue test failed");

                }
                i=i+1;
                }

        }
    }


}
