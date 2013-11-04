package com.lakala.demo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.jmkgreen.morphia.query.Query;
import com.lakala.demo.beans.generated.Student;

@Component
public class StudentMongoDao extends MongoBaseDAO<Student> {
	/**
	 * findAll(默认按照createDate升序，降序查询"-createDate")
	 * 
	 * @return
	 *         返回类型：List<TabStaticsLog>
	 * @exception
	 * @since 1.0.0
	 */
	public List<Student> findAll(int start, int end) {
		Query<Student> query = getDS().createQuery(Student.class);
		query.skip(start).limit(end).order("createDate");
		return query.asList();
	}
}
