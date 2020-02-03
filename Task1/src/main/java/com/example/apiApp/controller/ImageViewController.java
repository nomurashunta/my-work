package com.example.apiApp.controller;
import com.example.apiApp.domain.service.RepositoryService;
import com.example.common.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

/**
 * 商品画像表示画面のControllerクラスです。
 */
@Controller
public class ImageViewController {

    @Autowired
    RepositoryService repositoryService;

    /**
     * 商品の画像を表示するための処理を行います。
     * @param id 商品ID
     * @param model
     * @return 画像表示画面のURL
     */
    @RequestMapping(value = "/api/items/{id}/image", method = RequestMethod.GET)
    public String showImage(@PathVariable("id") String id, Model model){
        Item item = repositoryService.getItemObjectByID(id);
        model.addAttribute("b64img", item.getImg());
        return "imageview";
    }
}
