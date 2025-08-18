package com.hp.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hp.model.Authorship_Article;

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
			if (mapdataex.getKey().equals("availablity_date")) {
				SimpleDateFormat forDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = (String) mapdataex.getValue();
				criteria.add(Restrictions.eq("availablity_date", java.sql.Date.valueOf(date)));
			} else {
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

		for (Entry<String, Object> entry : anddata.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if ("month".equalsIgnoreCase(key) || "year".equalsIgnoreCase(key)) {
				continue; // skip for now, handled below
			}

			if (value instanceof Integer) {
				criteria.add(Restrictions.eq(key, value));
			} else if ("createdAt".equalsIgnoreCase(key)) {
				criteria.add(Restrictions.eq(key, java.sql.Date.valueOf(value.toString())));
			} else {
				criteria.add(Restrictions.eq(key, value.toString()));
			}
		}

		Disjunction disjunction = Restrictions.disjunction();
		for (Entry<String, Object> entry : mapdata.entrySet()) {
			if (entry.getValue() instanceof Integer) {
				disjunction.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			} else {
				disjunction.add(Restrictions.ilike(entry.getKey(), entry.getValue().toString(), MatchMode.ANYWHERE));
			}
		}
		criteria.add(disjunction);

		if (anddata.containsKey("month") && anddata.containsKey("year")) {
			Integer month = (Integer) anddata.get("month");
			Integer year = (Integer) anddata.get("year");

			criteria.add(Restrictions.sqlRestriction("MONTH(createdAt) = ?", month, StandardBasicTypes.INTEGER));
			criteria.add(Restrictions.sqlRestriction("YEAR(createdAt) = ?", year, StandardBasicTypes.INTEGER));
		}

		if (orderby != null) {
			if ("asc".equalsIgnoreCase(orderby)) {
				criteria.addOrder(Order.asc(orderbycolumn));
			} else {
				criteria.addOrder(Order.desc(orderbycolumn));
			}
		}

		criteria.setFirstResult(start);
		if (length != -1) {
			criteria.setMaxResults(length);
		}

		return criteria.list();
	}

	// for and and or serach size together
	public int getDataByMapSearchAndSize(Map<String, Object> anddata, Map<String, Object> mapdata, Object objectdata,
			String orderbycolumn, String orderby) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(objectdata.getClass());

		for (Entry<String, Object> entry : anddata.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if ("month".equalsIgnoreCase(key) || "year".equalsIgnoreCase(key)) {
				continue;
			}

			if (value instanceof Integer) {
				criteria.add(Restrictions.eq(key, value));
			} else {
				criteria.add(Restrictions.eq(key, value.toString()));
			}
		}

		Disjunction disjunction = Restrictions.disjunction();
		for (Entry<String, Object> entry : mapdata.entrySet()) {
			if (entry.getValue() instanceof Integer) {
				disjunction.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			} else {
				disjunction.add(Restrictions.ilike(entry.getKey(), entry.getValue().toString(), MatchMode.ANYWHERE));
			}
		}
		criteria.add(disjunction);

		if (anddata.containsKey("month") && anddata.containsKey("year")) {
			Integer month = (Integer) anddata.get("month");
			Integer year = (Integer) anddata.get("year");

			criteria.add(Restrictions.sqlRestriction("MONTH(createdAt) = ?", month, StandardBasicTypes.INTEGER));
			criteria.add(Restrictions.sqlRestriction("YEAR(createdAt) = ?", year, StandardBasicTypes.INTEGER));
		}

		if (orderby != null) {
			if ("asc".equalsIgnoreCase(orderby)) {
				criteria.addOrder(Order.asc(orderbycolumn));
			} else {
				criteria.addOrder(Order.desc(orderbycolumn));
			}
		}

		return criteria.list().size();
	}

	// for and and or search size together and filter with date Range
	public Object getDataByMapWithDateRange(
	        Map<String, Object> anddata,
	        Map<String, Object> mapdata,
	        Object objectdata,
	        String orderbycolumn,
	        String orderby,
	        int start,
	        int length,
	        String fromDateStr,
	        String toDateStr) {

	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(objectdata.getClass());

	    // 🔹 AND conditions
	    for (Entry<String, Object> entry : anddata.entrySet()) {
	        String key = entry.getKey();
	        Object value = entry.getValue();

	        if (value instanceof Integer) {
	            criteria.add(Restrictions.eq(key, value));
	        } else {
	            criteria.add(Restrictions.eq(key, value.toString()));
	        }
	    }

	    // 🔹 OR search (Disjunction)
	    Disjunction disjunction = Restrictions.disjunction();
	    for (Entry<String, Object> entry : mapdata.entrySet()) {
	        if (entry.getValue() instanceof Integer) {
	            disjunction.add(Restrictions.eq(entry.getKey(), entry.getValue()));
	        } else {
	            disjunction.add(Restrictions.ilike(entry.getKey(), entry.getValue().toString(), MatchMode.ANYWHERE));
	        }
	    }
	    criteria.add(disjunction);

	    // 🔹 Filter by createdAt using from and to date
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    try {
	        if (fromDateStr != null && toDateStr != null &&
	            !fromDateStr.isEmpty() && !toDateStr.isEmpty()) {

	            LocalDate fromDate = LocalDate.parse(fromDateStr, formatter);
	            LocalDate toDate = LocalDate.parse(toDateStr, formatter);

	            LocalDateTime fromDateTime = fromDate.atStartOfDay(); // 00:00:00
	            LocalDateTime toDateTime = toDate.atTime(LocalTime.MAX); // 23:59:59.999

	            Timestamp fromTimestamp = Timestamp.valueOf(fromDateTime);
	            Timestamp toTimestamp = Timestamp.valueOf(toDateTime);

	            criteria.add(Restrictions.between("createdAt", fromTimestamp, toTimestamp));
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Consider logging instead
	    }

	    // 🔹 Ordering
	    if (orderby != null && !orderby.isEmpty()) {
	        if ("asc".equalsIgnoreCase(orderby)) {
	            criteria.addOrder(Order.asc(orderbycolumn));
	        } else {
	            criteria.addOrder(Order.desc(orderbycolumn));
	        }
	    }

	    // 🔹 Pagination
	    criteria.setFirstResult(start);
	    if (length != -1) {
	        criteria.setMaxResults(length);
	    }

	    return criteria.list();
	}
	
	//For Count and filter with date Range 
	public int getDataByMapWithDateRangeSize(
	        Map<String, Object> anddata,
	        Map<String, Object> mapdata,
	        Object objectdata,
	        String orderbycolumn,
	        String orderby,
	        String fromDateStr,
	        String toDateStr) {

	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(objectdata.getClass());

	    // 🔹 Apply AND conditions
	    for (Entry<String, Object> entry : anddata.entrySet()) {
	        String key = entry.getKey();
	        Object value = entry.getValue();

	        if (value instanceof Integer) {
	            criteria.add(Restrictions.eq(key, value));
	        } else {
	            criteria.add(Restrictions.eq(key, value.toString()));
	        }
	    }

	    // 🔹 Apply OR search (disjunction)
	    Disjunction disjunction = Restrictions.disjunction();
	    for (Entry<String, Object> entry : mapdata.entrySet()) {
	        if (entry.getValue() instanceof Integer) {
	            disjunction.add(Restrictions.eq(entry.getKey(), entry.getValue()));
	        } else {
	            disjunction.add(Restrictions.ilike(entry.getKey(), entry.getValue().toString(), MatchMode.ANYWHERE));
	        }
	    }
	    criteria.add(disjunction);

	    // 🔹 Handle from-to date filtering on createdAt (DATETIME)
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    try {
	        if (fromDateStr != null && toDateStr != null &&
	            !fromDateStr.isEmpty() && !toDateStr.isEmpty()) {

	            LocalDate fromDate = LocalDate.parse(fromDateStr, formatter);
	            LocalDate toDate = LocalDate.parse(toDateStr, formatter);

	            LocalDateTime fromDateTime = fromDate.atStartOfDay(); // 00:00:00
	            LocalDateTime toDateTime = toDate.atTime(LocalTime.MAX); // 23:59:59.999

	            Timestamp fromTimestamp = Timestamp.valueOf(fromDateTime);
	            Timestamp toTimestamp = Timestamp.valueOf(toDateTime);

	            criteria.add(Restrictions.between("createdAt", fromTimestamp, toTimestamp));
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Consider logging this instead of just printing
	    }

	    // 🔹 Apply ordering if needed
	    if (orderby != null && !orderby.isEmpty()) {
	        if ("asc".equalsIgnoreCase(orderby)) {
	            criteria.addOrder(Order.asc(orderbycolumn));
	        } else {
	            criteria.addOrder(Order.desc(orderbycolumn));
	        }
	    }

	    // 🔹 Return total count
	    return criteria.list().size();
	}
	public void delete(Object object, String sno) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(object.getClass());
		Object objectdata = criteria.add(Restrictions.eq("sno", Integer.parseInt(sno))).uniqueResult();
		sessionFactory.getCurrentSession().delete(objectdata);

	}

	public int updateMethodForAll(Map<String, Object> updatecolumn, String tableName, Map<String, Object> wheredata) {
		try {
			String query = "";
			String wherequery = "";
			for (Map.Entry<String, Object> entry : updatecolumn.entrySet()) {
				if (entry.getValue() instanceof Integer) {
					if (Integer.parseInt(entry.getValue() + "") > 0) {
						if (!query.equalsIgnoreCase("")) {
							query = query + " ," + entry.getKey().trim() + " = '" + entry.getValue() + "'";
						} else {
							query = query + " " + entry.getKey().trim() + " = '" + entry.getValue() + "'";
						}
					}
				} else if (entry.getValue() instanceof List) {

				} else {
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
		List<String> distinctDataList = criteria.setProjection(Projections.distinct(Projections.property(column_name)))
				.list();
		return distinctDataList;
	}

	public List<Date> getDistinctDataDate(String column_name, Object object) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(object.getClass());
		List<Date> distinctDataList = criteria.setProjection(Projections.distinct(Projections.property(column_name)))
				.list();
		return distinctDataList;
	}

	public int deleteById(Map<String, Object> where) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("delete from " + where.get("tablename") + "  where " + where.get("column") + " =:"
				+ where.get("column") + "");
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
			String hqlDelete = "DELETE FROM SafariAvailablity WHERE destination_id = " + destinationId + "";
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

	public void deleteHotelAvailabilityByDestinationId(int destination_id, int hotel_id) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String hqlDelete = "DELETE FROM HotelAvailability WHERE park_id = " + destination_id + " AND hotel_id = "
					+ hotel_id + "";
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
			String hqlDelete = "DELETE FROM BookingDate WHERE sno = " + sno + "";
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

	public List<String> getDistinctDataByDateRange(String columnName, Object object, String from_date, String to_date) {
	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(object.getClass());

	    // Apply projection for distinct column
	    criteria.setProjection(Projections.distinct(Projections.property(columnName)));
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    try {
	    	 LocalDate fromDate = LocalDate.parse(from_date, formatter);
	            LocalDate toDate = LocalDate.parse(to_date, formatter);

	            LocalDateTime fromDateTime = fromDate.atStartOfDay(); // 00:00:00
	            LocalDateTime toDateTime = toDate.atTime(LocalTime.MAX); // 23:59:59.999

	            Timestamp fromTimestamp = Timestamp.valueOf(fromDateTime);
	            Timestamp toTimestamp = Timestamp.valueOf(toDateTime);

	            criteria.add(Restrictions.between("createdAt", fromTimestamp, toTimestamp));

	    } catch (Exception e) {
	        e.printStackTrace();
	        // Optionally, handle error appropriately
	        return new ArrayList<>();
	    }

	    @SuppressWarnings("unchecked")
	    List<String> distinctDataList = criteria.list();

	    return distinctDataList;
	}

}
