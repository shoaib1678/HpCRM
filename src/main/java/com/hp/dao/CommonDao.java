package com.hp.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.InstanceAlreadyExistsException;
import javax.swing.text.AbstractDocument.LeafElement;
import javax.transaction.Transactional;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javassist.expr.Instanceof;

@Repository
@Transactional
public class CommonDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public int addDataToDb(Object objectdata) {
		Integer i = (Integer) sessionFactory.getCurrentSession().save(objectdata);
		return i;
	}

	public void updateDataToDb(Object objectdata) {
		sessionFactory.getCurrentSession().update(objectdata);
	}

	public Object getDataByMap(Map<String, Object> mapdata, Object objectdata, String orderbycolumn, String orderby,
			int start, int length) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(objectdata.getClass());
		for (Entry<String, Object> mapdataex : mapdata.entrySet()) {
			if(mapdataex.getKey().equals("availablity_date")) {
				SimpleDateFormat forDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = (String) mapdataex.getValue();
				criteria.add(Restrictions.eq("availablity_date", java.sql.Date.valueOf(date)));
			}else{
				System.out.println(mapdataex.getKey() + "-" + mapdataex.getValue());
				criteria.add(Restrictions.eq(mapdataex.getKey(), mapdataex.getValue()));
			}
			
		}
		if (orderby != null) {
			if (orderby.equalsIgnoreCase("asc")) {
				criteria.addOrder(Order.desc(orderbycolumn));
			} else {
				criteria.addOrder(Order.asc(orderbycolumn));
			}
		}
		criteria.setFirstResult(start);
		if (length != -1) {
			criteria.setMaxResults(length);
		}
		List<Object> list = criteria.list();
		return list;
	}
	public Object getDataByMapOr(Map<String, Object> mapdata, Object objectdata, String orderbycolumn, String orderby,
			int start, int length) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(objectdata.getClass());
		Disjunction disjunction = Restrictions.disjunction();
		for (Entry<String, Object> mapdataex : mapdata.entrySet()) {
			System.out.println(mapdataex.getKey() + "-" + mapdataex.getValue());
			disjunction.add(Restrictions.eq(mapdataex.getKey(), mapdataex.getValue()));
		}
		criteria.add(disjunction);
		if (orderby != null) {
			if (orderby.equalsIgnoreCase("asc")) {
				criteria.addOrder(Order.desc(orderbycolumn));
			} else {
				criteria.addOrder(Order.asc(orderbycolumn));
			}
		}
		criteria.setFirstResult(start);
		if (length != -1) {
			criteria.setMaxResults(length);
		}
		List<Object> list = criteria.list();
		return list;
	}
	public Object getDataByMapLike(Map<String, Object> mapdata, Object objectdata, String orderbycolumn, String orderby,
			int start, int length) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(objectdata.getClass());
		Disjunction data = Restrictions.disjunction();
		for (Entry<String, Object> mapdataex : mapdata.entrySet()) {
			if (mapdataex.getValue() instanceof Integer) {
				data.add(Restrictions.eq(mapdataex.getKey(), mapdataex.getValue()));
			} else {
				data.add(Restrictions.ilike(mapdataex.getKey(), (String) mapdataex.getValue(), MatchMode.ANYWHERE));
			}
		}
		criteria.add(data);
		if (orderby != null) {
			if (orderby.equalsIgnoreCase("asc")) {
				criteria.addOrder(Order.desc(orderbycolumn));
			} else {
				criteria.addOrder(Order.asc(orderbycolumn));
			}
		}
		criteria.setFirstResult(start);
		if (length != -1) {
			criteria.setMaxResults(length);
		}
		List<Object> list = criteria.list();
		return list;
	}
	
	// for and and or serach together
		public Object getDataByMapSearchAnd(Map<String, Object> anddata, Map<String, Object> mapdata, Object objectdata,
				String orderbycolumn, String orderby, int start, int length) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(objectdata.getClass());
			for (Entry<String, Object> mapdataex : anddata.entrySet()) {
				if (mapdataex.getValue() instanceof Integer) {
					criteria.add(Restrictions.eq(mapdataex.getKey(), mapdataex.getValue()));
				}else if (mapdataex.getKey().equals("createdAt") ){
					criteria.add(Restrictions.eq(mapdataex.getKey(), java.sql.Date.valueOf(mapdataex.getValue().toString()) ));
				}else if (mapdataex.getKey().equals("availablity_date") ){
					criteria.add(Restrictions.eq(mapdataex.getKey(), java.sql.Date.valueOf(mapdataex.getValue().toString()) ));
				}
				else {
					criteria.add(Restrictions.eq(mapdataex.getKey(), (String) mapdataex.getValue()));
				}
			}
			Disjunction data = Restrictions.disjunction();
			for (Entry<String, Object> mapdataex : mapdata.entrySet()) {
				if (mapdataex.getValue() instanceof Integer) {
					data.add(Restrictions.eq(mapdataex.getKey(), mapdataex.getValue()));
				} else {
					data.add(Restrictions.ilike(mapdataex.getKey(), (String) mapdataex.getValue(), MatchMode.ANYWHERE));
				}
			}
			criteria.add(data);
			System.out.println(data);
			if (orderby != null) {
				if (!orderby.equalsIgnoreCase("asc")) {
					criteria.addOrder(Order.desc(orderbycolumn));
				} else {
					criteria.addOrder(Order.asc(orderbycolumn));
				}
			}
			criteria.setFirstResult(start);
			if (length != -1) {
				criteria.setMaxResults(length);
			}
			List<Object> list = criteria.list();
			return list;
		}
	// for and and or serach size together
			public int getDataByMapSearchAndSize(Map<String, Object> anddata, Map<String, Object> mapdata, Object objectdata,
					String orderbycolumn, String orderby) {
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(objectdata.getClass());
				for (Entry<String, Object> mapdataex : anddata.entrySet()) {
					if (mapdataex.getValue() instanceof Integer) {
						criteria.add(Restrictions.eq(mapdataex.getKey(), mapdataex.getValue()));
					} else {
						criteria.add(Restrictions.eq(mapdataex.getKey(), (String) mapdataex.getValue()));
					}

				}
				Disjunction data = Restrictions.disjunction();
				for (Entry<String, Object> mapdataex : mapdata.entrySet()) {
					if (mapdataex.getValue() instanceof Integer) {
						data.add(Restrictions.eq(mapdataex.getKey(), mapdataex.getValue()));
					} else {
						data.add(Restrictions.ilike(mapdataex.getKey(), (String) mapdataex.getValue(), MatchMode.ANYWHERE));
					}
				}
				criteria.add(data);
				if (orderby != null) {
					if (!orderby.equalsIgnoreCase("asc")) {
						criteria.addOrder(Order.desc(orderbycolumn));
					} else {
						criteria.addOrder(Order.asc(orderbycolumn));
					}
				}

				List<Object> list = criteria.list();
				return list.size();
			}
	public int getDataByMapSize(Map<String, Object> mapdata, Object objectdata, String orderbycolumn, String orderby,
			int start, int length) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(objectdata.getClass());
		for (Entry<String, Object> mapdataex : mapdata.entrySet()) {
			System.out.println(mapdataex.getKey() + "-" + mapdataex.getValue());
			criteria.add(Restrictions.eq(mapdataex.getKey(), mapdataex.getValue()));
		}
		if (orderby != null) {
			if (orderby.equalsIgnoreCase("asc")) {
				criteria.addOrder(Order.desc(orderbycolumn));
			} else {
				criteria.addOrder(Order.asc(orderbycolumn));
			}
		}
		criteria.setFirstResult(start);
		if (length != -1) {
			criteria.setMaxResults(length);
		}
		List<Object> list = criteria.list();
		return list.size();
	}
	
	public void delete(Object object,String sno) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(object.getClass());
		Object objectdata = criteria.add(Restrictions.eq("sno",Integer.parseInt(sno))).uniqueResult();
		sessionFactory.getCurrentSession().delete(objectdata);
	
	}

	public int updateMethodForAll(Map<String, Object> updatecolumn, String tableName, Map<String, Object> wheredata) {
		try {
			String query = "";
			String wherequery = "";
			for (Map.Entry<String, Object> entry : updatecolumn.entrySet()) {
				if(entry.getValue() instanceof Integer){
					if(Integer.parseInt(entry.getValue()+"") > 0){
						if (!query.equalsIgnoreCase("")) {
							query = query + " ," + entry.getKey().trim() + " = '" + entry.getValue() + "'";
						} else {
							query = query + " " + entry.getKey().trim() + " = '" + entry.getValue() + "'";
						}
					}					
				}
				else if(entry.getValue() instanceof List){
					
					
				}
				else{
					if (entry.getValue() != null) {
						if (!query.equalsIgnoreCase("")) {
							query = query + " ," + entry.getKey().trim() + " = '" + entry.getValue() + "'";
						} else {
							query = query + " " + entry.getKey().trim() + " = '" + entry.getValue() + "'";
						}
					}
				}				
			}
			for (Map.Entry<String, Object> entry : wheredata.entrySet()) {
				if (entry.getValue() != null) {
					if (!wherequery.equalsIgnoreCase("")) {
						wherequery = wherequery + " and " + entry.getKey().trim() + " = '" + entry.getValue() + "'";
					} else {
						wherequery = wherequery + " " + entry.getKey().trim() + " = '" + entry.getValue() + "'";
					}
				}

			}
			String query1 = "Update " + tableName + " set " + query + " where " + wherequery;
			Query sql = sessionFactory.openSession().createQuery(query1);
			int result = sql.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<String> getDistinctData(String column_name, Object object) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(object.getClass());
		List<String> distinctDataList = criteria.setProjection(Projections.distinct(Projections.property(column_name))).list();
		return distinctDataList;
    }
	
	
	public List<Date> getDistinctDataDate(String column_name, Object object) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(object.getClass());
		List<Date> distinctDataList = criteria.setProjection(Projections.distinct(Projections.property(column_name))).list();
		return distinctDataList;
    }

	public int deleteById(Map<String, Object> where) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("delete from " + where.get("tablename") + "  where " + where.get("column") + " =:"+ where.get("column") + "");
		q.setParameter(where.get("column").toString(), where.get("value"));
		int result = q.executeUpdate();
		transaction.commit();
		session.clear();
		session.close();
		return result;
	}

	public void deleteSafariAvailabilityByDestinationId(int destinationId) {
	    Session session = null;
	    Transaction tx = null;
	    try {
	        session = sessionFactory.openSession();
	        tx = session.beginTransaction();
	        String hqlDelete = "DELETE FROM SafariAvailablity WHERE destination_id = "+destinationId+"";
	        Query deleteQuery = session.createQuery(hqlDelete);
	        int rowCount = deleteQuery.executeUpdate();
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}
	public void deleteHotelAvailabilityByDestinationId(int  destination_id,int hotel_id) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String hqlDelete = "DELETE FROM HotelAvailability WHERE park_id = "+destination_id+" AND hotel_id = "+hotel_id+"";
			Query deleteQuery = session.createQuery(hqlDelete);
			int rowCount = deleteQuery.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void deleteBookingDate(int sno) {
	    Session session = null;
	    Transaction tx = null;
	    try {
	        session = sessionFactory.openSession();
	        tx = session.beginTransaction();
	        String hqlDelete = "DELETE FROM BookingDate WHERE sno = "+sno+"";
	        Query deleteQuery = session.createQuery(hqlDelete);
	        int rowCount = deleteQuery.executeUpdate();
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}
}
