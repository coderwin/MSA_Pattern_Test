package com.example.memberservice.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
//redis에 저장할 로그인 사용자의 정보를 시큐리티 내부에서 인식할 수 있도록
//객체로 정의
public class MyUserDetail extends User {
    private UserDto userDto;
    public MyUserDetail(UserDto dto,
                        Collection<? extends GrantedAuthority> authorities) {
        super(dto.getUserId()+"",dto.getPassword(), authorities);
        this.userDto = dto;
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
