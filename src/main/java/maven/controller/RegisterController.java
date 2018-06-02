package maven.controller;

import maven.businessLogic.registerBL.RegisterBLImpl;
import maven.businessLogic.registerBL.RegisterBLService;
import maven.businessLogic.registerBL.RegisterBLStub;
import maven.model.primitiveType.Email;
import maven.model.primitiveType.Password;
import maven.model.primitiveType.Phone;
import maven.model.primitiveType.Username;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private RegisterBLService registerBL;

    public RegisterController(){
        registerBL = new RegisterBLImpl();
    }

    /**
     * 判断是否重名
     * @param username 用户昵称
     * @return 是否重名
     */
    @RequestMapping(value = "/register/isUsernameExist", method = RequestMethod.GET)
    public boolean isUsernameExist(String username){
        return registerBL.isUsernameExist(new Username(username));
    }

    /**
     * 判断是否注册成功
     * @param username 用户昵称
     * @param password 用户密码
     * @return 是否注册成功
     */
    @RequestMapping(value = "/register/register", method = RequestMethod.GET)
    public Exception register(String username, String password, String email, String phone){
        return registerBL.register(new Username(username), new Password(password), new Email(email), new Phone(phone));
    }

}
