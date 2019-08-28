package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.po.*;
import com.example.demo.exception.BizException;
import com.example.demo.service.UnionService;
import com.example.demo.service.WorkshopService;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/jpa")
public class JpaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UnionService unionService;

    @Autowired
    private WorkshopService workshopService;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @RequestMapping(value = "/mongo/create", method = RequestMethod.POST)
    public Union mongoCreate(@RequestParam(name = "name") String name, @RequestBody(required = false) JSONObject body) {
        if (body == null) {
            throw BizException.builder().msg("empty body").build();
        }

        Union union = new Union();
        union.setName(name);

        List<User> users = new ArrayList<>();
        JSONArray userJSONArray = body.getJSONArray("users");
        for (int i = 0; i < userJSONArray.size(); i++) {
            JSONObject userJSON = userJSONArray.getJSONObject(i);
            User user = new User();
            user.setName(userJSON.getString("name"));
            user.setAge(userJSON.getInteger("age"));
            users.add(user);
        }

        union = unionService.createUnion(union, users);
        return union;
    }

    @RequestMapping(value = "/mongo/query", method = RequestMethod.GET)
    public Page<User> mongoQuery(@RequestParam(name = "page") Integer pageNo, @RequestParam(name = "per_page") Integer pageSize) {
        return unionService.queryUsers(pageNo, pageSize);
    }

    @RequestMapping(value = "/mysql/create", method = RequestMethod.POST)
    public Workshop mysqlCreate(@RequestParam(name = "name") String name, @RequestBody(required = false) JSONObject body) {
        if (body == null) {
            throw BizException.builder().msg("empty body").build();
        }

        Workshop workshop = new Workshop();
        workshop.setName(name);

        List<Worker> workers = new ArrayList<>();
        JSONArray workerJSONArray = body.getJSONArray("workers");
        for (int i = 0; i < workerJSONArray.size(); i++) {
            JSONObject workerJSON = workerJSONArray.getJSONObject(i);
            Worker worker = new Worker();
            worker.setName(workerJSON.getString("name"));
            worker.setAge(workerJSON.getInteger("age"));
            workers.add(worker);
        }

        workshop = workshopService.createWorkshop(workshop, workers);
        return workshop;
    }

    @RequestMapping(value = "/mysql/query", method = RequestMethod.GET)
    public Page<Worker> mysqlQuery(@RequestParam(name = "page") Integer pageNo, @RequestParam(name = "per_page") Integer pageSize) {
        return workshopService.queryWorkers(pageNo, pageSize);
    }

    @RequestMapping(value = "/mysql/raw", method = RequestMethod.GET)
    public QueryResults mysqlRaw() {
        QWorker qWorker = QWorker.worker;
        QWorkshop qWorkshop = QWorkshop.workshop;
        JPAQuery jpaQuery = jpaQueryFactory.select(qWorker, qWorkshop.name).from(qWorker, qWorkshop).where(qWorker.workshop.eq(qWorkshop.id));
        QueryResults queryResults = jpaQuery.fetchResults();
        return queryResults;
    }
}
