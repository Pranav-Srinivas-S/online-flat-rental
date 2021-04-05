package com.capg.onlineflatrental.util;

import java.util.ArrayList;
import java.util.List;

import com.capg.onlineflatrental.entities.Login;
import com.capg.onlineflatrental.model.LoginDTO;

public class LoginUtils {
	public static List<LoginDTO> convertToLoginDtoList(List<Login> list){
		List<LoginDTO> dtolist = new ArrayList<LoginDTO>();
		for(Login login : list) 
			dtolist.add(convertToLoginDto(login));
		return dtolist;
	}
	
	public static Login convertToLogin(LoginDTO dto) {
		Login login = new Login();
		login.setLoginId(dto.getLoginId());
		login.setLoginPassword(dto.getLoginPassword());
		return login;
	}		
	
	public static LoginDTO convertToLoginDto(Login login) {
		LoginDTO dto = new LoginDTO();
		dto.setLoginId(login.getLoginId());
		dto.setLoginPassword(login.getLoginPassword());
		return dto;
	}
	
}

