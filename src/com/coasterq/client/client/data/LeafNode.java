package com.coasterq.client.client.data;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class LeafNode extends Node
{
   // --------------------------------------------------------------------------
   // Private Data
   // --------------------------------------------------------------------------
   
   private Anchor anchor;
 
   // --------------------------------------------------------------------------
   // Constructor
   // --------------------------------------------------------------------------
   
   public LeafNode(String title, String href)
   {
      addStyleName("leafnode");
      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      Anchor anchor = new Anchor(title, href);
      
      anchor.addStyleName("leafnodelabel");

      anchor.addClickHandler(new ClickHandler()
      {
         @Override
         public void onClick(ClickEvent event)
         {
            notifyOfClick(LeafNode.this);
         }
      });
      
      add(anchor);
   }

   // --------------------------------------------------------------------------
   // Public Members
   // --------------------------------------------------------------------------
   
   @Override
   public String getTitle()
   {
      return anchor.getText();
   }

   @Override
   public boolean isLeaf()
   {
      return true;
   }

   @Override
   public void setTitle(String title)
   {
      anchor.setText(title);
   }
}
