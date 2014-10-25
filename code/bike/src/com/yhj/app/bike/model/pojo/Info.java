package com.yhj.app.bike.model.pojo;

import java.io.Serializable;
import java.util.List;

public class Info implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1169803661963597470L;
	
	private List<Project> project;

	public Info() {
		
	}

	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Info [project=" + project + "]";
	}

	
}
