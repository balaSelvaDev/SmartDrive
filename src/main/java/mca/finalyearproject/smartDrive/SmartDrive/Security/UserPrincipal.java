package mca.finalyearproject.smartDrive.SmartDrive.Security;

import java.util.Collection;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.LoginCredentialEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserListEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private final UserListEntity userListEntity;
    private final LoginCredentialEntity loginCredentialEntity;

    public UserPrincipal(UserListEntity userListEntity, LoginCredentialEntity loginCredentialEntity) {
        this.userListEntity = userListEntity;
        this.loginCredentialEntity  = loginCredentialEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Integer getUserId() {
        return this.userListEntity.getUserId();
    }

    @Override
    public String getPassword() {
        return this.loginCredentialEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userListEntity.getFullName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
