package hu.ideaimpl.mageofscrum.server.auth;

import hu.ideaimpl.mageofscrum.server.HibernateUtil;
import hu.ideaimpl.mageofscrum.server.dao.UserDAO;
import hu.ideaimpl.mageofscrum.server.dao.UserDAOImpl;
import hu.ideaimpl.mageofscrum.server.entity.Role;
import hu.ideaimpl.mageofscrum.server.entity.User;

import javax.persistence.EntityManager;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MosRealm extends AuthorizingRealm{
	
	public MosRealm() {
		setName("MosRealm");
		HashedCredentialsMatcher cred = new HashedCredentialsMatcher();
		cred.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
		setCredentialsMatcher(cred);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userId = (String) principals.fromRealm(getName()).iterator().next();
		EntityManager em = HibernateUtil.getEntityManager();
//		Session session = HibernateUtil.getSession();
		UserDAO userDBO = new UserDAOImpl();
        User user = userDBO.findUser(userId);
        if( user != null ) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for( Role role : user.getRoles() ) {
                info.addRole(role.getName());
//                info.addStringPermission("");
            }
            return info;
        } else {
            return null;
        }
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) arg;
		EntityManager em = HibernateUtil.getEntityManager();
//		Session session = HibernateUtil.getSession();
		UserDAO userDBO = new UserDAOImpl();
		User user = userDBO.findUser(token.getUsername());
		//TODO a principalok k�z� betenni a userId-t is �gy egyszer�bb az adatb�zis m�veletek
		if(user != null){
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName()); 
		}
		return null;
	}

}
