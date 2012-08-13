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
package org.gatein.portal.livescore;

import org.gatein.portal.livescore.models.PremierLeague;
import org.gatein.portal.livescore.service.DataListener;
import org.gatein.portal.livescore.service.LiveData;
import org.gatein.portal.livescore.service.LiveDataProvider;
import org.gatein.portal.livescore.service.LivescoreData;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/6/12
 */
@WebServlet(
   name = "livescore",
   asyncSupported = true,
   urlPatterns = {"/livescore"}
)
public class LivescoreServlet extends HttpServlet implements DataListener
{

   private final Queue<AsyncContext> contexts = new ConcurrentLinkedQueue<AsyncContext>();

   private final Executor ctxExecutor = Executors.newFixedThreadPool(5);

   private volatile String liveScore = null;

   @Override
   public void init(ServletConfig config) throws ServletException
   {
      super.init(config);
      LiveDataProvider.getInstance().addListener(this);
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      String tmp = liveScore;
      if (tmp == null)
      {
         AsyncContext ctx = req.startAsync(req, resp);
         contexts.add(ctx);
      }
   }

   @Override
   public void onReceive(LiveData data)
   {
      if(data instanceof LivescoreData)
      {
         receiveScore((LivescoreData)data);
      }
   }

   private void receiveScore(LivescoreData score)
   {
      final String jsonData = score.toJSON();
      liveScore = jsonData;

      try
      {
         AsyncContext ctx = contexts.poll();
         while (ctx != null)
         {
            final AsyncContext localCtx = ctx;
            ctx = contexts.poll();
            ctxExecutor.execute(new Runnable()
            {
               @Override
               public void run()
               {
                  try
                  {
                     ServletResponse resp = localCtx.getResponse();
                     resp.setContentType("text/json");
                     resp.getWriter().append(jsonData);
                     resp.getWriter().flush();
                     localCtx.complete();
                  }
                  catch (Exception ex)
                  {
                     //Catch the exception like this to make sure that no new thread need to be recreated by executor.
                  }
               }
            });
         }
      }
      finally
      {
         liveScore = null;

         for(Map.Entry<String, String> matchScore : score.getMatchScores().entrySet())
         {
            PremierLeague.PREMIER_LEAGUE_MATCHES.get(matchScore.getKey()).setScore(matchScore.getValue());
         }
      }
   }
}
