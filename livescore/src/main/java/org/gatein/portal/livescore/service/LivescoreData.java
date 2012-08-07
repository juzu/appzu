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
package org.gatein.portal.livescore.service;

import java.util.Map;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/7/12
 */
public class LivescoreData implements LiveData
{
   private final Map<String, String> matchScores;

   public LivescoreData(Map<String, String> matchScores)
   {
      if(matchScores == null)
      {
         throw new IllegalArgumentException();
      }
      this.matchScores = matchScores;
   }

   @Override
   public String toJSON()
   {
      int size = matchScores.size();
      int counter = 0;
      StringBuilder b = new StringBuilder("{");
      for(Map.Entry<String, String> matchScore : matchScores.entrySet())
      {
         b.append("\"").append(matchScore.getKey()).append("\"").append(":");
         b.append("\"").append(matchScore.getValue()).append("\"");
         counter++;
         if (counter < size)
         {
            b.append(",");
         }
      }

      b.append("}");

      return b.toString();
   }

   public Map<String, String> getMatchScores()
   {
      return matchScores;
   }
}
