package com.busy.engine.service;

import com.busy.engine.dao.AddressDao;
import com.busy.engine.dao.AddressDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Address;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class AddressServiceImpl extends AbstractService implements AddressService
{

    protected AddressDao addressDao;
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;

    public AddressServiceImpl()
    {
        super();

        addressDao = new AddressDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }

    public AddressServiceImpl(ServletContext context)
    {
        super();

        addressDao = (AddressDao) context.getAttribute("addressDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Address> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao))
            {
                return ResultFactory.getSuccessResult(addressDao.find(id));
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
    public Result<List<Address>> findAll(String userName)
    {
        try
        {
            if (isValidUser(userName, userDao))
            {
                List<Address> addressList = addressDao.findAll(null, null);
                return ResultFactory.getSuccessResult(addressList);
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
    public Result<Address> store(String userName, Integer id, String recipient, String address1, String address2, String city, String stateProvince, String zipPostalCode, String country, String region, Integer addressStatus, String locale)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);

        if (!checkUserRoles(roles))
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Address address;

        if (id == null)
        {
            address = new Address();
        }
        else
        {
            address = addressDao.find(id);

            if (address == null)
            {
                return ResultFactory.getFailResult("Unable to find Address instance with Id=" + id);
            }
        }

        address.setRecipient(recipient);
        address.setAddress1(address1);
        address.setAddress2(address2);
        address.setCity(city);
        address.setStateProvince(stateProvince);
        address.setZipPostalCode(zipPostalCode);
        address.setCountry(country);
        address.setRegion(region);
        address.setAddressStatus(addressStatus);
        address.setLocale(locale);

        if (address.getId() == null)
        {
            addressDao.add(address);
        }
        else
        {
            address = addressDao.update(address);
        }

        return ResultFactory.getSuccessResult(address);

    }

    @Override
    public Result<Address> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);

        if (!checkUserRoles(roles))
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null)
        {
            return ResultFactory.getFailResult("Unable to remove Address [null id]");
        }

        Address address = addressDao.find(id);

        if (address == null)
        {
            return ResultFactory.getFailResult("Unable to load Address for removal with id=" + id);
        }
        else
        {
            //if all related objects are empty for the given object then we can erase this object
            addressDao.getRelatedObjects(address);

            String relatedObjectNames = "";
            boolean canBeDeleted = true;

            if (address.getAffiliateList().size() != 0)
            {
                relatedObjectNames += "Affiliate ";
                canBeDeleted = false;
            }

            if (address.getItemLocationList().size() != 0)
            {
                relatedObjectNames += "ItemLocation ";
                canBeDeleted = false;
            }

            if (address.getUserList().size() != 0)
            {
                relatedObjectNames += "User ";
                canBeDeleted = false;
            }

            if (canBeDeleted)
            {
                addressDao.remove(address);

                String msg = "Address with Id: " + address.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);
            }
            else
            {
                return ResultFactory.getFailResult("Address is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
        }
    }
}
