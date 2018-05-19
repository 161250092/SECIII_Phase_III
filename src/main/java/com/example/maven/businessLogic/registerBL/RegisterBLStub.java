package com.example.maven.businessLogic.registerBL;

import com.example.maven.model.primitiveType.Password;
import com.example.maven.model.primitiveType.Username;

public class RegisterBLStub implements RegisterBLService{

    @Override
    public boolean isUsernameExist(Username username) {
        return false;
    }

    @Override
    public Exception register(Username username, Password password) {
        return null;
    }
}
