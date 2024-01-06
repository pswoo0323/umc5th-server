package umc.spring.base.exception.handler;

import umc.spring.base.Code;
import umc.spring.base.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(Code errorCode){
        super(errorCode);
    }
}
