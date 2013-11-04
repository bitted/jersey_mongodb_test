package com.lakala.demo.beans;

import com.lakala.demo.util.ReflectUtil;

/**
 * 类名称：RetCode
 * 类描述：(接收返回作业系统返回值的bean)
 * 创建人：litj
 * 创建时间：2013-9-7 上午10:42:50
 * 修改人：
 * 修改时间：2013-9-7 上午10:42:50
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class RetCode {

	private String success;// 结果
	private String msg;// 备注
	private String mobile;// 首次开通手机号
	private String prov;// 省份编码
	private String prov_name;// 省份名称
	private String city;// 城市编码
	private String city_name;// 城市名称
	private String area;// 城区编码
	private String area_name;// 城区名称
	private String pm_term_type;// 终端类型1自用 2商用
	private String bankno;// 账户号
	private String contact_name;// 负责人姓名
	private String info;//我要收款开通，收单系统返回的信息
	private String step;//终端开通成功的阶段 0-都失败了 1-终端开通成功 2-我要收款开通成功

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPm_term_type() {
		return pm_term_type;
	}

	public void setPm_term_type(String pm_term_type) {
		this.pm_term_type = pm_term_type;
	}

	public String getProv_name() {
		return prov_name;
	}

	public void setProv_name(String prov_name) {
		this.prov_name = prov_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	@Override
	public String toString() {
		return ReflectUtil.fieldsToString(this);
	}

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}
	
}
