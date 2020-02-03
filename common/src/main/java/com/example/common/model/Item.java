package com.example.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 商品データのEntityクラスです。
 */
@Entity
@JsonIgnoreProperties({"$$_hibernate_interceptor", "hibernateLazyInitializer"})
@Data
@Table(name="item")
public class Item extends AbstractItem implements Serializable {

    /**
     * コンストラクタ
     */
    public Item(){

    }

    /**
     * IDを指定する場合のコンストラクタ
     * @param id ID
     */
    public Item(String id){
        setId(id);
        setDescription("");
        setTitle("");
        setImg("");
        setPrice(0);
    }

    /**
     * 商品の編集を行います。
     * @param item 編集後の商品データが入ったEntityオブジェクトです。
     */
    public void edit (Item item){
        setImg(item.getImg());
        setTitle(item.getTitle());
        setDescription(item.getDescription());
        setPrice(item.getPrice());
    }

}
