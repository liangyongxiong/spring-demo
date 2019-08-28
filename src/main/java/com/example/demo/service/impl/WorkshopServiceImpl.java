package com.example.demo.service.impl;

import com.example.demo.domain.po.QWorker;
import com.example.demo.domain.po.Worker;
import com.example.demo.domain.po.Workshop;
import com.example.demo.repository.mysql.WorkerRepository;
import com.example.demo.repository.mysql.WorkshopRepository;
import com.example.demo.service.WorkshopService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkshopServiceImpl implements WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    @Transactional(value = "mysqlTransactionManager")
    public Workshop createWorkshop(Workshop workshop, List<Worker> workers) {
        workshopRepository.save(workshop);

        for (Worker worker : workers) {
            worker.setWorkshop(workshop.getId());
        }
        workerRepository.saveAll(workers);

        List<Long> workerIds = new ArrayList<>();
        for (Worker worker : workers) {
            workerIds.add(worker.getId());
        }

        workshop.setWorkers(workerIds);
        workshopRepository.save(workshop);

        return workshop;
    }

    @Override
    public Page<Worker> queryWorkers(Integer pageNo, Integer pageSize) {
        QWorker qWorker = QWorker.worker;
        Predicate predicate = qWorker.age.gt(30);

        Sort sort = new Sort(Sort.Direction.ASC, "age");
        PageRequest pageRequest = PageRequest.of(pageNo-1, pageSize, sort);

        Page<Worker> workerPage = (Page<Worker>) workerRepository.findAll(predicate, pageRequest);
        return workerPage;
    }
}
