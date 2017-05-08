package com.ship.domain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 提现记录
 */
@Entity
@Table(name = "withdrawals", catalog = "membershiptwo")
public class WithDrawals implements java.io.Serializable {
	/**
	 *主键
	 */
	private Long id;
	/**
	 * 用户Id
	 */
	private Long userId;
	/**
	 *流水号
	 */
	private String serialnumber;
	/**
	 * 备注
	 */
	private String mark;
	/**
	 *银行账户
	 */
	private Long bankId;
	/**
	 *0：申请中，1：银行处理中，2：提现成功，3：提现失败
	 */
	private Integer status;
	/**
	 * 提现方式
	 */
	private Integer types;
	/**
	 *手续费
	 */
	private Double counterFee;
	/**
	 *提现总金额
	 */
	private Double tatalmoney;
	/**
	 *实际金额
	 */
	private Double money;
	/**
	 *处理时间
	 */
	private Date handlerTime;
	/**
	 *申请时间
	 */
	private Date applyTime;


	public WithDrawals() {

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

	@Column(name = "userId",  nullable = false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "serialnumber", length = 50)
	public String getSerialnumber() {
		return this.serialnumber;
	}


	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}


	@Column(name = "mark", length = 50)
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Column(name = "bankId")
	public Long getBankId() {
		return this.bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "types")
	public Integer getTypes() {
		return types;
	}

	public void setTypes(Integer types) {
		this.types = types;
	}

	@Column(name = "counterFee", precision = 10)
	public Double getCounterFee() {
		return this.counterFee;
	}

	public void setCounterFee(Double counterFee) {
		this.counterFee = counterFee;
	}

	@Column(name = "tatalmoney", precision = 10)
	public Double getTatalmoney() {
		return this.tatalmoney;
	}

	public void setTatalmoney(Double tatalmoney) {
		this.tatalmoney = tatalmoney;
	}

	@Column(name = "money", precision = 10)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "handlerTime", length = 19)
	public Date getHandlerTime() {
		return this.handlerTime;
	}

	public void setHandlerTime(Date handlerTime) {
		this.handlerTime = handlerTime;
	}

	@Column(name = "applyTime", length = 19)
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

}