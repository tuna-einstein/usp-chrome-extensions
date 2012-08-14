package com.usp.tabcloud.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Resources used by the entire application.
 */
public interface Resources extends ClientBundle {
//  @Source("Style.css")
//  Style style();

  @Source("gears.gif")
  ImageResource logo();

//  public interface Style extends CssResource {
//    String mainBlock();
//    String nameSpan();
//    Sprite userPictureSprite();
//  }
}