import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
 * A class is used for web scraping an article and do basic counting for a given word with case sensitivity,
 * case insensitivity, and unique words.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(numberOfWordOccurances("gentlemen", "http://erdani.com/tdpl/hamlet.txt"));
        System.out.println(numberOfWordOccurancesCaseInsensitive("gentlemen", "http://erdani.com/tdpl/hamlet.txt"));
        System.out.println(countWords("http://erdani.com/tdpl/hamlet.txt"));
    }


    /**
     * Will count the number of unique words that exists in the article by using a Set and adding each
     * word into the set. If the word already exists in the set, then it will not be added into the set.
     *
     * @param url the url of the article.
     * @return the number of unique words in the article.
     */
    public static int countWords(String url) {
        Set<String> inArticle = new HashSet<String>();
        String article = urlToString(url);

        String[] words = article.split(" ");
        for(String word : words){
            inArticle.add(word);
        }

        return inArticle.size();
    }


    /**
     * Will count the number of times the given word exists in the article of the given URL. This
     * method is case sensitive.
     *
     * @param url the url of the article.
     * @return the number of times the given word exists in the article (case sensitive).
     */
    public static int numberOfWordOccurances(String word, String url) {
        int count = 0;
        String article = urlToString(url);

        while (article.contains(word)) {
            count++;
            article = article.substring(0, article.indexOf(word)) + article.substring(article.indexOf(word) + 1);
        }

        return count;
    }

    /**
     * Will count the number of times the given word exists in the article of the given URL. This
     * method is case insensitive.
     *
     * @param url the url of the article.
     * @return the number of times the given word exists in the article (case insensitive).
     */
    public static int numberOfWordOccurancesCaseInsensitive(String word, String url) {
        int count = 0;
        String article = urlToString(url).toUpperCase();
        word = word.toUpperCase();

        while (article.contains(word)) {
            count++;
            article = article.substring(0, article.indexOf(word)) + article.substring(article.indexOf(word) + 1);
        }

        return count;
    }

    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

}
