package edu.miu.attendance.config;

import edu.miu.attendance.domain.Person;
import edu.miu.attendance.domain.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class JPAPersonDetails implements UserDetails {
    String firstName;
    String lastName;
    String email;
    String password;
    Set<Role> roles;
    String username;

    public JPAPersonDetails(Person person) {
        this.username = person.getUsername();
        this.email    = person.getEmail();
        this.password = person.getPassword();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.roles    = person.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> (GrantedAuthority) role::getRole).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}