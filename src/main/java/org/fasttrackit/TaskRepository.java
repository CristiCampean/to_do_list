package org.fasttrackit;

import org.fasttrackit.config.DatabaseConfiguration;
import org.fasttrackit.transfer.CreateTaskRequest;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskRepository {

    public void createTask(CreateTaskRequest request) throws SQLException {
         String sql = "INSERT INTO task (description, deadline) VALUES( ? ,?)";
        // try with resorceous
         try(
            PreparedStatement preparedStatement = DatabaseConfiguration.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,request.getDescription());
            preparedStatement.setDate(2, Date.valueOf(request.getDeadline()));
            preparedStatement.executeUpdate();
        }

    }
}

