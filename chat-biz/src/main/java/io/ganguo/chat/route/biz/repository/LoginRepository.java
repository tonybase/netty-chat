package io.ganguo.chat.route.biz.repository;

import io.ganguo.chat.route.biz.entity.Login;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

/**
 * Created by Tony on 2/20/15.
 */
public interface LoginRepository extends CrudRepository<Login, BigInteger> {

    Login findByUin(long uin);

}
