/*
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
package org.gatein.portal.people;

import java.util.Iterator;
import java.util.Map;

import juzu.io.Streamable;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Nguyen Thanh Hai</a>
 * @version $Id$
 *
 */
public class JSONUtil
{
	private static StringBuilder append(StringBuilder appender, final Map<String, Object> data)
	{
		Iterator<Map.Entry<String, Object>> iterator = data.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			appender.append("\"").append(key).append("\"");
			appender.append(':');
			
			//
			if (value == null)
			{
				appender.append("\"\"");
			}
			else if (value instanceof String)
			{
				appender.append("\"").append(value).append("\"");
			}
			else if (value instanceof Map)
			{
				appender.append('{');
				append(appender, (Map<String, Object>) value);
				appender.append('}');
			}
			else if (value instanceof String[])
			{
				appender.append("[");
				String[] array = (String[]) value;
				if(array.length == 1)
				{
					appender.append("\"").append(array[0]).append("\"");
				}
				else
				{
					for(int i = 0; i < array.length; i++)
					{
						appender.append("\"").append(array[i]).append("\"");
						if(i < (array.length - 1))
						{
							appender.append(',');
						}
					}
				}
				appender.append("]");
			}
			
			if(iterator.hasNext())
			{
				appender.append(',');
			}
		}
		return appender;
	}
	
	public static JSON createJSON(StringBuilder appender, final Map<String, Object> data) 
	{
		appender.append('{');
		append(appender, data);
		appender.append('}');
		JSON json = new JSON(new Streamable.CharSequence(appender.toString()));
		return json;
	}
}
