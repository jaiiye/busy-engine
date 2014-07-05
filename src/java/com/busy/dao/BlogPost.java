package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BlogPost extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_BLOGPOSTID = "BlogPostId";
    public static final String PROP_POSTTITLE = "PostTitle";
    public static final String PROP_POSTBODY = "PostBody";
    public static final String PROP_POSTPICURL = "PostPicURL";
    public static final String PROP_POSTDATE = "PostDate";
    public static final String PROP_POSTTAGS = "PostTags";
    public static final String PROP_POSTFEATURED = "PostFeatured";
    public static final String PROP_USERID = "UserId";
    public static final String PROP_POSTRATING = "PostRating";
    public static final String PROP_BLOGID = "BlogId";

    private Integer blogPostId;
    private String postTitle;
    private String postBody;
    private String postPicURL;
    private Date postDate;
    private String postTags;
    private Integer postFeatured;
    private Integer userId;
    private Double postRating;
    private Integer blogId;

    public BlogPost()
    {
        this.blogPostId = 0;
        this.postTitle = "";
        this.postBody = "";
        this.postPicURL = "";
        this.postDate = null;
        this.postTags = "";
        this.postFeatured = 0;
        this.userId = 0;
        this.postRating = 0.0;
        this.blogId = 0;
    }

    public BlogPost(Integer BlogPostId, String PostTitle, String PostBody, String PostPicURL, Date PostDate, String PostTags, Integer PostFeatured, Integer UserId, Double PostRating, Integer BlogId)
    {
        this.blogPostId = BlogPostId;
        this.postTitle = PostTitle;
        this.postBody = PostBody;
        this.postPicURL = PostPicURL;
        this.postDate = PostDate;
        this.postTags = PostTags;
        this.postFeatured = PostFeatured;
        this.userId = UserId;
        this.postRating = PostRating;
        this.blogId = BlogId;
    }

    public Integer getBlogPostId()
    {
        return this.blogPostId;
    }

    public void setBlogPostId(Integer BlogPostId)
    {
        this.blogPostId = BlogPostId;
    }

    public String getPostTitle()
    {
        return this.postTitle;
    }

    public void setPostTitle(String PostTitle)
    {
        this.postTitle = PostTitle;
    }

    public String getPostBody()
    {
        return this.postBody;
    }

    public void setPostBody(String PostBody)
    {
        this.postBody = PostBody;
    }

    public String getPostPicURL()
    {
        return this.postPicURL;
    }

    public void setPostPicURL(String PostPicURL)
    {
        this.postPicURL = PostPicURL;
    }

    public Date getPostDate()
    {
        return this.postDate;
    }

    public void setPostDate(Date PostDate)
    {
        this.postDate = PostDate;
    }

    public String getPostTags()
    {
        return this.postTags;
    }

    public void setPostTags(String PostTags)
    {
        this.postTags = PostTags;
    }

    public Integer getPostFeatured()
    {
        return this.postFeatured;
    }

    public void setPostFeatured(Integer PostFeatured)
    {
        this.postFeatured = PostFeatured;
    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer UserId)
    {
        this.userId = UserId;
    }

    public Double getPostRating()
    {
        return this.postRating;
    }

    public void setPostRating(Double PostRating)
    {
        this.postRating = PostRating;
    }

    public Integer getBlogId()
    {
        return this.blogId;
    }

    public void setBlogId(Integer BlogId)
    {
        this.blogId = BlogId;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(BlogPost.PROP_BLOGPOSTID) || column.equals(BlogPost.PROP_POSTTITLE) || column.equals(BlogPost.PROP_POSTBODY) || column.equals(BlogPost.PROP_POSTPICURL) || column.equals(BlogPost.PROP_POSTDATE) || column.equals(BlogPost.PROP_POSTTAGS) || column.equals(BlogPost.PROP_POSTFEATURED) || column.equals(BlogPost.PROP_USERID) || column.equals(BlogPost.PROP_POSTRATING) || column.equals(BlogPost.PROP_BLOGID))
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
        if (column.equals(BlogPost.PROP_BLOGPOSTID) || column.equals(BlogPost.PROP_POSTFEATURED) || column.equals(BlogPost.PROP_USERID) || column.equals(BlogPost.PROP_POSTRATING) || column.equals(BlogPost.PROP_BLOGID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static BlogPost processBlogPost(ResultSet rs) throws SQLException
    {
        return new BlogPost(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getDouble(9), rs.getInt(10));
    }

    public static int addBlogPost(String PostTitle, String PostBody, String PostPicURL, Date PostDate, String PostTags, Integer PostFeatured, Integer UserId, Double PostRating, Integer BlogId)
    {
        int id = 0;
        try
        {

            checkColumnSize(PostTitle, 255);
            checkColumnSize(PostBody, 65535);
            checkColumnSize(PostPicURL, 255);

            checkColumnSize(PostTags, 255);

            openConnection();
            prepareStatement("INSERT INTO blog_post(PostTitle,PostBody,PostPicURL,PostDate,PostTags,PostFeatured,UserId,PostRating,BlogId) VALUES (?,?,?,?,?,?,?,?,?);");
            preparedStatement.setString(1, PostTitle);
            preparedStatement.setString(2, PostBody);
            preparedStatement.setString(3, PostPicURL);
            preparedStatement.setDate(4, new java.sql.Date(PostDate.getTime()));
            preparedStatement.setString(5, PostTags);
            preparedStatement.setInt(6, PostFeatured);
            preparedStatement.setInt(7, UserId);
            preparedStatement.setDouble(8, PostRating);
            preparedStatement.setInt(9, BlogId);

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog_post;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addBlogPost error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllBlogPostCount()
    {
        return getAllRecordsCountByTableName("blog_post");
    }

    public static ArrayList<BlogPost> getAllBlogPost()
    {
        ArrayList<BlogPost> blog_post = new ArrayList<BlogPost>();
        try
        {
            getAllRecordsByTableName("blog_post");
            while (rs.next())
            {
                blog_post.add(processBlogPost(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllBlogPost error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return blog_post;
    }

    public static ArrayList<BlogPost> getBlogPostPaged(int limit, int offset)
    {
        ArrayList<BlogPost> blog_post = new ArrayList<BlogPost>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("blog_post", limit, offset);
            while (rs.next())
            {
                blog_post.add(processBlogPost(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getBlogPostPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return blog_post;
    }

    public static ArrayList<BlogPost> getAllBlogPostByColumn(String columnName, String columnValue)
    {
        ArrayList<BlogPost> blog_post = new ArrayList<BlogPost>();
        try
        {
            getAllRecordsByColumn("blog_post", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                blog_post.add(processBlogPost(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllBlogPostByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return blog_post;
    }

    public static BlogPost getBlogPostByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        BlogPost blog_post = new BlogPost();
        try
        {
            getRecordsByColumnWithLimitAndOffset("blog_post", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                blog_post = processBlogPost(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getBlogPostByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return blog_post;
    }

    public static void updateBlogPost(Integer BlogPostId, String PostTitle, String PostBody, String PostPicURL, Date PostDate, String PostTags, Integer PostFeatured, Integer UserId, Double PostRating, Integer BlogId)
    {
        try
        {

            checkColumnSize(PostTitle, 255);
            checkColumnSize(PostBody, 65535);
            checkColumnSize(PostPicURL, 255);

            checkColumnSize(PostTags, 255);

            openConnection();
            prepareStatement("UPDATE blog_post SET PostTitle=?,PostBody=?,PostPicURL=?,PostDate=?,PostTags=?,PostFeatured=?,UserId=?,PostRating=?,BlogId=? WHERE BlogPostId=?;");
            preparedStatement.setString(1, PostTitle);
            preparedStatement.setString(2, PostBody);
            preparedStatement.setString(3, PostPicURL);
            preparedStatement.setDate(4, new java.sql.Date(PostDate.getTime()));
            preparedStatement.setString(5, PostTags);
            preparedStatement.setInt(6, PostFeatured);
            preparedStatement.setInt(7, UserId);
            preparedStatement.setDouble(8, PostRating);
            preparedStatement.setInt(9, BlogId);
            preparedStatement.setInt(10, BlogPostId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateBlogPost error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllBlogPost()
    {
        try
        {
            updateQuery("DELETE FROM blog_post;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllBlogPost error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteBlogPostById(String id)
    {
        try
        {
            updateQuery("DELETE FROM blog_post WHERE BlogPostId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteBlogPostById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteBlogPostByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM blog_post WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteBlogPostByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
