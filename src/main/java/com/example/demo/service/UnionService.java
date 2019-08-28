package com.example.demo.service;


import com.example.demo.domain.po.Union;
import com.example.demo.domain.po.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnionService {
    Union createUnion(Union union, List<User> users);
    Page<User> queryUsers(Integer pageNo, Integer pageSize);
}
