package com.steelunicorn.david;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DavidController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String index()
    {
        List<Task> tasks = jdbcTemplate.query("SELECT * FROM task", new TaskMapper());
        return tasks.stream().map(Task::getName).collect(Collectors.joining("<br>"));
    }

    class TaskMapper implements RowMapper<Task>{

        @Override
        public Task mapRow(ResultSet resultSet, int i) throws SQLException {
            String name = resultSet.getString("name");
            return new Task(name);
        }
    }

}
