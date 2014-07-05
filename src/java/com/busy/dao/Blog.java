package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Blog extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_BLOGID = "BlogId";
    public static final String PROP_BLOGNAME = "BlogName";
    public static final String PROP_BLOGLAYOUTTYPE = "BlogLayoutType";

    private Integer blogId;
    private String blogName;
    private Integer blogLayoutType;

    public Blog()
    {
        this.blogId = 0;
        this.blogName = "";
        this.blogLayoutType = 0;
    }

    public Blog(Integer BlogId, String BlogName, Integer BlogLayoutType)
    {
        this.blogId = BlogId;
        this.blogName = BlogName;
        this.blogLayoutType = BlogLayoutType;
    }

    public Integer getBlogId()
    {
        return this.blogId;
    }

    public void setBlogId(Integer BlogId)
    {
        this.blogId = BlogId;
    }

    public String getBlogName()
    {
        return this.blogName;
    }

    public void setBlogName(String BlogName)
    {
        this.blogName = BlogName;
    }

    public Integer getBlogLayoutType()
    {
        return this.blogLayoutType;
    }

    public void setBlogLayoutType(Integer BlogLayoutType)
    {
        this.blogLayoutType = BlogLayoutType;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(Blog.PROP_BLOGID) || column.equals(Blog.PROP_BLOGNAME) || column.equals(Blog.PROP_BLOGLAYOUTTYPE))
        {
            return column;
        }
        else
        {
            throw new SQLException("Invalid column name: " + column);
        }
    }

    public static void checkColumnSize(String column, int size) throws Exception
    {
        if (column.length() > size)
        {
            throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
        }
    }

    public static boolean isColumnNumeric(String column)
    {
        if (column.equals(Blog.PROP_BLOGID) || column.equals(Blog.PROP_BLOGLAYOUTTYPE))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Blog processBlog(ResultSet rs) throws SQLException
    {
        return new Blog(rs.getInt(1), rs.getString(2), rs.getInt(3));
    }

    public static int addBlog(String BlogName, Integer BlogLayoutType)
    {
        int id = 0;
        try
        {

            checkColumnSize(BlogName, 100);

            openConnection();
            prepareStatement("INSERT INTO blog(BlogName,BlogLayoutType) VALUES (?,?);");
            preparedStatement.setString(1, BlogName);
            preparedStatement.setInt(2, BlogLayoutType);

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addBlog error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllBlogCount()
    {
        return getAllRecordsCountByTableName("blog");
    }

    public static ArrayList<Blog> getAllBlog()
    {
        ArrayList<Blog> blog = new ArrayList<Blog>();
        try
        {
            getAllRecordsByTableName("blog");
            while (rs.next())
            {
                blog.add(processBlog(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllBlog error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return blog;
    }

    public static ArrayList<Blog> getBlogPaged(int limit, int offset)
    {
        ArrayList<Blog> blog = new ArrayList<Blog>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("blog", limit, offset);
            while (rs.next())
            {
                blog.add(processBlog(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getBlogPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return blog;
    }

    public static ArrayList<Blog> getAllBlogByColumn(String columnName, String columnValue)
    {
        ArrayList<Blog> blog = new ArrayList<Blog>();
        try
        {
            getAllRecordsByColumn("blog", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                blog.add(processBlog(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllBlogByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return blog;
    }

    public static Blog getBlogByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        Blog blog = new Blog();
        try
        {
            getRecordsByColumnWithLimitAndOffset("blog", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                blog = processBlog(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getBlogByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return blog;
    }

    public static void updateBlog(Integer BlogId, String BlogName, Integer BlogLayoutType)
    {
        try
        {

            checkColumnSize(BlogName, 100);

            openConnection();
            prepareStatement("UPDATE blog SET BlogName=?,BlogLayoutType=? WHERE BlogId=?;");
            preparedStatement.setString(1, BlogName);
            preparedStatement.setInt(2, BlogLayoutType);
            preparedStatement.setInt(3, BlogId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateBlog error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllBlog()
    {
        try
        {
            updateQuery("DELETE FROM blog;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllBlog error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteBlogById(String id)
    {
        try
        {
            updateQuery("DELETE FROM blog WHERE BlogId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteBlogById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteBlogByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM blog WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteBlogByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
