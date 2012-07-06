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

import juzu.SessionScoped;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collection;
import javax.inject.Named;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/4/12
 */
@Named("githubAccess")
@SessionScoped
public class GithubAccess implements Serializable
{
   private String accessToken;

   private transient GitHub github;

   private transient GHMyself githubMyself;

   public boolean hasAccessToken()
   {
      return accessToken != null;
   }

   public void setAccessToken(String accessToken)
   {
      this.accessToken = accessToken;
   }

   public GitHub getGithub()
   {
      if(github == null)
      {
         synchronized (this)
         {
            if(github == null)
            {
               try
               {
                  github = GitHub.connectUsingOAuth(accessToken);
               }
               catch (IOException ioEx)
               {
                  throw new UndeclaredThrowableException(ioEx);
               }
            }
         }
      }
      return github;
   }

   public GHMyself getGithubMyself()
   {
      if (githubMyself == null)
      {
         try
         {
            githubMyself = getGithub().getMyself();
         }
         catch (IOException ioEx)
         {
            throw new UndeclaredThrowableException(ioEx);
         }
      }
      return githubMyself;
   }

   public String getGithubLogin()
   {
      return getGithubMyself().getLogin();
   }

   public String getAvatarURL()
   {
      return getGithubMyself().getAvatarUrl();
   }

   public Collection<GHRepository> getMyselfRepositories()
   {
      try
      {
         return getGithubMyself().getRepositories().values();
      }
      catch (IOException ioEx)
      {
         throw new UndeclaredThrowableException(ioEx);
      }
   }
}
