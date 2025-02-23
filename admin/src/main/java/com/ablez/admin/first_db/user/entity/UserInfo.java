package com.ablez.admin.first_db.user.entity;

import com.ablez.admin.first_db.security.entity.Authority;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_id")
    private Long userInfoId;
    @Column(name = "code", nullable = false)
    private String code;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Authority> authorities = new HashSet<>();

    @OneToOne(mappedBy = "userInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter
    private UserEp00 userEp00;
    @OneToOne(mappedBy = "userInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter
    private UserEp01 userEp01;
    @OneToOne(mappedBy = "userInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter
    private UserEp02 userEp02;
    @OneToOne(mappedBy = "userInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter
    private UserEp03 userEp03;

    public UserInfo(String code) {
        this.code = code;
    }

    public List<String> getRoles() {
        return authorities.stream().map(Authority::getRole).collect(Collectors.toList());
    }

    public void addRole(Authority authority) {
        this.authorities.add(authority);
    }
}
