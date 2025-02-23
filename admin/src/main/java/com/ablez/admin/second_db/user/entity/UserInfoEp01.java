package com.ablez.admin.second_db.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_info_ep01")
public class UserInfoEp01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_id")
    private Long userInfoId;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Column(name = "phone")
    private String phone;

    @OneToOne(mappedBy = "userInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter
    private UserEp01 user;

    public UserInfoEp01(String code, String phone) {
        this.code = code;
        this.phone = phone;
    }
}
