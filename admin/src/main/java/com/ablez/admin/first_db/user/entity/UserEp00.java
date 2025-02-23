package com.ablez.admin.first_db.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_ep00", indexes = {
        @Index(name = "idx_user_info_id", columnList = "user_info_id")
})
public class UserEp00 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Setter
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Setter
    @Column(name = "first_id")
    private String firstId;
    @Setter
    @Column(name = "second_id")
    private String secondId;
    @Setter
    @Column(name = "is_banned")
    private Boolean isBanned = false;
    @Setter
    @ColumnDefault("0")
    @Column(name = "custom")
    private Integer custom = 0;

    @OneToOne
    @JoinColumn(name = "user_info_id")
    @Setter
    private UserInfo userInfo;

    public UserEp00(String code) {
        this.code = code;
    }

    public UserEp00(String code, UserInfo userInfo) {
        this.code = code;
        this.userInfo = userInfo;
        userInfo.setUserEp00(this);
    }
}
