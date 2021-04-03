package com.resumly.resumeapi.modules.core.services;

import com.resumly.resumeapi.modules.core.exceptions.DataValidationException;

import java.io.Serializable;

public interface IService<T extends Serializable> {
    void save(T entity) throws DataValidationException;
    void update(T entity) throws DataValidationException;
}
