package com.example.apiApp.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

//    @Autowired
//    DatesRepository datesRepository;

    @GetMapping("/public")
    public String pub(){
//
//        try{
//            DeletedItem addedItem = new DeletedItem("unnnnnn");
//            //Dates dates = new Dates("tetugakiu");
//            deletedItemRepository.save(addedItem);
//            System.out.println("kazu;" + deletedItemRepository.count());
//            //System.out.println(deletedItemRepository.findAll().get(1).getId());
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return "test";
    }

}
