package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.service.IPDFService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PDFService implements IPDFService{

    private Logger logger = LoggerFactory.getLogger(PDFService.class);

    public final TemplateEngine templateEngine;

    @Override
    public ByteArrayInputStream generatePdf(String templateName, Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream, false);
            renderer.finishPDF();
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());


            String templateFolder = Paths.get("src", "main", "resources", "templates","pdf").toString();
            String outputFilePath = Paths.get(templateFolder, "score.pdf").toString();

            generateAndSavePDF(htmlContent, outputFilePath);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return byteArrayInputStream;
    }

    public void generateAndSavePDF(String htmlContent, String outputFilePath) throws IOException {
        // Initialize the ITextRenderer
        ITextRenderer renderer = new ITextRenderer();

        // Set up the HTML content to be rendered
        renderer.setDocumentFromString(htmlContent);

        // Layout and create the PDF
        renderer.layout();

        // Save the rendered PDF to the specified output file
        try (FileOutputStream os = new FileOutputStream(outputFilePath)) {
            renderer.createPDF(os);
        }

        System.out.println("PDF saved to " + outputFilePath);
    }
}
