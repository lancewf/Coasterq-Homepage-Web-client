package com.coasterq.client.client.connection;

import com.google.gwt.core.client.GWT;

public class TestServiceLocations implements ServiceLocations
{

   @Override
   public String getFriendStatusDataAddress()
   {
      return GWT.getHostPageBaseURL() + "getallenteredridewaits";
   }

   @Override
   public String getParkInfoAddress()
   {
      return GWT.getHostPageBaseURL() + "getParkInfoAddress";
   }
   
   @Override
   public String getOnlineUsersAddress()
   {
      return GWT.getHostPageBaseURL() + "getNumberOfUsersOnline";
   }
}
