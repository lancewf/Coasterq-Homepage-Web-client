package com.coasterq.client.client.view;

import com.coasterq.client.client.connection.OnlineUsersRetriever;
import com.coasterq.client.client.connection.ServiceLocations;
import com.finfrock.client.Loadable;
import com.finfrock.client.Returnable;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class UsersOnlineView extends HorizontalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private Label count;
   private OnlineUsersRetriever onlineUsersRetriever;
   private ServiceLocations serviceLocations;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public UsersOnlineView(ServiceLocations serviceLocations)
   {
      this.serviceLocations = serviceLocations;
      
      initialize();
   }

   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private void initialize()
   {
      Label text = new Label("Online Users: ");
      text.addStyleName("textWithSpace");
      
      count = new Label("");
      count.addStyleName("textWithSpace");
      
      add(text);
      add(count);
      
      retrieverOnlineUsers();
   }

   private void retrieverOnlineUsers()
   {
      onlineUsersRetriever = new OnlineUsersRetriever(serviceLocations);

      onlineUsersRetriever.startLoad(new Returnable<Loadable>()
      {
         @Override
         public void returned(Loadable loadable)
         {
            Integer onlineUsersCount = onlineUsersRetriever.getObject();

            count.setText(onlineUsersCount + "");
         }
      });

      Timer timer = new Timer()
      {
         public void run()
         {
            onlineUsersRetriever.startLoad(new Returnable<Loadable>()
            {
               @Override
               public void returned(Loadable loadable)
               {
                  Integer onlineUsersCount = onlineUsersRetriever.getObject();

                  count.setText(onlineUsersCount + "");
               }
            });
         }
      };

      // Schedule the timer to run every 5 minutes.
      timer.scheduleRepeating(300000);
   }
}
