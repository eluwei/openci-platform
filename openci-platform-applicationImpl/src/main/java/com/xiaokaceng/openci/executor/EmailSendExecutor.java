package com.xiaokaceng.openci.executor;

import javax.inject.Inject;

import org.springframework.core.task.TaskExecutor;

import com.xiaokaceng.openci.domain.Project;

public class EmailSendExecutor {

	@Inject
	private TaskExecutor taskExecutor;

	public void execute(Project project) {
		
	}
	
	
}
