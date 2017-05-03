package com.github.huangp.app;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @author Patrick Huang <a href="mailto:pahuang@redhat.com">pahuang@redhat.com</a>
 */
@WebFilter(urlPatterns = "*")
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String locale = request.getParameter("locale");
        ResourceLoader resourceLoader = new ResourceLoader(locale);
        String title = resourceLoader.titleText();
        String welcomeText = resourceLoader.welcomeText();
        request.setAttribute("title", title);
        request.setAttribute("welcome", welcomeText);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
