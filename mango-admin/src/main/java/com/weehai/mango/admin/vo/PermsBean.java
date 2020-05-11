package com.weehai.mango.admin.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.weehai.mango.admin.model.Config;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PermsBean {
	private Long id;
	private String path;
    private int hidden;
	private Meta meta;
	
	class Meta{
		public Meta() {
			
		}
		public Meta(String title) {
			this.title=title;
		}
		private String title;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
	}

	public Meta Meta(String title) {
		// TODO Auto-generated method stub
		return new Meta(title);
		
	}


	

}
