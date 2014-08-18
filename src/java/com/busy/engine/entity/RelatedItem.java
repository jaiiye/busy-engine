



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class RelatedItem extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_RELATED_ITEM_ID = "RelatedItemId";
        public static final String PROP_ITEM1 = "Item1";
        public static final String PROP_ITEM2 = "Item2";
        

        private Integer relatedItemId;
                
        private Integer item1;
                
        private Integer item2;
                
                 
        
        

        public RelatedItem()
        {
            this.relatedItemId = 0; 
       this.item1 = 0; 
       this.item2 = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return relatedItemId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("relatedItemId", relatedItemId == null ? 0 : relatedItemId);
                
            builder.add("item1", item1 == null ? 0 : item1);
                
            builder.add("item2", item2 == null ? 0 : item2);
        
        
    
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(RelatedItem.PROP_RELATED_ITEM_ID) || column.equals(RelatedItem.PROP_ITEM1) || column.equals(RelatedItem.PROP_ITEM2) )
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
            if (column.equals(RelatedItem.PROP_RELATED_ITEM_ID) || column.equals(RelatedItem.PROP_ITEM1) || column.equals(RelatedItem.PROP_ITEM2) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static RelatedItem process(ResultSet rs) throws SQLException
        {        
            return new RelatedItem(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
              
       public RelatedItem(Integer RelatedItemId, Integer Item1, Integer Item2)
       {
            this.relatedItemId = RelatedItemId;
       this.item1 = Item1;
       this.item2 = Item2;
              
       
       } 
        
             
        
            public Integer getRelatedItemId()
            {
                return this.relatedItemId;
            }
            
            public void setRelatedItemId(Integer RelatedItemId)
            {
                this.relatedItemId = RelatedItemId;
            }
            
            
        
            public Integer getItem1()
            {
                return this.item1;
            }
            
            public void setItem1(Integer Item1)
            {
                this.item1 = Item1;
            }
            
            
        
            public Integer getItem2()
            {
                return this.item2;
            }
            
            public void setItem2(Integer Item2)
            {
                this.item2 = Item2;
            }
            
            
         
        
        
            
    }

