package io.ganguo.chat.route.server.session;

import io.ganguo.chat.route.biz.entity.Login;
import io.ganguo.chat.route.biz.bean.Presence;
import io.ganguo.chat.route.core.connetion.IMConnection;

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

        // bind Uin
        connection.setUin(login.getUin());
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
