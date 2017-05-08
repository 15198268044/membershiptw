package com.ship.domain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 分润记录
 */
@Entity
@Table(name = "db_admin", catalog = "membershiptwo", uniqueConstraints = @UniqueConstraint(columnNames = "account"))
public class DbAdmin implements java.io.Serializable {

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 *账户
	 */
	private String account;
	/**
	 *密码
	 */
	private String password;
	/**
	 *登录时间
	 */
	private Date loginTime;
	/**
	 *登录次数
	 */
	private Integer loginnum;
	/**
	 *登录Ip
	 */
	private String loginIp;
	/**
	 *状态锁
	 */
	private Short islock;
	/**
	 *创建时间
	 */
	private Date createTime;

	public DbAdmin() { }

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "account", unique = true, length = 50)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "loginTime", length = 19)
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "loginnum")
	public Integer getLoginnum() {
		return this.loginnum;
	}

	public void setLoginnum(Integer loginnum) {
		this.loginnum = loginnum;
	}

	@Column(name = "loginIp", length = 100)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "islock")
	public Short getIslock() {
		return this.islock;
	}

	public void setIslock(Short islock) {
		this.islock = islock;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}