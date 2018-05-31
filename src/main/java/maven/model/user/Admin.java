package maven.model.user;

import maven.model.primitiveType.Password;
import maven.model.primitiveType.UserId;

public class Admin {
    private UserId userId;
    private Password password;

    public Admin (UserId userId,Password password){
        this.userId = userId;
        this.password = password;
    }

    public UserId getUserId() {
        return userId;
    }

    public Password getPassword() {
        return password;
    }

    public void setUserId(UserId userId){
        this.userId = userId;
    }

    public void setPassword(Password password){
        this.password = password;
    }
}
