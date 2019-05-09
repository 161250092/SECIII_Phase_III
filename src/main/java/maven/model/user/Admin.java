package maven.model.user;

import maven.model.primitiveType.Password;
import maven.model.primitiveType.UserId;
import maven.model.primitiveType.Username;

public class Admin {
    private Username username;
    private UserId userId;
    private Password password;

    public Admin (Username username,UserId userId,Password password){
        this.username = username;
        this.userId = userId;
        this.password = password;
    }

    public Username getUsername() {
        return username;
    }

    public UserId getUserId(){
        return userId;
    }

    public Password getPassword() {
        return password;
    }

    public void setUsername(Username username){
        this.username = username;
    }

    public void setUserId(UserId userId){
        this.userId = userId;
    }

    public void setPassword(Password password){
        this.password = password;
    }
}
