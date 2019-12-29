package com.authentication.service;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;

import com.authentication.repository.IBaseRepository;

@NoRepositoryBean
public interface IGenericService<T, D extends Serializable> extends IBaseRepository<T, Long> {

}
