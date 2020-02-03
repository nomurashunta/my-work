package com.example.apiApp.domain.model;

import com.example.apiApp.utils.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class AbstractItem implements Serializable {

    public AbstractItem(){}

    @Id
    @NotNull
    @Column(name = "id")
    private String id;

    @Column(name = "img")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NonNull
    private String img;

    @Setter(AccessLevel.NONE)
    private String imgURI;

    public String getImgURI(){
        return String.format("/api/items/%s/image", id) ;
    }

    @Column(name = "title")
    @NotNull
    @Size(max = 100)
    private String title;

    @Column(name = "description")
    @NotNull
    @Size(max=500)
    private String description;

    @Column(name = "price")
    @NotNull
    @Range(max = Constants.MAX_PRICE, min = 0)
    private int price;

}
