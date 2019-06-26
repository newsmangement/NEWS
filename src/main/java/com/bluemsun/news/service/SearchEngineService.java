package com.bluemsun.news.service;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mafx on 2019/6/15.
 */
@Service
public class SearchEngineService {
    @Autowired
    TransportClient client;

    public void addNews(String jsonStr,int id){
        String index="news_search_center";
        String type="news";
        IndexResponse response=client.prepareIndex(index,type,""+id).setSource(jsonStr, XContentType.JSON).get();
        System.out.println(response);
    }
    public String searchEng(String key){
        SearchRequestBuilder requestBuilder = client.prepareSearch("news_search_center").setTypes("news");
        BoolQueryBuilder qbs = QueryBuilders.boolQuery();
        QueryBuilder qb1 = QueryBuilders.matchQuery("text", key);
        BoolQueryBuilder bqb1 = QueryBuilders.boolQuery().must(qb1);
        qbs.must(bqb1);
        requestBuilder.setQuery(qbs);
        SearchResponse responses = requestBuilder.execute().actionGet();
        return responses.toString();
    }
}
