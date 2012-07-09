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
package org.gatein.portal.livescore.models;

import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/6/12
 */
public class Match
{
   String id;

   String score, startDate, endDate;

   Team home, away;

   public Match(String id, Team home, Team away)
   {
      this.id = id;
      this.home = home;
      this.away = away;
      this.score = "0 - 0";
   }

   public final static List<Match> PREMIER_LEAGUE_MATCHES = new LinkedList<Match>();

   static
   {
      int n = Team.PREMIER_LEAGUE.size();
      for(int i = 0; i< n/2; i++)
      {
         PREMIER_LEAGUE_MATCHES.add(new Match("match-" + i, Team.PREMIER_LEAGUE.get(i), Team.PREMIER_LEAGUE.get(n -1 -i)));
      }

   }
}
