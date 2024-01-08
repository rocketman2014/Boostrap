//package ru.kata.spring.boot_security.demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import ru.kata.spring.boot_security.demo.model.User;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
//    private final User user;
//    public UserDetails(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return user.getRole().stream().map(role -> new SimpleGrantedAuthority(role.getRole()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//        return this.user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return this.user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//    public User getUser() {
//        return this.user;
//    }
//}