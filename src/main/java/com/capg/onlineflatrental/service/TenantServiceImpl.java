package com.capg.onlineflatrental.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.onlineflatrental.entities.Tenant;
import com.capg.onlineflatrental.exception.TenantNotFoundException;
import com.capg.onlineflatrental.model.TenantDTO;
import com.capg.onlineflatrental.repository.ITenantRepository;
import com.capg.onlineflatrental.util.TenantUtils;

@Service
public class TenantServiceImpl implements ITenantService {

	@Autowired
	ITenantRepository tenantRepo;
	
	static String tenantNotFound = "No Tenant found in given ID";
	
	@Override
	public TenantDTO addTenant(Tenant tenant) throws TenantNotFoundException {
		Tenant tenantEntity;
		if(tenant == null)
			tenantEntity = null;
		else if(!validateTenant(tenant))
			throw new TenantNotFoundException(tenantNotFound);
		else
			tenantEntity = tenantRepo.save(tenant);
		return TenantUtils.convertToTenantDto(tenantEntity);
	}

	@Override
	public TenantDTO updateTenant(Tenant tenant) throws TenantNotFoundException {
		Tenant tenantEntity;
		if(tenant == null)
			tenantEntity = null;
		Tenant existTenant = tenantRepo.findById(tenant.getTenantId()).orElse(null);
		if(existTenant == null)
			throw new TenantNotFoundException(tenantNotFound);
		else
			tenantEntity = tenantRepo.save(tenant);
		return TenantUtils.convertToTenantDto(tenantEntity);
		
	}

	@Override
	public TenantDTO deleteTenant(int id) throws TenantNotFoundException {
		Tenant existTenant = tenantRepo.findById(id).orElse(null);
		if(existTenant == null)
			throw new TenantNotFoundException(tenantNotFound);
		else
			tenantRepo.delete(existTenant);
		return TenantUtils.convertToTenantDto(existTenant);
	}

	@Override
	public TenantDTO viewTenant(int id) throws TenantNotFoundException {
		Tenant existTenant = tenantRepo.findById(id).orElse(null);
		if(existTenant == null)
			throw new TenantNotFoundException(tenantNotFound);
		return TenantUtils.convertToTenantDto(existTenant);
	}

	@Override
	public List<TenantDTO> viewAllTenant() {
		List<Tenant> tenantList = tenantRepo.findAll();
		return TenantUtils.convertToTenantDtoList(tenantList);
	}

	public static boolean validateTenant(Tenant tenant) throws TenantNotFoundException
	{
		boolean flag = false;
		if(tenant == null)
			throw new TenantNotFoundException("Tenant details cannot be blank");
		else if(!(validateTenantAge(tenant.getTenantAge()) && validateTenantName(tenant.getTenantName()) 
				&& validateTenantHouseNo(tenant.getTenantAddress().getHouseNo())
				&& validateTenantStreet(tenant.getTenantAddress().getStreet()) && validateTenantCity(tenant.getTenantAddress().getCity())
				&& validateTenantState(tenant.getTenantAddress().getState()) && validateTenantCountry(tenant.getTenantAddress().getCountry())
				&& validateTenantPin(tenant.getTenantAddress().getPin())))
			throw new TenantNotFoundException("Invalid Address");
		else
			flag = true;
		return flag;
	}
	
	public static boolean validateTenantName(String tenantName) throws TenantNotFoundException 
	{
		boolean flag = false;
		if(tenantName == null)
			throw new TenantNotFoundException("Tenant name cannot be empty");
		else if(!tenantName.matches("^[a-zA-Z ]+$"))
			throw new TenantNotFoundException("Tenant Name cannot contain Numbers or Special Characters");
		else if(tenantName.length()<3 || tenantName.length()>30)
			throw new TenantNotFoundException("Tenant Name length should be in range 3 to 30");
		else
			flag = true;
		return flag;
	}
	
	public static boolean validateTenantAge(int age) throws TenantNotFoundException
	{
		boolean flag = false;
		if(age <= 0)
			throw new TenantNotFoundException("Age cannot be 0 or negative");
		else if(age < 18)
			throw new TenantNotFoundException("Minor Age is not allowed");
		else
			flag = true; 
		return flag;
	}
	
	public static boolean validateTenantHouseNo(int houseNo) throws TenantNotFoundException
	{
		boolean flag = false;
		if(houseNo <= 0)
			throw new TenantNotFoundException("House Number cannot be 0 or negative");
		else
			flag = true;
		return flag;
	}
	
	public static boolean validateTenantStreet(String street) throws TenantNotFoundException
	{
		boolean flag = false;
		if(street == null)
			throw new TenantNotFoundException("Street cannot be empty");
		else if(!street.matches("^[a-zA-Z0-9 ]+$"))
			throw new TenantNotFoundException("Street cannot contain Numbers or Special Characters");
		else
			flag = true;
		return flag;
	}

	public static boolean validateTenantCity(String city) throws TenantNotFoundException
	{
		boolean flag = false;
		if(city == null)
			throw new TenantNotFoundException("City cannot be empty");
		else if(!city.matches("^[a-zA-Z ]+$"))
			throw new TenantNotFoundException("City cannot contain Numbers or Special Characters");
		else
			flag = true;
		return flag;
	}

	public static boolean validateTenantState(String state) throws TenantNotFoundException
	{
		boolean flag = false;
		if(state == null)
			throw new TenantNotFoundException("State cannot be empty");
		else if(!state.matches("^[a-zA-Z ]+$"))
			throw new TenantNotFoundException("State cannot contain Numbers or Special Characters");
		else
			flag = true;
		return flag;
	}

	public static boolean validateTenantCountry(String country) throws TenantNotFoundException
	{
		boolean flag = false;
		if(country == null)
			throw new TenantNotFoundException("Country cannot be empty");
		else if(!country.matches("^[a-zA-Z ]+$"))
			throw new TenantNotFoundException("Country cannot contain Numbers or Special Characters");
		else
			flag = true;
		return flag;
	}
	
	public static boolean validateTenantPin(long pin) throws TenantNotFoundException
	{
		boolean flag = false;
		if(pin <= 0)
			throw new TenantNotFoundException("PinCode cannot be 0 or negative");
		else if(Long.toString(pin).length() != 6)
			throw new TenantNotFoundException("PinCode should be length 6");
		else if(!Long.toString(pin).matches("^[0-9]+$"))
			throw new TenantNotFoundException("PinCode cannot contain any Characters");
		else
			flag = true;
		return flag;
	}
	
}
