import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;

public class HtmlToPdf {
    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
    public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException{
        // ref: https://www.baeldung.com/pdf-conversions-java
        Document document = new Document();
        PdfWriter pdfwriter = PdfWriter.getInstance(document,
                new FileOutputStream("src/main/resources/sample_html.pdf"));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document,
                new FileInputStream("src/main/resources/sample.html"));
        document.close();
    }
}
