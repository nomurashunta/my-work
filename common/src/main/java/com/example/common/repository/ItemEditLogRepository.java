package com.example.common.repository;


import com.example.common.model.EditStatus;
import com.example.common.model.ItemEditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * データベースの操作を行うためのRepositoryクラスのインターフェースです。
 */
@Repository
public interface ItemEditLogRepository extends JpaRepository<ItemEditLog, String> {

    @Query("select i from ItemEditLog i where i.status = ?1")
    public List<ItemEditLog> findItemsByEditStatus(EditStatus status);

    @Query(value = "select * from log where datetime between ?1 and ?2", nativeQuery = true)
    public List<ItemEditLog> findItemByPeriod(LocalDateTime dayBefore, LocalDateTime dayAfter);

}
