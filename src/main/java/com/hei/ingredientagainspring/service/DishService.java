package com.hei.ingredientagainspring.service;


import com.hei.ingredientagainspring.entity.Dish;
import com.hei.ingredientagainspring.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }
}
