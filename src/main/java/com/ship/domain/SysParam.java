package com.ship.domain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 系统参数
 */
@Entity
@Table(name = "sys_param", catalog = "membershiptwo")
public class SysParam implements java.io.Serializable {

	/**
	 * 主键Id
	 */
	private Long id;
	/**
	 * 参数key
	 */
	private String parkey;

	/**
	 *接送数据
	 */
	private String json;
	/**
	 *添加时间
	 */
	private Date addTime;


	public SysParam() {
	}

	public SysParam(String json, Date addTime) {
		this.json = json;
		this.addTime = addTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "parkey")
	public String getParkey() {
		return parkey;
	}

	public void setParkey(String parkey) {
		this.parkey = parkey;
	}

	@Column(name = "json")
	public String getJson() {
		return this.json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Column(name = "addTime", length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}