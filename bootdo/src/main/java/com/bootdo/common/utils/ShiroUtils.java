package com.bootdo.common.utils;

import com.bootdo.system.domain.UserToken;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;

import com.bootdo.system.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

public class ShiroUtils {
    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static UserDO getUser() {
        Object object = getSubjct().getPrincipal();
        return (UserDO)object;
    }
    public static Long getUserId() {
        return getUser().getUserId();
    }
    public static void logout() {
        getSubjct().logout();
    }

    public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }
        /**
     * 切换身份，登录后，动态更改subject的用户属性
     * @param userInfo
     */
    public static void setUser(UserDO userInfo) {
    	Subject subject = SecurityUtils.getSubject();
    	PrincipalCollection principalCollection = subject.getPrincipals(); 
    	String realmName = principalCollection.getRealmNames().iterator().next();
    	PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(userInfo, realmName);
    	subject.runAs(newPrincipalCollection);
    }
}
