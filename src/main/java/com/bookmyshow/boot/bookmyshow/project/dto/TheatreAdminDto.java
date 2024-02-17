package com.bookmyshow.boot.bookmyshow.project.dto;


import com.bookmyshow.boot.bookmyshow.project.entity.Theatre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheatreAdminDto 
{
	private int theatreAdminId;
	private String theatreAdminName;
	private String theatreAdminMail;
	private Theatre theatre;

}
