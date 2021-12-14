package com.thymewizards.util;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.thymewizards.domain.entity.User;

public class ApplicationUserDetails implements UserDetails {

	private static final long serialVersionUID = -5720872984879290483L;

	private final UUID id;
    private final String username;
    private final String displayName;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public ApplicationUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.displayName = user.getUserName();
        this.password = user.getPassword();
        this.authorities = user.getSetOfRoles().stream().map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.name())).collect(Collectors.toSet());
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

    public UUID getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

}
