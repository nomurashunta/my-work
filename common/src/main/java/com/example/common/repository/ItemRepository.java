package com.example.common.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.common.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * データベースの操作を行うためのRepositoryクラスのインターフェースです。
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, String>{

    /**
     * データベースから指定された条件を満たす商品を検索し、データをリスト形式で返します。
     * @param id 商品のIDを指定します。部分一致で検索されます。
     * @param title 商品のタイトルを指定します。部分一致で検索されます。
     * @param description 商品の説明文を指定します。部分一致で検索されます。
     * @param maxPrice 商品価格の上限を指定します。
     * @param minPrice 商品価格の下限を指定します。
     * @return 商品データのリストです。
     */
    @Query("select i from Item i where i.id like %?1% and i.title like %?2% and " +
            "i.description like %?3% and i.price < ?4 and i.price > ?5")
    public List<Item> findByPostedQuery(String id, String title, String description,
                                        int maxPrice, int minPrice);
}
