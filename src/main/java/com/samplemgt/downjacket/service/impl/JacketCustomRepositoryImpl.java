package com.samplemgt.downjacket.service.impl;

import com.samplemgt.downjacket.dto.CustomJacketReq;
import com.samplemgt.downjacket.entity.Jacket;
import com.samplemgt.downjacket.repository.JacketCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class JacketCustomRepositoryImpl implements JacketCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Jacket> findJacketByProperties(String keyword, String status, Date from, Date to) {

        List<String> keys = getTokens(keyword);
        TextCriteria criteria = TextCriteria.forDefaultLanguage();
        for (String key : keys){
            criteria = criteria.matching(key);
        }
        // Build Query
        Query query = TextQuery.queryText(criteria);
        // Add the additional criteria to query
        query.addCriteria(Criteria.where("createDate").gt(from)
                .andOperator(Criteria.where("createDate").lt(to)));
        query.addCriteria(Criteria.where("status").is(status));

        List<Jacket> jackets = mongoTemplate.find(query, Jacket.class);
        return jackets;
    }

    private List<String> getTokens(String str) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str, " ");
        while (tokenizer.hasMoreElements()) {
            tokens.add("\""+tokenizer.nextToken()+"\"");
        }
        return tokens;
    }
    @Override
    public List<Jacket> findJacketByProperties(String keyword, Date from, Date to) {

        List<String> keys = getTokens(keyword);
        TextCriteria criteria = TextCriteria.forDefaultLanguage();
        for (String key : keys){
            criteria = criteria.matching(key);
        }
        // Build Query
        Query query = TextQuery.queryText(criteria);


        // Add the additional criteria to query
        query.addCriteria(Criteria.where("createDate").gt(from)
                .andOperator(Criteria.where("createDate").lt(to)));
        List<Jacket> jackets = mongoTemplate.find(query, Jacket.class);

        return jackets;
    }

    @Override
    public List<Jacket> findJacketByProperties(Date from, Date to) {

        // Build Query
        Query query = new Query();
        // Add the additional criteria to query
        query.addCriteria(Criteria.where("createDate").gt(from)
                .andOperator(Criteria.where("createDate").lt(to)));
        //query.addCriteria(Criteria.where("createDate").lt(to));

        List<Jacket> jackets = mongoTemplate.find(query, Jacket.class);
        return jackets;
    }

    @Override
    public List<Jacket> findJacketByProperties(CustomJacketReq req)  {
        String dateCrit = req.dateCriteria;
        String typeCrit = "type";
        Date from = new Date();
        Date to = new Date();

        Query query = new Query();
        if(!req.keyword.isEmpty()){
            List<String> keys = getTokens(req.keyword);
            TextCriteria criteria = TextCriteria.forDefaultLanguage();
            for (String key : keys){
                criteria = criteria.matching(key);
            }
            query = TextQuery.queryText(criteria);
        }

        try{
            from = DF.parse(req.startDate);
        }catch (ParseException ex){
            System.out.println("error in start date format");
        }

        if (!req.endDate.isEmpty()) {
            try{
                to = DF.parse(req.endDate);
                query.addCriteria(Criteria.where(dateCrit).gt(from)
                        .andOperator(Criteria.where(dateCrit).lt(to)));
            }catch (ParseException ex){
                System.out.println("error in end date format");
            }

        }else{
            query.addCriteria(Criteria.where(dateCrit).gt(from));
        }

        if (!req.type.isEmpty()){
            query.addCriteria(Criteria.where(typeCrit).is(req.type));
        }

        List<Jacket> jackets = mongoTemplate.find(query, Jacket.class);
        return jackets;
    }
}
