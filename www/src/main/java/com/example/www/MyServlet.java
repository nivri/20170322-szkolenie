package com.example.www;

import com.example.dictionary.translation.DictionaryWord;
import com.example.dictionary.translation.TranslationService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/*")
public class MyServlet extends HttpServlet {

    WebApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String foo = req.getParameter("word");
        System.out.println("word = " + foo);

        TranslationService translationService = context.getBean(TranslationService.class);
        List<DictionaryWord> wordList = translationService.getTranslationsForWord(foo);

        resp.setContentType("text/plain");
        PrintWriter writer = resp.getWriter();
        wordList.forEach(writer::println);
        writer.flush();
    }
}
