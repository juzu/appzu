package org.gatein.portal.people.test;

import org.exoplatform.container.RootContainer;
import org.exoplatform.container.component.RequestLifeCycle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/** @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a> */
public class FilterImpl implements Filter {

  public void init(FilterConfig filterConfig) throws ServletException {

  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    RequestLifeCycle.begin(RootContainer.getInstance());
    try {
      chain.doFilter(request, response);
    }
    finally {
      RequestLifeCycle.end();
    }
  }

  public void destroy() {

  }
}
