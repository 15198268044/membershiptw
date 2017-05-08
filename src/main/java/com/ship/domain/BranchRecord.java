package com.ship.domain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 分润
 */
@Entity
@Table(name = "branchrecord", catalog = "membershiptwo")
public class BranchRecord implements java.io.Serializable {

	/**
	 * id
	 */
	private Long id;
	/**
	 *分红金额
	 */
	private Double tatalmoney;
	/**
	 *会员名称
	 */
	private String realname;
	/**
	 *流水号
	 */
	private String serialnumber;
	/**
	 *备注
	 */
	private String mark;
	/**
	 *会员Id
	 */
	private Long vipId;
	/**
	 *等级
	 */
	private Integer grade;
	/**
	 *实际金额
	 */
	private Double actualmoney;
	/**
	 *添加时间
	 */
	private Date addTime;

	/**
	 * 非数据字段
	 */

	private String dateTime;
	/**
	 * 用户等级
	 */
	private String usergrade;

	public BranchRecord() {
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

	@Column(name = "tatalmoney", precision = 10)
	public Double getTatalmoney() {
		return this.tatalmoney;
	}

	public void setTatalmoney(Double tatalmoney) {
		this.tatalmoney = tatalmoney;
	}
	@Column(name = "realname", precision = 10)
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "serialnumber", length = 100)
	public String getSerialnumber() {
		return this.serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	@Column(name = "mark", length = 150)
	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Column(name = "vipId")
	public Long getVipId() {
		return this.vipId;
	}

	public void setVipId(Long vipId) {
		this.vipId = vipId;
	}

	@Column(name = "grade")
	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Column(name = "actualmoney", precision = 10)
	public Double getActualmoney() {
		return this.actualmoney;
	}

	public void setActualmoney(Double actualmoney) {
		this.actualmoney = actualmoney;
	}

	@Column(name = "addTime", length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Transient
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	@Transient
	public String getUsergrade() {
		return usergrade;
	}

	public void setUsergrade(String usergrade) {
		this.usergrade = usergrade;
	}
}