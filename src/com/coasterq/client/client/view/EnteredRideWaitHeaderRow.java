package com.coasterq.client.client.view;

import com.finfrock.client.Column;
import com.finfrock.client.CommonColumn;
import com.finfrock.client.Row;

public class EnteredRideWaitHeaderRow extends Row 
{
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public EnteredRideWaitHeaderRow()
   {
      addColumn(createFriendColumn());
      addColumn(createDateTimeColumn());
      addColumn(createRideColumn());
      addColumn(createParkColumn());
      addColumn(createWaitTimeColumn());
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private Column createRideColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Ride");
      column.setStyleName("tableHeader");
      column.setIndex(EnteredRideWaitRow.RIDE_NAME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createDateTimeColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Date/Time");
      column.setStyleName("tableHeader");
      column.setIndex(EnteredRideWaitRow.DATE_TIME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createFriendColumn()
   {
      CommonColumn column = new CommonColumn();

      column.setValue("Friend");
      column.setStyleName("tableHeader");
      column.setIndex(EnteredRideWaitRow.FRIEND_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);

      return column;
   }
   
   private Column createParkColumn()
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue("Park");
      column.setStyleName("tableHeader");
      column.setIndex(EnteredRideWaitRow.PARK_NAME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createWaitTimeColumn()
   {
      CommonColumn column = new CommonColumn();

      column.setValue("Wait Time");
      column.setStyleName("tableHeader");
      column.setIndex(EnteredRideWaitRow.WAIT_TIME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);

      return column;
   }
}
