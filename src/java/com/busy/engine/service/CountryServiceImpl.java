





















































package com.busy.engine.service;

import com.busy.engine.dao.CountryDao;
import com.busy.engine.dao.CountryDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Country;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class CountryServiceImpl extends AbstractService implements CountryService 
{
    protected CountryDao countryDao = new CountryDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public CountryServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Country> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(countryDao.find(id));
            }
            else 
            {            
                return ResultFactory.getFailResult(USER_INVALID);
            }
        }
        catch (Exception ex)
        {            
            return ResultFactory.getFailResult(ex.getMessage());
        }
    }
    
    @Override
    public Result<List<Country>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<Country> countryList =  countryDao.findAll(null, null);
                return ResultFactory.getSuccessResult(countryList);
            } 
            else 
            {
                return ResultFactory.getFailResult(USER_INVALID);
            }
        }
        catch (Exception ex)
        {            
            return ResultFactory.getFailResult(ex.getMessage());
        }
    }

    @Override
    public Result<Country> store(String userName, Integer id, String name, String isoCode, Integer isoNumber, Integer hasVat)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Country country;

        if (id == null) 
        {
            country = new Country();
        } 
        else 
        {
            country = countryDao.find(id);

            if (country == null) 
            {
                return ResultFactory.getFailResult("Unable to find Country instance with Id=" + id);
            }
        }

        country.setName(name);
        country.setIsoCode(isoCode);
        country.setIsoNumber(isoNumber);
        country.setHasVat(hasVat);
        
        
        if (country.getId() == null) 
        {
            countryDao.add(country);
        } 
        else 
        {
            country = countryDao.update(country);
        }

        return ResultFactory.getSuccessResult(country);

    }
  
    @Override
    public Result<Country> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Country [null id]");
        } 

        Country country = countryDao.find(id);

        if (country == null) 
        {
            return ResultFactory.getFailResult("Unable to load Country for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            countryDao.getRelatedObjects(country);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(country.getShippingList().size() != 0)
            {
                relatedObjectNames += "Shipping ";  
                canBeDeleted = false;
            }
                        
            if(country.getStateProvinceList().size() != 0)
            {
                relatedObjectNames += "StateProvince ";  
                canBeDeleted = false;
            }
                        
            if(country.getTaxRateList().size() != 0)
            {
                relatedObjectNames += "TaxRate ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                countryDao.remove(country);
                
                String msg = "Country with Id: " + country.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Country is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
