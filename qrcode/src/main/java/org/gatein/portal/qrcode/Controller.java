package org.gatein.portal.qrcode;

import org.exoplatform.portal.application.PortalRequestContext;
import juzu.Path;
import juzu.View;
import juzu.template.Template;

import javax.inject.Inject;
import javax.portlet.PortletPreferences;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** @author <a href="mailto:benjamin.paillereau@exoplatform.com">Benjamin Paillereau</a> */
public class Controller
{

   /** . */
   @Inject
   @Path("index.gtmpl")
   Template indexTemplate;


   @Inject
   PortletPreferences portletPreferences;

   @Inject
   public Controller()
   {
   }

   @View
   public void index() throws IOException
   {
      PortalRequestContext portalRequestContext = PortalRequestContext.getCurrentInstance();

      String size = portletPreferences.getValue("size", "128");
      String url = portalRequestContext.getRequest().getRequestURL() + "?" + portalRequestContext.getRequest().getQueryString();
       
      Map<String, Object> parameters = new HashMap<String, Object>();
      parameters.put("url", url);
      parameters.put("size", size);
      indexTemplate.render(parameters);
   }

}
