package com.qbao.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author by zhangchanghong on 15/12/6.
 */
public class CasUser implements UserDetails {
    private long userId; // 用户ID
    private String userName;    // 用户名
    private String nickName;
    private String mobile;  // 手机号
    private boolean enabled;    // 是否冻结
    private String email;   // 邮箱
    private List<GrantedAuthority> authorities;
    private Long createTime;

    public CasUser() {
    }

    public CasUser(long userId, String userName, String mobile, boolean enabled, String email, Long createTime,
                   List<GrantedAuthority> authorities) {
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
        this.enabled = enabled;
        this.email = email;
        this.createTime = createTime;
        this.authorities = authorities;
    }

    public CasUser(long userId, String userName, String nickName, String mobile, boolean enabled, String email, Long createTime, List<GrantedAuthority> authorities) {
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.mobile = mobile;
        this.enabled = enabled;
        this.email = email;
        this.createTime = createTime;
        this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return userName;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
