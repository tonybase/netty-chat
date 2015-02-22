package io.ganguo.chat.server.session;

import io.ganguo.chat.biz.entity.Login;
import io.ganguo.chat.biz.bean.Presence;
import io.ganguo.chat.core.connetion.IMConnection;

/**
 * Created by Tony on 2/21/15.
 */
public class ClientSession {

    private IMConnection mConnection;
    private Presence mPresence;
    private Login mLogin;

    public ClientSession(Login login, IMConnection connection) {
        mLogin = login;
        mConnection = connection;
    }

    public long getUin() {
        return mLogin.getUin();
    }

    public IMConnection getConnection() {
        return mConnection;
    }

    public void setConnection(IMConnection mConnection) {
        this.mConnection = mConnection;
    }

    public Presence getPresence() {
        return mPresence == null ? new Presence() : mPresence;
    }

    public void setPresence(Presence presence) {
        mPresence = presence;
    }

    public Login getLogin() {
        return mLogin;
    }

    public void setLogin(Login login) {
        mLogin = login;
    }
}
