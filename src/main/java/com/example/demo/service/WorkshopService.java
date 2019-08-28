package com.example.demo.service;


import com.example.demo.domain.po.Worker;
import com.example.demo.domain.po.Workshop;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkshopService {
    Workshop createWorkshop(Workshop workshop, List<Worker> workers);
    Page<Worker> queryWorkers(Integer pageNo, Integer pageSize);
}
