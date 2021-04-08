package com.capg.onlineflatrental.util;

import java.util.ArrayList;
import java.util.List;
import com.capg.onlineflatrental.entities.User;
import com.capg.onlineflatrental.model.UserDTO;

public class UserUtils {

	private UserUtils() {
		
	}
	
	public static List<UserDTO> convertToUserDtoList(List<User> list){
		List<UserDTO> dtolist = new ArrayList<>();
		for(User user : list) 
			dtolist.add(convertToUserDto(user));
		return dtolist;
	}
	
	public static User convertToUser(UserDTO dto) {
		User user = new User();
		user.setUserId(dto.getUserId());
		user.setUserName(dto.getUserName());
		user.setPassword(dto.getPassword());
		user.setUserType(dto.getUserType());
		return user;
	}		
	
	public static UserDTO convertToUserDto(User user) {
		UserDTO dto = new UserDTO();
		dto.setUserId(user.getUserId());
		dto.setUserName(user.getUserName());
		dto.setPassword(user.getPassword());
		dto.setUserType(user.getUserType());
		return dto;
	}
	
}
