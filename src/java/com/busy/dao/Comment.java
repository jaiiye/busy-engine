package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Comment extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_COMMENTID = "CommentId";
    public static final String PROP_POSTID = "PostId";
    public static final String PROP_COMMENTTITLE = "CommentTitle";
    public static final String PROP_COMMENTBODY = "CommentBody";
    public static final String PROP_COMMENTDATE = "CommentDate";
    public static final String PROP_USERID = "UserId";

    private Integer commentId;
    private Integer postId;
    private String commentTitle;
    private String commentBody;
    private Date commentDate;
    private Integer userId;

    public Comment()
    {
        this.commentId = 0;
        this.postId = 0;
        this.commentTitle = "";
        this.commentBody = "";
        this.commentDate = null;
        this.userId = 0;
    }

    public Comment(Integer CommentId, Integer PostId, String CommentTitle, String CommentBody, Date CommentDate, Integer UserId)
    {
        this.commentId = CommentId;
        this.postId = PostId;
        this.commentTitle = CommentTitle;
        this.commentBody = CommentBody;
        this.commentDate = CommentDate;
        this.userId = UserId;
    }

    public Integer getCommentId()
    {
        return this.commentId;
    }

    public void setCommentId(Integer CommentId)
    {
        this.commentId = CommentId;
    }

    public Integer getPostId()
    {
        return this.postId;
    }

    public void setPostId(Integer PostId)
    {
        this.postId = PostId;
    }

    public String getCommentTitle()
    {
        return this.commentTitle;
    }

    public void setCommentTitle(String CommentTitle)
    {
        this.commentTitle = CommentTitle;
    }

    public String getCommentBody()
    {
        return this.commentBody;
    }

    public void setCommentBody(String CommentBody)
    {
        this.commentBody = CommentBody;
    }

    public Date getCommentDate()
    {
        return this.commentDate;
    }

    public void setCommentDate(Date CommentDate)
    {
        this.commentDate = CommentDate;
    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer UserId)
    {
        this.userId = UserId;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(Comment.PROP_COMMENTID) || column.equals(Comment.PROP_POSTID) || column.equals(Comment.PROP_COMMENTTITLE) || column.equals(Comment.PROP_COMMENTBODY) || column.equals(Comment.PROP_COMMENTDATE) || column.equals(Comment.PROP_USERID))
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
        if (column.equals(Comment.PROP_COMMENTID) || column.equals(Comment.PROP_POSTID) || column.equals(Comment.PROP_USERID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Comment processComment(ResultSet rs) throws SQLException
    {
        return new Comment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6));
    }

    public static int addComment(Integer PostId, String CommentTitle, String CommentBody, Date CommentDate, Integer UserId)
    {
        int id = 0;
        try
        {
            checkColumnSize(CommentTitle, 45);
            checkColumnSize(CommentBody, 65535);

            openConnection();
            prepareStatement("INSERT INTO comment(PostId,CommentTitle,CommentBody,CommentDate,UserId) VALUES (?,?,?,?,?);");
            preparedStatement.setInt(1, PostId);
            preparedStatement.setString(2, CommentTitle);
            preparedStatement.setString(3, CommentBody);
            preparedStatement.setDate(4, new java.sql.Date(CommentDate.getTime()));
            preparedStatement.setInt(5, UserId);
            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from comment;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addComment error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllCommentCount()
    {
        return getAllRecordsCountByTableName("comment");
    }

    public static ArrayList<Comment> getAllComment()
    {
        ArrayList<Comment> comment = new ArrayList<Comment>();
        try
        {
            getAllRecordsByTableName("comment");
            while (rs.next())
            {
                comment.add(processComment(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllComment error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return comment;
    }

    public static ArrayList<Comment> getCommentPaged(int limit, int offset)
    {
        ArrayList<Comment> comment = new ArrayList<Comment>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("comment", limit, offset);
            while (rs.next())
            {
                comment.add(processComment(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getCommentPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return comment;
    }

    public static ArrayList<Comment> getAllCommentByColumn(String columnName, String columnValue)
    {
        ArrayList<Comment> comment = new ArrayList<Comment>();
        try
        {
            getAllRecordsByColumn("comment", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                comment.add(processComment(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllCommentByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return comment;
    }

    public static Comment getCommentByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        Comment comment = new Comment();
        try
        {
            getRecordsByColumnWithLimitAndOffset("comment", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                comment = processComment(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getCommentByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return comment;
    }

    public static void updateComment(Integer CommentId, Integer PostId, String CommentTitle, String CommentBody, Date CommentDate, Integer UserId)
    {
        try
        {
            checkColumnSize(CommentTitle, 45);
            checkColumnSize(CommentBody, 65535);

            openConnection();
            prepareStatement("UPDATE comment SET PostId=?,CommentTitle=?,CommentBody=?,CommentDate=?,UserId=? WHERE CommentId=?;");
            preparedStatement.setInt(1, PostId);
            preparedStatement.setString(2, CommentTitle);
            preparedStatement.setString(3, CommentBody);
            preparedStatement.setDate(4, new java.sql.Date(CommentDate.getTime()));
            preparedStatement.setInt(5, UserId);
            preparedStatement.setInt(6, CommentId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateComment error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllComment()
    {
        try
        {
            updateQuery("DELETE FROM comment;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllComment error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteCommentById(String id)
    {
        try
        {
            updateQuery("DELETE FROM comment WHERE CommentId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteCommentById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteCommentByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM comment WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteCommentByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
