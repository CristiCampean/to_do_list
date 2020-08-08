package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.fasttrackit.config.ObjectMapperConfiguration;
import org.fasttrackit.domain.Task;
import org.fasttrackit.service.TaskService;
import org.fasttrackit.transfer.CreateTaskRequest;
import org.fasttrackit.transfer.UpdateTaskRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
    private TaskService taskService = new TaskService();

    // endpoint
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Task> tasks = taskService.getTasks();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(), tasks);
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        CreateTaskRequest request = objectMapper.readValue(req.getReader(),CreateTaskRequest.class);
        try {
            taskService.createTask(request);
        } catch (SQLException e) {
            resp.sendError(500,e.getMessage());
        }
    }
}