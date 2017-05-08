package com.ship.handler.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.ship.dao.SmsCodeDao;
import com.ship.handler.SmsCodeHander;
import com.ship.domain.SmsCode;
import java.io.Serializable;
/**
 * @author Mryang
 * @createTime 2017年3月20日
 * @version 1.0
 * SmsCodeHander 实现
 */
@Transactional
public class SmsCodeHanderImpl  implements SmsCodeHander {
	
	@Inject
	private SmsCodeDao codeDao;
 
	public SmsCode find(long id) {
		return codeDao.find(id);
	}

	public void delete(Serializable... entityIds) {
		codeDao.delete(entityIds);
	}

	public void update(SmsCode entity) {
		codeDao.update(entity);
	}

	public void save(SmsCode entity) {
		codeDao.save(entity);
	}


	@Override
	public SmsCode isPhoneSms(String phone) {
		return codeDao.isPhoneSms(phone) ;
	}
}
