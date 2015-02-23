package io.ganguo.chat.route.server.dto;

import io.ganguo.chat.route.biz.entity.UserDetail;
import io.ganguo.chat.route.core.transport.DataBuffer;
import io.ganguo.chat.route.core.transport.IMSerializer;

/**
 * Created by Tony on 2/19/15.
 */
public class UserDetailDTO implements IMSerializer {

    private UserDetail userDetail;

    public UserDetailDTO() {
    }

    public UserDetailDTO(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public DataBuffer encode(short version) {
        DataBuffer data = new DataBuffer();
        data.writeString(userDetail.getId());
        data.writeString(userDetail.getEmail());
        data.writeString(userDetail.getTelphone());
        data.writeString(userDetail.getBirthDate());
        data.writeString(userDetail.getCity());
        data.writeString(userDetail.getProvince());
        data.writeString(userDetail.getPosition());
        return null;
    }

    @Override
    public void decode(DataBuffer buffer, short version) {
        if (userDetail == null) {
            userDetail = new UserDetail();
        }
        userDetail.setId(buffer.readString());
        userDetail.setEmail(buffer.readString());
        userDetail.setTelphone(buffer.readString());
        userDetail.setBirthDate(buffer.readString());
        userDetail.setCity(buffer.readString());
        userDetail.setProvince(buffer.readString());
        userDetail.setPosition(buffer.readString());
    }
}
