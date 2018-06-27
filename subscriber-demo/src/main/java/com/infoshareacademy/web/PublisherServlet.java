package com.infoshareacademy.web;

import java.io.IOException;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/publisher")
public class PublisherServlet extends HttpServlet {

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "java:/jms/topic/ISA.TOPIC")
    private Topic topic;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        final String message = req.getParameter("msg");

        JMSProducer producer = jmsContext.createProducer();
        Message msg = jmsContext.createTextMessage(message);
        producer.send(topic, msg);

        resp.getWriter().println("Message sent.");
    }
}
