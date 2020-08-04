package org.fasttrackit;

import org.fasttrackit.transfer.CreateTaskRequest;

import java.sql.SQLException;
import java.time.LocalDate;


public class App 
{
    public static void main( String[] args ) throws SQLException {
        System.out.println( "Hello World!" );
        TaskRepository taskRepository = new TaskRepository();
        CreateTaskRequest request= new CreateTaskRequest();

        request.setDescription("Learn JDBS");
        request.setDeadline(LocalDate.now().plusWeeks(1));
       taskRepository.createTask(request);
       taskRepository.deleteTask(1);
       System.out.println(taskRepository.getTasks());
    }
}
