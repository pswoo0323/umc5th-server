package umc.spring.base.exception;

import umc.spring.base.Code;

public class RegionException extends GeneralException{

    public RegionException(Code errCode){
        super(errCode);
    }
}
