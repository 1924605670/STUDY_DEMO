package com.utils.demo.controller;


import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.DaoTemplate;
import cn.hutool.db.Entity;
import com.utils.demo.vo.Pass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/")
public class SqlController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/downPic")
    public long downPic(String sql) throws SQLException {
        List<Pass> passes = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Pass.class));
        passes.forEach(x -> {
            try {
               ImgUtil.write(ImgUtil.read(new URL(x.getTpurl())),new File("d://pic//"+x.getHphm()+ StrUtil.UNDERLINE+ RandomUtil.randomNumbers(5)+".jpg"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });

        return passes.size();
    }

}
