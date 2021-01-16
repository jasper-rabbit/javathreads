package realworld.ch2.ch4;

import org.junit.Test;
import realworld.ch4.Attributes;
import realworld.ch4.Document;
import realworld.ch4.DocumentManagementSystem;
import realworld.ch4.UnknownFileTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static realworld.ch4.Attributes.*;

public class DocumentManagementSystemTest {
    private final DocumentManagementSystem system = new DocumentManagementSystem();
    private static final String RESOURCES = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    private final String LETTER = RESOURCES + "test_letter.letter";
    private final String JOE_BLOGGS = "Joe Bloggs";

    private final String XRAY = RESOURCES + "unnamed.jpg";

    @Test
    public void shouldImportFile() throws Exception {
        system.importFile(LETTER);
        final Document document = onlyDocument();
        assertAttributeEquals(document, Attributes.PATH, LETTER);
    }

    private void assertAttributeEquals(Document document, String attributeName, String expectedValue) {
        assertEquals("Document has the wrong value for " + attributeName,
                expectedValue, document.getAttribute(attributeName));
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        org.hamcrest.MatcherAssert.assertThat(documents, hasSize(1));
        return documents.get(0);
    }

    @Test
    public void shouldImportLetterAttributes() throws Exception {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, ADDRESS,
                "123 Fake Street\n" +
                "Westminster\n" +
                "London\n" +
                "United Kingdom");
        assertAttributeEquals(document, BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                    "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
        assertTypeIs("LETTER", document);
    }

    private void assertTypeIs(String type, Document document) {
        assertEquals(type, document.getAttribute("type"));
    }

    @Test
    public void shouldImportImageAttributes() throws Exception {
        system.importFile(XRAY);

        final Document document = onlyDocument();

        assertAttributeEquals(document, WIDTH, "268");
        assertAttributeEquals(document, HEIGHT, "353");
        assertTypeIs("IMAGE", document);
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldNotImportMissingFile() throws Exception {
        system.importFile(RESOURCES + "gobbledygook.txt");
    }

    @Test(expected = UnknownFileTypeException.class)
    public void shouldNotImportUnknownFile() throws Exception {
        system.importFile(RESOURCES + "unknown.txt");
    }
}
