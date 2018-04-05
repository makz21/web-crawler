import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CrawlerLeg {
    private List<String> links = new LinkedList<>();
    private Document htmlDocument;

    public boolean crawl(String url){
        try{
            Connection connection = Jsoup.connect(url);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;

            if(connection.response().statusCode() == 200){
                System.out.println("\nVisiting page " +url);
            }
            if(!connection.response().contentType().contains("text/html")){
                System.out.println("\n Visiting failed, something other than HTML");
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found " +linksOnPage.size() + " links on page");
            for(Element link : linksOnPage){
                this.links.add(link.absUrl("href"));
            }
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public boolean searchWordOnPage(String searchWord){
        if(this.htmlDocument == null){
            System.out.println("You have to crawl site before looking for word in document");
            return false;
        }
        System.out.println("Searching word:: " + searchWord +" ...");
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());

    }

    public List<String> getLinks(){
        return this.links;
    }
}
