package com.example.maven.businessLogic.loginBL;

import com.example.maven.exception.loginException.*;
import com.example.maven.model.primitiveType.Password;
import com.example.maven.model.primitiveType.Username;

public class LoginBLStub implements LoginBLService{
    @Override
    public Exception login(Username username, Password password) {
        return null;
    }
}
