package com.usp.tabcloud.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.RequestLocator;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.usp.tabcloud.shared.model.TabsInfo;

public class GetSharedUrlsController extends Controller {

    @Override
    public Navigation run() throws Exception {
        HttpServletRequest request = RequestLocator.get();
        String keyAsString = request.getParameter("key");
        response.setContentType("text/html");
       
        
        if (keyAsString == null) {
            throw new Exception(new Error("key is null"));
        }
        TabsInfo info;
        if (keyAsString.equals("test")) {

            info = new TabsInfo();;
            info.setDate(new Date());
            String[] urls = {"www.google.com", "www.microsoft.com"};
            info.setUrls(Arrays.asList(urls));
            String[] titles = {"google", "microsoft"};
            info.setTitles(Arrays.asList(titles));
            info.setSubjectText("Hello");

        } else {
            Key key = KeyFactory.stringToKey(keyAsString);
            info = Datastore.get(TabsInfo.class, key);
        }

        printHeader(info);
        printBody(info);
        response.getWriter().flush();
        return null;    
    }

    private void printHeader(TabsInfo info) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.print("<html itemscope itemtype=\"http://schema.org/WebPage\">");
        pw.print("<head>");
        String content = info.getSubjectText();
        if (content == null || content.equals("")) {
            content = "Urls shared:";
        }
        pw.println("<title>" + content + "</title>");
       
        pw.print("<meta itemprop=\"name\" content=\"" + content +"\">");
        pw.print("<meta itemprop=\"description\" content=\"");
        pw.print("<ul>");
      for (String url : info.getUrls()) {
            pw.print("<li>" + getHost(url) + "</li>");
        }
       pw.print("</ul>");
       pw.print("\">");
      
        pw.println("</head>");
    }
    
    private void printBody(TabsInfo info) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.print("<body>");
        String content = info.getSubjectText();
        if (content == null || content.equals("")) {
            content = "Urls shared:";
        }
        
        pw.print("<h2>" + content + "</h2>" );
        pw.print("<ol>");
         for (int i=0 ; i < info.getUrls().size() ; i ++) {
             pw.print("<li><a href=\"" + info.getUrls().get(i) + "\">");
             pw.print(info.getTitles().get(i));
             pw.print("</a></li>");
         }
        pw.print("</ol>"); 
        pw.print("</body>");
        pw.print("</html>");
    }

    /* Will take a url such as http://www.stackoverflow.com and return www.stackoverflow.com
     * 
     * @param url
     * @return
     */
    public static String getHost(String url){
        if(url == null || url.length() == 0)
            return "";

        int doubleslash = url.indexOf("//");
        if(doubleslash == -1)
            doubleslash = 0;
        else
            doubleslash += 2;

        int end = url.indexOf('/', doubleslash);
        end = end >= 0 ? end : url.length();

        return url.substring(doubleslash, end);
    }
}
