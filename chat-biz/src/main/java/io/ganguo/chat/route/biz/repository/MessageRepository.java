package io.ganguo.chat.route.biz.repository;

import io.ganguo.chat.route.biz.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

/**
 * Created by Tony on 2/23/15.
 */
public interface MessageRepository extends CrudRepository<Message, BigInteger> {

}
