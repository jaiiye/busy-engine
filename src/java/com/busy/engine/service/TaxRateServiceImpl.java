





































package com.busy.engine.service;

import com.busy.engine.dao.TaxRateDao;
import com.busy.engine.dao.TaxRateDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.TaxRate;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class TaxRateServiceImpl extends AbstractService implements TaxRateService 
{
    protected TaxRateDao taxRateDao = new TaxRateDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public TaxRateServiceImpl() 
    {
        super();
    }

    @Override
    public Result<TaxRate> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(taxRateDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<TaxRate>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<TaxRate> taxRateList =  taxRateDao.findAll(null, null);
            return ResultFactory.getSuccessResult(taxRateList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<TaxRate> store(String userName, Integer id, String taxCategory, Double percentage, String zipPostalCode, Integer stateProvinceId, Integer countryId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        TaxRate taxRate;

        if (id == null) 
        {
            taxRate = new TaxRate();
        } 
        else 
        {
            taxRate = taxRateDao.find(id);

            if (taxRate == null) 
            {
                return ResultFactory.getFailResult("Unable to find TaxRate instance with Id=" + id);
            }
        }

        taxRate.setTaxCategory(taxCategory);
        taxRate.setPercentage(percentage);
        taxRate.setZipPostalCode(zipPostalCode);
        taxRate.setStateProvinceId(stateProvinceId);
        taxRate.setCountryId(countryId);
        
        
        if (taxRate.getId() == null) 
        {
            taxRateDao.add(taxRate);
        } 
        else 
        {
            taxRate = taxRateDao.update(taxRate);
        }

        return ResultFactory.getSuccessResult(taxRate);

    }
  
    @Override
    public Result<TaxRate> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove TaxRate [null id]");
        } 

        TaxRate taxRate = taxRateDao.find(id);

        if (taxRate == null) 
        {
            return ResultFactory.getFailResult("Unable to load TaxRate for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            taxRateDao.getRelatedObjects(taxRate);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                taxRateDao.remove(taxRate);
                
                String msg = "TaxRate with Id: " + taxRate.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("TaxRate is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

