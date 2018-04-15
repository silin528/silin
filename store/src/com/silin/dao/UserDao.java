package com.silin.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.silin.domain.User;
import com.silin.utils.DataSourceUtils;

public class UserDao {
	//添加用户
	public int addUser(User user) throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user(uid,username,password,gender,email,telephone,role,registTime,state,code) values (?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {user.getUid(),user.getUserName(),user.getPassword(),
				user.getGender(),user.getEmail(),user.getTelephone(),
				user.getRole(),user.getRegistTime(),user.getState(),user.getCode() };
				int update = runner.update(sql, params);
				return update;
	}
	//根据用户名与密码查找用户
	public static User findUserByUsernameAndPassword(String username,String password) throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where username=? and password=?";
		User existUser = runner.query(sql, new BeanHandler<User>(User.class),username,password);
		return existUser;
	}
	public User findByUsername(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where username=?";
		User existUser = runner.query(sql, new BeanHandler<User>(User.class),username);
		return existUser;
	}
//	public User findByCode(String code) throws SQLException {
//		// TODO Auto-generated method stub
//		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		String sql="select * from user where code=?";
//		User existUser = runner.query(sql, new BeanHandler<User>(User.class),code);
//		return existUser;
//	}
//	public void update(User user) throws SQLException {
//		// TODO Auto-generated method stub
//		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		String sql = "update user set username=?,password=?,gender=?,email=?,telephone=?,role=?,registTime=?,state=?,code=? where uid=? ";
//		
//		Object[] params = {user.getUserName(),user.getPassword(),
//				user.getGender(),user.getEmail(),user.getTelephone(),
//				user.getRole(),user.getRegistTime(),user.getState(),user.getCode(),user.getUid() };
//		runner.update(sql, params);
//	}
	//激活
	public void active(String activeCode) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update user set state=? where code=?";
		runner.update(sql, 1,activeCode);
		
	}
	//校验用户名是否存在
	public Long checkUsername(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from user where username=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(), username);
		return query;
	}
}
