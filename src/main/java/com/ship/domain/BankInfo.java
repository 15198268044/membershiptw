package com.ship.domain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 银行账户
 */
@Entity
@Table(name = "bankinfo", catalog = "membershiptwo")
public class BankInfo implements java.io.Serializable {

	/**
	 *主键
	 */
	private Long id;
	/**
	 *银行账户
	 */
	private String bankname;
	/**
	 *状态（0：禁用，1：启用）
	 */
	private Integer status;
	/**
	 *添加时间
	 */
	private Date addTime;

	public BankInfo() {

	}

	public BankInfo(String bankname, Integer status, Date addTime) {
		this.bankname = bankname;
		this.status = status;
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

	@Column(name = "bankname", length = 50)
	public String getBankname() {
		return this.bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "addTime")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}