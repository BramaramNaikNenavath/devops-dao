package com.tek.dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tek.beans.Login;
import com.tek.utils.HibernateUtil;

public class LoginDao {

	private static Logger log = Logger.getLogger(RegisterDao.class);
	private static SessionFactory factory = HibernateUtil.getSessionFactory();

	public static boolean loginAuth(String user, String password) {
		log.info(">> LoginDao Authenticating User >>");
		try (Session session = factory.openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Login> query = criteriaBuilder.createQuery(Login.class);

			Root<Login> root = query.from(Login.class);
			CriteriaQuery<Login> cQuery = query.select(root).where(criteriaBuilder.equal(root.get("userName"), user));
			try {
				Login login = session.createQuery(cQuery).getSingleResult();
				log.info(">> login username >> " + login.getUserName());
				if (login != null) {
					if (login.getUserName().equals(user) && login.getPassword().equals(password)) {
						return true;
					} else {
						return false;
					}
				}
			}catch(NoResultException ex){
				return false;
			}
		}
		return false;
	}
}
