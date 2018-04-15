package com.silin.service;

import java.sql.SQLException;
import java.util.List;

import redis.clients.jedis.Jedis;

import com.silin.dao.CategoryDao;
import com.silin.domain.Category;
import com.silin.utils.JedisPoolUtils;

public class CategoryService {

	public List<Category> findAll() throws SQLException {
		// TODO Auto-generated method stub
		CategoryDao categoryDao = new CategoryDao();
		return categoryDao.findAll();
	}
	
	public String findAllByAjax() throws Exception{
		Jedis j = null;
		String value = null;
		try{
			//从redis获取分类信息
			//1获取连接
			j = JedisPoolUtils.getJedis();
			
			//2.获取数据 判断数据是否为空
			value = j.get("cateorg_list");
			//2.1若不为空，直接返回数据
			if(value != null){
				System.out.println("缓存中有数据库");
				return value;
			}
			//2.2若为空，从mysql数据库中获取并放入redis中
			List<Category>clist = findAll();
			//value = JsonUtil.list2Json(clist);
			//将value放入redis中
			j.set("category_list",value);
			return value;
		}finally{
			JedisPoolUtils.close(j);
		}
	}

}
