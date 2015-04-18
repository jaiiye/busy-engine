package com.busy.engine.entity;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.JsonObjectBuilder;
import java.text.SimpleDateFormat;

public class SiteImage extends AbstractEntity implements EntityItem<Integer>
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_SITE_IMAGE_ID = "SiteImageId";
    public static final String PROP_FILE_NAME = "FileName";
    public static final String PROP_DESCRIPTION = "Description";
    public static final String PROP_LINK_URL = "LinkUrl";
    public static final String PROP_RANK = "Rank";
    public static final String PROP_IMAGE_TYPE_ID = "ImageTypeId";
    public static final String PROP_SITE_ID = "SiteId";

    private Integer siteImageId;
    private String fileName;
    private String description;
    private String linkUrl;
    private Integer rank;

    private Integer imageTypeId;
    private ImageType imageType;
    private Integer siteId;
    private Site site;

    public SiteImage()
    {
        this.siteImageId = 0;
        this.fileName = "";
        this.description = "";
        this.linkUrl = "";
        this.rank = 0;
        this.imageTypeId = 0;
        this.siteId = 0;
    }

    @Override
    public Integer getId()
    {
        return siteImageId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder)
    {

        builder.add("siteImageId", siteImageId == null ? 0 : siteImageId);

        builder.add("fileName", fileName == null ? "" : fileName);

        builder.add("description", description == null ? "" : description);

        builder.add("linkUrl", linkUrl == null ? "" : linkUrl);

        builder.add("rank", rank == null ? 0 : rank);

        builder.add("imageTypeId", imageTypeId == null ? 0 : imageTypeId);

        builder.add("siteId", siteId == null ? 0 : siteId);

        if (imageType != null)
        {
            imageType.addJson(builder);
        }

        if (site != null)
        {
            site.addJson(builder);
        }

    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(SiteImage.PROP_SITE_IMAGE_ID) || column.equals(SiteImage.PROP_FILE_NAME) || column.equals(SiteImage.PROP_DESCRIPTION) || column.equals(SiteImage.PROP_LINK_URL) || column.equals(SiteImage.PROP_RANK) || column.equals(SiteImage.PROP_IMAGE_TYPE_ID) || column.equals(SiteImage.PROP_SITE_ID))
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
        if (column.equals(SiteImage.PROP_SITE_IMAGE_ID) || column.equals(SiteImage.PROP_RANK) || column.equals(SiteImage.PROP_IMAGE_TYPE_ID) || column.equals(SiteImage.PROP_SITE_ID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static SiteImage process(ResultSet rs) throws SQLException
    {
        if (rs.getRow() == 0)
        {
            rs.first();
        }
        return new SiteImage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
    }

    public SiteImage(Integer SiteImageId, String FileName, String Description, String LinkUrl, Integer Rank, Integer ImageTypeId, Integer SiteId)
    {
        this.siteImageId = SiteImageId;
        this.fileName = FileName;
        this.description = Description;
        this.linkUrl = LinkUrl;
        this.rank = Rank;
        this.imageTypeId = ImageTypeId;
        this.siteId = SiteId;

    }

    public Integer getSiteImageId()
    {
        return this.siteImageId;
    }

    public void setSiteImageId(Integer SiteImageId)
    {
        this.siteImageId = SiteImageId;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public void setFileName(String FileName)
    {
        this.fileName = FileName;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String Description)
    {
        this.description = Description;
    }

    public String getLinkUrl()
    {
        return this.linkUrl;
    }

    public void setLinkUrl(String LinkUrl)
    {
        this.linkUrl = LinkUrl;
    }

    public Integer getRank()
    {
        return this.rank;
    }

    public void setRank(Integer Rank)
    {
        this.rank = Rank;
    }

    public Integer getImageTypeId()
    {
        return this.imageTypeId;
    }

    public void setImageTypeId(Integer ImageTypeId)
    {
        this.imageTypeId = ImageTypeId;
    }

    public ImageType getImageType()
    {
        return this.imageType;
    }

    public void setImageType(ImageType imageType)
    {
        this.imageType = imageType;
    }

    public Integer getSiteId()
    {
        return this.siteId;
    }

    public void setSiteId(Integer SiteId)
    {
        this.siteId = SiteId;
    }

    public Site getSite()
    {
        return this.site;
    }

    public void setSite(Site site)
    {
        this.site = site;
    }

}
