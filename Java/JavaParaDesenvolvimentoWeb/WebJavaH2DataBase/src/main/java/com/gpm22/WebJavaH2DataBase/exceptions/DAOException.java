package com.gpm22.WebJavaH2DataBase.exceptions;

public class DAOException extends RuntimeException {
    
    public DAOException(Throwable cause){
        super(cause);
    }

    public DAOException(String str){
        super(str);
    }
}
