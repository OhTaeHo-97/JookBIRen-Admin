package com.ablez.admin.user.entity;

import com.ablez.admin.security.entity.AuthorityEp02;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@Getter
@Entity
public class UserEp02 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String code;
    @Setter
    @ColumnDefault("0")
    private Integer score = 0;
    @Setter
    @ColumnDefault("0")
    private Integer answerStatusCode = 0;
    @Setter
    @ColumnDefault("0")
    private Integer answerCount = 0;
    @Setter
    @ColumnDefault("0")
    private Integer solvedQuizCount = 0;
    @Setter
    @ColumnDefault("0")
    private Integer criminal1 = 0;
    @Setter
    @ColumnDefault("0")
    private Integer criminal2 = 0;
    @Setter
    private String accessToken;
    @Setter
    private LocalDateTime answerTime;
    private LocalDateTime firstLoginTime;

    @OneToOne
    @JoinColumn(name = "user_info_id")
    @Setter
    private UserInfoEp02 userInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AuthorityEp02> authorities = new HashSet<>();
}
