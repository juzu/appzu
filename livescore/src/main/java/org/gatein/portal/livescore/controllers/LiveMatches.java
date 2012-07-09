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
package org.gatein.portal.livescore.controllers;

import juzu.Action;
import juzu.Controller;
import juzu.Path;
import juzu.Response;
import juzu.View;
import org.gatein.portal.livescore.models.Match;
import javax.inject.Inject;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/6/12
 */
public class LiveMatches extends Controller
{
   @Inject
   @Path("livematches.gtmpl")
   org.gatein.portal.livescore.templates.livematches livematches;

   @Inject
   @Path("livebet.gtmpl")
   org.gatein.portal.livescore.templates.livebet livebet;

   @Inject
   public LiveMatches()
   {
   }

   @Action
   public Response sendBetOrder()
   {
      return LiveMatches_.showLiveMatches();
   }

   @View
   public void showLiveMatches()
   {
      StringBuilder b = new StringBuilder();
      b.append(httpContext.getScheme()).append("://");
      b.append(httpContext.getServerName()).append(":");
      b.append(httpContext.getServerPort());
      b.append("/livescore/livescore");

      livematches.with().matches(Match.PREMIER_LEAGUE_MATCHES).livescoreURL(b.toString()).render();
   }

   @View
   public void index()
   {
      showLiveMatches();
   }

   @View
   public void showLiveBet(String matchID)
   {
      livebet.with().match(Match.PREMIER_LEAGUE_MATCHES.get(0)).render();
   }
}
