package com.bong.demospringsecurityform.common;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.GenericFilterBean;

public class LoggingFilter extends GenericFilterBean {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start(((HttpServletRequest) request).getRequestURI());

    filterChain.doFilter(request, servletResponse);

    stopWatch.stop();
    logger.info(stopWatch.prettyPrint());
  }
}
