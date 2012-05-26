package com.coasterq.client.client.view;

import java.util.Date;

import com.coasterq.client.client.data.EnteredRideWait;
import com.finfrock.client.Column;
import com.finfrock.client.CommonColumn;
import com.finfrock.client.FacebookConnectionStatus;
import com.finfrock.client.Row;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Widget;

public class EnteredRideWaitRow extends Row 
{

   // --------------------------------------------------------------------------
   // Public static Data
   // --------------------------------------------------------------------------

   public static final int FRIEND_INDEX = 0;

   public static final int PARK_NAME_INDEX = 1;
   
   public static final int RIDE_NAME_INDEX = 2;

   public static final int DATE_TIME_INDEX = 3;
   
   public static final int WAIT_TIME_INDEX = 4;

   // --------------------------------------------------------------------------
   // Private static Data
   // --------------------------------------------------------------------------

   private static DateTimeFormat datetimeformat = 
      DateTimeFormat.getFormat("MMMM d, y h:mm a");
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public EnteredRideWaitRow(EnteredRideWait enteredRideWait,
         FacebookConnectionStatus facebookConnectionStatus)
   {
      addColumn(createFriendColumn(enteredRideWait, facebookConnectionStatus));
      addColumn(createDateTimeColumn(enteredRideWait));
      addColumn(createRideColumn(enteredRideWait));
      addColumn(createParkColumn(enteredRideWait));
      addColumn(createWaitTimeColumn(enteredRideWait));
   }

   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------

   private Column createWaitTimeColumn(EnteredRideWait enteredRideWait)
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue(getFormatTime(enteredRideWait.getWaitTime()));
      column.setStyleName("tableNumericColumn");
      column.setIndex(WAIT_TIME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createParkColumn(EnteredRideWait enteredRideWait)
   {
      CommonColumn column = new CommonColumn();
      
      Anchor park = new Anchor(enteredRideWait.getParkName(), 
         enteredRideWait.getParkUrl());
      
      column.setValue(park);
      column.setIndex(PARK_NAME_INDEX);
      column.setValueType(Column.WIDGET_COLUMN_TYPE);
      
      return column;
   }
   
   private Column createRideColumn(EnteredRideWait enteredRideWait)
   {
      CommonColumn column = new CommonColumn();
      
      column.setValue(enteredRideWait.getRideName());
      column.setIndex(RIDE_NAME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createDateTimeColumn(EnteredRideWait enteredRideWait)
   {
      CommonColumn column = new CommonColumn();
      
      Date date = enteredRideWait.getDateTime();
      
      String text = datetimeformat.format(date);
      
      column.setValue(text);
      column.setIndex(DATE_TIME_INDEX);
      column.setValueType(Column.TEXT_COLUMN_TYPE);
      
      return column;
   }

   private Column createFriendColumn(EnteredRideWait enteredRideWait, 
                              FacebookConnectionStatus facebookConnectionStatus)
   {
      CommonColumn column = new CommonColumn();
      
      Widget friendProfilePicture = 
         facebookConnectionStatus.createProfilePicture(
            enteredRideWait.getFacebookUser());
      
      column.setValue(friendProfilePicture);
      column.setStyleName("buttonColumn");
      column.setIndex(FRIEND_INDEX);
      column.setValueType(Column.WIDGET_COLUMN_TYPE);
      
      return column;
   }
   
   private String getFormatTime(int timeMin)
   {
      String text = "";
      
      if (timeMin != -1)
      {
         timeMin = Math.round((timeMin / 5)) * 5;

         int hours = timeMin / 60;
         int mins = timeMin % 60;

         if (hours > 0)
         {
            text += hours + " hr ";
         }

         text += mins + " min";
      }
      else
      {
         text = "Closed";
      }
      
      return text;
   }
}
