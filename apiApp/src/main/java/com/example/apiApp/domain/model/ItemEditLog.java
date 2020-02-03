package com.example.apiApp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"$$_hibernate_interceptor", "hibernateLazyInitializer"})
@Data
@Table(name="log")
public class ItemEditLog extends AbstractItem{

    public ItemEditLog(){

    }

    public ItemEditLog(Item item, EditStatus editStatus){
        setItemInfo(item);
        setStatus(editStatus);
        setDate(LocalDateTime.now());
    }

    private void setItemInfo(Item item){
        setId(item.getId());
        setTitle(item.getTitle());
        setImg(item.getImg());
        setDescription(item.getDescription());
        setPrice(item.getPrice());
    }

    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
    private EditStatus status;

    @Column(name = "datetime")
    private LocalDateTime date;

    public String toString(){
        String str = "";
        String br = System.getProperty("line.separator");
        str += "商品ID：" + getId() + br;
        str += "商品名：" + getTitle() + br;
        str +=  status.toString() + "日時：" + date + br;
        return str;
    }

}
