package com.manager.services;

import com.manager.model.GlobalPasswordEntity;
import com.manager.repository.GlobalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
public class ChangeMainPassword {
    @Autowired
    GlobalRepository globalRepository;

    @Transactional
    public GlobalPasswordEntity saveAndFlush(GlobalPasswordEntity globalPasswordEntity){
        globalPasswordEntity = globalRepository.saveAndFlush(globalPasswordEntity);
        return globalPasswordEntity;
    }
}
