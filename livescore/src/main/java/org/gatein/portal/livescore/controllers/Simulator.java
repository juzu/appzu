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
import juzu.Path;
import juzu.Response;
import juzu.View;
import org.gatein.portal.livescore.models.PremierLeague;
import org.gatein.portal.livescore.service.LiveDataProvider;
import org.gatein.portal.livescore.service.LivescoreData;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/10/12
 */
public class Simulator
{
   @Inject
   @Path("simulator.gtmpl")
   org.gatein.portal.livescore.templates.simulator simulator;

   @Inject
   public Simulator()
   {
   }

   @Action
   public Response updateLivescores(String livescores)
   {
      String[] scores = livescores.split(";");
      Map<String, String> data = new HashMap<String, String>();
      for(String score : scores)
      {
         int i = score.indexOf(":");
         if(i >= 0)
         {
            data.put(score.substring(0, i), score.substring(i + 1));
         }
      }
      LiveDataProvider.getInstance().receiveData(new LivescoreData(data));

      return simulator.render();
   }

   @View
   public void index()
   {
      simulator.with().matches(PremierLeague.PREMIER_LEAGUE_MATCHES.values()).render();
   }
}
