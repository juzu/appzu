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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 8/6/12
 */
public class PremierLeague
{
   public final static List<Team> PREMIER_LEAGUE = new ArrayList<Team>(20);

   static
   {
      PREMIER_LEAGUE.add(new Team("FC Arsenal"));
      PREMIER_LEAGUE.add(new Team("Aston Villa"));
      PREMIER_LEAGUE.add(new Team("FC Chelsea"));
      PREMIER_LEAGUE.add(new Team("Everton"));
      PREMIER_LEAGUE.add(new Team("FC Fulham"));
      PREMIER_LEAGUE.add(new Team("FC Liverpool"));
      PREMIER_LEAGUE.add(new Team("Manchester City"));
      PREMIER_LEAGUE.add(new Team("Manchester United"));
      PREMIER_LEAGUE.add(new Team("Newcastle United"));
      PREMIER_LEAGUE.add(new Team("Norwich City"));
      PREMIER_LEAGUE.add(new Team("Queens Park Rangers"));
      PREMIER_LEAGUE.add(new Team("Reading"));
      PREMIER_LEAGUE.add(new Team("Southampton"));
      PREMIER_LEAGUE.add(new Team("Stoke City"));
      PREMIER_LEAGUE.add(new Team("Sunderland"));
      PREMIER_LEAGUE.add(new Team("Swansea City"));
      PREMIER_LEAGUE.add(new Team("Tottenham Hotspur"));
      PREMIER_LEAGUE.add(new Team("West Bromwich Albion"));
      PREMIER_LEAGUE.add(new Team("West Ham United"));
      PREMIER_LEAGUE.add(new Team("Wigan Athletic"));
   }

   public final static Map<String, Match> PREMIER_LEAGUE_MATCHES = new HashMap<String, Match>();

   static
   {
      int n = PREMIER_LEAGUE.size();
      for (int i = 0; i < n / 2; i++)
      {
         PREMIER_LEAGUE_MATCHES.put("match-" + i, new Match("match-" + i, PREMIER_LEAGUE.get(i), PREMIER_LEAGUE.get(n - 1 - i)));
      }
   }
}
