package com.capg.onlineflatrental.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.onlineflatrental.entities.Admin;
import com.capg.onlineflatrental.exception.AdminNotFoundException;
import com.capg.onlineflatrental.model.AdminDTO;
import com.capg.onlineflatrental.repository.IAdminRepository;
import com.capg.onlineflatrental.util.AdminUtils;

/*
 * Author : NITHISHA K A
 * Version : 1.0
 * Date : 03-04-2021
 * Description : This is Admin Service Layer
*/

@Service
public class AdminServiceImpl implements IAdminService {

	static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	static String adminNotFound = "No Admin found in given ID";
	
	static String validationSuccessful = "Validation Successful";

	@Autowired
	private IAdminRepository adminRepo;

	/*
	 * Description : This method Adds new Admin 
	 * Input Param : Admin Object 
	 * Return Value : AdminDTO Object 
	 * Exception : AdminNotFoundException
	 */
	@Override
	public AdminDTO addAdmin(Admin admin) throws AdminNotFoundException {
		LOGGER.info("addAdmin() service is initiated");
		Admin adminEntity;
		if (admin == null)
			adminEntity = null;
		else
		{
			validateAdmin(admin);
			adminEntity = adminRepo.save(admin);
		}
		LOGGER.info("addAdmin() service has executed");
		return AdminUtils.convertToAdminDto(adminEntity);
	}

	/*
	 * Description : This method Updates existing Admin 
	 * Input Param : Admin Object
	 * Return Value : AdminDTO Object 
	 * Exception : AdminNotFoundException
	 */
	@Override
	public AdminDTO updateAdmin(Admin admin) throws AdminNotFoundException {
		LOGGER.info("updateAdmin() service is initiated");
		Admin adminEntity;
		Admin existAdmin = adminRepo.findById(admin.getAdminId()).orElse(null);
		if (existAdmin == null)
			throw new AdminNotFoundException(adminNotFound);
		else
		{
			validateAdmin(admin);
			adminEntity = adminRepo.save(admin);
		}
		LOGGER.info("updateAdmin() service has executed");
		return AdminUtils.convertToAdminDto(adminEntity);
	}

	/*
	 * Description : This method Deletes existing Admin 
	 * Input Param : int
	 * Return Value : AdminDTO Object 
	 * Exception : AdminNotFoundException
	 */
	@Override
	public AdminDTO deleteAdmin(int id) throws AdminNotFoundException {
		LOGGER.info("deleteAdmin() service is initiated");
		Admin existAdmin = adminRepo.findById(id).orElse(null);
		if (existAdmin == null)
			throw new AdminNotFoundException(adminNotFound);
		else
			adminRepo.delete(existAdmin);
		LOGGER.info("deleteAdmin() service has executed");
		return AdminUtils.convertToAdminDto(existAdmin);
	}

	/*
	 * Description : This method Shows existing Admin by ID 
	 * Input Param : int
	 * Object Return Value : AdminDTO Object 
	 * Exception : AdminNotFoundException
	 */
	@Override
	public AdminDTO viewAdmin(int id) throws AdminNotFoundException {
		LOGGER.info("viewAdmin() service is initiated");
		Admin existAdmin = adminRepo.findById(id).orElse(null);
		if (existAdmin == null)
			throw new AdminNotFoundException(adminNotFound);
		LOGGER.info("viewAdmin() service has executed");
		return AdminUtils.convertToAdminDto(existAdmin);
	}

	/*
	 * Description : This method Shows all existing Admins 
	 * Object Return Value : List<AdminDTO> Object 
	 */
	@Override
	public List<AdminDTO> viewAllAdmin() {
		LOGGER.info("viewAllAdmin() service is initiated");
		List<Admin> adminList = adminRepo.findAll();
		LOGGER.info("viewAllAdmin() service has executed");
		return AdminUtils.convertToAdminDtoList(adminList);
	}

	public static boolean validateAdmin(Admin admin) throws AdminNotFoundException {
		LOGGER.info("validateAdmin() is initiated");
		boolean flag = false;
		if (admin == null) {
			LOGGER.error("Admin details cannot be blank");
			throw new AdminNotFoundException("Admin details cannot be blank");
		} else {
			validatePassword(admin.getAdminPassword());
			LOGGER.info(validationSuccessful);
			flag = true;
		}
		LOGGER.info("validateAdmin() has executed");
		return flag;
	}

	public static boolean validateId(int adminId) throws AdminNotFoundException {
		LOGGER.info("validateId() is initiated");
		boolean flag = false;
		if (adminId <= 0) {
			LOGGER.error("Admin id cannot be 0 or negative");
			throw new AdminNotFoundException("Admin id cannot be 0 or negative");
		} else {
			flag = true;
			LOGGER.info(validationSuccessful);
		}
		LOGGER.info("validateId() has executed");
		return flag;
	}

	public static boolean validatePassword(String adminPassword) throws AdminNotFoundException {
		LOGGER.info("validatePasswrod() is initiated");
		boolean flag = false;
		if (adminPassword == null) {
			LOGGER.error("Password cannot be 0 or negative");
			throw new AdminNotFoundException("Password cannot be 0 or negative");
		} else if (!(adminPassword.matches(".*[@#$%^&+=].*") && adminPassword.matches(".*[a-z].*")
				&& adminPassword.matches(".*[A-Z].*") && adminPassword.matches(".*[0-9].*")
				&& adminPassword.length() >= 8)) {
			LOGGER.error("Password wrong format");
			throw new AdminNotFoundException(
					"Format for Password is Wrong\r\n" + "\r\n" + "Please Enter Password Again :\r\n"
							+ "____________________________________________________________\r\n" + "\r\n"
							+ "Valid Format for Password :\r\n" + "\r\n" + "Password should not contain any space.\r\n"
							+ "Password should contain at least one digit(0-9).\r\n"
							+ "Password length should be between 8 to 15 characters.\r\n"
							+ "Password should contain at least one lowercase letter(a-z).\r\n"
							+ "Password should contain at least one uppercase letter(A-Z).\r\n"
							+ "Password should contain at least one special character ( @, #, %, &, !, $, etc….)");
		} else {
			flag = true;
			LOGGER.info(validationSuccessful);
		}
		LOGGER.info("validatePasswrod() has executed");
		return flag;
	}

}
