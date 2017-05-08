package com.ship.domain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Mryang
 * @createTime 2017年4月7日
 * @version 1.0
 * 用户银行账户
 */
@Entity
@Table(name = "userbank", catalog = "membershiptwo")
public class UserBank implements java.io.Serializable {

	private static final long serialVersionUID = 3532992823407742788L;

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 类型ID
	 */
	private Long bankId;
	/**
	 * 用户Id
	 */
	private Long userId;
	/**
	 * 0:银联，1：支付宝
	 */
	private Integer type;
	/**
	 * 账户
	 */
	private String banknum;
	/**
	 * 支付宝账户
	 */
	private String alipayAccount;
	/**
	 * 银行账户名称
	 */
	private String bankaccountname;
	/**
	 *支行地址
	 */
	private String address;
	/**
	 * 添加时间
	 */
	private Date addTime;

	public UserBank() {
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

	@Column(name = "bankId")
	public Long getBankId() {
		return this.bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "banknum", length = 150)
	public String getBanknum() {
		return this.banknum;
	}


	public void setBanknum(String banknum) {
		this.banknum = banknum;
	}

	@Column(name = "alipayAccount", length = 150)
	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	@Column(name = "bankaccountname", length = 150)
	public String getBankaccountname() {
		return this.bankaccountname;
	}

	public void setBankaccountname(String bankaccountname) {
		this.bankaccountname = bankaccountname;
	}

	@Column(name = "address", length = 150)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "addTime", length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}