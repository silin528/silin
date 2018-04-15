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
			//��redis��ȡ������Ϣ
			//1��ȡ����
			j = JedisPoolUtils.getJedis();
			
			//2.��ȡ���� �ж������Ƿ�Ϊ��
			value = j.get("cateorg_list");
			//2.1����Ϊ�գ�ֱ�ӷ�������
			if(value != null){
				System.out.println("�����������ݿ�");
				return value;
			}
			//2.2��Ϊ�գ���mysql���ݿ��л�ȡ������redis��
			List<Category>clist = findAll();
			//value = JsonUtil.list2Json(clist);
			//��value����redis��
			j.set("category_list",value);
			return value;
		}finally{
			JedisPoolUtils.close(j);
		}
	}

}
