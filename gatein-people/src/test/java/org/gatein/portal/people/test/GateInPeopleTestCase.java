/*
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.gatein.portal.people.test;

import java.net.URL;

import org.exoplatform.component.test.ConfigurationUnit;
import org.exoplatform.component.test.ConfiguredBy;
import org.exoplatform.component.test.ContainerScope;
import org.exoplatform.component.test.KernelBootstrap;
import org.gatein.pc.embed.EmbedServlet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Nguyen Thanh Hai</a>
 * @version $Id$
 * 
 */
@ConfiguredBy({
	@ConfigurationUnit(scope = ContainerScope.ROOT, path = "people-configuration.xml")
})
@RunWith(Arquillian.class)
public class GateInPeopleTestCase
{

  @Deployment()
  public static WebArchive deployment() throws Exception
  {
    WebArchive war = ShrinkWrap.create(WebArchive.class);
    String webDesc = "" +
        "<web-app\n" +
        "xmlns=\"http://java.sun.com/xml/ns/javaee\"\n" +
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
        "xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd\"\n" +
        "version=\"3.0\">" +
        "<filter>\n" +
        "<filter-name>FilterImpl</filter-name>\n" +
        "<filter-class>org.gatein.portal.people.test.FilterImpl</filter-class>\n" +
        "</filter>\n" +
        "<filter-mapping>\n" +
        "<filter-name>FilterImpl</filter-name>\n" +
        "<servlet-name>EmbedServlet</servlet-name>\n" +
        "</filter-mapping>\n" +
        "<servlet>\n" +
        "<servlet-name>EmbedServlet</servlet-name>\n" +
        "<servlet-class>" + EmbedServlet.class.getName() + "</servlet-class>\n" +
        "<load-on-startup>0</load-on-startup>\n" +
        "</servlet>\n" +
        "<servlet-mapping>\n" +
        "<servlet-name>EmbedServlet</servlet-name>\n" +
        "<url-pattern>/embed/*</url-pattern>\n" +
        "</servlet-mapping>\n" +
        "<servlet>\n" +
        "<servlet-name>AssetServlet</servlet-name>\n" +
        "<servlet-class>juzu.impl.asset.AssetServlet</servlet-class>\n" +
        "<load-on-startup>0</load-on-startup>\n" +
        "</servlet>\n" +
        "<servlet-mapping>\n" +
        "<servlet-name>AssetServlet</servlet-name>\n" +
        "<url-pattern>/assets/*</url-pattern>\n" +
        "</servlet-mapping>\n" +
        "</web-app>\n";
    war.setWebXML(new ByteArrayAsset(webDesc.getBytes()));
    String portletDesc = "" +
        "<portlet-app\n" +
        "xmlns=\"http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd\"\n" +
        "version=\"2.0\"\n" +
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
        "xsi:schemaLocation=\"http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd\">\n" +
        "<portlet>\n" +
        "<portlet-name>PeopleApplication</portlet-name>\n" +
        "<portlet-class>org.gatein.portal.people.GateInPeoplePortlet</portlet-class>\n" +
        "<supports>\n" +
        "<mime-type>text/html</mime-type>\n" +
        "</supports>\n" +
        "<portlet-info>\n" +
        "<title>Gatein People</title>\n" +
        "</portlet-info>\n" +
        "</portlet>\n" +
        "</portlet-app>";
    war.setWebXML(new ByteArrayAsset(webDesc.getBytes()));
    war.addAsWebInfResource(new ByteArrayAsset(portletDesc.getBytes()), "portlet.xml");
    return war;
  }

  /** . */
  private final KernelBootstrap kernel = new KernelBootstrap();

  @Before
  @RunAsClient
  public void startKernel()
  {
    kernel.addConfiguration(GateInPeopleTestCase.class);
    kernel.boot();
  }

  @After
  @RunAsClient
  public void stopKernel()
  {
    kernel.dispose();
  }

  @Drone
  WebDriver driver;

  @ArquillianResource
  URL deploymentURL;

  @Test
  @RunAsClient
  public void test() throws Exception
	{
    URL url = deploymentURL.toURI().resolve("embed/PeopleApplication").toURL();

    //
    driver.get(url.toString());
	}
}
