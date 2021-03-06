package lyr.securityserver.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @ClassName RedisUtil
 * @Author LYR
 * @Date 2019/4/11 9:47
 * @Version 1.0
 **/
@Component
public final class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

//    ============================common============================
    /**
     * 指定缓存失效时间
     * @param key
     * @param time
     * @Author LinYouRu
     * @Date 10:09 2019/4/11
     * @return boolean
     **/
    public boolean expire(String key,long time){
        key = keyAndSpace(key);
        try {
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     * @param key 键 不能为null
     * @Author LinYouRu
     * @Date 10:13 2019/4/11
     * @return long 时间（秒） 0代表永久有效
     **/
    public long getExpire(String key){
        key = keyAndSpace(key);
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断key是否存在
     * @param key
     * @Author LinYouRu
     * @Date 10:17 2019/4/11
     * @return boolean
     **/
    public boolean hasKey(String key){
        key = keyAndSpace(key);
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个或多个值
     * @Author LinYouRu
     * @Date 10:21 2019/4/11
     * @return void
     **/
    @SuppressWarnings("unchecked")
    public void del(String... key) {

        if (key != null && key.length > 0) {
            List<String> keyList = CollectionUtils.arrayToList(key);
            List<String> targetKeyList = new ArrayList<>();
            for (String k : keyList) {
                targetKeyList.add(keyAndSpace(k));
            }
            if (targetKeyList.size() == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(targetKeyList);
            }
        }
    }

//    ============================String============================

    /**
     * 普通缓存获取
     * @param key
     * @Author LinYouRu
     * @Date 10:24 2019/4/11
     * @return java.lang.Object
     **/
    public Object get(String key){
        key = keyAndSpace(key);
        return key == null? null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存存储
     * @param key
     * @param value
     * @Author LinYouRu
     * @Date 10:28 2019/4/11
     * @return boolean
     **/
    public boolean set (String key,Object value){
        key = keyAndSpace(key);
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key
     * @param value
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @Author LinYouRu
     * @Date 10:37 2019/4/11
     * @return boolean
     **/
    public boolean set(String key, Object value, long time) {
        key = keyAndSpace(key);
        try {
            if(time>0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 递增,方法以增量方式存储double值
     * @param key
     * @param delta 要增加几(大于0)
     * @Author LinYouRu
     * @Date 10:43 2019/4/11
     * @return long
     **/
    public long incr(String key, long delta) {
        key = keyAndSpace(key);
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减,方法以增量方式存储double值
     * @param key
     * @param delta 要减少几(大于0)
     * @Author LinYouRu
     * @Date 10:44 2019/4/11
     * @return long
     **/
    public long decr(String key, long delta) {
        key = keyAndSpace(key);
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

//    ============================Hash============================

    /**
     * HashGet
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @Author LinYouRu
     * @Date 10:59 2019/4/11
     * @return java.lang.Object
     **/
    public Object hget(String key, String item) {
        key = keyAndSpace(key);
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @Author LinYouRu
     * @Date 11:01 2019/4/11
     * @return java.util.Map<java.lang.Object,java.lang.Object>
     **/
    public Map<Object, Object> hmget(String key) {
        key = keyAndSpace(key);
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     * @Author LinYouRu
     * @Date 11:02 2019/4/11
     * @return boolean
     **/
    public boolean hmset(String key, Map<String, Object> map) {
        key = keyAndSpace(key);
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间（秒）
     * @Author LinYouRu
     * @Date 11:16 2019/4/11
     * @return boolean
     **/
    public boolean hmset(String key, Map<String, Object> map, long time) {
        key = keyAndSpace(key);
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key
     * @param item
     * @param value
     * @Author LinYouRu
     * @Date 11:18 2019/4/11
     * @return boolean
     **/
    public boolean hset(String key, String item, Object value) {
        key = keyAndSpace(key);
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建，并设置时间
     * @param key
     * @param item
     * @param value
     * @param time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @Author LinYouRu
     * @Date 11:18 2019/4/11
     * @return boolean
     **/
    public boolean hset(String key, String item, Object value,long time) {
        key = keyAndSpace(key);
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 删除hash表中的值
     * @param key 不能为null
     * @param item 不能为null 可传多个
     * @Author LinYouRu
     * @Date 11:25 2019/4/11
     * @return void
     **/
    public void hdel(String key,Object... item){
        key = keyAndSpace(key);
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 不能为null
     * @param item 不能为null
     * @Author LinYouRu
     * @Date 11:31 2019/4/11
     * @return boolean
     **/
    public boolean hHasKey(String key, String item) {
        key = keyAndSpace(key);
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key
     * @param item
     * @param by 要增加几(大于0)
     * @Author LinYouRu
     * @Date 11:53 2019/4/11
     * @return double
     **/
    public double hincr(String key, String item, double by) {
        key = keyAndSpace(key);
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     * @param key
     * @param item 
     * @param by 要增加几
     * @Author LinYouRu
     * @Date 11:54 2019/4/11
     * @return double
     **/
    public double hdecr(String key, String item, double by) {
        key = keyAndSpace(key);
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
//    ============================Set============================

    /**
     * 根据key获取Set中的所有值
     * @param key
     * @Author LinYouRu
     * @Date 11:55 2019/4/11
     * @return java.util.Set<java.lang.Object>
     **/
    public Set<Object> sGet(String key) {
        key = keyAndSpace(key);
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     * @param key
     * @param value
     * @Author LinYouRu
     * @Date 11:58 2019/4/11
     * @return boolean
     **/
    public boolean sHasKey(String key, Object value) {
        key = keyAndSpace(key);
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     * @param key
     * @param values 值，可以是多个
     * @Author LinYouRu
     * @Date 14:38 2019/4/11
     * @return long 成功个数
     **/
    public long sSet(String key, Object... values) {
        key = keyAndSpace(key);
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将数据放入set缓存，并设置时间
     * @param key
     * @param time 时间(秒)
     * @param values 值，可以是多个
     * @Author LinYouRu
     * @Date 14:38 2019/4/11
     * @return long 成功个数
     **/
    public long sSetAndTime(String key, long time, Object... values) {
        key = keyAndSpace(key);
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
            expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * @param key
     * @Author LinYouRu
     * @Date 14:43 2019/4/11
     * @return long
     **/
    public long sGetSetSize(String key) {
        key = keyAndSpace(key);
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     * @param key
     * @param values 值 可以是多个
     * @Author LinYouRu
     * @Date 14:44 2019/4/11
     * @return long 移除的个数
     **/
    public long setRemove(String key, Object... values) {
        key = keyAndSpace(key);
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

//    ============================List============================

    /**
     * 获取list缓存的内容
     * @param key
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @Author LinYouRu
     * @Date 14:49 2019/4/11
     * @return java.util.List<java.lang.Object>
     **/
    public List<Object> lGet(String key, long start, long end) {
        key = keyAndSpace(key);
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存长度
     * @param key
     * @Author LinYouRu
     * @Date 15:00 2019/4/11
     * @return long
     **/
    public long lGetListSize(String key) {
        key = keyAndSpace(key);
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @Author LinYouRu
     * @Date 15:01 2019/4/11
     * @return java.lang.Object
     **/
    public Object lGetIndex(String key, long index) {
        key = keyAndSpace(key);
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将数据放入list缓存
     * @param key
     * @param value
     * @Author LinYouRu
     * @Date 15:01 2019/4/11
     * @return boolean
     **/
    public boolean lSet(String key, Object value) {
        key = keyAndSpace(key);
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入list缓存  并设置时间
     * @param key
     * @param value
     * @param time 时间（秒）
     * @Author LinYouRu
     * @Date 15:03 2019/4/11
     * @return boolean
     **/
    public boolean lSet(String key, Object value, long time) {
        key = keyAndSpace(key);
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
            expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入list缓存
     * @param key
     * @param value
     * @Author LinYouRu
     * @Date 15:05 2019/4/11
     * @return boolean
     **/
    public boolean lSet(String key, List<Object> value) {
        key = keyAndSpace(key);
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入list缓存 并设置时间
     * @param key
     * @param value
     * @param time 时间（秒）
     * @Author LinYouRu
     * @Date 15:06 2019/4/11
     * @return boolean
     **/
    public boolean lSet(String key, List<Object> value, long time) {
        key = keyAndSpace(key);
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
            expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key
     * @param index 索引
     * @param value 值
     * @Author LinYouRu
     * @Date 15:07 2019/4/11
     * @return boolean
     **/
    public boolean lUpdateIndex(String key, long index, Object value) {
        key = keyAndSpace(key);
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value的
     * @param key
     * @param count 移除多少个
     * @param value
     * @Author LinYouRu
     * @Date 15:08 2019/4/11
     * @return long 移除的个数
     **/
    public long lRemove(String key, long count, Object value) {
        key = keyAndSpace(key);
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
//    ============================私有方法============================

    /**
     * key添加命名空间
     * @param key
     * @Author LinYouRu
     * @Date 11:20 2019/6/6
     * @return String
     **/
    public String keyAndSpace(String key){
        return "rjtx:dw:"+key;
    }
}
