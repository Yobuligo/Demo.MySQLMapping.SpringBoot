package com.vpsd.demomysqlmapping.interest;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import com.vpsd.demomysqlmapping.model.Model;

@Entity
public class Interest extends Model {

	@Size(max = 200)
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
