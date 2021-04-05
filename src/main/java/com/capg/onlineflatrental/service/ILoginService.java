package com.capg.onlineflatrental.service;

import com.capg.onlineflatrental.entities.Login;
import com.capg.onlineflatrental.model.LoginDTO;
import com.capg.onlineflatrental.model.UserDTO;

public interface ILoginService {
	
	public LoginDTO validateLogin(int id,String password);
	public UserDTO addLogin(Login login);

}
