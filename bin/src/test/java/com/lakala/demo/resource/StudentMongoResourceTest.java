package com.lakala.demo.resource;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lakala.demo.beans.generated.Student;
import com.lakala.demo.exception.ServiceException;
import com.lakala.demo.service.StudentMongoService;
import com.lakala.demo.util.DateUtils;

public class StudentMongoResourceTest extends BaseTest {

	@Autowired
	StudentMongoService studentMongoService;

	String uploadDate = DateUtils.getNowDate("yyyy-MM-dd HH:mm:ss");

	@Test
	public void add() throws ServiceException {

		for (int i = 0; i < 1000; i++) {
			Student student = new Student();

			student.setName("zhangsan" + i);
			student.setEmail("zhangsan" + i + "@gmail.com");
			student.setLoginName("zhangsan" + i);
			student.setNote("张三的备注");
			student.setStatus("1");
			student.setCreateDate(new Date());

			studentMongoService.insert(student);
			System.out.println(i);
		}
	}

	/**
	 * deleteOne(删除根据Id查询出来的实体)
	 * 返回类型：void
	 * 
	 * @throws ServiceException
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void deleteOne() throws ServiceException {
		System.out.println(studentMongoService);
		studentMongoService.delete(new ObjectId("52735ab6fd42d770cbabe6bc"));
	}

	/**
	 * update(获取一个对象并且更新,对象更新)
	 * 
	 * @throws ServiceException
	 *             返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void merge() throws ServiceException {
		Student student = studentMongoService.query(new ObjectId("52735ab6fd42d770cbabe6c1"));

		student.setName("张三哥哥");
		student.setUpdateDate(new Date());

		studentMongoService.merge(student);
	}

	/**
	 * update(获取一个对象并且更新，原子更新)
	 * 
	 * @throws ServiceException
	 *             返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void update() throws ServiceException {
		Student student = studentMongoService.query(new ObjectId("52735ab6fd42d770cbabe6c1"));

		student.setName("张三哥哥");
		student.setUpdateDate(new Date());

		studentMongoService.update(student);
	}

	@Test
	public void updateBetch() throws ServiceException {
		Student student = new Student();
		student.setLoginName("zhangsan1");

		student.setName("李四弟弟");
		student.setUpdateDate(new Date());

		studentMongoService.updateBetch(student);

		System.out.println("==批量更新成功！==");
	}

	/**
	 * findAll(查询所有数据，默认按照createDate升序)
	 * 返回类型：void
	 * 
	 * @throws ServiceException
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void findAll() throws ServiceException {
		List<Student> list = studentMongoService.findAll(0, 100);

		System.out.println("===list大小==" + list.size());
		for (Student entity : list) {
			System.out.println(entity.toString());
		}
	}
}
