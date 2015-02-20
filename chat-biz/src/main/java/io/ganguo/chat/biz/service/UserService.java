package io.ganguo.chat.biz.service;

import io.ganguo.chat.biz.entity.Login;
import io.ganguo.chat.biz.entity.User;

/**
 * Created by Tony on 2/20/15.
 */
public interface UserService {
    public Login login(String account, String password);
    User findByUin(Long uin);
}
