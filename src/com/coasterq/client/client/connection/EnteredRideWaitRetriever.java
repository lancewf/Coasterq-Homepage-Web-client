package com.coasterq.client.client.connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coasterq.client.client.data.EnteredRideWait;
import com.finfrock.client.FacebookUser;
import com.finfrock.client.RetrieverAndroid15;
import com.google.gwt.core.client.JsArray;

public class EnteredRideWaitRetriever extends RetrieverAndroid15<List<EnteredRideWait>>
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------

   public EnteredRideWaitRetriever(ServiceLocations serviceLocations)
   {
      super(serviceLocations.getFriendStatusDataAddress());
   }
   
   // --------------------------------------------------------------------------
   // Retriever Members
   // --------------------------------------------------------------------------
   
   @SuppressWarnings("deprecation")
   @Override
   protected List<EnteredRideWait> parseText(String rawText)
   {
      JsArray<EnteredRideWaitData> enteredRideWaitDataJsArray = 
         asArrayOfFriendStatusData(rawText);
      
      List<EnteredRideWait> enteredRideWaits = new ArrayList<EnteredRideWait>();
      
      for(int index = 0; index < enteredRideWaitDataJsArray.length(); index++)
      {
         EnteredRideWaitData enteredRideWaitData = enteredRideWaitDataJsArray.get(index);
         
         EnteredRideWait enteredRideWait = new EnteredRideWait();
         
         FacebookUser user = new FacebookUser(enteredRideWaitData.getFacebookUid());
         
         enteredRideWait.setFacebookUser(user);
         
         Date dateTime = new Date(enteredRideWaitData.getYear() - 1900, 
            enteredRideWaitData.getMonth() - 1, 
            enteredRideWaitData.getDayOfMonth(), enteredRideWaitData.get24Hour(), 
            enteredRideWaitData.getMinutes());
         
         enteredRideWait.setDateTime(dateTime);
         enteredRideWait.setParkName(enteredRideWaitData.getParkName());
         enteredRideWait.setParkUrl(enteredRideWaitData.getParkUrl());
         enteredRideWait.setRideName(enteredRideWaitData.getRideName());
         enteredRideWait.setWaitTime(enteredRideWaitData.getWaitTime());
         
         enteredRideWaits.add(enteredRideWait);
      }
      
      return enteredRideWaits;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private final native JsArray<EnteredRideWaitData> 
      asArrayOfFriendStatusData(String json) 
   /*-{
      return eval(json);
   }-*/;
}
