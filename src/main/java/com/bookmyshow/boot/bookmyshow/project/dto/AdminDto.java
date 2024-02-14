package com.bookmyshow.boot.bookmyshow.project.dto;

import java.util.List;

import com.bookmyshow.boot.bookmyshow.project.entity.Theatre;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminDto 
{
	private int adminId;
	private String adminName;
	private String adminMail;
	private List<Theatre> theatreList;
}
