package org.txazo.spring.email.dao;

import java.util.List;

import org.txazo.spring.email.vo.MailVo;

public interface MailDao {

	public boolean saveMail(MailVo mailVo);

	public List<MailVo> readMail();

}
