package com.busy.engine.domain;

import com.busy.engine.dao.DashboardDaoImpl;
import com.busy.engine.dao.UserActionDaoImpl;
import com.busy.engine.data.Database;
import com.busy.engine.entity.Dashboard;
import com.busy.engine.entity.UserAction;
import java.util.ArrayList;

public class DashboardInfo extends Dashboard
{
    private ArrayList<UserAction> activities;

    public DashboardInfo()
    {
        super();
        activities = new ArrayList<UserAction>();
    }

    public DashboardInfo(Integer DashboardId, Integer UserCount, Integer BlogPostCount, Integer ItemCount, Integer OrderCount, Integer SiteFileCount, Integer ImageCount, Integer BlogCount, Integer CommentCount, Integer PageCount, Integer FormCount, Integer SliderCount, Integer ItemBrandCount, Integer CategoryCount, Integer ItemOptionCount)
    {
        super(DashboardId, UserCount, BlogPostCount, ItemCount, OrderCount, SiteFileCount, ImageCount, BlogCount, CommentCount, PageCount, FormCount, SliderCount, ItemBrandCount, CategoryCount, ItemOptionCount, 0, 0, 0);
        activities = new ArrayList<UserAction>();
    }

    public ArrayList<UserAction> getActivities()
    {
        return activities;
    }

    public void setActivities(ArrayList<UserAction> activities)
    {
        this.activities = activities;
    }
    
    public static DashboardInfo getInfo(){
        ArrayList<Dashboard> d = new DashboardDaoImpl().findByColumn(Dashboard.PROP_DASHBOARD_ID, "1", null, null);
        Dashboard db = d.get(0);        
        DashboardInfo di = new DashboardInfo(db.getDashboardId(), db.getUserCount(), db.getBlogPostCount(), db.getItemCount(), db.getOrderCount(), db.getSiteFileCount(), db.getImageCount(), db.getBlogCount(), db.getCommentCount(), db.getPageCount(), db.getFormCount(), db.getSliderCount(), db.getItemBrandCount(), db.getCategoryCount(), db.getItemOptionCount());
        di.setActivities(Database.getMostRecentActivities(50));
        return di;
    }
    
}
