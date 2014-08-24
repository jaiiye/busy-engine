package com.busy.engine.web;

import com.busy.engine.entity.AbstractEntity;
import com.busy.engine.entity.User;
import com.busy.engine.service.*;
import com.busy.engine.util.PathProcessor;
import com.busy.engine.vo.Result;
import java.util.List;
import static com.busy.engine.web.AbstractHandler.getJsonErrorMsg;
import static com.busy.engine.web.AbstractHandler.getJsonSuccessData;
import static com.busy.engine.web.AbstractHandler.getJsonSuccessMsg;
import static com.busy.engine.web.SecurityHelper.getSessionUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 *
 * @author Sourena
 */
@WebServlet("/rest/*")
public class ServiceHandler extends AbstractHandler
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json");
        PathProcessor pp = new PathProcessor(request.getPathInfo());
        PrintWriter out = response.getWriter();
        SimpleDateFormat operatingDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        User sessionUser = getSessionUser(request);
        if (sessionUser == null)
        {
            out.print(getJsonErrorMsg("User is not logged on"));
        }
        else
        {
            switch (pp.getResource())
            {

                case "address":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new AddressServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "addressId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new AddressServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "affiliate":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new AffiliateServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "affiliateId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new AffiliateServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "blog":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new BlogServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "blogId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new BlogServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "blogPost":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new BlogPostServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "blogPostId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new BlogPostServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "blogPostCategory":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new BlogPostCategoryServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "blogPostCategoryId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new BlogPostCategoryServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "blogType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new BlogTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "blogTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new BlogTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "category":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new CategoryServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "categoryId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new CategoryServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "comment":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new CommentServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "commentId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new CommentServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "contact":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ContactServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "contactId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ContactServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "country":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new CountryServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "countryId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new CountryServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "customer":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new CustomerServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "customerId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new CustomerServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "customerOrder":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new CustomerOrderServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "customerOrderId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new CustomerOrderServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "dashboard":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new DashboardServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "dashboardId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new DashboardServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "discount":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new DiscountServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "discountId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new DiscountServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "entityStatus":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new EntityStatusServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "entityStatusId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new EntityStatusServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "fileFolder":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new FileFolderServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "fileFolderId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new FileFolderServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "form":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new FormServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "formId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new FormServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "formField":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new FormFieldServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "formFieldId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new FormFieldServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "formFieldType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new FormFieldTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "formFieldTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new FormFieldTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "imageType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ImageTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "imageTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ImageTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "item":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemAttribute":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemAttributeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemAttributeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemAttributeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemAttributeType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemAttributeTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemAttributeTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemAttributeTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemAvailability":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemAvailabilityServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemAvailabilityId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemAvailabilityServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemBrand":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemBrandServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemBrandId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemBrandServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemCategory":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemCategoryServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemCategoryId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemCategoryServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemDiscount":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemDiscountServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemDiscountId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemDiscountServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemFile":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemFileServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemFileId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemFileServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemImage":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemImageServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemImageId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemImageServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemLocation":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemLocationServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemLocationId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemLocationServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemOption":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemOptionServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemOptionId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemOptionServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemReview":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemReviewServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemReviewId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemReviewServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ItemTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "itemTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ItemTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "knowledgeBase":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new KnowledgeBaseServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "knowledgeBaseId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new KnowledgeBaseServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "locale":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new LocaleServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "localeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new LocaleServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "localizedString":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new LocalizedStringServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "localizedStringId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new LocalizedStringServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "mailinglist":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new MailinglistServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "mailinglistId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new MailinglistServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "metaTag":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new MetaTagServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "metaTagId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new MetaTagServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "optionAvailability":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new OptionAvailabilityServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "optionAvailabilityId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new OptionAvailabilityServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "order":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new OrderServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "orderId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new OrderServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "orderItem":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new OrderItemServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "orderItemId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new OrderItemServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "page":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new PageServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "pageId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new PageServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "pageTemplate":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new PageTemplateServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "pageTemplateId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new PageTemplateServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "paypal":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new PaypalServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "paypalId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new PaypalServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "postCategory":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new PostCategoryServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "postCategoryId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new PostCategoryServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "recurringPayment":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new RecurringPaymentServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "recurringPaymentId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new RecurringPaymentServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "relatedItem":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new RelatedItemServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "relatedItemId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new RelatedItemServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "resourceType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ResourceTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "resourceTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ResourceTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "resourceUrl":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ResourceUrlServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "resourceUrlId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ResourceUrlServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "returnRequest":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ReturnRequestServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "returnRequestId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ReturnRequestServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "service":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ServiceServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "serviceId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ServiceServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "serviceCharge":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ServiceChargeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "serviceChargeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ServiceChargeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "serviceType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ServiceTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "serviceTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ServiceTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "shipment":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ShipmentServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "shipmentId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ShipmentServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "shipping":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new ShippingServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "shippingId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new ShippingServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "site":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SiteServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "siteId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SiteServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteAttribute":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SiteAttributeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "siteAttributeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SiteAttributeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteFile":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SiteFileServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "siteFileId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SiteFileServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteFolder":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SiteFolderServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "siteFolderId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SiteFolderServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteImage":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SiteImageServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "siteImageId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SiteImageServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteItem":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SiteItemServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "siteItemId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SiteItemServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteLanguage":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SiteLanguageServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "siteLanguageId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SiteLanguageServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "sitePage":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SitePageServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "sitePageId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SitePageServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "slider":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SliderServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "sliderId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SliderServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "sliderItem":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SliderItemServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "sliderItemId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SliderItemServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "sliderType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new SliderTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "sliderTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new SliderTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "stateProvince":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new StateProvinceServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "stateProvinceId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new StateProvinceServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "taxRate":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new TaxRateServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "taxRateId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new TaxRateServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "template":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new TemplateServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "templateId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new TemplateServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "templateType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new TemplateTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "templateTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new TemplateTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "textString":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new TextStringServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "textStringId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new TextStringServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "user":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new UserServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "userId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new UserServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "userAction":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new UserActionServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "userActionId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new UserActionServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "userActionType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new UserActionTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "userActionTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new UserActionTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "userGroup":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new UserGroupServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "userGroupId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new UserGroupServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "userService":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new UserServiceServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "userServiceId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new UserServiceServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "userType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new UserTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "userTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new UserTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "vendor":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new VendorServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "vendorId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new VendorServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "vendorType":
                {
                    try
                    {
                        switch (pp.getOperation())
                        {
                            case "find":
                                generateFindServiceResult(new VendorTypeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "vendorTypeId"))), out);
                                break;
                            case "findAll":
                                generateFindAllServiceResult(new VendorTypeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                default:
                    out.print(getJsonErrorMsg("Invalid Resource"));
                    break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json");
        PathProcessor pp = new PathProcessor(request.getPathInfo());
        PrintWriter out = response.getWriter();
        SimpleDateFormat operatingDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        User sessionUser = getSessionUser(request);
        if (sessionUser == null)
        {
            out.print(getJsonErrorMsg("User is not logged on"));
        }
        else
        {
            switch (pp.getResource())
            {

                case "address":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new AddressServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("addressId")), obj.getString("recipient"), obj.getString("address1"), obj.getString("address2"), obj.getString("city"), obj.getString("stateProvince"), obj.getString("zipPostalCode"), obj.getString("country"), obj.getString("region"), getIntegerValue(obj.get("addressStatus")), obj.getString("locale")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new AddressServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("addressId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "affiliate":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new AffiliateServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("affiliateId")), obj.getString("companyName"), obj.getString("email"), obj.getString("phone"), obj.getString("fax"), obj.getString("webUrl"), obj.getString("details"), getIntegerValue(obj.get("serviceHours")), getIntegerValue(obj.get("affiliateStatus")), getIntegerValue(obj.get("userId")), getIntegerValue(obj.get("contactId")), getIntegerValue(obj.get("addressId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new AffiliateServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("affiliateId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "blog":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new BlogServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("blogId")), obj.getString("topic"), getIntegerValue(obj.get("blogTypeId")), getIntegerValue(obj.get("knowledgeBaseId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new BlogServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("blogId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "blogPost":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new BlogPostServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("blogPostId")), obj.getString("title"), obj.getString("content"), obj.getString("imageURL"), obj.getString("tags"), getIntegerValue(obj.get("featured")), getIntegerValue(obj.get("ratingSum")), getIntegerValue(obj.get("voteCount")), getIntegerValue(obj.get("commentCount")), getIntegerValue(obj.get("postStatus")), obj.getString("excerpt"), operatingDateFormat.parse(obj.getString("lastModified")), obj.getString("locale"), getIntegerValue(obj.get("userId")), getIntegerValue(obj.get("blogId")), getIntegerValue(obj.get("metaTagId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new BlogPostServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("blogPostId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "blogPostCategory":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new BlogPostCategoryServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("blogPostCategoryId")), getIntegerValue(obj.get("blogPostId")), getIntegerValue(obj.get("postCategoryId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new BlogPostCategoryServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("blogPostCategoryId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "blogType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new BlogTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("blogTypeId")), obj.getString("typeName")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new BlogTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("blogTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "category":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new CategoryServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("categoryId")), obj.getString("categoryName"), getIntegerValue(obj.get("discountId")), getIntegerValue(obj.get("parentCategoryId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new CategoryServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("categoryId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "comment":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new CommentServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("commentId")), obj.getString("title"), obj.getString("content"), operatingDateFormat.parse(obj.getString("date")), getIntegerValue(obj.get("commentStatus")), getIntegerValue(obj.get("userId")), getIntegerValue(obj.get("blogPostId")), getIntegerValue(obj.get("itemReviewId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new CommentServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("commentId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "contact":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ContactServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("contactId")), obj.getString("title"), obj.getString("firstName"), obj.getString("lastName"), obj.getString("position"), obj.getString("phone"), obj.getString("fax"), obj.getString("email"), getIntegerValue(obj.get("contactStatus")), obj.getString("webUrl"), obj.getString("info")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ContactServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("contactId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "country":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new CountryServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("countryId")), obj.getString("name"), obj.getString("isoCode"), getIntegerValue(obj.get("isoNumber")), getIntegerValue(obj.get("hasVat"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new CountryServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("countryId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "customer":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new CustomerServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("customerId")), getIntegerValue(obj.get("contactId")), getIntegerValue(obj.get("userId")), getIntegerValue(obj.get("billingAddressId")), getIntegerValue(obj.get("shippingAddressId")), getIntegerValue(obj.get("customerStatus"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new CustomerServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("customerId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "customerOrder":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new CustomerOrderServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("customerOrderId")), getIntegerValue(obj.get("customerId")), getIntegerValue(obj.get("orderId")), getIntegerValue(obj.get("discountId")), obj.getString("customerIp")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new CustomerOrderServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("customerOrderId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "dashboard":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new DashboardServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("dashboardId")), getIntegerValue(obj.get("userCount")), getIntegerValue(obj.get("blogPostCount")), getIntegerValue(obj.get("itemCount")), getIntegerValue(obj.get("orderCount")), getIntegerValue(obj.get("siteFileCount")), getIntegerValue(obj.get("imageCount")), getIntegerValue(obj.get("blogCount")), getIntegerValue(obj.get("commentCount")), getIntegerValue(obj.get("pageCount")), getIntegerValue(obj.get("formCount")), getIntegerValue(obj.get("sliderCount")), getIntegerValue(obj.get("itemBrandCount")), getIntegerValue(obj.get("categoryCount")), getIntegerValue(obj.get("itemOptionCount"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new DashboardServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("dashboardId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "discount":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new DiscountServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("discountId")), obj.getString("discountName"), obj.getJsonNumber("discountAmount").doubleValue(), obj.getJsonNumber("discountPercent").doubleValue(), operatingDateFormat.parse(obj.getString("startDate")), operatingDateFormat.parse(obj.getString("endDate")), obj.getString("couponCode"), getIntegerValue(obj.get("discountStatus")), getIntegerValue(obj.get("askCouponCode")), getIntegerValue(obj.get("usePercentage"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new DiscountServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("discountId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "entityStatus":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new EntityStatusServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("entityStatusId")), getIntegerValue(obj.get("statusCode")), obj.getString("statusName"), obj.getString("appliesTo")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new EntityStatusServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("entityStatusId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "fileFolder":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new FileFolderServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("fileFolderId")), getIntegerValue(obj.get("siteFileId")), getIntegerValue(obj.get("siteFolderId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new FileFolderServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("fileFolderId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "form":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new FormServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("formId")), obj.getString("formName"), obj.getString("description"), obj.getString("submissionEmail"), obj.getString("submissionMethod"), obj.getString("action"), getIntegerValue(obj.get("resettable")), getIntegerValue(obj.get("fileUpload"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new FormServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("formId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "formField":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new FormFieldServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("formFieldId")), obj.getString("fieldName"), obj.getString("label"), obj.getString("errorText"), obj.getString("validationRegex"), getIntegerValue(obj.get("rank")), obj.getString("defaultValue"), obj.getString("options"), obj.getString("groupName"), getIntegerValue(obj.get("optional")), getIntegerValue(obj.get("formFieldTypeId")), getIntegerValue(obj.get("formId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new FormFieldServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("formFieldId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "formFieldType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new FormFieldTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("formFieldTypeId")), obj.getString("typeName"), obj.getString("inputType")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new FormFieldTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("formFieldTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "imageType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ImageTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("imageTypeId")), obj.getString("typeName"), obj.getString("description")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ImageTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("imageTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "item":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemId")), obj.getString("itemName"), obj.getString("description"), obj.getJsonNumber("listPrice").doubleValue(), obj.getJsonNumber("price").doubleValue(), obj.getString("shortDescription"), getIntegerValue(obj.get("adjustment")), obj.getString("sku"), getIntegerValue(obj.get("ratingSum")), getIntegerValue(obj.get("voteCount")), getIntegerValue(obj.get("rank")), getIntegerValue(obj.get("itemStatus")), obj.getString("locale"), getIntegerValue(obj.get("itemTypeId")), getIntegerValue(obj.get("itemBrandId")), getIntegerValue(obj.get("metaTagId")), getIntegerValue(obj.get("templateId")), getIntegerValue(obj.get("vendorId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemAttribute":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemAttributeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemAttributeId")), obj.getString("key"), obj.getString("value"), obj.getString("locale"), getIntegerValue(obj.get("itemAttributeTypeId")), getIntegerValue(obj.get("itemId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemAttributeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemAttributeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemAttributeType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemAttributeTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemAttributeTypeId")), obj.getString("attributeName"), obj.getString("description")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemAttributeTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemAttributeTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemAvailability":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemAvailabilityServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemAvailabilityId")), obj.getString("type")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemAvailabilityServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemAvailabilityId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemBrand":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemBrandServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemBrandId")), obj.getString("brandName"), obj.getString("description")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemBrandServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemBrandId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemCategory":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemCategoryServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemCategoryId")), getIntegerValue(obj.get("categoryId")), getIntegerValue(obj.get("itemId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemCategoryServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemCategoryId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemDiscount":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemDiscountServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemDiscountId")), getIntegerValue(obj.get("itemId")), getIntegerValue(obj.get("discountId")), getIntegerValue(obj.get("applyToOptions"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemDiscountServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemDiscountId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemFile":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemFileServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemFileId")), obj.getString("fileName"), obj.getString("description"), obj.getString("label"), getIntegerValue(obj.get("hidden")), getIntegerValue(obj.get("itemId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemFileServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemFileId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemImage":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemImageServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemImageId")), obj.getString("imageName"), obj.getString("thumbnailName"), obj.getString("alternateText"), getIntegerValue(obj.get("rank")), getIntegerValue(obj.get("itemId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemImageServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemImageId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemLocation":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemLocationServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemLocationId")), obj.getString("latitude"), obj.getString("longitude"), getIntegerValue(obj.get("itemId")), getIntegerValue(obj.get("addressId")), getIntegerValue(obj.get("contactId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemLocationServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemLocationId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemOption":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemOptionServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemOptionId")), obj.getString("optionName"), obj.getString("description")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemOptionServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemOptionId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemReview":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemReviewServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemReviewId")), getIntegerValue(obj.get("itemId")), getIntegerValue(obj.get("rating")), getIntegerValue(obj.get("helpfulYes")), getIntegerValue(obj.get("helpfulNo"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemReviewServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemReviewId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "itemType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ItemTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("itemTypeId")), obj.getString("typeName"), getIntegerValue(obj.get("rank"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ItemTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("itemTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "knowledgeBase":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new KnowledgeBaseServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("knowledgeBaseId")), obj.getString("knowledgeBaseName"), obj.getString("description"), getIntegerValue(obj.get("rank")), operatingDateFormat.parse(obj.getString("lastModified")), getIntegerValue(obj.get("latestTopic")), getIntegerValue(obj.get("latestPost"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new KnowledgeBaseServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("knowledgeBaseId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "locale":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new LocaleServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("localeId")), obj.getString("localeString"), obj.getString("localeCharacterSet")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new LocaleServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("localeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "localizedString":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new LocalizedStringServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("localizedStringId")), getIntegerValue(obj.get("locale")), obj.getString("stringValue"), getIntegerValue(obj.get("textStringId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new LocalizedStringServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("localizedStringId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "mailinglist":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new MailinglistServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("mailinglistId")), obj.getString("fullName"), obj.getString("email"), getIntegerValue(obj.get("listStatus")), getIntegerValue(obj.get("userId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new MailinglistServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("mailinglistId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "metaTag":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new MetaTagServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("metaTagId")), obj.getString("title"), obj.getString("description"), obj.getString("keywords")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new MetaTagServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("metaTagId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "optionAvailability":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new OptionAvailabilityServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("optionAvailabilityId")), getIntegerValue(obj.get("itemId")), getIntegerValue(obj.get("itemOptionId")), getIntegerValue(obj.get("itemAvailabilityId")), getIntegerValue(obj.get("availableQuantity")), obj.getJsonNumber("price").doubleValue(), operatingDateFormat.parse(obj.getString("availableFrom")), operatingDateFormat.parse(obj.getString("availableTo")), getIntegerValue(obj.get("maximumQuantity"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new OptionAvailabilityServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("optionAvailabilityId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "order":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new OrderServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("orderId")), operatingDateFormat.parse(obj.getString("orderDate")), operatingDateFormat.parse(obj.getString("shipDate")), obj.getString("paymentMethod"), obj.getString("purchaseOrder"), obj.getString("transactionId"), obj.getJsonNumber("amountBilled").doubleValue(), obj.getString("paymentStatus"), obj.getString("pendingReason"), obj.getString("paymentType"), obj.getJsonNumber("transactionFee").doubleValue(), obj.getString("currencyCode"), obj.getString("payerId"), obj.getJsonNumber("subtotalAmount").doubleValue(), obj.getJsonNumber("discountAmount").doubleValue(), obj.getJsonNumber("taxAmount").doubleValue(), obj.getJsonNumber("shippingAmount").doubleValue(), obj.getJsonNumber("totalAmount").doubleValue(), obj.getJsonNumber("refundAmount").doubleValue(), obj.getString("notes"), getIntegerValue(obj.get("orderStatus")), getIntegerValue(obj.get("shippingId")), getIntegerValue(obj.get("affiliateId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new OrderServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("orderId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "orderItem":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new OrderItemServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("orderItemId")), getIntegerValue(obj.get("customerOrderId")), getIntegerValue(obj.get("itemId")), getIntegerValue(obj.get("quantity")), obj.getString("optionName"), obj.getJsonNumber("unitPrice").doubleValue()), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new OrderItemServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("orderItemId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "page":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new PageServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("pageId")), obj.getString("pageName"), obj.getString("content"), getIntegerValue(obj.get("pageStatus")), getIntegerValue(obj.get("formId")), getIntegerValue(obj.get("sliderId")), getIntegerValue(obj.get("metaTagId")), getIntegerValue(obj.get("templateId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new PageServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("pageId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "pageTemplate":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new PageTemplateServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("pageTemplateId")), obj.getString("name"), obj.getString("markup")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new PageTemplateServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("pageTemplateId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "paypal":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new PaypalServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("paypalId")), obj.getString("payPalUrl"), obj.getString("currencyCode"), obj.getString("apiUsername"), obj.getString("apiPassword"), obj.getString("apiSignature"), obj.getString("apiEndpoint"), obj.getBoolean("activeProfile"), obj.getString("returnUrl"), obj.getString("cancelUrl"), obj.getString("paymentType"), obj.getString("environment")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new PaypalServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("paypalId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "postCategory":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new PostCategoryServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("postCategoryId")), obj.getString("categoryName")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new PostCategoryServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("postCategoryId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "recurringPayment":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new RecurringPaymentServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("recurringPaymentId")), getIntegerValue(obj.get("cycleLength")), getIntegerValue(obj.get("cyclePeriod")), getIntegerValue(obj.get("totalCycles")), operatingDateFormat.parse(obj.getString("startDate")), getIntegerValue(obj.get("orderId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new RecurringPaymentServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("recurringPaymentId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "relatedItem":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new RelatedItemServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("relatedItemId")), getIntegerValue(obj.get("item1")), getIntegerValue(obj.get("item2"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new RelatedItemServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("relatedItemId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "resourceType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ResourceTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("resourceTypeId")), obj.getString("typeName"), obj.getString("typeValue")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ResourceTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("resourceTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "resourceUrl":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ResourceUrlServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("resourceUrlId")), obj.getString("url"), getIntegerValue(obj.get("templateId")), getIntegerValue(obj.get("resourceTypeId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ResourceUrlServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("resourceUrlId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "returnRequest":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ReturnRequestServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("returnRequestId")), getIntegerValue(obj.get("quantity")), operatingDateFormat.parse(obj.getString("requestDate")), obj.getString("returnReason"), obj.getString("requestedAction"), obj.getString("notes"), getIntegerValue(obj.get("requestStatus")), getIntegerValue(obj.get("orderItemId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ReturnRequestServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("returnRequestId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "service":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ServiceServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("serviceId")), obj.getString("serviceName"), obj.getString("description"), getIntegerValue(obj.get("serviceStatus")), getIntegerValue(obj.get("serviceChargeId")), getIntegerValue(obj.get("serviceTypeId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ServiceServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("serviceId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "serviceCharge":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ServiceChargeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("serviceChargeId")), obj.getString("chargeName"), obj.getString("description"), obj.getString("rate"), obj.getString("units"), operatingDateFormat.parse(obj.getString("date")), getIntegerValue(obj.get("userServiceId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ServiceChargeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("serviceChargeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "serviceType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ServiceTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("serviceTypeId")), obj.getString("typeName"), obj.getString("desciption")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ServiceTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("serviceTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "shipment":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ShipmentServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("shipmentId")), operatingDateFormat.parse(obj.getString("createdOn")), obj.getString("trackingNumber"), obj.getJsonNumber("totalWeight").doubleValue(), operatingDateFormat.parse(obj.getString("shipDate")), operatingDateFormat.parse(obj.getString("deliveryDate")), getIntegerValue(obj.get("itemQuantity")), getIntegerValue(obj.get("orderId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ShipmentServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("shipmentId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "shipping":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new ShippingServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("shippingId")), obj.getString("methodName"), obj.getJsonNumber("quantity").doubleValue(), obj.getString("unitOfMeasure"), obj.getJsonNumber("ratePerUnitCost").doubleValue(), obj.getJsonNumber("additionalCost").doubleValue(), getIntegerValue(obj.get("stateProvinceId")), getIntegerValue(obj.get("countryId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new ShippingServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("shippingId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "site":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SiteServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("siteId")), obj.getString("siteName"), obj.getString("domain"), getIntegerValue(obj.get("mode")), obj.getString("url"), obj.getString("logoTitle"), obj.getString("logoImage"), getIntegerValue(obj.get("useAsStore")), obj.getString("emailHost"), getIntegerValue(obj.get("emailPort")), obj.getString("emailUsername"), obj.getString("emailPassword"), getIntegerValue(obj.get("siteStatus")), obj.getString("locale"), getIntegerValue(obj.get("templateId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SiteServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("siteId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteAttribute":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SiteAttributeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("siteAttributeId")), obj.getString("attributeKey"), obj.getString("attributeValue"), obj.getString("attributeType"), getIntegerValue(obj.get("siteId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SiteAttributeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("siteAttributeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteFile":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SiteFileServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("siteFileId")), obj.getString("fileName"), obj.getString("description"), obj.getString("label")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SiteFileServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("siteFileId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteFolder":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SiteFolderServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("siteFolderId")), obj.getString("folderName"), obj.getString("description"), getIntegerValue(obj.get("rank")), getIntegerValue(obj.get("siteId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SiteFolderServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("siteFolderId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteImage":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SiteImageServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("siteImageId")), obj.getString("fileName"), obj.getString("description"), obj.getString("linkUrl"), getIntegerValue(obj.get("rank")), getIntegerValue(obj.get("imageTypeId")), getIntegerValue(obj.get("siteId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SiteImageServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("siteImageId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteItem":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SiteItemServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("siteItemId")), getIntegerValue(obj.get("siteId")), getIntegerValue(obj.get("itemId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SiteItemServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("siteItemId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "siteLanguage":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SiteLanguageServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("siteLanguageId")), obj.getString("languageName"), obj.getString("locale"), getIntegerValue(obj.get("rtl")), obj.getString("flagFileName"), getIntegerValue(obj.get("siteId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SiteLanguageServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("siteLanguageId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "sitePage":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SitePageServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("sitePageId")), getIntegerValue(obj.get("siteId")), getIntegerValue(obj.get("pageId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SitePageServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("sitePageId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "slider":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SliderServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("sliderId")), obj.getString("sliderName"), getIntegerValue(obj.get("sliderTypeId")), getIntegerValue(obj.get("formId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SliderServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("sliderId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "sliderItem":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SliderItemServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("sliderItemId")), obj.getString("title"), obj.getString("description"), obj.getString("url"), obj.getString("imageName"), obj.getString("alternateText"), getIntegerValue(obj.get("rank")), getIntegerValue(obj.get("sliderId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SliderItemServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("sliderItemId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "sliderType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new SliderTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("sliderTypeId")), obj.getString("typeName"), obj.getString("code")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new SliderTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("sliderTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "stateProvince":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new StateProvinceServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("stateProvinceId")), obj.getString("name"), obj.getString("abbreviation"), getIntegerValue(obj.get("countryId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new StateProvinceServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("stateProvinceId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "taxRate":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new TaxRateServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("taxRateId")), obj.getString("taxCategory"), obj.getJsonNumber("percentage").doubleValue(), obj.getString("zipPostalCode"), getIntegerValue(obj.get("stateProvinceId")), getIntegerValue(obj.get("countryId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new TaxRateServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("taxRateId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "template":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new TemplateServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("templateId")), obj.getString("templateName"), obj.getString("markup"), getIntegerValue(obj.get("templateStatus")), getIntegerValue(obj.get("templateTypeId")), getIntegerValue(obj.get("parentTemplateId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new TemplateServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("templateId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "templateType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new TemplateTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("templateTypeId")), obj.getString("typeName"), obj.getString("typeValue")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new TemplateTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("templateTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "textString":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new TextStringServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("textStringId")), obj.getString("key")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new TextStringServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("textStringId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "user":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new UserServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("userId")), obj.getString("username"), obj.getString("password"), obj.getString("email"), obj.getString("securityQuestion"), obj.getString("securityAnswer"), operatingDateFormat.parse(obj.getString("registerDate")), obj.getString("imageUrl"), getIntegerValue(obj.get("userStatus")), getIntegerValue(obj.get("rank")), obj.getString("webUrl"), getIntegerValue(obj.get("itemBrandId")), getIntegerValue(obj.get("userTypeId")), getIntegerValue(obj.get("addressId")), getIntegerValue(obj.get("contactId")), getIntegerValue(obj.get("userGroupId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new UserServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("userId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "userAction":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new UserActionServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("userActionId")), operatingDateFormat.parse(obj.getString("date")), obj.getString("detail"), getIntegerValue(obj.get("userActionTypeId")), getIntegerValue(obj.get("userId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new UserActionServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("userActionId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "userActionType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new UserActionTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("userActionTypeId")), obj.getString("typeName")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new UserActionTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("userActionTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "userGroup":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new UserGroupServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("userGroupId")), obj.getString("groupName"), getIntegerValue(obj.get("siteId")), getIntegerValue(obj.get("discountId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new UserGroupServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("userGroupId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "userService":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new UserServiceServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("userServiceId")), operatingDateFormat.parse(obj.getString("startDate")), operatingDateFormat.parse(obj.getString("endDate")), obj.getString("details"), obj.getString("contractUrl"), obj.getString("deliverableUrl"), obj.getJsonNumber("depositAmount").doubleValue(), getIntegerValue(obj.get("userRank")), getIntegerValue(obj.get("blogId")), getIntegerValue(obj.get("userId")), getIntegerValue(obj.get("serviceId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new UserServiceServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("userServiceId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "userType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new UserTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("userTypeId")), obj.getString("typeName"), obj.getString("description"), obj.getString("redirectUrl")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new UserTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("userTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "vendor":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new VendorServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("vendorId")), obj.getString("vendorName"), obj.getString("description"), getIntegerValue(obj.get("rank")), getIntegerValue(obj.get("vendorStatus")), getIntegerValue(obj.get("metaTagId")), getIntegerValue(obj.get("templateId")), getIntegerValue(obj.get("vendorTypeId"))), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new VendorServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("vendorId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                case "vendorType":
                {
                    try
                    {
                        JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                        switch (pp.getOperation())
                        {
                            case "store":
                                generateStoreServiceResult(new VendorTypeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("vendorTypeId")), obj.getString("typeName")), out);
                                break;
                            case "remove":
                                generateRemoveServiceResult(new VendorTypeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("vendorTypeId"))), out);
                                break;
                            default:
                                out.print(getJsonErrorMsg("Invalid Operation"));
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        out.print(getJsonErrorMsg(ex.getMessage()));
                    }
                    break;
                }

                default:
                    out.print(getJsonErrorMsg("Invalid Resource"));
                    break;
            }
        }
    }

    @Override
    public String getServletInfo()
    {
        return "Handles the restful web service requests for various services in the following format: rest/{ServiceName}/{OperationName}/{Id:optional}";
    }

    private String getRequiredParameter(HttpServletRequest request, String paramName) throws Exception
    {
        String value = request.getParameter(paramName);
        if (value == null || value.equals(""))
        {
            throw new Exception("Invalid required parameter was passed.");
        }
        else
        {
            return value;
        }
    }

    private void generateFindServiceResult(Result<? extends AbstractEntity> result, PrintWriter out)
    {
        if (result.isSuccess())
        {
            out.print(getJsonSuccessData(result.getData()));
        }
        else
        {
            out.print(getJsonErrorMsg(result.getMsg()));
        }
    }

    private void generateFindAllServiceResult(Result<? extends List<? extends AbstractEntity>> result, PrintWriter out)
    {
        if (result.isSuccess())
        {
            out.print(getJsonSuccessData(result.getData()));
        }
        else
        {
            out.print(getJsonErrorMsg(result.getMsg()));
        }
    }

    private void generateStoreServiceResult(Result<? extends AbstractEntity> result, PrintWriter out)
    {
        if (result.isSuccess())
        {
            out.print(getJsonSuccessData(result.getData()));
        }
        else
        {
            out.print(getJsonErrorMsg(result.getMsg()));
        }
    }

    private void generateRemoveServiceResult(Result<?> result, PrintWriter out)
    {
        if (result.isSuccess())
        {
            out.print(getJsonSuccessMsg(result.getMsg()));
        }
        else
        {
            out.print(getJsonErrorMsg(result.getMsg()));
        }
    }
}
