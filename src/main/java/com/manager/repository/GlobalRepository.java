package com.manager.repository;

import com.manager.model.GlobalPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GlobalRepository extends JpaRepository<GlobalPasswordEntity, Integer> {

    GlobalPasswordEntity findByGlobalpassword(String string);

}