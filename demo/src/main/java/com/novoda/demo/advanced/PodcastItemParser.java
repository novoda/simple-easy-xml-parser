package com.novoda.demo.advanced;

import com.novoda.demo.advanced.pojo.Author;
import com.novoda.demo.advanced.pojo.Link;
import com.novoda.demo.advanced.pojo.Title;
import com.novoda.sax.Element;
import com.novoda.sax.ElementListener;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseWatcher;
import com.novoda.sexp.parser.Parser;

import org.xml.sax.Attributes;

public class PodcastItemParser implements Parser<PodcastItem> {

    private static final String TAG_TITLE = "title";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_LINK = "link";

    private final ElementFinder<Author> authorFinder;
    private final ElementFinder<Link> linkFinder;
    private final ElementFinder<Title> titleFinder;

    private ItemHolder itemHolder;
    private ParseWatcher<PodcastItem> listener;

    public PodcastItemParser(ElementFinderFactory factory) {
        this.titleFinder = factory.getStringWrapperTypeFinder(Title.class);
        this.authorFinder = factory.getStringWrapperTypeFinder(Author.class);
        this.linkFinder = factory.getStringWrapperTypeFinder(Link.class);
    }

    @Override
    public void parse(Element element, final ParseWatcher<PodcastItem> listener) {
        this.listener = listener;
        element.setElementListener(recipeParseListener);
        titleFinder.find(element, TAG_TITLE);
        authorFinder.find(element, TAG_AUTHOR);
        linkFinder.find(element, TAG_LINK);
    }

    private final ElementListener recipeParseListener = new ElementListener() {
        @Override
        public void start(Attributes attributes) {
            itemHolder = new ItemHolder();
        }

        @Override
        public void end() {
            itemHolder.title = titleFinder.getResult();
            itemHolder.author = authorFinder.getResult();
            itemHolder.link = linkFinder.getResult();

            listener.onParsed(itemHolder.asItem());
        }
    };

    private static class ItemHolder {
        Title title;
        Author author;
        Link link;

        public PodcastItem asItem() {
            return new PodcastItem(title, author, link);
        }
    }

}
