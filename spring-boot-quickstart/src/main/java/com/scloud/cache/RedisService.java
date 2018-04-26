package com.scloud.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


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
     * 读取缓存 可以是对象 根据正则表达式匹配
     * @param regKey
     * @return
     */
    public List<Object> getByRegular(String regKey) {
        Set<String> stringSet = getAllKeys();
        List<Object> objectList = new ArrayList<>();
        for (String s : stringSet) {
            if (Pattern.compile(regKey).matcher(s).matches() && getType(s) == DataType.STRING) {
                objectList.add(get(s));
            }
        }
        return objectList;
    }

    /**
     * 写入缓存 可以是对象
     * @param key
     * @param value
     */
    public void set(long key, Object value) {
        this.set(String.valueOf(key) ,value);
    }

    public void set(int key, Object value) {
        this.set(String.valueOf(key) ,value);
    }

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
     * 获取所有的普通key-value
     */
    public Map<String, Object> getAllString() {
        Set<String> stringSet = getAllKeys();
        Map<String, Object> map = new HashMap<>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (getType(k) == DataType.STRING) {
                map.put(k, get(k));
            }
        }
        return map;
    }

    /**
     * 获取所有的Set -key-value
     * @return
     */
    public Map<String, Set<Object>> getAllSet() {
        Set<String> stringSet = getAllKeys();
        Map<String, Set<Object>> map = new HashMap<>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (getType(k) == DataType.SET) {
                map.put(k, getSet(k));
            }
        }
        return map;
    }

    /**
     * 获取所有的List -key-value
     * @return
     */
    public Map<String, List<Object>> getAllList() {
        Set<String> stringSet = getAllKeys();
        Map<String, List<Object>> map = new HashMap<>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (getType(k) == DataType.LIST) {
                map.put(k, getList(k));
            }
        }
        return map;
    }

    /**
     * 获取所有的Map -key-value
     * @return
     */
    public Map<String, Map<String, Object>> getAllMap() {
        Set<String> stringSet = getAllKeys();
        Map<String, Map<String, Object>> map = new HashMap<>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (getType(k) == DataType.HASH) {
                map.put(k, getMap(k));
            }
        }
        return map;
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
     * 获取完整的list
     * @param key
     */
    public List<Object> getList(String key) {
        return redisTemplate.boundListOps(key).range(0, getListSize(key));
    }

    /**
     * 获取list集合中元素的个数
     * @param key
     * @return
     */
    public long getListSize(String key) {
        return redisTemplate.boundListOps(key).size();
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
     * 模糊移除 支持*号等匹配移除
     * @param blears
     */
    public void removeBlear(String... blears) {
        for (String blear : blears) {
            removeBlear(blear);
        }
    }
    /**
     * 模糊移除 支持*号等匹配移除
     * @param blear
     */
    public void removeBlear(String blear) {
        redisTemplate.delete(redisTemplate.keys(blear));
    }

    /**
     * 修改key名 如果不存在该key或者没有修改成功返回false
     * @param oldKey
     * @param newKey
     * @return
     */
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 根据正则表达式来移除key-value
     * @param blears
     */
    public void removeByRegular(String... blears) {
        for (String blear : blears) {
            removeByRegular(blear);
        }
    }

    /**
     * 根据正则表达式来移除key-value
     * @param blear
     */
    public void removeByRegular(String blear) {
        Set<String> stringSet = getAllKeys();
        for (String s : stringSet) {
            if (Pattern.compile(blear).matcher(s).matches()) {
                redisTemplate.delete(s);
            }
        }
    }

    /**
     * 根据正则表达式来移除 Map中的key-value
     * @param key
     * @param blears
     */
    public void removeMapFieldByRegular(String key, String... blears) {
        for (String blear : blears) {
            removeMapFieldByRegular(key, blear);
        }
    }

    /**
     * 根据正则表达式来移除 Map中的key-value
     * @param key
     * @param blear
     */
    public void removeMapFieldByRegular(String key, String blear) {
        Map<String, Object> map = getMap(key);
        Set<String> stringSet = map.keySet();
        for (String s : stringSet) {
            if (Pattern.compile(blear).matcher(s).matches()) {
                redisTemplate.boundHashOps(key).delete(s);
            }
        }
    }

    /**
     * 获取key的类型
     * @param key
     * @return
     */
    public DataType getType(String key) {
        return redisTemplate.type(key);
    }

    /**
     * 删除map中的某个对象
     * @param key   map对应的key
     * @param field map中该对象的key
     */
    public void removeMapField(String key, Object... field) {
        redisTemplate.boundHashOps(key).delete(field);
    }

    public Long getMapSize(String key) {
        return redisTemplate.boundHashOps(key).size();
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
     * 获取map缓存中的某个对象
     * @param key map对应的key
     * @param field map中该对象的key
     * @return
     */
    public <T> T getMapField(String key, String field) {
        return (T) redisTemplate.boundHashOps(key).get(field);
    }

    /**
     * 判断map中对应key的key是否存在
     * @param key map对应的key
     * @return
     */
    public Boolean hasMapKey(String key, String field) {
        return redisTemplate.boundHashOps(key).hasKey(field);
    }

    /**
     * 获取map对应key的value
     * @param key map对应的key
     * @return
     */
    public List<Object> getMapFieldValue(String key) {
        return redisTemplate.boundHashOps(key).values();
    }

    /**
     * 获取map的key
     * @param key map对应的key
     * @return
     */
    public Set<Object> getMapFieldKey(String key) {
        return redisTemplate.boundHashOps(key).keys();
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
     * 移除set中的某些值
     * @param key  对象key
     * @param obj  值
     */
    public long removeSetValue(String key, Object obj) {
        return redisTemplate.boundSetOps(key).remove(obj);
    }

    public long removeSetValue(String key, Object... obj) {
        if (obj != null && obj.length > 0) {
            return redisTemplate.boundSetOps(key).remove(obj);
        }
        return 0L;
    }

    public long getSetSize(String key) {
        return redisTemplate.boundSetOps(key).size();
    }

    /**
     * 判断set中是否存在这个值
     * @param key  对象key
     */
    public Boolean hasSetValue(String key, Object obj) {
        Boolean boo = null;
        int t =0;
        while (true){
            try {
                boo = redisTemplate.boundSetOps(key).isMember(obj);
                break;
            } catch (Exception e) {
                log.error("key[" + key + "],obj[" + obj + "]判断Set中的值是否存在失败,异常信息:" + e.getMessage());
                t++;
            }
            if(t>times){
                break;
            }
        }
        log.info("key[" + key + "],obj[" + obj + "]是否存在,boo:" + boo);
        return boo;
    }

    /**
     * 获得整个set
     * @param key  对象key
     */
    public Set<Object> getSet(String key) {
        return redisTemplate.boundSetOps(key).members();
    }

    /**
     * 获得set 并集
     * @param key
     * @param otherKey
     * @return
     */
    public Set<Object> getSetUnion(String key, String otherKey) {
        return redisTemplate.boundSetOps(key).union(otherKey);
    }

    public Set<Object> getSetUnion(String key, Set<Object> set) {
        return redisTemplate.boundSetOps(key).union(set);
    }

    /**
     * 获得set 交集
     * @param key
     * @param otherKey
     * @return
     */
    public Set<Object> getSetIntersect(String key, String otherKey) {
        return redisTemplate.boundSetOps(key).intersect(otherKey);
    }

    public Set<Object> getSetIntersect(String key, Set<Object> set) {
        return redisTemplate.boundSetOps(key).intersect(set);
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
