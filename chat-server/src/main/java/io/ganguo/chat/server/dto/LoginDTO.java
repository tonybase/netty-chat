package io.ganguo.chat.server.dto;

import io.ganguo.chat.biz.bean.ClientType;
import io.ganguo.chat.biz.entity.Login;
import io.ganguo.chat.core.transport.DataBuffer;
import io.ganguo.chat.core.transport.IMSerializer;

/**
 * Created by Tony on 2/19/15.
 */
public class LoginDTO implements IMSerializer {

    private Login login;

    public LoginDTO() {

    }

    public LoginDTO(Login login) {
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public DataBuffer encode(short version) {
        DataBuffer buffer = new DataBuffer();
        buffer.writeString(login.getId());
        buffer.writeLong(login.getUin());
        buffer.writeString(login.getAuthToken());
        buffer.writeLong(login.getActiveTime());
        return buffer;
    }

    @Override
    public void decode(DataBuffer buffer, short version) {
        if(login == null) {
            login = new Login();
        }
        login.setId(buffer.readString());
        login.setUin(buffer.readLong());
        login.setAuthToken(buffer.readString());
        login.setActiveTime(buffer.readLong());
    }

}
