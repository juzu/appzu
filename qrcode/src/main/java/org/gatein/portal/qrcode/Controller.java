package org.gatein.portal.qrcode;

import juzu.Path;
import juzu.View;
import juzu.template.Template;

import javax.inject.Inject;
import javax.portlet.PortletPreferences;

/** @author <a href="mailto:benjamin.paillereau@exoplatform.com">Benjamin Paillereau</a> */
public class Controller
{

  /** . */
  @Inject
  @Path("index.gtmpl")
  Template indexTemplate;

  @Inject
  PortletPreferences portletPreferences;

  @View
  public void index()
  {
    String size = portletPreferences.getValue("size", "128");
    indexTemplate.with().set("size", size).render();
  }

}
