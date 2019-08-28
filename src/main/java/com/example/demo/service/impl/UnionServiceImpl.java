package com.example.demo.service.impl;

import com.example.demo.domain.po.Hobby;
import com.example.demo.domain.po.QUser;
import com.example.demo.domain.po.Union;
import com.example.demo.domain.po.User;
import com.example.demo.repository.mongo.UnionRepository;
import com.example.demo.repository.mongo.UserRepository;
import com.example.demo.service.UnionService;
import com.querydsl.core.types.Predicate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UnionServiceImpl implements UnionService {

    @Autowired
    private UnionRepository unionRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional(value = "mongoTransactionManager")
    public Union createUnion(Union union, List<User> users) {
        unionRepository.save(union);

        for (User user : users) {
            List<Hobby> hobbies = new ArrayList<>();

            Hobby hobby1 = new Hobby();
            hobby1.setName(UUID.randomUUID().toString());
            hobbies.add(hobby1);

            Hobby hobby2 = new Hobby();
            hobby2.setName(UUID.randomUUID().toString());
            hobbies.add(hobby2);

            user.setHobbies(hobbies);
            user.setUnion(union.getId());
        }
        userRepository.saveAll(users);

        List<ObjectId> userIds = new ArrayList<>();
        for (User user : users) {
            userIds.add(user.getId());
        }

        union.setUsers(userIds);
        unionRepository.save(union);

        //if (true) {
        //    throw new RuntimeException("rollback");
        //}

        return union;
    }

    @Override
    public Page<User> queryUsers(Integer pageNo, Integer pageSize) {
        QUser qUser = QUser.user;
        Predicate predicate = qUser.age.gt(30);

        Sort sort = new Sort(Sort.Direction.ASC, "age");
        PageRequest pageRequest = PageRequest.of(pageNo-1, pageSize, sort);

        Page<User> userPage = (Page<User>) userRepository.findAll(predicate, pageRequest);
        return userPage;
    }
}
