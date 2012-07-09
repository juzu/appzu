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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * TODO: Implement a real messaging service
 *
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 7/7/12
 */
public class LiveDataProvider
{
   private final List<DataListener> listeners;

   private final Executor executor;

   private static volatile LiveDataProvider instance;

   LiveDataProvider()
   {
      listeners = Collections.synchronizedList(new ArrayList<DataListener>());
      executor = Executors.newFixedThreadPool(5);
   }

   public static LiveDataProvider getInstance()
   {
      if(instance == null)
      {
         synchronized (LiveDataProvider.class)
         {
            if(instance == null)
            {
               LiveDataProvider tmp = new LiveDataProvider();
               instance = tmp;
            }
         }
      }
      return instance;
   }

   public void addListener(DataListener listener)
   {
      listeners.add(listener);
   }

   public void receiveData(final LiveData data)
   {
      for(final DataListener listener : listeners)
      {
         executor.execute(new Runnable()
         {
            @Override
            public void run()
            {
               listener.onReceive(data);
            }
         });
      }
   }
}
