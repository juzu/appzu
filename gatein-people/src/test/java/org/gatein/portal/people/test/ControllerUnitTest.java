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

import java.util.Arrays;

import juzu.impl.common.Cardinality;
import juzu.impl.plugin.application.descriptor.ApplicationDescriptor;
import juzu.impl.plugin.controller.descriptor.MethodDescriptor;
import juzu.impl.plugin.controller.descriptor.ParameterDescriptor;
import juzu.request.Phase;
import juzu.test.AbstractTestCase;
import juzu.test.CompilerAssert;

import org.junit.Test;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Nguyen Thanh Hai</a>
 * @version $Id$
 * 
 */
public class ControllerUnitTest extends AbstractTestCase
{
	@Override
	public void setUp() throws Exception
	{
		CompilerAssert<?, ?> compiler = compiler("org", "gatein", "portal", "people");
		compiler.assertCompile();
		
		aClass = compiler.assertClass("org.gatein.portal.people.Controller");
		compiler.assertClass("org.gatein.portal.people.Controller_");
		
		Class<?> appClass = compiler.assertClass("org.gatein.portal.people.Application");
		descriptor = ApplicationDescriptor.create(appClass);
	}
	
	/** . */
	private Class<?> aClass;
	
	/** . */
	private ApplicationDescriptor descriptor;

	@Test
	public void testFindUser() throws Exception
	{
		 MethodDescriptor cm = descriptor.getControllers().getMethod(aClass, "findUsers", String.class, String.class, String.class);
		 assertEquals("findUsers", cm.getName());
		 assertEquals(Phase.RESOURCE, cm.getPhase());
		 assertEquals(Arrays.asList(
			 new ParameterDescriptor("userName", Cardinality.SINGLE),
			 new ParameterDescriptor("offset", Cardinality.SINGLE),
			 new ParameterDescriptor("limit", Cardinality.SINGLE)), cm.getArguments());
	}
	
	@Test
	public void testFindGroups() throws Exception
	{
		MethodDescriptor cm = descriptor.getControllers().getMethod(aClass, "findGroups", String.class, String[].class);
		assertEquals("findGroups", cm.getName());
		assertEquals(Phase.RESOURCE, cm.getPhase());
		assertEquals(Arrays.asList(
			new ParameterDescriptor("groupName", Cardinality.SINGLE),
			new ParameterDescriptor("userName", Cardinality.ARRAY)), cm.getArguments());
	}
	
	@Test
	public void testGetProfile() throws Exception
	{
		MethodDescriptor cm = descriptor.getControllers().getMethod(aClass, "getProfile", String.class);
		assertEquals("getProfile", cm.getName());
		assertEquals(Phase.RESOURCE, cm.getPhase());
		assertEquals(Arrays.asList(new ParameterDescriptor("userName", Cardinality.SINGLE)), cm.getArguments());
	}
	
	@Test
	public void testSetProfile() throws Exception
	{
		MethodDescriptor cm = descriptor.getControllers().getMethod(aClass, "setProfile", String.class, String.class);
		assertEquals("setProfile", cm.getName());
		assertEquals(Phase.RESOURCE, cm.getPhase());
		assertEquals(Arrays.asList(
			new ParameterDescriptor("userName", Cardinality.SINGLE),
			new ParameterDescriptor("email", Cardinality.SINGLE)), cm.getArguments());
	}
	
	@Test
	public void testRemoveMembership() throws Exception
	{
		MethodDescriptor cm = descriptor.getControllers().getMethod(aClass, "removeMembership", String[].class);
		assertEquals("removeMembership", cm.getName());
		assertEquals(Phase.RESOURCE, cm.getPhase());
		assertEquals(Arrays.asList(new ParameterDescriptor("id", Cardinality.ARRAY)), cm.getArguments());
	}
	
	@Test
	public void testAddMembership() throws Exception
	{
		MethodDescriptor cm = descriptor.getControllers().getMethod(aClass, "addMembership", String.class, String.class, String[].class);
		assertEquals("addMembership", cm.getName());
		assertEquals(Phase.RESOURCE, cm.getPhase());
		assertEquals(Arrays.asList(
			new ParameterDescriptor("type", Cardinality.SINGLE),
			new ParameterDescriptor("groupId", Cardinality.SINGLE),
			new ParameterDescriptor("userName", Cardinality.ARRAY)), cm.getArguments());
	}
}
