package com.ablez.admin.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@Getter
@Entity
public class UserEp00 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false, unique = true)
    private String code;
    @Setter
    private String firstId;
    @Setter
    private String secondId;
    @Setter
    private Boolean isBanned = false;
    @Setter
    @ColumnDefault("0")
    private Integer custom = 0;

    public UserEp00(String code) {
        this.code = code;
    }
}
