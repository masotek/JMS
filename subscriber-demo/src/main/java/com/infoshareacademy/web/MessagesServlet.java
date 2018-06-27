package com.infoshareacademy.web;

import com.infoshareacademy.data.MessageRepository;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/messages")
public class MessagesServlet extends HttpServlet {

    @Inject
    private MessageRepository messageRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        final PrintWriter printWriter = resp.getWriter();
        messageRepository.getMessages().forEach((date, msg) -> {
            printWriter.println(date + ": " + msg);
        });
    }
}
