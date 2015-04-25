package com.busy.engine.listener;

import com.busy.engine.dao.*;
import com.busy.engine.data.*;
import java.util.AbstractMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Sourena
 */
public class ContextListener implements ServletContextListener
{
    private ServletContext context = null;

    public ContextListener()
    {
    }

    // This method is invoked when the Web Application is ready to service requests
    public void contextInitialized(ServletContextEvent event)
    {
        this.context = event.getServletContext();

        System.out.println("Initializing Data Access Objects...");
        
        context.setAttribute("itemDao", new ItemDaoImpl(true));   
        context.setAttribute("userDao", new UserDaoImpl(true));
        context.setAttribute("userRoleDao", new UserRoleDaoImpl(true));
        context.setAttribute("siteDao", new SiteDaoImpl());
        context.setAttribute("itemCategoryDao", new ItemCategoryDaoImpl());
        context.setAttribute("textStringDao", new TextStringDaoImpl());
        context.setAttribute("localizedStringDao", new LocalizedStringDaoImpl());
        
        
        System.out.println("Initializing Localizations...");
        for (AbstractMap.SimpleEntry e : Database.getLanguageStrings("1"))
        {
            context.setAttribute((String) e.getKey(), e.getValue());
            System.out.println("setting Application attribute: (" + e.getKey() + ":" + e.getValue() + ")");
        }
        
//        context.setAttribute("itemAttributeDao", new ItemAttributeDaoImpl());
//        context.setAttribute("itemAttributeTypeDao", new ItemAttributeTypeDaoImpl());
//        context.setAttribute("itemAvailabilityDao", new ItemAvailabilityDaoImpl());
//        context.setAttribute("itemBrandDao", new ItemBrandDaoImpl());

//        context.setAttribute("itemDiscountDao", new ItemDiscountDaoImpl());
//        context.setAttribute("itemFileDao", new ItemFileDaoImpl());
//        context.setAttribute("itemImageDao", new ItemImageDaoImpl());
//        context.setAttribute("itemLocationDao", new ItemLocationDaoImpl());
//        context.setAttribute("itemOptionDao", new ItemOptionDaoImpl());
//        context.setAttribute("itemReviewDao", new ItemReviewDaoImpl());
//        context.setAttribute("itemTypeDao", new ItemTypeDaoImpl());
        
//        context.setAttribute("blogDao", new BlogDaoImpl());
//        context.setAttribute("blogPostDao", new BlogPostDaoImpl());
//        context.setAttribute("blogPostCategoryDao", new BlogPostCategoryDaoImpl());
//        context.setAttribute("blogTypeDao", new BlogTypeDaoImpl());
//
//        context.setAttribute("formDao", new FormDaoImpl());
//        context.setAttribute("formFieldDao", new FormFieldDaoImpl());
//        context.setAttribute("formFieldTypeDao", new FormFieldTypeDaoImpl());
//
//        context.setAttribute("addressDao", new AddressDaoImpl());
//        context.setAttribute("affiliateDao", new AffiliateDaoImpl());
//        context.setAttribute("categoryDao", new CategoryDaoImpl());
//        context.setAttribute("commentDao", new CommentDaoImpl());
//        context.setAttribute("contactDao", new ContactDaoImpl());
//        context.setAttribute("countryDao", new CountryDaoImpl());
//        context.setAttribute("customerDao", new CustomerDaoImpl());
//        context.setAttribute("customerOrderDao", new CustomerOrderDaoImpl());
//        context.setAttribute("dashboardDao", new DashboardDaoImpl());
//        context.setAttribute("discountDao", new DiscountDaoImpl());
//        context.setAttribute("entityStatusDao", new EntityStatusDaoImpl());
//        context.setAttribute("fileFolderDao", new FileFolderDaoImpl());
//        context.setAttribute("imageTypeDao", new ImageTypeDaoImpl());
//        context.setAttribute("knowledgeBaseDao", new KnowledgeBaseDaoImpl());
//        context.setAttribute("localeDao", new LocaleDaoImpl());
//        context.setAttribute("localizedStringDao", new LocalizedStringDaoImpl());
//        context.setAttribute("mailinglistDao", new MailinglistDaoImpl());
//        context.setAttribute("metaTagDao", new MetaTagDaoImpl());
//        context.setAttribute("optionAvailabilityDao", new OptionAvailabilityDaoImpl());
//        context.setAttribute("orderDao", new OrderDaoImpl());
//        context.setAttribute("orderItemDao", new OrderItemDaoImpl());
//        context.setAttribute("pageDao", new PageDaoImpl());
//        context.setAttribute("pageTemplateDao", new PageTemplateDaoImpl());
//        context.setAttribute("paypalDao", new PaypalDaoImpl());
        
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
//        context.setAttribute("Dao", new DaoImpl());
        
    }
        
    // This method is invoked when the Web Application has been removed 
    // and is no longer able to accept requests
    public void contextDestroyed(ServletContextEvent event)
    {
        //Output a simple message to the server's console
        System.out.println("The Simple Web App. Has Been Removed");
        this.context = null;
    }
}
