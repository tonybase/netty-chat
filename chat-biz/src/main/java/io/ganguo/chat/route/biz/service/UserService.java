package io.ganguo.chat.route.biz.service;

import io.ganguo.chat.route.biz.entity.Login;
import io.ganguo.chat.route.biz.entity.User;

/**
 * Created by Tony on 2/20/15.
 */
public interface UserService {

    public Login login(String account, String password);

    public boolean authenticate(Long uin, String token);
        
    User findByUin(long uin);

}
