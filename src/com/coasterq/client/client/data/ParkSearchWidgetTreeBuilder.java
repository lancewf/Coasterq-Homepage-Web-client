package com.coasterq.client.client.data;

import com.coasterq.client.client.connection.ServiceLocations;
import com.coasterq.client.client.view.LocalParksPanel;
import com.finfrock.client.Returnable;

public class ParkSearchWidgetTreeBuilder
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private CollectionNode root;
   private ServiceLocations serviceLocations;
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public ParkSearchWidgetTreeBuilder(ServiceLocations serviceLocations)
   {
      this.serviceLocations = serviceLocations;
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public Node buildTree()
   {  
      Returnable<Node> returnable = new Returnable<Node>()
      {
         @Override
         public void returned(Node selectedNode)
         {
            for(Node child : root.getChildrenNodes())
            {
               if(child instanceof CollectionNode)
               {
                  CollectionNode collectionNode = (CollectionNode) child;
                  if(child != selectedNode)
                  {
                     collectionNode.hideChildren();
                  }
               }
            }
         }
      };
      
      root = new CollectionNode("");
      
      root.addStyleName("root");
      
      Node childNode = new LocalParksPanel(serviceLocations);
      childNode.addListener(returnable);
      root.addChildren(childNode);
      
      childNode = buildDisneyParks();
      childNode.addListener(returnable);
      root.addChildren(childNode);
      
      childNode = buildUniversalParks();
      childNode.addListener(returnable);
      root.addChildren(childNode);
      
      childNode = buildSixFlagsParks();
      childNode.addListener(returnable);
      root.addChildren(childNode);
      
      childNode = buildOtherParks();
      childNode.addListener(returnable);
      root.addChildren(childNode);
      
      root.showChildren();
      
      return root;
   }
   
   // --------------------------------------------------------------------------
   // Private Members
   // --------------------------------------------------------------------------
   
   private Node buildDisneyParks()
   {
      CollectionNode root = new CollectionNode("Walt Disney Parks");
      Node child = null;
      
      child = new LeafNode("California Adventure", "http://california-adventure.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Disneyland Resort", "http://disneyland.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Disneyland Park (Paris)", "http://disneyland-park.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Disney's Animal Kingdom", "http://animal-kingdom.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Disney's Hollywood Studios", "http://hollywood-studios.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Epcot", "http://epcot.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Magic Kingdom", "http://magic-kingdom.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Tokyo Disneyland", "http://tokyo-disneyland.coasterq.com/");
      root.addChildren(child); 
      
      child = new LeafNode("Tokyo DisneySea", "http://tokyo-disneysea.coasterq.com/");
      root.addChildren(child); 
      
      child = new LeafNode("Walt Disney Studios Park (Paris)", "http://walt-disney-studios-park.coasterq.com/");
      root.addChildren(child); 
      
      return root;
   }
   
   private Node buildSixFlagsParks()
   {
      CollectionNode root = new CollectionNode("Six Flags Parks");
      Node child = null;
      
      child = new LeafNode("Six Flags America (Baltimore/Washington, D.C.)", "http://six-flags-america.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Six Flags Discovery Kingdom", "http://discovery-kingdom.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Six Flags Great Adventure", "http://great-adventure.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Six Flags Great America", "http://great-america.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Six Flags Magic Mountain", "http://magic-mountain.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Six Flags New England", "http://new-england.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Six Flags Over Georgia", "http://over-georgia.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Six Flags Over Texas", "http://over-texas.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Six Flags St. Louis", "http://st-louis.coasterq.com/");
      root.addChildren(child);
      
      return root;
   }
   
   private Node buildUniversalParks()
   {
      CollectionNode root = new CollectionNode("Universal Studios Parks");
      Node child = null;
      
      child = new LeafNode("Universal's Islands of Adventure", "http://islands-of-adventure.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Universal Studios Florida", "http://universal-studios-florida.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Universal Studios Hollywood", "http://universal-studios-hollywood.coasterq.com/");
      root.addChildren(child);
      
      return root;
   }
   
   private Node buildOtherParks()
   {
      CollectionNode root = new CollectionNode("Other Parks");
      Node child = null;
      
      child = new LeafNode("Busch Gardens Tampa Bay", "http://busch-gardens-tampa.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Canada's Wonderland", "http://canadas-wonderland.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Cedar Point", "http://cedar-point.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Hersheypark", "http://hersheypark.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Knott's Berry Farm", "http://knotts-berry-farm.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("LEGOLAND California", "http://legoland-california.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("SeaWorld Orlando", "http://seaworld-orlando.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("SeaWorld San Antonio", "http://seaworld-san-antonio.coasterq.com/");
      root.addChildren(child);
      
      child = new LeafNode("Thorpe Park", "http://thorpe-park.coasterq.com/");
      root.addChildren(child);
      
      return root;
   }
}
