package org.larry.exercise.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.larry.exercise.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * Created by Larry on 2017/2/24.
 * 缓存
 */
public class RedisDao {

    private Logger logger = LoggerFactory.getLogger(RedisDao.class) ;

    private JedisPool jedisPool  ;

    public RedisDao(String host,int port,JedisPoolConfig jedisPoolConfig){
        jedisPool = new JedisPool(jedisPoolConfig, host, port, 2000);
       // jedisPool = new JedisPool(host,port) ;
    }

    public RedisDao(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public RedisDao(){

    }

    private RuntimeSchema<Seckill> runtimeSchema = RuntimeSchema.createFrom(Seckill.class) ;

    public Seckill getSeckill(long seckillId){
        try {
            Jedis jedis = jedisPool.getResource() ;
           // jedis.auth("123456") ;
            try {
                String key = "seckill:"+seckillId ;
                // 使用另一种方式反序列化
                byte [] objects = jedis.get(key.getBytes()) ;
                if(objects!=null){
                    Seckill seckill = runtimeSchema.newMessage() ;
                    ProtobufIOUtil.mergeFrom(objects,seckill,runtimeSchema);
                    return seckill ;
                }
            }finally {
                jedisPool.close();
            }
        }catch (Exception e){
            jedisPool.close();
            logger.info(e.getMessage(),e);
        }
        return null ;
    }

    public String putSeckill(Seckill seckill){
        try{
            Jedis jedis = jedisPool.getResource() ;
            String key = "seckill:"+seckill.getSeckillId() ;
            try {
                // 设置默认长度
                byte [] seckillBytes = ProtobufIOUtil.toByteArray(seckill,runtimeSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE)) ;
                int timeout = 60 * 60  ; // 一个小时
                String result = jedis.setex(key.getBytes(),timeout,seckillBytes) ;
                return result ;
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            jedisPool.close();
            logger.info(e.getMessage(),e);
        }
        return  null ;
    }
}
