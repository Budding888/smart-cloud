package com.smart.auth.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author LErry.li
 * Description:
 * <p>
 * Date: 2019/7/25 20:31
 */
public class MobileAuthenticationToken extends AbstractAuthenticationToken{

    private transient Object principal;

    private transient Object credentials;

    /**
     * 登录成功调用
     * @param principal
     * @param credentials
     * @param authorities
     */
    public MobileAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    /**
     * 还没登录调用
     * @param mobile
     * @param credentials
     */
    public MobileAuthenticationToken(Object mobile, Object credentials) {
        super(null);
        this.principal = mobile;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * The credentials that prove the principal is correct. This is usually a password,
     * but could be anything relevant to the <code>AuthenticationManager</code>. Callers
     * are expected to populate the credentials.
     *
     * @return the credentials that prove the identity of the <code>Principal</code>
     */
    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    /**
     * The identity of the principal being authenticated. In the case of an authentication
     * request with username and password, this would be the username. Callers are
     * expected to populate the principal for an authentication request.
     * <p>
     * The <tt>AuthenticationManager</tt> implementation will often return an
     * <tt>Authentication</tt> containing richer information as the principal for use by
     * the application. Many of the authentication providers will create a
     * {@code UserDetails} object as the principal.
     *
     * @return the <code>Principal</code> being authenticated or the authenticated
     * principal after authentication.
     */
    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
