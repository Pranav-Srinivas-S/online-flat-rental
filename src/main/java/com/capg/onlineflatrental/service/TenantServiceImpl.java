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
	static ITenantRepository tenantRepo;
	
	static String tenantNotFound = "No Tenant found in given ID";
	
	@Override
	public TenantDTO addTenant(Tenant tenant) {
		Tenant tenantEntity;
		if(tenant == null)
			tenantEntity = null;
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
		else if(!(validateTenantAge(tenant.getTenantAge()) && validateTenantHouseNo(tenant.getTenantAddress().getHouseNo())
				&& validateTenantStreet(tenant.getTenantAddress().getStreet()) && validateTenantCity(tenant.getTenantAddress().getStreet())
				&& validateTenantState(tenant.getTenantAddress().getState()) && validateTenantCountry(tenant.getTenantAddress().getCountry())
				&& validateTenantPin(tenant.getTenantAddress().getPin())))
			throw new TenantNotFoundException("Invalid Address");
		else
			flag = true;
		return flag;
	}
	
	public static boolean validateTenantId(int id) throws TenantNotFoundException
	{
		boolean flag = tenantRepo.existsById(id);
		if(flag == false)
			throw new TenantNotFoundException(tenantNotFound);
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
		else if(!street.matches("^[a-zA-Z]+$"))
			throw new TenantNotFoundException("Street cannot contain Numbers or Special Characters");
		else
			flag = true;
		return flag;
	}

	public static boolean validateTenantCity(String city) throws TenantNotFoundException
	{
		boolean flag = false;
		if(city == null)
			throw new TenantNotFoundException("city cannot be empty");
		else if(!city.matches("^[a-zA-Z]+$"))
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
		else if(!state.matches("^[a-zA-Z]+$"))
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
		else if(!country.matches("^[a-zA-Z]+$"))
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
	
	
	
//	public boolean validateTenant(Tenant tenant) throws TenantNotFoundException
//	{
//		boolean flag = false;
//		if(tenant.getTenantAge() > 0)
//			flag = true;
//		else
//			throw new TenantNotFoundException("Age cannot be 0 or negative");
//		if(tenant.getTenantAddress().getHouseNo() > 0)
//			flag = true;
//		else
//			throw new TenantNotFoundException("House Number cannot be 0 or negative");
//		if(tenant.getTenantAddress().getStreet().matches("^[a-zA-Z]+$") 
//				&& tenant.getTenantAddress().getCity().matches("^[a-zA-Z]+$")
//				&& tenant.getTenantAddress().getState().matches("^[a-zA-Z]+$")
//				&& tenant.getTenantAddress().getCountry().matches("^[a-zA-Z]+$"))
//			flag = true;
//		else
//			throw new TenantNotFoundException("Address should not contain Numbers or Special Characters");
//		if(tenant.getTenantAddress().getPin() > 0 
//				&& Long.toString(tenant.getTenantAddress().getPin()).length() == 6
//				&& Long.toString(tenant.getTenantAddress().getPin()).matches("^[0-9]+$"))
//			flag = true;
//		else
//			throw new TenantNotFoundException("Pincode cannot be 0 or negative, length should be 6 and should not contain any Characters");
//		return flag;
//	}

	
	
}
