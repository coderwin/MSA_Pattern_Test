package com.example.memberservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="myuser")
public class MyUser {
    @JsonIgnore
    @Id
    @GeneratedValue
    private Long userId;
    private String name;
    @JsonIgnore
    private String password;
    private String nickname;
    @JsonIgnore
    private boolean checkVal;
    private int point;
    //jpa의 기능을 테스트하기 위해 사용
    //1:다 다대1로 작업
    @ManyToMany
    @JoinTable(
            name="user_authority",
            joinColumns = {@JoinColumn(name="userId",
                    referencedColumnName = "userId" )},
            inverseJoinColumns =
                    {@JoinColumn(name="authorityId",
                            referencedColumnName ="authorityId" )}
    )
    private Set<Authority> authoritylist;
}
