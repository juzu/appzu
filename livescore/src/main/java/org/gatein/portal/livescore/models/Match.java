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

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/6/12
 */
public class Match
{
   String id;

   volatile String score;

   String startDate, endDate;

   Team home, away;

   public Match(String id, Team home, Team away)
   {
      this.id = id;
      this.home = home;
      this.away = away;
      this.score = "0-0";
   }

   public void setScore(String score)
   {
      this.score = score;
   }

   public String getScore()
   {
      return score;
   }

   public Team getHome()
   {
      return home;
   }

   public Team getAway()
   {
      return away;
   }

}
