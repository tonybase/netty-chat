package io.ganguo.chat.biz.repository;

import io.ganguo.chat.biz.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Created by Tony on 2/19/15.
 */
@Repository
public interface UserRepository extends CrudRepository<User, BigInteger> {

    User findByAccount(String account);

    User findByUin(Long uin);
}
