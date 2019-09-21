package br.com.guedes.jwt.security;

import br.com.guedes.jwt.entities.enums.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {
    private static final long serialVersionUID = 1l;

    private String id;
    private String email;
    private String password;
  //  private Collection<? extends GrantedAuthority> authorities;
    private Set<GrantedAuthority> authorities = new HashSet<>();

    public UserSS() {}

    public UserSS(String id, String email, String password, Set<Profile> profiles) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        for (GrantedAuthority role : getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            // this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
        }
    }
    public String getId() {
        return id;
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
        return email;
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

    public boolean hasRole(Profile profiles) {
        return getAuthorities().contains(new SimpleGrantedAuthority(profiles.getDescription()));
    }
}