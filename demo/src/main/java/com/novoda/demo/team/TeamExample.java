package com.novoda.demo.team;

import com.novoda.demo.Example;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.SimpleTagInstigator;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

import java.util.List;

public class TeamExample implements Example {

    //language=XML
    private static final String XML =
            "<novoda>" +
                    "<team>" +
                    "<name>Adam</name>" +
                    "<name>Ben</name>" +
                    "<name>Carl</name>" +
                    "<name>David</name>" +
                    "<name>Franky</name>" +
                    "<name>Kevin</name>" +
                    "<name>Moe</name>" +
                    "<name>Paul</name>" +
                    "<name>Peter</name>" +
                    "<name>Shiv</name>" +
                    "</team>" +
                    "</novoda>";
    private static ElementFinder<List<TeamMember>> elementFinder;

    @Override
    public void execute() {
        ElementFinderFactory factory = SimpleEasyXmlParser.getElementFinderFactory();
        elementFinder = factory.getStringWrapperTypeListFinder("name", TeamMember.class);
        Instigator instigator = new TeamInstigator(elementFinder, finishWatcher);
        SimpleEasyXmlParser.parse(XML, instigator);
    }

    private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
        @Override
        public void onFinish() {
            System.out.println("Found : " + elementFinder.requireResult());
        }
    };

    public static class TeamInstigator extends SimpleTagInstigator {

        public TeamInstigator(ElementFinder<?> elementFinder, ParseFinishWatcher parseFinishWatcher) {
            super(elementFinder, "team", parseFinishWatcher);
        }

        @Override
        public String getRootTag() {
            return "novoda";
        }
    }

}
