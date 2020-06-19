package com.tek.dao;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mysql.cj.log.Log;
import com.tek.beans.Login;
import com.tek.beans.Register;
import com.tek.utils.HibernateUtil;

public class RegisterDao {

	private static Logger log = Logger.getLogger(RegisterDao.class);
	private static SessionFactory factory = HibernateUtil.getSessionFactory();

	Transaction transaction = null;

	public void register(Register registerObj) {
		log.info(">> RegisterDao register() invoked >> " + registerObj);
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();

			Login login = new Login();
			login.setFullName(registerObj.getFullName());
			login.setUserName(registerObj.getEmail());
			login.setPassword(registerObj.getPassword());
			login.setRegister(registerObj);

			registerObj.setLogin(login);

			session.save(registerObj);

			transaction.commit();
		} catch (Exception e) {
		}
	}
}
