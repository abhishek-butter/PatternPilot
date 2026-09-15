package com.PatternPilot.Repository;

import com.PatternPilot.Domain.Topic;
import com.PatternPilot.Exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * @author Abhishek V S
 **/
@Repository
public class TopicRepositoryImp implements TopicRepository{

    private static final String SQL_CREATE="insert into pp_topics(userId, topicName, confidenceScore) values (?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "select * from pp_topics where userId = ? and topicId = ?";
    private static final String SQL_FIND_ALL = "select * from pp_topics where userId = ?";
    private static final String SQL_FIND_BY_NAME = "select * from pp_topics where userId = ? and topicName = ?";
    private static final String SQL_UPDATE =
            "UPDATE pp_topics SET topicName = ?, ConfidenceScore = ? WHERE userId = ? and topicId= ?";
    private static final String SQL_DELETE = "DELETE FROM pp_topics WHERE userId = ? and topicId = ?";
    private static final String SQL_FIND_SCORE = "SELECT AVG(confidenceScore) FROM pp_problem WHERE topicId = ? AND userId = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public int create(Integer userId, String topicName, Double confidenceScore) {
        try{
            KeyHolder key=new GeneratedKeyHolder();
            jdbcTemplate.update(connection->{
                PreparedStatement ps=connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,userId);
                ps.setString(2,topicName);
                ps.setDouble(3,confidenceScore);
                return ps;
            },key);
            return ((Number)key.getKeys().get("topicid")).intValue();

        }
        catch (Exception e){
            throw new PPBadRequestException("Invalid Data!");
        }
    }

    @Override
    public Topic findById(Integer topicId, Integer userId) throws  PPResourceNotFound{
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID,topicRowMapper,userId,topicId);

        }
        catch (Exception e){
            throw new PPResourceNotFound("Invalid Id's");
        }
    }

    @Override
    public List<Topic> findAll(Integer userId) {
        try{
            return jdbcTemplate.query(SQL_FIND_ALL,topicRowMapper,userId);
        }
        catch (Exception e){
            throw  new PPResourceNotFound("Invalid userId");
        }
    }

    @Override
    public Topic findByName( Integer userId, String name) {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_NAME,topicRowMapper,userId,name);

        }
        catch (Exception e){
            throw new PPResourceNotFound("Invalid Name");
        }

    }

    @Override
    public Double findScore(Integer topicId, Integer userId) {
        try{
            Double avg= jdbcTemplate.queryForObject(SQL_FIND_SCORE,Double.class,topicId,userId);
            return (avg!=null)?avg:0.0;

        }
        catch (Exception e){
            return 0.0;
        }



    }



    @Override
    public void update(Integer topicId,Integer userId, Topic topic) {
        jdbcTemplate.update(SQL_UPDATE,topic.getTopicName(),topic.getConfidenceScore(),userId,topicId);

    }
    private final RowMapper<Topic> topicRowMapper=((rs,rowNum)-> {
        return new Topic
                (
                rs.getInt("topicId"),
                rs.getInt("userId"),
                rs.getString("topicName"),
                rs.getDouble("confidenceScore")
                );

    });
}
