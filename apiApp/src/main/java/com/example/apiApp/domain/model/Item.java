package com.example.apiApp.domain.model;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;
import java.io.Serializable;

import com.example.apiApp.utils.*;
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
