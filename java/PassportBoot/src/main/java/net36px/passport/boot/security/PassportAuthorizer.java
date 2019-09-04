package net36px.passport.boot.security;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.PrincipalCollection;

public class PassportAuthorizer implements Authorizer {

	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPermitted(PrincipalCollection subjectPrincipal, Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] isPermitted(PrincipalCollection subjectPrincipal, String... permissions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean[] isPermitted(PrincipalCollection subjectPrincipal, List<Permission> permissions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermittedAll(PrincipalCollection subjectPrincipal, String... permissions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPermittedAll(PrincipalCollection subjectPrincipal, Collection<Permission> permissions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkPermission(PrincipalCollection subjectPrincipal, String permission) throws AuthorizationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkPermission(PrincipalCollection subjectPrincipal, Permission permission)
			throws AuthorizationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkPermissions(PrincipalCollection subjectPrincipal, String... permissions)
			throws AuthorizationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkPermissions(PrincipalCollection subjectPrincipal, Collection<Permission> permissions)
			throws AuthorizationException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasRole(PrincipalCollection subjectPrincipal, String roleIdentifier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] hasRoles(PrincipalCollection subjectPrincipal, List<String> roleIdentifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAllRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkRole(PrincipalCollection subjectPrincipal, String roleIdentifier) throws AuthorizationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers)
			throws AuthorizationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkRoles(PrincipalCollection subjectPrincipal, String... roleIdentifiers)
			throws AuthorizationException {
		// TODO Auto-generated method stub

	}

}
