package umc.spring.base.exception;

import umc.spring.base.Code;

public class TempException extends GeneralException {

    public TempException(Code errorCode){
        super(errorCode);
    }
}
