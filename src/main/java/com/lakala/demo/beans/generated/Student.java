package com.lakala.demo.beans.generated;

import java.util.Date;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.annotations.Id;
import com.lakala.demo.util.ReflectUtil;

public class Student {
	@Id
	private ObjectId id;
	private String loginName;
	private String name;
	private String email;
	private String status;
	private String note;
	private Date createDate;
	private Date updateDate;

	@Override
	public String toString() {
		return ReflectUtil.fieldsToString(this);
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}