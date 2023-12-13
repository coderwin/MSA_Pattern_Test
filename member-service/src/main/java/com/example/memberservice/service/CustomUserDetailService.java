package com.example.memberservice.service;


import com.example.memberservice.dto.MyUserDetail;
import com.example.memberservice.dto.UserDto;
import com.example.memberservice.entity.MyUser;
import com.example.memberservice.redis.UserCacheRepository;
import com.example.memberservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
//스프링시큐리티의 인증 안에서 자동으로 호출되는 메소드가 정의된 객체
//꼭 정해진 형식에 맞게 만들어야 한다.

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository; //DB에서 조회하기
    private final UserCacheRepository userCacheRepository;//redis에서 조회하기
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Long userId = Long.parseLong(id);
        //---------------------------------------------------------------------
        //1. redis에서 사용자정보를 조회
        UserDto cacheuser = userCacheRepository.getUser(id);
        if(cacheuser==null){
            //2. redis에 저장된 정보가 없으면 디비에서 조회하기
            return createUser(userId,userRepository.findById(userId).get());

        }
        //MyUserDetail을 만들기 위해서 권한정보를 추출
        List<GrantedAuthority> authorities = cacheuser.getAuthoritylist().stream()
                .map(authority ->
                        new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        //MyUserDetail을 만들어서 리턴
        return new MyUserDetail(cacheuser,authorities);
    }

    //인증성공후 Authentication객체에 저장할 User객체가 필요 - User객체를 만들어서 리턴
    //loadUserByUsername메소드의 리턴결과로 호출
    //지금은 메소드 내부에서만 사용되므로
    private User createUser(Long id, MyUser user){
        //권한
        List<GrantedAuthority> authorities =
                user.getAuthoritylist().stream()
                    .map(authority -> new SimpleGrantedAuthority(
                            authority.getAuthorityName()))
                        .collect(Collectors.toList());

        //조회된 엔티티객체를 UserDTO로 변환 - redis에 저장하기 위해
        ModelMapper mapper = new ModelMapper();
        UserDto userDto = mapper.map(user,UserDto.class);
        //UserDTO와 권한정보를 이용해서 MyUserDTO를 생성
        // - 스프링시큐리티의 인증시스템에서 자동으로 인식되도록 하기 위함

        return new MyUserDetail(userDto,authorities);
    }
}
