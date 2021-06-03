package com.orange.dao;

import com.orange.pojo.Vote;
import com.orange.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoteDaoImpl implements VoteDao{

    @Override
    public List<Vote> getVote() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Vote> voteList = new ArrayList<>();
        try {

            connection = JDBCUtils.getConnection();
            String sql = "select t2.post_id, sum(t2.vote) from post t1, vote t2 where t1.id = t2.post_id group by post_id";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vote vote = new Vote();
                vote.setPost_id(resultSet.getInt("post_id"));
                vote.setVote(resultSet.getInt("sum(t2.vote)"));
                voteList.add(vote);
            }
            System.out.println("Get Vote!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }

        return voteList;
    }
}
