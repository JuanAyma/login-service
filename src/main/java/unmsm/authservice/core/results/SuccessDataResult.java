package unmsm.authservice.core.results;

import unmsm.authservice.common.HttpStatusCodes;

public class SuccessDataResult<T> extends DataResult<T> {
    public SuccessDataResult(T data) {
        super(data, true, HttpStatusCodes.HTTP_OK, "Succesfull");
    }
    public SuccessDataResult(T data, String message) {
        super(data, true, HttpStatusCodes.HTTP_OK,message);
    }
    public SuccessDataResult(T data,String status, String message) {
        super(data, true, status,message);
    }
}
