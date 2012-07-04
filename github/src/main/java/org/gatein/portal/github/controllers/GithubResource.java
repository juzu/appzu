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

import juzu.Path;
import juzu.View;
import javax.inject.Inject;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/4/12
 */
public class GithubResource
{

   @Inject
   @Path("github/index.gtmpl")
   org.gatein.portal.github.templates.github.index index;

   @Inject
   GithubAccess githubAccess;

   @View
   public void showResources()
   {
      index.with().repositories(githubAccess.getMyselfRepositories()).render();
   }
}
