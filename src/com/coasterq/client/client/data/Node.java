package com.coasterq.client.client.data;

import java.util.ArrayList;
import java.util.List;

import com.finfrock.client.Returnable;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class Node extends VerticalPanel
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private List<Returnable<Node>> returnables = new ArrayList<Returnable<Node>>();
   
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public Node()
   {
      addStyleName("node");
   }
   
   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   public abstract void setTitle(String title);
   
   public abstract String getTitle();
   
   public abstract boolean isLeaf();
   
   public void addListener(Returnable<Node> returnable)
   {
      returnables.add(returnable);
   }
   
   public void removeListener(Returnable<Node> returnable)
   {
      returnables.remove(returnable);
   }
   
   protected void notifyOfClick(Node nodeClicked)
   {
      for(Returnable<Node> returnable : returnables)
      {
         returnable.returned(nodeClicked);
      }
   }
}
