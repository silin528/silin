package com.silin.service;

import java.sql.SQLException;

import com.silin.dao.UserDao;
import com.silin.domain.User;
import com.silin.exception.LoginException;

public class UserService{	
	public boolean register(User user){
		UserDao dao = new UserDao();
		//保存用户
		int row = 0;
		try{	
			row = dao.addUser(user);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return row > 0?true:false;
	}
	
	
	//登陆操作
	public User login(User user)throws LoginException{
		User login = null;
		try{
			//根据登陆时的表单输入的用户名与密码，查找用户
			login = UserDao.findUserByUsernameAndPassword(user.getUserName(),user.getPassword());
			//System.out.println("userService1" + user.getUserName());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("找不到用户");
		}
		return login;
	}


	public User findByUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		return userDao.findByUsername(username);
	}

	//激活
	public void active(String activeCode){
		UserDao dao = new UserDao();
		try {
			dao.active(activeCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	//校验用户名是否存在
		public boolean checkUsername(String username) {
			UserDao dao = new UserDao();
			Long isExist = 0L;
			try {
				isExist = dao.checkUsername(username);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return isExist>0?true:false;
		}




//	public static void activeUser(String code) throws SQLException {
//		// TODO Auto-generated method stub
//		//通过激活码查询用户
//		UserDao userDao = new UserDao();
//		User existUser = userDao.findByCode(code);
//		if(existUser == null){
//			throw new RuntimeException("用户激活码无效，请重新发送邮件");
//		}
//		//更新用户信息
//		existUser.setState(1);
//		existUser.setCode(null);
//		
//		userDao.update(existUser);
//	}


}
