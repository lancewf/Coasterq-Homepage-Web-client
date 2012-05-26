package com.coasterq.client.client.data;

import java.util.Date;

import com.finfrock.client.FacebookUser;

public class EnteredRideWait 
{
  // -------------------------------------------------------------------------
  // Private Data
  // -------------------------------------------------------------------------
  
  private Date dateTime;
  private String rideName;
  private FacebookUser facebookUser;
  private int waitTime;
  private String parkName;
  private String parkUrl;
  
  // -------------------------------------------------------------------------
  // Constructor
  // -------------------------------------------------------------------------
  
  public EnteredRideWait()
  {
     //
     // Do nothing
     //
  }

  // -------------------------------------------------------------------------
  // Public Members
  // -------------------------------------------------------------------------
  
  public Date getDateTime() 
  {
    return dateTime;
  }

   public String getParkUrl()
   {
      return parkUrl;
   }

   public void setParkUrl(String parkUrl)
   {
      this.parkUrl = parkUrl;
   }

   public void setDateTime(Date dateTime)
  {
    this.dateTime = dateTime;
  }

  public String getRideName() 
  {
    return rideName;
  }

  public void setRideName(String rideName) 
  {
    this.rideName = rideName;
  }

  public FacebookUser getFacebookUser() 
  {
    return facebookUser;
  }

  public void setFacebookUser(FacebookUser facebookUser) 
  {
    this.facebookUser = facebookUser;
  }

  public int getWaitTime() 
  {
    return waitTime;
  }

  public void setWaitTime(int waitTime) 
  {
    this.waitTime = waitTime;
  }

  public String getParkName() 
  {
    return parkName;
  }

  public void setParkName(String parkName) 
  {
    this.parkName = parkName;
  }
  
}
