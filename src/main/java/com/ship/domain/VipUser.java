package com.ship.domain;

import javax.persistence.*;
import java.util.Date;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 用户实体
 */
@Entity
@Table(name = "vipuser", catalog = "membershiptwo", uniqueConstraints = @UniqueConstraint(columnNames = "phone"))
public class VipUser implements java.io.Serializable {
	/**
	 *主键
	 */
	private Long id;
	/**
	 *推荐人Id
	 */
	private String referrercode;
	/**
	 *绑定邮箱
	 */
	private String email;
	/**
	 *手机号
	 */
	private String phone;
	/**
	 *用户名称
	 */
	private String vipname;
	/**
	 *登录时间
	 */
	private Date loginTime;
	/**
	 *登录Ip
	 */
	private String loginIp;
	/**
	 * 是否锁定(0:正常，1：分红冻结，2：禁止登录 )
	 */
	private Integer islock;
	/**
	 *密码
	 */
	private String password;
	/**
	 *头像URL
	 */
	private String headUrl;
	/**
	 *简接推荐Id
	 */
	private String indirectId;
	/**
	 *总业绩
	 */
	private Double total;
	/**
	 *推荐人数
	 */
	private Integer pernum;
	/**
	 * 登录次数
	 */
	private Integer loginnum;
	/**
	 *身份证号
	 */
	private String idcard;
	/**
	 *账户余额
	 */
	private Double balance;

	/**
	 *
	 */
	private Double gradeone;
	/**
	 *
	 */
	private Double gradetwo;
	/**
	 *
	 */
	private Double gradethree;
	/**
	 * 已提现金额
	 */
	private Double already;
	/**
	 *添加时间
	 */
	private Date addTime;

	/**
	 * 真实名称
	 */
	private String realname;



	public VipUser() {
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



	@Column(name = "referrercode")
	public String getReferrercode() {
		return referrercode;
	}

	public void setReferrercode(String referrercode) {
		this.referrercode = referrercode;
	}



	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", unique = true, length = 11)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "vipname", length = 20)
	public String getVipname() {
		return this.vipname;
	}

	public void setVipname(String vipname) {
		this.vipname = vipname;
	}

	@Column(name = "loginTime", length = 19)
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "loginIp", length = 50)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "islock")
	public Integer getIslock() {
		return this.islock;
	}

	public void setIslock(Integer islock) {
		this.islock = islock;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "headUrl", length = 150)
	public String getHeadUrl() {
		return this.headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	@Column(name = "indirectId")
	public String getIndirectId() {
		return this.indirectId;
	}

	public void setIndirectId(String indirectId) {
		this.indirectId = indirectId;
	}

	@Column(name = "total", precision = 10)
	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "pernum")
	public Integer getPernum() {
		return this.pernum;
	}


	public void setPernum(Integer pernum) {
		this.pernum = pernum;
	}

	@Column(name = "loginnum")
	public Integer getLoginnum() {
		return loginnum;
	}

	public void setLoginnum(Integer loginnum) {
		this.loginnum = loginnum;
	}

	@Column(name = "idcard", length = 50)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "balance", precision = 10)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}


	@Column(name = "gradeone", precision = 10)
	public Double getGradeone() {
		return gradeone;
	}

	public void setGradeone(Double gradeone) {
		this.gradeone = gradeone;
	}

	@Column(name = "gradetwo", precision = 10)
	public Double getGradetwo() {
		return gradetwo;
	}

	public void setGradetwo(Double gradetwo) {
		this.gradetwo = gradetwo;
	}

	@Column(name = "gradethree", precision = 10)
	public Double getGradethree() {
		return gradethree;
	}

	public void setGradethree(Double gradethree) {
		this.gradethree = gradethree;
	}

	@Column(name = "already", precision = 10)
	public Double getAlready() {
		return already;
	}

	public void setAlready(Double already) {
		this.already = already;
	}

	@Column(name = "addTime", length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "realname", length = 20)
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
}