package com.coasterq.client.client;

import com.coasterq.client.client.connection.EnteredRideWaitRetriever;
import com.coasterq.client.client.connection.EnteredRideWaitUpdateAgent;
import com.coasterq.client.client.connection.ReleaseServiceLocations;
import com.coasterq.client.client.connection.ServiceLocations;
import com.coasterq.client.client.data.EnteredRideWaitManager;
import com.coasterq.client.client.data.Node;
import com.coasterq.client.client.data.ParkSearchWidgetTreeBuilder;
import com.coasterq.client.client.view.EnteredRideWaitTable;
import com.coasterq.client.client.view.UsersOnlineView;
import com.finfrock.client.FacebookConnectionStatus;
import com.finfrock.client.UserPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Coasterqhomeclient implements EntryPoint
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private static final String MAIN_DIV_TAG = "main";
   private static final String PARKS_DIV_TAG = "parks";
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   /**
    * This is the entry point method.
    */
   public void onModuleLoad()
   {
//    ServiceLocations serviceLocations = new TestServiceLocations();
      ServiceLocations serviceLocations = new ReleaseServiceLocations();

      Node staticParksPanel = createParksView(serviceLocations);
      
      RootPanel.get(PARKS_DIV_TAG).getElement().setInnerHTML("");
      RootPanel.get(PARKS_DIV_TAG).add(staticParksPanel);
      
      VerticalPanel panel = createStatusView(serviceLocations);
      
      RootPanel.get(MAIN_DIV_TAG).add(panel);
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private Node createParksView(ServiceLocations serviceLocations)
   {
      ParkSearchWidgetTreeBuilder builder = 
         new ParkSearchWidgetTreeBuilder(serviceLocations);
      
      Node rootNode = builder.buildTree();     
      
      return rootNode;
   }
   
   private VerticalPanel createStatusView(ServiceLocations serviceLocations)
   {
      VerticalPanel panel = new VerticalPanel();
      
      EnteredRideWaitManager enteredRideWaitManager = 
         buildEnteredRideManager(serviceLocations);
      
      FacebookConnectionStatus facebookConnectionStatus = new FacebookConnectionStatus();
      
      EnteredRideWaitTable table = new EnteredRideWaitTable(
         enteredRideWaitManager, facebookConnectionStatus);
      
      Widget userPanel = createUserAndOnlineUsersPanel(facebookConnectionStatus, 
         serviceLocations);
      
      panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      
      panel.add(userPanel);
      
      panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      
      panel.add(table);
      
      return panel;
   }
   
   private Widget createUserAndOnlineUsersPanel(
                              FacebookConnectionStatus facebookConnectionStatus, 
                              ServiceLocations serviceLocations)
   {
      HorizontalPanel panel = new HorizontalPanel();
      
      panel.addStyleName("userpanel");
      
      UsersOnlineView usersOnlineView = new UsersOnlineView(serviceLocations);
      
      panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
      
      panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
      
      panel.add(usersOnlineView);
      
      UserPanel userPanel = new UserPanel(facebookConnectionStatus);
      
      panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
      panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
      panel.add(userPanel);
      
      return panel;
   }

   private EnteredRideWaitManager buildEnteredRideManager(
                                              ServiceLocations serviceLocations)
   {
      EnteredRideWaitRetriever enteredRideWaitRetriever = 
         new EnteredRideWaitRetriever(serviceLocations);

      EnteredRideWaitManager enteredRideWaitManager = 
         new EnteredRideWaitManager();

      EnteredRideWaitUpdateAgent enteredRideWaitUpdateAgent = 
         new EnteredRideWaitUpdateAgent(enteredRideWaitRetriever, 
            enteredRideWaitManager);

      enteredRideWaitUpdateAgent.start();

      return enteredRideWaitManager;
   }
}
