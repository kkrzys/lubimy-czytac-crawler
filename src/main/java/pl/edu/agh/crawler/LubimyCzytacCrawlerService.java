package pl.edu.agh.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.edu.agh.model.Author;
import pl.edu.agh.model.Book;
import pl.edu.agh.model.User;
import pl.edu.agh.util.CrawlerUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class LubimyCzytacCrawlerService implements ICrawlerService {

    @Override
    public User crawlUserFromUrl(Document doc) {
        Element profileHeader = doc.getElementsByClass("profile-header").first();

        Element userNameH = profileHeader.getElementsByClass("title").first();
        String userName = CrawlerUtil.removeLastChar(userNameH.ownText());

        Element profileHeaderInfoDiv = doc.getElementsByClass("profile-header-info").first();

        Element userDescriptionSpan = profileHeaderInfoDiv.getElementsByTag("span").first();
        String userDescription = userDescriptionSpan.ownText();

        Element basicInformationDiv = profileHeaderInfoDiv.select("div.font-szary-a3.spacer-10-t").first();
        String basicInformation = basicInformationDiv.text();

        return new User(userName, userDescription, basicInformation, doc.location());
    }

    @Override
    public Book crawlBookFromUrl(Document doc) {
        try {

            String bookName = doc.select("h1[itemprop=name]").text();

            Elements authorsSpan = doc.select("span[itemprop=author]");
            Elements authorsLinks = authorsSpan.select("a[itemprop=name]");
            Set<Author> authors = authorsLinks.stream().map(author -> new Author(author.text(), author.attr("href"))).collect(Collectors.toSet());

            Double ratingValue = Double.parseDouble(doc.getElementById("rating-value").select("span[itemprop=ratingValue]").text().replace(',', '.'));
            Integer ratingVotes = Integer.parseInt(doc.getElementById("rating-votes").select("span[itemprop=ratingCount").text());
            Integer ratingAmount = Integer.parseInt(doc.getElementById("rating-amount").text());

            Element dBookDetailsDiv = doc.getElementById("dBookDetails");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date datePublished = dateFormat.parse(dBookDetailsDiv.select("dd[itemprop=datePublished]").attr("content"));
            String isbn = dBookDetailsDiv.select("span[itemprop=isbn]").text();
            Integer numOfPages = Integer.parseInt(dBookDetailsDiv.select(":contains(liczba stron)").parents().last().select("dd").text());
            /*Element categoryEl = dBookDetailsDiv.select("a[itemprop=genre]").first();
            String categoryName = categoryEl.text();
            String categoryUrl = "http://lubimyczytac.pl/" + categoryEl.attr("href");
            Category category = new Category(categoryName, categoryUrl);*/

            String language = dBookDetailsDiv.select("dd[itemprop=inLanguage").text();
            String description = doc.select("p.description.regularText").text();

            return new Book(bookName, authors, ratingValue, ratingVotes, ratingAmount, datePublished, isbn, numOfPages, language, description, doc.location());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
