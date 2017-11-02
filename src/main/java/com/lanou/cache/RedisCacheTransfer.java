package com.lanou.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by dllo on 17/11/2.
 */
public class RedisCacheTransfer {

    //该类就是个RadisCache赋值
    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);

    }
}
