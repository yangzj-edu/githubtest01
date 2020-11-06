package com.xszx.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xszx.entity.User;
import com.xszx.mapper.UserMapper;
import com.xszx.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    public SqlSession sqlSession = SqlSessionUtil.getSqlSession();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("访问到servlet");
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();
        //System.out.println(users);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        //users-----> json格式的字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
        writer.print(json);
        writer.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
