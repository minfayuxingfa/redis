package com.lanou.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by dllo on 17/11/2.
 */
public class RedisCache implements Cache {

    private static JedisConnectionFactory jedisConnectionFactory;

    private final String id;

    private  final  static ReadWriteLock lock=new ReentrantReadWriteLock();

    public RedisCache(final String id) {
//        如果传入的ID为空 抛出异常
        if (id == null) {
            throw new IllegalArgumentException("缓存需要一个id");
        }
        this.id = id;
    }

    //获取缓存对象的唯一标识
    public String getId() {
        return this.id;
    }

    //    将参数的key和value存到缓存对象中
    public void putObject(Object key, Object value) {
        JedisConnection connection = null;

        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            //        redis的序列化工具
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();

            connection.set(serializer.serialize(key), serializer.serialize(value));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
// 关闭Connection
            if (connection != null) {
                connection.close();
            }
        }

    }

    //    从缓存中根据key获取值
    public Object getObject(Object key) {

        JedisConnection connection = null;
        Object result = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            //        redis的序列化工具
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();

//            根据可以获取缓存中的值

            result = serializer.deserialize(connection.get(serializer.serialize(key)));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
// 关闭Connection
            if (connection != null) {
                connection.close();
            }
        }


        return result;
    }

    //    根据key移除value
    //这个方法可选
    public Object removeObject(Object key) {
        JedisConnection connection = null;
        Object result = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            //        redis的序列化工具
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();

//          移除key对应的一条数据

            result = connection.expire(serializer.serialize(key), 0);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
// 关闭Connection
            if (connection != null) {
                connection.close();
            }
        }


        return result;
    }

    public void clear() {
//清空缓存
        JedisConnection connection = null;

        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            connection.flushDb();
            connection.flushAll();

        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public int getSize() {

        int count=0;
        JedisConnection connection=null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            count=Integer.valueOf( connection.dbSize().toString());

        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return count;
    }

    public ReadWriteLock getReadWriteLock() {

        return this.lock;
    }

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }
}
