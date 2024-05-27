package com.unmsm.alumnoservice.core.results;


import com.unmsm.alumnoservice.core.abstracts.IDataResult;

public class DataResult<T> extends IDataResult<T> {
    public DataResult(T data, boolean success, String status, String message) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.status= status;
    }

}
