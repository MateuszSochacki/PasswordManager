package com.manager.services;

import com.manager.model.PasswordEntity;
import com.manager.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
public class PasswordCreator {

    @Autowired
    PasswordRepository passwordRepository;

    @Transactional
    public PasswordEntity saveAndFlush(PasswordEntity passwordEntity){
        passwordEntity = passwordRepository.saveAndFlush(passwordEntity);
        return passwordEntity;
    }
}
