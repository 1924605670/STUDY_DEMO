package com.study.admin.controller;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cache")
@CacheConfig(cacheNames = {"cacheManager"})
public class CacheController {
    private boolean flag = true;

    @GetMapping("/cache1")
    @Cacheable(key = "#id")
    public String cache1(String id) {
        if (flag) {
            flag = false;
            return "2";
        }
        return id;
    }

}
