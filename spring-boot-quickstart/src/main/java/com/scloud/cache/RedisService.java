package com.scloud.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Redis 服务类
 *
 * Create by andy on 2018/4/26
 */
@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 出异常，重复操作的次数
     */
    private static Integer times = 5;

    /**
     * 获取Redis中所有的键的key
     */
    public Set<String> getAllKeys() {
        return redisTemplate.keys("*");
    }

    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有key对应的value
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据key获取缓存
     * @param key
     * @return
     */
    public Object get(int key) {
        return this.get(String.valueOf(key));
    }

    /**
     * 根据key获取缓存
     * @param key
     * @return
     */
    public Object get(long key) {
        return this.get(String.valueOf(key));
    }

    /**
     * 根据key获取缓存
     * @param key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    public List<Object> get(String... keys) {
        List<Object> list = new ArrayList<>();
        for (String key : keys) {
            list.add(get(key));
        }
        return list;
    }

    /**
     * 写入缓存 可以是对象
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.boundValueOps(key).set(value);
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @param expireTime 过期时间 -单位s
     * @return
     */
    public void set(String key, Object value, Long expireTime) {
        redisTemplate.boundValueOps(key).set(value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 设置一个key的过期时间（单位：秒）
     * @param key
     * @param expireTime
     * @return
     */
    public boolean setExpireTime(String key, Long expireTime) {
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 添加一个list
     * @param key
     * @param objectList
     */
    public void addList(String key, List<Object> objectList) {
        for (Object obj : objectList) {
            addList(key, obj);
        }
    }

    /**
     * 向list中增加值
     * @param key
     * @param obj
     * @return 返回在list中的下标
     */
    public long addList(String key, Object obj) {
        return redisTemplate.boundListOps(key).rightPush(obj);
    }

    /**
     * 向list中增加值
     * @param key
     * @param obj
     * @return 返回在list中的下标
     */
    public long addList(String key, Object... obj) {
        return redisTemplate.boundListOps(key).rightPushAll(obj);
    }

    /**
     * 获取list
     * @param key List的key
     * @param s 开始下标
     * @param e 结束的下标
     * @return
     */
    public List<Object> getList(String key, long s, long e) {
        return redisTemplate.boundListOps(key).range(s, e);
    }


    /**
     * 移除list中某值
     * @param key
     * @param object
     * @return 移除数量
     */
    public long removeListValue(String key, Object object) {
        return redisTemplate.boundListOps(key).remove(0, object);
    }

    /**
     * 移除list中某值
     * @param key
     * @param objects
     * @return 返回移除数量
     */
    public long removeListValue(String key, Object... objects) {
        long r = 0;
        for (Object object : objects) {
            r += removeListValue(key, object);
        }
        return r;
    }

    /**
     * 批量删除key对应的value
     * @param key
     */
    public void remove(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                remove(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 删除map中的某个对象
     * @param key   map对应的key
     * @param field map中该对象的key
     */
    public void removeMapField(String key, Object... field) {
        redisTemplate.boundHashOps(key).delete(field);
    }

    /**
     * 获取map对象
     * @param key map对应的key
     * @return
     */
    public Map<String, Object> getMap(String key) {
        return redisTemplate.boundHashOps(key).entries();
    }

    /**
     * 添加map
     * @param key
     * @param map
     */
    public void addMap(String key, Map<String, Object> map) {
        redisTemplate.boundHashOps(key).putAll(map);
    }

    public void addMap(String key, String field, Object value) {
        redisTemplate.boundHashOps(key).put(field, value);
    }

    /**
     * 向key对应的map中添加缓存对象
     * @param key   cache对象key
     * @param field map对应的key
     * @param time 过期时间-整个MAP的过期时间（单位秒）
     * @param value     值
     */
    public void addMap(String key, String field, Object value, long time) {
        redisTemplate.boundHashOps(key).put(field, value);
        redisTemplate.boundHashOps(key).expire(time, TimeUnit.SECONDS);
    }

    /**
     * 处理事务时锁定key
     * @param key
     */
    public void watch(String key) {
        redisTemplate.watch(key);
    }

    /**
     * 向set中加入对象
     * @param key  对象key
     * @param obj  值
     */
    public void addSet(String key, Object... obj) {
        redisTemplate.boundSetOps(key).add(obj);
    }

    /**
     * 获得整个set
     * @param key  对象key
     */
    public Set<Object> getSet(String key) {
        return redisTemplate.boundSetOps(key).members();
    }

    /**
     * 给指定key的值增加inc
     * @param key
     * @param inc
     * @return
     */
    public Long incr(String key, Long inc) {
        return redisTemplate.opsForValue().increment(key, inc);
    }

    /**
     * 给指定key的值增加inc
     * @param key
     * @param inc
     * @return
     */
    public Double incr(String key, double inc) {
        return redisTemplate.opsForValue().increment(key, inc);
    }

}
