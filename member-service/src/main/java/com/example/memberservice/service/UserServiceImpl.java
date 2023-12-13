package com.example.memberservice.service;

import com.example.memberservice.dao.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserDAO dao;
    @Override
    public void addPoint(String username,int totalprice) {
        dao.addPoint(username,(int)(totalprice * 0.1));
    }
}
