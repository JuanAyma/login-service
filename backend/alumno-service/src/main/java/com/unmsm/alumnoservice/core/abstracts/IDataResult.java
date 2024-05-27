package com.unmsm.alumnoservice.core.abstracts;

import lombok.Setter;

public abstract class IDataResult<T> {
    public T data;
    public Boolean success;
    @Setter
    public String message;
    public String status;

}
