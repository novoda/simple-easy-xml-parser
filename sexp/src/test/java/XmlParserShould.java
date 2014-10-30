import com.novoda.sexp.XmlParser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by joaobiriba on 30/10/14.
 */
public class XmlParserShould {

    @Mock
    XMLReader xmlReader;

    //language=XML
    private static final String xml =
            "<novoda>" +
                    "<favouriteColour>Blue</favouriteColour>" +
                    "</novoda>";



    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }


    @Test
    public void call_reader_parse_when_parse_is_called() {
        XmlParser xmlParser = new XmlParser();

        xmlParser.parse(xml,xmlReader);

        try {
            verify(xmlReader).parse(Mockito.<InputSource>any());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
