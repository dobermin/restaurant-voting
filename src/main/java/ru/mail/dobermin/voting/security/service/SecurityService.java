package ru.mail.dobermin.voting.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.mail.dobermin.voting.security.enums.Role;

import java.util.Collection;

@Service
public class SecurityService {

    public boolean hasRole(Role role) {
        Collection<? extends GrantedAuthority> grantedAuthorities =
                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities();
        for (GrantedAuthority g : grantedAuthorities)
            if (g.getAuthority().equals(role.name()))
                return true;
        return false;
    }
}
