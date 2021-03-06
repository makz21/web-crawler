import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Crawler {

    private static final int PAGES_TO_SEARCH = 10;
    private Set<String> pagesVisited = new HashSet<>();
    private List<String> pagesToVisit = new LinkedList<>();

    public void search(String url, String searchWord) {
        while (this.pagesVisited.size() < PAGES_TO_SEARCH) {
            String currentUrl;
            CrawlerLeg leg = new CrawlerLeg();
            if(this.pagesToVisit.isEmpty()){
                currentUrl = url;
                this.pagesVisited.add(url);
            }else{
                currentUrl = this.nextUrl();
            }
            leg.crawl(currentUrl);
            if(leg.searchWordOnPage(searchWord)){
                System.out.println(String.format("Word: %s found at page %s", searchWord, currentUrl));
                break;
            }

            this.pagesToVisit.addAll(leg.getLinks());
            }
        System.out.println("\nEnd of program! Visited " + this.pagesVisited.size() + " pages");

    }

    private String nextUrl() {
        String nextUrl;
        do {
            nextUrl = this.pagesToVisit.remove(0);
        } while (this.pagesVisited.contains(nextUrl));
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }

}

