package com.capg.onlineflatrental.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.TenantDTO;
import com.capg.onlineflatrental.repository.ITenantRepository;
import com.capg.onlineflatrental.util.TenantUtils;

/*
 * Author : PRNANAV SRINIVAS S
 * Version : 1.0
 * Date : 03-04-2021
 * Description : This is Tenant Service Layer that provides services to Add New User, Update Existing User, Delete Existing User,
 *  			 View Existing User and View All Existing Users
*/

@Service
public class TenantServiceImpl implements ITenantService {

	final static Logger LOGGER = LoggerFactory.getLogger(TenantServiceImpl.class);

	@Autowired
	private ITenantRepository tenantRepo;

	static String tenantNotFound = "No Tenant found in given ID";

	/*
	 * Description : This method Adds new Tenant
	 * Input Param : Tenant Object 
	 * Return Value : TenantDTO Object 
	 * Exception : TenantNotFoundException
	 */
	@Override
	public TenantDTO addTenant(Tenant tenant) throws TenantNotFoundException {
		LOGGER.info("addTenant() service is initiated");
		Tenant tenantEntity;
		if (tenant == null)
			tenantEntity = null;
		else if (!validateTenant(tenant))
			throw new TenantNotFoundException(tenantNotFound);
		else
			tenantEntity = tenantRepo.save(tenant);
		LOGGER.info("addTenant() service has executed");
		return TenantUtils.convertToTenantDto(tenantEntity);
	}

	/*
	 * Description : This method Updates existing Tenant
	 * Input Param : Tenant Object 
	 * Return Value : TenantDTO Object 
	 * Exception : TenantNotFoundException
	 */
	@Override
	public TenantDTO updateTenant(Tenant tenant) throws TenantNotFoundException {
		LOGGER.info("updateTenant() service is initiated");
		Tenant tenantEntity;
		if (tenant == null)
			tenantEntity = null;
		Tenant existTenant = tenantRepo.findById(tenant.getTenantId()).orElse(null);
		if (existTenant == null)
			throw new TenantNotFoundException(tenantNotFound);
		else if (!validateTenant(tenant))
			throw new TenantNotFoundException(tenantNotFound);
		else
			tenantEntity = tenantRepo.save(tenant);
		LOGGER.info("updateTenant() service has executed");
		return TenantUtils.convertToTenantDto(tenantEntity);
	}

	/*
	 * Description : This method Deletes existing Tenant
	 * Input Param : int 
	 * Return Value : TenantDTO Object 
	 * Exception : TenantNotFoundException
	 */
	@Override
	public TenantDTO deleteTenant(int id) throws TenantNotFoundException {
		LOGGER.info("deleteTenant() service is initiated");
		Tenant existTenant = tenantRepo.findById(id).orElse(null);
		if (existTenant == null)
			throw new TenantNotFoundException(tenantNotFound);
		else
			tenantRepo.delete(existTenant);
		LOGGER.info("deleteTenant() service has executed");
		return TenantUtils.convertToTenantDto(existTenant);
	}

	/*
	 * Description : This method Shows existing Tenant
	 * Input Param : int
	 * Return Value : TenantDTO Object 
	 * Exception : TenantNotFoundException
	 */
	@Override
	public TenantDTO viewTenant(int id) throws TenantNotFoundException {
		LOGGER.info("viewTenant() service is initiated");
		Tenant existTenant = tenantRepo.findById(id).orElse(null);
		if (existTenant == null)
			throw new TenantNotFoundException(tenantNotFound);
		LOGGER.info("viewTenant() service has executed");
		return TenantUtils.convertToTenantDto(existTenant);
	}

	/*
	 * Description : This method Shows all existing Tenants 
	 * Return Value : List<TenantDTO>
	 */
	@Override
	public List<TenantDTO> viewAllTenant() {
		LOGGER.info("viewAllTenant() service is initiated");
		List<Tenant> tenantList = tenantRepo.findAll();
		LOGGER.info("viewaaTenant() service has executed");
		return TenantUtils.convertToTenantDtoList(tenantList);
	}

	public static boolean validateTenant(Tenant tenant) throws TenantNotFoundException {
		LOGGER.info("validateTenant() is initiated");
		boolean flag = false;
		if (tenant == null) {
			LOGGER.error("Tenant details cannot be blank");
			throw new TenantNotFoundException("Tenant details cannot be blank");
		} else if (!(validateTenantAge(tenant.getTenantAge()) && validateTenantName(tenant.getTenantName())
				&& validateTenantHouseNo(tenant.getTenantAddress().getHouseNo())
				&& validateTenantStreet(tenant.getTenantAddress().getStreet())
				&& validateTenantCity(tenant.getTenantAddress().getCity())
				&& validateTenantState(tenant.getTenantAddress().getState())
				&& validateTenantCountry(tenant.getTenantAddress().getCountry())
				&& validateTenantPin(tenant.getTenantAddress().getPin()))) {
			LOGGER.error("Invalid Address");
			throw new TenantNotFoundException("Invalid Address");
		} else {
			LOGGER.info("Validation Successful");
			flag = true;
		}
		LOGGER.info("validateTenant() has executed");
		return flag;
	}

	public static boolean validateTenantName(String tenantName) throws TenantNotFoundException {
		LOGGER.info("validateTenantName() is initiated");
		boolean flag = false;
		if (tenantName == null) {
			LOGGER.error("Tenant name cannot be empty");
			throw new TenantNotFoundException("Tenant name cannot be empty");
		} else if (!tenantName.matches("^[a-zA-Z ]+$")) {
			LOGGER.error("Tenant Name cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("Tenant Name cannot contain Numbers or Special Characters");
		} else if (tenantName.length() < 3 || tenantName.length() > 30) {
			LOGGER.error("Tenant Name length should be in range 3 to 30");
			throw new TenantNotFoundException("Tenant Name length should be in range 3 to 30");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateTenantName() has executed");
		return flag;
	}

	public static boolean validateTenantAge(int age) throws TenantNotFoundException {
		LOGGER.info("validateTenantAge() is initiated");
		boolean flag = false;
		if (age <= 0) {
			LOGGER.error("Age cannot be 0 or negative");
			throw new TenantNotFoundException("Age cannot be 0 or negative");
		} else if (age < 18) {
			LOGGER.error("Minor Age is not allowed");
			throw new TenantNotFoundException("Minor Age is not allowed");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateTenantAge() has executed");
		return flag;
	}

	public static boolean validateTenantHouseNo(int houseNo) throws TenantNotFoundException {
		LOGGER.info("validateTenantHouseNo() is initiated");
		boolean flag = false;
		if (houseNo <= 0) {
			LOGGER.error("House Number cannot be 0 or negative");
			throw new TenantNotFoundException("House Number cannot be 0 or negative");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateTenantHouseNo() has executed");
		return flag;
	}

	public static boolean validateTenantStreet(String street) throws TenantNotFoundException {
		LOGGER.info("validateTenantStreet() is initiated");
		boolean flag = false;
		if (street == null) {
			LOGGER.error("Street cannot be empty");
			throw new TenantNotFoundException("Street cannot be empty");
		} else if (!street.matches("^[a-zA-Z0-9 ]+$")) {
			LOGGER.error("Street cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("Street cannot contain Numbers or Special Characters");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateTenantStreet() has executed");
		return flag;
	}

	public static boolean validateTenantCity(String city) throws TenantNotFoundException {
		LOGGER.info("validateTenantCity() is initiated");
		boolean flag = false;
		if (city == null) {
			LOGGER.error("City cannot be empty");
			throw new TenantNotFoundException("City cannot be empty");
		} else if (!city.matches("^[a-zA-Z ]+$")) {
			LOGGER.error("City cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("City cannot contain Numbers or Special Characters");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateTenantCity() has executed");
		return flag;
	}

	public static boolean validateTenantState(String state) throws TenantNotFoundException {
		LOGGER.info("validateTenantState() is initiated");
		boolean flag = false;
		if (state == null) {
			LOGGER.error("State cannot be empty");
			throw new TenantNotFoundException("State cannot be empty");
		} else if (!state.matches("^[a-zA-Z ]+$")) {
			LOGGER.error("State cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("State cannot contain Numbers or Special Characters");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateTenantState() has executed");
		return flag;
	}

	public static boolean validateTenantCountry(String country) throws TenantNotFoundException {
		LOGGER.info("validateTenantCounty() is initiated");
		boolean flag = false;
		if (country == null) {
			LOGGER.error("Country cannot be empty");
			throw new TenantNotFoundException("Country cannot be empty");
		} else if (!country.matches("^[a-zA-Z ]+$")) {
			LOGGER.error("Country cannot contain Numbers or Special Characters");
			throw new TenantNotFoundException("Country cannot contain Numbers or Special Characters");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateTenantCounty() is initiated");
		return flag;
	}

	public static boolean validateTenantPin(long pin) throws TenantNotFoundException {
		LOGGER.info("validateTenantPin() is initiated");
		boolean flag = false;
		if (pin <= 0) {
			LOGGER.error("PinCode cannot be 0 or negative");
			throw new TenantNotFoundException("PinCode cannot be 0 or negative");
		} else if (Long.toString(pin).length() != 6) {
			LOGGER.error("PinCode should be length 6");
			throw new TenantNotFoundException("PinCode should be length 6");
		} else if (!Long.toString(pin).matches("^[0-9]+$")) {
			LOGGER.error("PinCode cannot contain any Characters");
			throw new TenantNotFoundException("PinCode cannot contain any Characters");
		} else {
			flag = true;
			LOGGER.info("Validation Successful");
		}
		LOGGER.info("validateTenantPin() is initiated");
		return flag;
	}

}
