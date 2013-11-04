package com.lakala.demo.resource;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lakala.demo.beans.generated.Student;
import com.lakala.demo.config.Constant;
import com.lakala.demo.exception.ServiceException;
import com.lakala.demo.service.StudentMongoService;
import com.lakala.demo.service.impl.StudentMongoServiceImpl;
import com.lakala.demo.util.JsonMapper;
import com.lakala.demo.util.ResultUtil;
import com.lakala.demo.util.SpringUtils;

/**
 * 类名称：MongoResource
 * 类描述：(实例)
 * 创建人：litj
 * 创建时间：2013-11-1 下午01:42:05
 * 修改人：
 * 修改时间：2013-11-1 下午01:42:05
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Path("/mongo/student/")
@Component
public class StudentMongoResource {
	protected static Logger logger = LoggerFactory.getLogger(StudentMongoResource.class);
	private static JsonMapper binder = JsonMapper.nonEmptyMapper();

	@Autowired
	private StudentMongoService studentMongoService;

	public StudentMongoResource() {
		studentMongoService = SpringUtils.getBean(StudentMongoServiceImpl.class);
	}

	@POST
	@Path("insert")
	@Consumes
	@Produces(Constant.JSON_UTF_8)
	public void insert(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("loginName") String loginName, @FormParam("status") String status, @FormParam("note") String note)
			throws ServiceException {

		Student student = new Student();

		student.setName(name);
		student.setEmail(email);
		student.setLoginName(loginName);
		student.setStatus("1");
		student.setNote(note);
		student.setCreateDate(new Date());

		studentMongoService.insert(student);
		logger.info("\n==insert()==student==OK===");
	}

	@POST
	@Path("delete")
	@Consumes
	@Produces(Constant.JSON_UTF_8)
	public void delete(@FormParam("id") String id) throws ServiceException {

		studentMongoService.delete(new ObjectId(id));

		logger.info("\n==delete()==student==OK===");
	}

	@POST
	@Path("update")
	@Consumes
	@Produces(Constant.JSON_UTF_8)
	public void update(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("loginName") String loginName, @FormParam("status") String status, @FormParam("note") String note)
			throws ServiceException {
		Student student = new Student();

		student.setName(name);
		student.setEmail(email);
		student.setLoginName(loginName);
		student.setStatus("0");
		student.setNote(note);
		student.setUpdateDate(new Date());

		studentMongoService.update(student);

		logger.info("\n==update()==student==OK===");
	}

	@POST
	@Path("query")
	@Consumes
	@Produces(Constant.JSON_UTF_8)
	public String query(@FormParam("id") String id) throws ServiceException {
		ObjectId objectId = new ObjectId(id);

		Student student = studentMongoService.query(objectId);
		logger.info("\n==query()==student==OK===");

		return binder.toJson(ResultUtil.getResult(student));
	}

	@POST
	@Path("queryByName")
	@Consumes
	@Produces(Constant.JSON_UTF_8)
	public String queryByName(@FormParam("name") String name) throws ServiceException {
		Student student = new Student();
		student.setName(name);

		List<Student> list = studentMongoService.query(student);
		logger.info("\n==queryByName()==student==OK===");

		return binder.toJson(ResultUtil.getResult(list));
	}
}
