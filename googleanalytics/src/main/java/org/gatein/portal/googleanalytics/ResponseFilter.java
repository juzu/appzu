package org.gatein.portal.googleanalytics;

import org.w3c.dom.Element;

import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.RenderFilter;
import java.io.IOException;

/** @author <a href="mailto:bpaillereau@exoplatform.com">Benjamin Paillereau</a> */
public class ResponseFilter implements RenderFilter
{

  public void init(FilterConfig filterConfig) throws PortletException
  {
  }

  public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain) throws IOException, PortletException
  {

    String account = request.getPreferences().getValue("account", null);

    if (account!=null) {
      Element jQuery1 = response.createElement("script");
      jQuery1.setAttribute("type", "text/javascript");
      String textContent = "var _gaq = _gaq || [];\n" +
              "  _gaq.push(['_setAccount', '"+account+"']);\n" +
              "  _gaq.push(['_trackPageview']);\n" +
              "\n" +
              "  (function() {\n" +
              "    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;\n" +
              "    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';\n" +
              "    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);\n" +
              "  })();";

      jQuery1.setTextContent(textContent);
      response.addProperty(MimeResponse.MARKUP_HEAD_ELEMENT, jQuery1);
    }

    //
    chain.doFilter(request, response);
  }

  public void destroy()
  {
  }
}
