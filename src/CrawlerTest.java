public class CrawlerTest {

    public static void main(String[] args){
        Crawler crawler = new Crawler();
        crawler.search("https://www.wykop.pl/", "#motoryzacja");
    }
}
