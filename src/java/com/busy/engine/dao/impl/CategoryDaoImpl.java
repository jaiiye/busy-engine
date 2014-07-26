package com.busy.engine.dao.impl;

import com.transitionsoft.BasicConnection;
import com.busy.engine.domain.*;
import com.busy.engine.dao.base.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.SQLException;

public class CategoryDaoImpl extends BasicConnection implements Serializable, CategoryDao
{

    private static final long serialVersionUID = 1L;

    @Override
    public Category find(Integer id)
    {
        return findByColumn("CategoryId", id.toString(), null, null).get(0);
    }

    @Override
    public ArrayList<Category> findAll(Integer limit, Integer offset)
    {
        ArrayList<Category> category = new ArrayList<>();
        try
        {
            getAllRecordsByTableName("category");
            while (rs.next())
            {
                category.add(Category.process(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Category object's findAll() method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return category;

    }

    @Override
    public ArrayList<Category> findAllWithInfo(Integer limit, Integer offset)
    {
        ArrayList<Category> categoryList = new ArrayList<>();
        try
        {
            getRecordsByTableNameWithLimitOrOffset("category", limit, offset);
            while (rs.next())
            {
                categoryList.add(Category.process(rs));
            }

            for (Category category : categoryList)
            {

                getRecordById("Discount", category.getDiscountId().toString());
                category.setDiscount(Discount.process(rs));

                getRecordById("Category", category.getParentId().toString());
                category.setParent(Category.process(rs));

            }

        }
        catch (SQLException ex)
        {
            System.out.println("Object Category method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return categoryList;
    }

    @Override
    public ArrayList<Category> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
    {
        ArrayList<Category> category = new ArrayList<>();
        try
        {
            getRecordsByColumnWithLimitOrOffset("category", Category.checkColumnName(columnName), columnValue, Category.isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                category.add(Category.process(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Object Category's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return category;
    }

    @Override
    public void add(Category obj)
    {
        try
        {

            Category.checkColumnSize(obj.getCategoryName(), 100);

            openConnection();
            prepareStatement("INSERT INTO category(CategoryName,DiscountId,ParentId) VALUES (?,?,?);");
            preparedStatement.setString(1, obj.getCategoryName());
            preparedStatement.setInt(2, obj.getDiscountId());
            preparedStatement.setInt(3, obj.getParentId());

            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("Category's add method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static int add(String CategoryName, Integer DiscountId, Integer ParentId)
    {
        int id = 0;
        try
        {

            Category.checkColumnSize(CategoryName, 100);

            openConnection();
            prepareStatement("INSERT INTO category(CategoryName,DiscountId,ParentId) VALUES (?,?,?);");
            preparedStatement.setString(1, CategoryName);
            preparedStatement.setInt(2, DiscountId);
            preparedStatement.setInt(3, ParentId);

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from category;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addCategory error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    @Override
    public Category update(Category obj)
    {
        try
        {

            Category.checkColumnSize(obj.getCategoryName(), 100);

            openConnection();
            prepareStatement("UPDATE category SET CategoryName=?,DiscountId=?,ParentId=? WHERE CategoryId=?;");
            preparedStatement.setString(1, obj.getCategoryName());
            preparedStatement.setInt(2, obj.getDiscountId());
            preparedStatement.setInt(3, obj.getParentId());
            preparedStatement.setInt(4, obj.getCategoryId());
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateCategory error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return obj;
    }

    public static void update(Integer CategoryId, String CategoryName, Integer DiscountId, Integer ParentId)
    {
        try
        {

            Category.checkColumnSize(CategoryName, 100);

            openConnection();
            prepareStatement("UPDATE category SET CategoryName=?,DiscountId=?,ParentId=? WHERE CategoryId=?;");
            preparedStatement.setString(1, CategoryName);
            preparedStatement.setInt(2, DiscountId);
            preparedStatement.setInt(3, ParentId);
            preparedStatement.setInt(4, CategoryId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateCategory error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    @Override
    public int getAllCount()
    {
        return getAllRecordsCountByTableName("category");
    }

    @Override
    public Category getRelatedInfo(Category category)
    {

        try
        {

            getRecordById("Discount", category.getDiscountId().toString());
            category.setDiscount(Discount.process(rs));

            getRecordById("Category", category.getParentId().toString());
            category.setParent(Category.process(rs));

        }
        catch (SQLException ex)
        {
            System.out.println("getRelatedInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return category;
    }

    @Override
    public Category getRelatedObjects(Category category)
    {
        category.setItemCategoryList(new ItemCategoryDaoImpl().findByColumn("CategoryId", category.getCategoryId().toString(), null, null));

        return category;
    }

    @Override
    public void remove(Category obj)
    {
        try
        {
            updateQuery("DELETE FROM category WHERE CategoryId=" + obj.getCategoryId() + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteCategoryById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    @Override
    public void remove(Integer id)
    {
        try
        {
            updateQuery("DELETE FROM category WHERE CategoryId=" + id.intValue() + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteCategoryById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    @Override
    public void removeAll()
    {
        try
        {
            updateQuery("DELETE FROM category;");
        }
        catch (Exception ex)
        {
            System.out.println("Category's deleteAll() method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    @Override
    public void removeByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM category WHERE " + Category.checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("Category's deleteByColumn method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public Category getRelatedItemCategoryList(Category category)
    {
        category.setItemCategoryList(new ItemCategoryDaoImpl().findByColumn("CategoryId", category.getCategoryId().toString(), null, null));
        return category;
    }

}
