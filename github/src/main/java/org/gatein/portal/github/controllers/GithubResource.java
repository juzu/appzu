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
import juzu.Path;
import juzu.Response;
import juzu.View;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.PagedIterable;
import org.kohsuke.github.PagedIterator;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/4/12
 */
public class GithubResource
{

   @Inject
   @Path("github/repositories.gtmpl")
   org.gatein.portal.github.templates.github.repositories repositories;

   @Inject
   @Path("github/commits.gtmpl")
   org.gatein.portal.github.templates.github.commits commits;

   @Inject
   @Path("github/network.gtmpl")
   org.gatein.portal.github.templates.github.network network;

   @Inject
   GithubAccess githubAccess;

   @Action
   public Response leave()
   {
      githubAccess.setAccessToken(null);
      return GithubApplication_.index();
   }

   @View
   public void showCommits(String repository) throws Exception
   {
      GHRepository ghRepo = githubAccess.getGithubMyself().getRepositories().get(repository);
      PagedIterator<GHCommit> iterator = ghRepo.listCommits().iterator();

      int n = 0;
      List<GHCommit> latestCommits = new LinkedList<GHCommit>();
      while(iterator.hasNext() && n < 10)
      {
         latestCommits.add(iterator.next());
         n++;
      }
      commits.with().repository(repository).latestCommits(latestCommits).render();
   }

   @View
   public void latestActivities()
   {
   }

   @View
   public void showRepositories()
   {
      repositories.with().repositories(githubAccess.getMyselfRepositories()).render();
   }

   @View
   public void forkNetwork(String repository)
   {

   }
}
