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
package org.gatein.portal.people.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import juzu.impl.common.Builder;

import org.gatein.portal.people.JSONUtil;
import org.junit.Test;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Nguyen Thanh Hai</a>
 * @version $Id$
 *
 */
public class JSONUtilTestCase
{

	@Test
	public void test() throws Exception 
	{
		String expected = "{\"foo\":\"bar\"}";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("foo", "bar");
		StringBuilder sb = new StringBuilder();
		JSONUtil.createJSON(sb, map);
		Assert.assertEquals(expected, sb.toString());
		
		//
		expected = "{\"foo\":\"\"}";
		map.clear();
		map.put("foo", null);
		sb.setLength(0);
		JSONUtil.createJSON(sb, map);
		Assert.assertEquals(expected, sb.toString());
		
		//
		expected = "{\"foo\":[\"bar\",\"juu\"]}"; 
		map.clear();
		map.put("foo", new String[] { "bar", "juu" });
		sb.setLength(0);
		JSONUtil.createJSON(sb, map);
		Assert.assertEquals(expected, sb.toString());
		
		//
		expected = "{\"foo\":{\"juu\":\"doo\",\"bar\":\"doo\"},\"bar\":\"juu\"}";
		map.clear();
		map.put("foo", Builder.map("juu", "doo").map("bar", "doo").build());
		map.put("bar", "juu");
		sb.setLength(0);
		JSONUtil.createJSON(sb, map);
		Assert.assertEquals(expected, sb.toString());
	}
}
