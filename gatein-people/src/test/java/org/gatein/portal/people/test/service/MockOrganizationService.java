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
package org.gatein.portal.people.test.service;

import java.util.ArrayList;
import java.util.Collection;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.services.organization.MembershipType;
import org.exoplatform.services.organization.MembershipTypeHandler;
import org.exoplatform.services.organization.Query;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.impl.MembershipImpl;
import org.exoplatform.services.organization.impl.MembershipTypeImpl;
import org.exoplatform.services.organization.impl.mock.DummyOrganizationService;
import org.exoplatform.services.organization.impl.mock.LazyListImpl;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Nguyen Thanh Hai</a>
 * @version $Id$
 *
 */
public class MockOrganizationService extends DummyOrganizationService {

	public MockOrganizationService() 
	{
		this.userDAO_ = new MockUserHandler();
		this.groupDAO_ = new GroupHandlerImpl();
		this.membershipDAO_ = new MockMembershipHandler();
		this.membershipTypeDAO_ = new MockMembershipTypeHandler();
	}
	
	public static class MockUserHandler extends DummyOrganizationService.UserHandlerImpl 
	{
		@Override
		public ListAccess<User> findUsersByQuery(Query query) throws Exception
      {

			ListAccess<User> users = super.findUsersByQuery(query);
			if(query.getUserName() != null) 
			{
				String userName = query.getUserName().substring(1, query.getUserName().length() -1);
				User user = findUserByName(userName);
				users = new LazyListImpl();
				((LazyListImpl) users).add(user);
			}
         return users;
      }
	}
	
	public static class MockMembershipHandler extends DummyOrganizationService.MembershipHandlerImpl 
	{
		@Override
		public Collection findMembershipsByUser(String userName) throws Exception {
			Collection<MembershipImpl>  memberships = super.findMembershipsByUser(userName);
			for(MembershipImpl membership : memberships) 
			{
				String _userName = membership.getUserName();
				String groupId = membership.getGroupId();
				String membershipType = membership.getMembershipType();
				membership.setId(_userName + ":" + groupId + ":" + membershipType);
			}
			
			return memberships;
		}
		
		@Override
		public Collection findMembershipsByUserAndGroup(String userName, String groupId) throws Exception
      {
			Collection<MembershipImpl> memberships = findMembershipsByUser(userName);
			Collection<MembershipImpl> holder = new ArrayList<MembershipImpl>();
			for(MembershipImpl membership : memberships) 
			{
				if(membership.getGroupId().equals(groupId)) 
				{
					holder.add(membership);
				}
			}
         return holder;
      }
	}
	
	public static class MockMembershipTypeHandler implements MembershipTypeHandler
	{

      public MembershipType createMembershipTypeInstance()
      {
	      return null;
      }

      public MembershipType createMembershipType(MembershipType mt, boolean broadcast) throws Exception
      {
	      return null;
      }

      public MembershipType saveMembershipType(MembershipType mt, boolean broadcast) throws Exception
      {
	      return null;
      }

      public MembershipType removeMembershipType(String name, boolean broadcast) throws Exception
      {
	      return null;
      }

      public MembershipType findMembershipType(String name) throws Exception
      {
	      return null;
      }

      public Collection findMembershipTypes() throws Exception
      {
      	Collection types = new ArrayList();
      	for(String s : new String[] { "*",  "user", "member", "admin", "validator" })
      	{
      		MembershipTypeImpl type = new MembershipTypeImpl();
      		type.setName(s	);
      		types.add(type);
      	}
	      return types;
      }
	}
}

