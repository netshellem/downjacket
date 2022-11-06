package com.samplemgt.downjacket.Utility;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class Context {

    public static boolean isAdmin() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
        boolean isAdmin = false;
        for (GrantedAuthority grantedAuthority : authorities){
            if (grantedAuthority.getAuthority().toLowerCase().equals("admin")) {
                isAdmin = true;
                break;
            }
        }

        return isAdmin;
    }
}
