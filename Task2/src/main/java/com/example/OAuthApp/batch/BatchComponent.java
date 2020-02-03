//package com.example.OAuthApp.batch;
//
//import Task1.*;
//import com.example.apiApp.domain.model.ItemEditLog;
//import com.example.apiApp.domain.repository.ItemEditLogRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class BatchComponent {
//
//    @Autowired
//    private ItemEditLogRepository itemEditLogRepository;
//
//    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
//    public void execute(){
//
//    }
//
//    public void printSummary(){
//
//        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
//        LocalDateTime now = LocalDateTime.now();
//
//        List<ItemEditLog> itemLogsByPeriod = itemEditLogRepository
//                .findItemByPeriod(yesterday, now);
//
//        List<ItemEditLog> todaysAddedItemLog = new ArrayList<>();
//        List<ItemEditLog> todaysDeletedItemLog = new ArrayList<>();
//
//        itemLogsByPeriod.forEach(i -> {
//            if (i.getStatus() == EditStatus.ADD){
//                todaysAddedItemLog.add(i);
//            } else if (i.getStatus() == EditStatus.DELETE){
//                todaysDeletedItemLog.add(i);
//            }
//        });
//
//        System.out.println(now + "の追加件数：" + todaysAddedItemLog.size());
//        printItemInfos(todaysAddedItemLog);
//        System.out.println(now + "の削除件数：" + todaysDeletedItemLog.size());
//        printItemInfos(todaysDeletedItemLog);
//
//    }
//
//    public void printItemInfos(List<ItemEditLog> log){
//        log.forEach(i -> {
//            System.out.println(i.toString());
//        });
//    }
//
//}
