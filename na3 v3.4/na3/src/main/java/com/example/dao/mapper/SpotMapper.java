package com.example.dao.mapper;

import com.example.dao.entity.Spot;
import com.example.dao.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Tan Shaobin
 * @Date: 2022/11/29/16:38
 * @Description:
 */
public interface SpotMapper {
    public List<Spot> getAllSpots();

    int addSpot(Spot spot);
    int deleteSpot(String spid);
    int updateSpot(Spot spot);

    Spot getSpot(@Param("id")String id);
    int deleteAllSpots();

}
