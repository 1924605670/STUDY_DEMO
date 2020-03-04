package com.study.es.controller;

import cn.hutool.core.util.RandomUtil;
import com.study.es.bean.LogBean;
import com.study.es.bean.TestIndex;
import com.study.es.repository.LogEsRepository;
import com.study.es.repository.TestIndexRepository;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/log")
public class LogController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private LogEsRepository logEsRepository;

    @Autowired
    private TestIndexRepository testIndexRepository;

    private final String content = "文章 编辑 讨论97\n" +
            "1984年6月26日陕西省西安市雁塔区中国\n" +
            "文章，1984年6月26日出生于陕西省西安市，中国内地男演员、导演。2006年毕业于中央戏剧学院表演系。\n" +
            "2004年参演电视剧《与青春有关的日子》，开始在影视圈崭露头角 [1]  。2005年拍摄古装剧《锦衣卫》。2007年主演赵宝刚导演的青春剧《奋斗》； [2]  同年，主演首部电影《走着瞧》。2008年主演滕华涛执导的电视剧《蜗居》，饰演80后城市青年小贝。 [1]  [3]  2009年，在电影《海洋天堂》中扮演自闭症患者王大福；同年参演抗战题材的电视剧《雪豹》 [4]  。2011年，主演的电视剧《裸婚时代》在各大卫视播出； [5]  2011年-2012年连续2年获得北京大学生电影节 [6-7]  最受大学生欢迎男演员奖。2012年，凭借电影《失恋33天》获得第31届大众电影百花奖最佳男主角奖； [8]  同年成立自己经营的北京君竹影视文化有限公司，并导演第一部影视作品《小爸爸》。2013年2月，主演的电影《西游·降魔篇》在全国上映。 [9] \n" +
            "2014年3月28日，主演的中韩合资文艺爱情片《我在路上最爱你》在全国上映。2014年12月18日，在姜文执导的动作喜剧片《一步之遥》中扮演武七一角。 [10]  2016年，主演电视剧《少帅》，饰演张学良 [11]  ；主演电视剧《剃刀边缘》 [12]  。7月15日导演的电影《陆垚知马俐》上映。 [13] \n" +
            "演艺事业外，文章也参与公益慈善事业，2010年成立大福自闭症关爱基金。\n" +
            "2017年9月16日，凭借《陆垚知马俐》获得第31届中国电影金鸡奖导演处女作奖 [14]  。\n" +
            "2019年7月28日，文章通过微博宣布，与妻子马伊琍离婚 [15]  。";
    @GetMapping(value = "/test")
    public String testLog() {
        elasticsearchTemplate.createIndex(LogBean.class);
        elasticsearchTemplate.putMapping(LogBean.class);
        return "hello ES";
    }

    @GetMapping(value = "add")
    public String addData(){

        List testIndexList = new ArrayList();

        for (int i = 0; i < 100; i++) {
            testIndexList.add(new TestIndex(Long.valueOf(i),"hello"+i*100,new Date(),RandomUtil.randomDouble()));
//            logEsRepository.save(new LogBean("你好，es"));
        }
        testIndexRepository.saveAll(testIndexList);

        return "add ok";
    }

    @GetMapping(value = "get")
    public String get(){
//        QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder("你好").field("content").defaultOperator(Operator.AND);
//        Iterable<LogBean> search = logEsRepository.search(queryStringQueryBuilder);
//        search.forEach(x ->{
//            System.out.println(x.getContent());
//        });
//        Optional<LogBean> byId = logEsRepository.findById("4a5e0d07-b0b2-43ec-9fb6-4fb566a41e12");
//        return byId.get().toString();
//        QueryBuilder queryBuilder = new QueryStringQueryBuilder("hello100   ").field("content");
//        Iterable<TestIndex> search = testIndexRepository.search(queryBuilder);
//        search.forEach(x->{
//            System.out.println(x.getContent());
//            System.out.println(x.getDate());
//        });
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.must(QueryBuilders.fuzzyQuery("content", "hello100"));

//        builder.must(new QueryStringQueryBuilder("hello100").field("content"));

        FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);

        //设置分页(从第一页开始，一页显示10条)
        //注意开始是从0开始，有点类似sql中的方法limit 的查询
        PageRequest page = new PageRequest(0, 10);

        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //3.执行方法1
        Page<TestIndex> res = testIndexRepository.search(query);

        res.getContent().forEach(x->{
            System.out.println(x.toString());
        });
        return "ok";
    }



}
