package com.xiaokaceng.openci.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("jira")
public class JiraConfiguration extends ToolConfiguration {

	private static final long serialVersionUID = 570103945579421999L;

	JiraConfiguration() {
	}

	public JiraConfiguration(String name, String serviceUrl, String username, String password) {
		super(name, serviceUrl, username, password);
	}

	@Override
	public String getRequestAddress(String projectName) {
		return getServiceUrl();
	}

}
