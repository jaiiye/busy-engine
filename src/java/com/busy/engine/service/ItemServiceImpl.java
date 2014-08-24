package com.busy.engine.service;

import com.busy.engine.dao.ItemDao;
import com.busy.engine.dao.ItemDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Item;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ItemServiceImpl extends AbstractService implements ItemService
{

    protected ItemDao itemDao;
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;

    public ItemServiceImpl()
    {
        super();

        itemDao = new ItemDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }

    public ItemServiceImpl(ServletContext context)
    {
        super();

        itemDao = (ItemDao) context.getAttribute("itemDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Item> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao))
            {
                return ResultFactory.getSuccessResult(itemDao.find(id));
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
    public Result<List<Item>> findAll(String userName)
    {
        try
        {
            if (isValidUser(userName, userDao))
            {
                List<Item> itemList = itemDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemList);
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
    public Result<Item> store(String userName, Integer id, String itemName, String description, Double listPrice, Double price, String shortDescription, Integer adjustment, String sku, Integer ratingSum, Integer voteCount, Integer rank, Integer itemStatus, String locale, Integer itemTypeId, Integer itemBrandId, Integer metaTagId, Integer templateId, Integer vendorId)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);

        if (!checkUserRoles(roles))
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Item item;

        if (id == null)
        {
            item = new Item();
        }
        else
        {
            item = itemDao.find(id);

            if (item == null)
            {
                return ResultFactory.getFailResult("Unable to find Item instance with Id=" + id);
            }
        }

        item.setItemName(itemName);
        item.setDescription(description);
        item.setListPrice(listPrice);
        item.setPrice(price);
        item.setShortDescription(shortDescription);
        item.setAdjustment(adjustment);
        item.setSku(sku);
        item.setRatingSum(ratingSum);
        item.setVoteCount(voteCount);
        item.setRank(rank);
        item.setItemStatus(itemStatus);
        item.setLocale(locale);
        item.setItemTypeId(itemTypeId);
        item.setItemBrandId(itemBrandId);
        item.setMetaTagId(metaTagId);
        item.setTemplateId(templateId);
        item.setVendorId(vendorId);

        if (item.getId() == null)
        {
            itemDao.add(item);
        }
        else
        {
            item = itemDao.update(item);
        }

        return ResultFactory.getSuccessResult(item);

    }

    @Override
    public Result<Item> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);

        if (!checkUserRoles(roles))
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null)
        {
            return ResultFactory.getFailResult("Unable to remove Item [null id]");
        }

        Item item = itemDao.find(id);

        if (item == null)
        {
            return ResultFactory.getFailResult("Unable to load Item for removal with id=" + id);
        }
        else
        {
            //if all related objects are empty for the given object then we can erase this object
            itemDao.getRelatedObjects(item);

            String relatedObjectNames = "";
            boolean canBeDeleted = true;

            if (item.getItemAttributeList().size() != 0)
            {
                relatedObjectNames += "ItemAttribute ";
                canBeDeleted = false;
            }

            if (item.getItemCategoryList().size() != 0)
            {
                relatedObjectNames += "ItemCategory ";
                canBeDeleted = false;
            }

            if (item.getItemDiscountList().size() != 0)
            {
                relatedObjectNames += "ItemDiscount ";
                canBeDeleted = false;
            }

            if (item.getItemFileList().size() != 0)
            {
                relatedObjectNames += "ItemFile ";
                canBeDeleted = false;
            }

            if (item.getItemImageList().size() != 0)
            {
                relatedObjectNames += "ItemImage ";
                canBeDeleted = false;
            }

            if (item.getItemLocationList().size() != 0)
            {
                relatedObjectNames += "ItemLocation ";
                canBeDeleted = false;
            }

            if (item.getItemReviewList().size() != 0)
            {
                relatedObjectNames += "ItemReview ";
                canBeDeleted = false;
            }

            if (item.getOptionAvailabilityList().size() != 0)
            {
                relatedObjectNames += "OptionAvailability ";
                canBeDeleted = false;
            }

            if (item.getOrderItemList().size() != 0)
            {
                relatedObjectNames += "OrderItem ";
                canBeDeleted = false;
            }

            if (item.getSiteItemList().size() != 0)
            {
                relatedObjectNames += "SiteItem ";
                canBeDeleted = false;
            }

            if (canBeDeleted)
            {
                itemDao.remove(item);

                String msg = "Item with Id: " + item.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);
            }
            else
            {
                return ResultFactory.getFailResult("Item is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
        }
    }
}
