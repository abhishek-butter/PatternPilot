package com.PatternPilot.Repository;

import com.PatternPilot.Domain.Problem;

import com.PatternPilot.Exceptions.PPBadRequestException;
import com.PatternPilot.Exceptions.PPResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * @author Abhishek V S
 **/
@Repository
public class ProblemRepositoryImp implements ProblemRepository{


    private static final String SQL_CREATE="insert into pp_problem(topicId,userId,problemName,ConfidenceScore) values (?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "select * from pp_problem where userId = ? and problemId= ?";
    private static final String SQL_FIND_ALL = "select * from pp_problem where userId = ? and topicId=?";
    private static final String SQL_FIND_BY_NAME = "select * from pp_problem where userId = ? and topicId= ? and problemName = ?";
    private static final String SQL_UPDATE =
            "UPDATE pp_problem SET problemName = ?, ConfidenceScore = ? WHERE userId = ? and problemId= ?";
    private static final String SQL_DELETE = "DELETE FROM pp_problem WHERE userId = ? and problemId = ?";
    private static final String SQL_FIND_SCORE = "SELECT confidenceScore FROM pp_problem WHERE problemId = ? AND userId = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;



    @Override
    public Integer create(Integer topicId, Integer userId, String problemName, Double confidenceScore) {
        try{
           KeyHolder key=new GeneratedKeyHolder();
           jdbcTemplate.update(connection->{
               PreparedStatement ps=connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
               ps.setInt(1,topicId);
               ps.setInt(2,userId);
               ps.setString(3,problemName);
               ps.setDouble(4,confidenceScore);
               return ps;




            },key);

           Map<String,Object> keys=key.getKeys();
           if(keys==null||keys.get("problemid")==null){
               throw new PPBadRequestException("Problem Creation failed no Id returned");
           }
            return ((Number) keys.get("problemid")).intValue();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new PPBadRequestException("Invalid Data");
        }
    }

    @Override
    public Problem findById(Integer problemId, Integer userId) {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID,problemRowMapper,userId,problemId);

        }
        catch (Exception e){
            e.printStackTrace();
            throw new PPResourceNotFound("Invalid Id's");
        }
    }

    @Override
    public List<Problem> findAll(Integer topicId, Integer userId) {
        try{
            return jdbcTemplate.query(SQL_FIND_ALL,problemRowMapper,userId,topicId);
        }
        catch (Exception e){
            e.printStackTrace();
            throw  new PPResourceNotFound("Invalid userId");
        }
    }

    @Override
    public Problem findByName(Integer topicId, Integer userId, String problemName) {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_NAME,problemRowMapper,userId,topicId,problemName);

        }
        catch (Exception e){
            e.printStackTrace();
            throw new PPResourceNotFound("Invalid Name");
        }
    }

    @Override
    public Double findScore(Integer problemId, Integer userId) {
        try{
            Double score= jdbcTemplate.queryForObject(SQL_FIND_SCORE,Double.class,problemId,userId);
            return score;

        }
        catch (Exception e){
            return 0.0;
        }
    }

    @Override
    public void update(Integer problemId, Integer userId, Problem problem) {
        jdbcTemplate.update(SQL_UPDATE,problem.getProblemName(),problem.getConfidenceScore(),userId,problemId);

    }
    private final RowMapper<Problem> problemRowMapper=((rs, rowNum)-> {
        return new Problem
                (       rs.getInt("problemId"),
                        rs.getInt("topicId"),
                        rs.getInt("userId"),
                        rs.getDouble("confidenceScore"),
                        rs.getString("problemName")
                );

    });
}
