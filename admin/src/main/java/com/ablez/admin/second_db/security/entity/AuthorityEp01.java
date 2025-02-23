package com.ablez.admin.second_db.security.entity;

import com.ablez.admin.second_db.user.entity.UserEp01;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
@Table(name = "authority_ep01", indexes = {
        @Index(name = "FKiqg2q1268x5c4gm6yltd3luhw", columnList = "user_id")
})
public class AuthorityEp01 implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long authorityId;
    @Column(name = "role")
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEp01 user;

    public AuthorityEp01(String role, UserEp01 user) {
        this.role = role;
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
