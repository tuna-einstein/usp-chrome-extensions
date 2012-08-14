package com.usp.tabcloud.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;

public class JavaScriptObjects {

    public static class TabsInfoJSO extends JavaScriptObject  {
        // Overlay types always have protected, zero argument constructors.
        protected TabsInfoJSO() {} 

        public final native String getReceiverEmail() /*-{ return this.receiverEmail; }-*/;
        public final native String getRequesterEmail() /*-{ return this.requesterEmail; }-*/;
        public final native String getDate() /*-{ return this.date; }-*/;
        public final native JsArrayString getUrls() /*-{ return this.urls; }-*/;
        public final native String getKeyAsString() /*-{ return this.keyAsString; }-*/;
        
        

        /**
         * Convert the string of JSON into JavaScript object array.
         */
        public static final native JsArray<TabsInfoJSO> asArrayOfTabsInfoJSO(String json)
        /*-{
          return eval(json);
        }-*/;

        /**
         * Convert the string of JSON into JavaScript object.
         */
        public static final native TabsInfoJSO asTabsInfoJSO(String json)
        /*-{
          return eval(json);
        }-*/;
    }
}
