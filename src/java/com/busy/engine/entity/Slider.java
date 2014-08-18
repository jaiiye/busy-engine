



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Slider extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SLIDER_ID = "SliderId";
        public static final String PROP_SLIDER_NAME = "SliderName";
        public static final String PROP_SLIDER_TYPE_ID = "SliderTypeId";
        public static final String PROP_FORM_ID = "FormId";
        

        private Integer sliderId;
                
        private String sliderName;
                
        private Integer sliderTypeId;
        private SliderType sliderType;        
        private Integer formId;
        private Form form;        
                 
        ArrayList<Page> pageList; 
        ArrayList<SliderItem> sliderItemList; 
        
        

        public Slider()
        {
            this.sliderId = 0; 
       this.sliderName = ""; 
       this.sliderTypeId = 0; 
       this.formId = 0; 
        
       pageList = null; 
        sliderItemList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return sliderId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("sliderId", sliderId == null ? 0 : sliderId);
                
            builder.add("sliderName", sliderName == null ? "" : sliderName);
                
            builder.add("sliderTypeId", sliderTypeId == null ? 0 : sliderTypeId);
                
            builder.add("formId", formId == null ? 0 : formId);
        
        
    
     
     if(sliderType != null) sliderType.addJson(builder);
        
     if(form != null) form.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Slider.PROP_SLIDER_ID) || column.equals(Slider.PROP_SLIDER_NAME) || column.equals(Slider.PROP_SLIDER_TYPE_ID) || column.equals(Slider.PROP_FORM_ID) )
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
            if (column.equals(Slider.PROP_SLIDER_ID) || column.equals(Slider.PROP_SLIDER_TYPE_ID) || column.equals(Slider.PROP_FORM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Slider process(ResultSet rs) throws SQLException
        {        
            return new Slider(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
              
       public Slider(Integer SliderId, String SliderName, Integer SliderTypeId, Integer FormId)
       {
            this.sliderId = SliderId;
       this.sliderName = SliderName;
       this.sliderTypeId = SliderTypeId;
       this.formId = FormId;
              
       pageList = null; 
        sliderItemList = null; 
        
       } 
        
             
        
            public Integer getSliderId()
            {
                return this.sliderId;
            }
            
            public void setSliderId(Integer SliderId)
            {
                this.sliderId = SliderId;
            }
            
            
        
            public String getSliderName()
            {
                return this.sliderName;
            }
            
            public void setSliderName(String SliderName)
            {
                this.sliderName = SliderName;
            }
            
            
        
            public Integer getSliderTypeId()
            {
                return this.sliderTypeId;
            }
            
            public void setSliderTypeId(Integer SliderTypeId)
            {
                this.sliderTypeId = SliderTypeId;
            }
            
            
                   
            public SliderType getSliderType()
                {
                    return this.sliderType;
                }

                public void setSliderType(SliderType sliderType)
                {
                    this.sliderType = sliderType;
                }
                   
            
        
            public Integer getFormId()
            {
                return this.formId;
            }
            
            public void setFormId(Integer FormId)
            {
                this.formId = FormId;
            }
            
            
                   
            public Form getForm()
                {
                    return this.form;
                }

                public void setForm(Form form)
                {
                    this.form = form;
                }
                   
            
         
        
        
        public ArrayList<Page> getPageList()
            {
                return this.pageList;
            }
            
            public void setPageList(ArrayList<Page> pageList)
            {
                this.pageList = pageList;
            }
        
        public ArrayList<SliderItem> getSliderItemList()
            {
                return this.sliderItemList;
            }
            
            public void setSliderItemList(ArrayList<SliderItem> sliderItemList)
            {
                this.sliderItemList = sliderItemList;
            }
        
            
    }

