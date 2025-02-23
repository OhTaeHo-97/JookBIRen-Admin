package com.ablez.admin.second_db.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_ep00")
public class UserEp00 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "code")
    private String code;
    @Column(name = "first_id")
    private String firstId;
    @Column(name = "second_id")
    private String secondId;
    @Column(name = "is_banned")
    private boolean isBanned;
    @Column(name = "custom")
    private int custom;

    public UserEp00(String code) {
        this.code = code;
    }
}
