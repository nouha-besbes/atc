package com.authentication.service;

import java.io.Serializable;

public class GenericService<T> implements IGenericService<T, Serializable> {

    // @Autowired
    // private IBaseRepository<T, Serializable> baseRepository;
    //
    // @Override
    // public List<T> findAll() {
    // return baseRepository.findAll();
    // }

}
