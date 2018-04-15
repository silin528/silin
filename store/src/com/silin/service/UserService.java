package com.silin.service;

import java.sql.SQLException;

import com.silin.dao.UserDao;
import com.silin.domain.User;
import com.silin.exception.LoginException;

public class UserService{	
	public boolean register(User user){
		UserDao dao = new UserDao();
		//�����û�
		int row = 0;
		try{	
			row = dao.addUser(user);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return row > 0?true:false;
	}
	
	
	//��½����
	public User login(User user)throws LoginException{
		User login = null;
		try{
			//���ݵ�½ʱ�ı�������û��������룬�����û�
			login = UserDao.findUserByUsernameAndPassword(user.getUserName(),user.getPassword());
			//System.out.println("userService1" + user.getUserName());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("�Ҳ����û�");
		}
		return login;
	}


	public User findByUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		return userDao.findByUsername(username);
	}

	//����
	public void active(String activeCode){
		UserDao dao = new UserDao();
		try {
			dao.active(activeCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	//У���û����Ƿ����
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
//		//ͨ���������ѯ�û�
//		UserDao userDao = new UserDao();
//		User existUser = userDao.findByCode(code);
//		if(existUser == null){
//			throw new RuntimeException("�û���������Ч�������·����ʼ�");
//		}
//		//�����û���Ϣ
//		existUser.setState(1);
//		existUser.setCode(null);
//		
//		userDao.update(existUser);
//	}


}
