package com.xiaokaceng.openci.application.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xiaokaceng.openci.AbstractIntegrationTest;
import com.xiaokaceng.openci.application.RoleApplication;
import com.xiaokaceng.openci.domain.Role;

public class RoleApplicationImplTest extends AbstractIntegrationTest {

	private static final String NAME = "test";
	
	@Inject
	private RoleApplication roleApplication;
	
	private Role role;
	
	@Test
	public void testRole() {
		roleApplication.createRole(role);
		assertEquals(role, Role.get(Role.class, role.getId()));
		role.remove();
	}
	
	@Test
	public void updateRole() {
		roleApplication.createRole(role);
		role.setName("abc");
		roleApplication.updateRole(role);
		assertEquals("abc", Role.get(Role.class, role.getId()).getName());
		role.remove();
	}
	
	@Test
	public void testAbolishRole() {
		role.save();
		roleApplication.abolishRole(role);
		assertNull(Role.get(Role.class, role.getId()));
		role.remove();
	}
	
	@Test
	public void testPagingQueryRole() {
		role.save();
		Role role2 = new Role(NAME, NAME);
		role2.save();
		
		List<Role> roles = roleApplication.pagingQeuryRoles(1, 2).getResult();
		assertEquals(2, roles.size());
		role.remove();
		role2.remove();
	}
	
	@Before
	public void init() {
		role = new Role(NAME, NAME);
	}
	
	@After
	public void destory() {
		role = null;
	}
}
