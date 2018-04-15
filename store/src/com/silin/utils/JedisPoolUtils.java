package com.silin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
	private static JedisPoolConfig config;
	private static JedisPool pool = null;
	
	static{
		config = new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(2);
		pool = new JedisPool(config, "192.168.17.132, 6379");
	}

	//获取连接方法
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	public static void close(Jedis j){
		if(j != null){
			j.close();
		}
	}
	
	
	
	
}
