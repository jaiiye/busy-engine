package com.busy;

import com.busy.dao.DashboardDAO;
import com.busy.dao.UserActionDAO;
import com.busy.entity.Dashboard;
import com.busy.entity.UserAction;
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
        super(DashboardId, UserCount, BlogPostCount, ItemCount, OrderCount, SiteFileCount, ImageCount, BlogCount, CommentCount, PageCount, FormCount, SliderCount, ItemBrandCount, CategoryCount, ItemOptionCount);
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
        ArrayList<Dashboard> d = DashboardDAO.getAllDashboardByColumn(Dashboard.PROP_DASHBOARD_ID, "1");
        ArrayList<UserAction> a = UserActionDAO.getUserActionPaged(50, 0);
        Dashboard db = d.get(0);        
        DashboardInfo di = new DashboardInfo(db.getDashboardId(), db.getUserCount(), db.getBlogPostCount(), db.getItemCount(), db.getOrderCount(), db.getSiteFileCount(), db.getImageCount(), db.getBlogCount(), db.getCommentCount(), db.getPageCount(), db.getFormCount(), db.getSliderCount(), db.getItemBrandCount(), db.getCategoryCount(), db.getItemOptionCount());
        di.setActivities(a);
        return di;
    }
    
}
