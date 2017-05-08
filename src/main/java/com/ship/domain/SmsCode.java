package com.ship.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 验证码记录
 */
@Entity
@Table(name = "smscode", catalog = "membershiptwo")
public class SmsCode implements java.io.Serializable {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 *手机号
	 */
	private String phone;
	/**
	 *验证码
	 */
	private String code;
	/**
	 *验证码类型
	 */
	private Integer type;
	/**
	 *添加时间
	 */
	private Long addTime;


	public SmsCode() {
	}

	public SmsCode(String phone, String code, Integer type, Long addTime) {
		this.phone = phone;
		this.code = code;
		this.type = type;
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

	@Column(name = "phone", length = 150)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "code", length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "addTime", length = 19)
	public Long getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

}