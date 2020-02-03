package com.example.common.service;

import com.example.common.model.EditStatus;
import com.example.common.model.ItemEditLog;
import com.example.common.repository.ItemEditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SummaryService {

    @Autowired
    private ItemEditLogRepository itemEditLogRepository;

    public String getSummary(){
        String summary = "";

        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDateTime now = LocalDateTime.now();

        List<ItemEditLog> itemLogsByPeriod = itemEditLogRepository.findItemByPeriod(yesterday, now);

        List<ItemEditLog> todaysAddedItemLog = new ArrayList<>();
        List<ItemEditLog> todaysDeletedItemLog = new ArrayList<>();

        itemLogsByPeriod.forEach(i -> {
            if (i.getStatus() == EditStatus.ADD){
                todaysAddedItemLog.add(i);
            } else if (i.getStatus() == EditStatus.DELETE){
                todaysDeletedItemLog.add(i);
            }
        });

        summary += (now + "の追加件数：" + todaysAddedItemLog.size());
        printItemInfos(todaysAddedItemLog);
        summary += (now + "の削除件数：" + todaysDeletedItemLog.size());
        printItemInfos(todaysDeletedItemLog);

        return summary;
    }

    public void printSummary(){
        System.out.println(getSummary());
    }

    public void printItemInfos(List<ItemEditLog> log){
        log.forEach(i -> {
            System.out.println(i.toString());
        });
    }

}
