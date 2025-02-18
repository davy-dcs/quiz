package fr.acajou.quiz.service;

import java.io.ByteArrayInputStream;
import java.util.Map;

public interface IPDFService {
    ByteArrayInputStream generatePdf(String templateName, Map<String, Object> data);
}
