/*
 * Copyright (C) 2012 eXo Platform SAS.
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
package org.gatein.portal.github.controllers;

import juzu.Action;
import juzu.Controller;
import juzu.Path;
import juzu.Response;
import juzu.View;
import juzu.template.Template;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.gatein.portal.github.templates.index;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.portlet.PortletPreferences;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/4/12
 */
public class GithubApplication extends Controller
{
   @Inject
   @Path("index.gtmpl")
   org.gatein.portal.github.templates.index index;

   @Inject
   GithubResource githubResource;

   @Inject
   GithubAccess githubAccess;

   @Inject
   PortletPreferences portletPreferences;

   private final HttpClient HTTP_AGENT;

   private final Pattern ACCESS_TOKEN_PATTERN;

   @Inject
   public GithubApplication()
   {
      HTTP_AGENT = new DefaultHttpClient();
      ACCESS_TOKEN_PATTERN = Pattern.compile("access_token=([^&]+?)&");
   }

   @Action
   public Response gotoGithubOAuth(String redirectURI) throws Exception
   {
      StringBuilder b = new StringBuilder();
      b.append(httpContext.getScheme()).append("://");
      b.append(httpContext.getServerName()).append(":").append(httpContext.getServerPort());
      b.append(redirectURI);

      String[] scopeParam = actionContext.getParameters().get("scope");
      String ghURL = "https://github.com/login/oauth/authorize";
      ghURL += "?client_id=" + portletPreferences.getValue("clientID", "55e31ea2fc7cdea37c00");
      if(scopeParam != null)
      {
         ghURL +="&scope=" + scopeParam[0];
      }
      ghURL += "&redirect_uri=" + URLEncoder.encode(b.toString(), "UTF-8");

      return new Response.Redirect(ghURL);
   }

   @View
   public void showGithubResource() throws Exception
   {
      HttpPost postReq = new HttpPost("https://github.com/login/oauth/access_token");
      List<NameValuePair> params = new ArrayList<NameValuePair>();
      params.add(new BasicNameValuePair("client_id", portletPreferences.getValue("clientID", "55e31ea2fc7cdea37c00")));
      params.add(new BasicNameValuePair("code", renderContext.getParameters().get("code")[0]));
      params.add(new BasicNameValuePair("client_secret", portletPreferences.getValue("clientSecret", "e272406acc57d4d1326460ab63cd4d72693e7686")));
      postReq.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

      String accessToken = HTTP_AGENT.execute(postReq, new ResponseHandler<String>()
      {
         @Override
         public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException
         {
            HttpEntity entity = response.getEntity();
            String token = null;
            if(entity != null)
            {
               Matcher matcher = ACCESS_TOKEN_PATTERN.matcher(EntityUtils.toString(entity));
               if(matcher.find())
               {
                  token = matcher.group(1);
               }
            }
            return token;
         }
      });

      githubAccess.setAccessToken(accessToken);
      githubResource.showResources();
   }

   @View
   public void index()
   {
      if(githubAccess.hasAccessToken())
      {
         githubResource.showResources();
      }
      else
      {
         index.with().callbackURI(GithubApplication_.showGithubResourceURL().escapeXML(true).toString()).render();
      }
   }
}
