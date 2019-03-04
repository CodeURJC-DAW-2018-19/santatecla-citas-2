package com.urjc.daw.practica.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;

@Service
public class DocumentGenerationService {

	private static final String TEMPLATE_NAME = "pdfTemplate";

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private QuoteManagementServiceImpl quoteService;

	public File generateDocument(Topic topic)  {

		Map<String, String> map = new HashMap<String, String>();

		// Iterate the quotes and text inside each topic
		Iterator<Long> it = topic.getQuoteIds().iterator();
		String quotes = "";
		while (it.hasNext()) {
			Quote q = quoteService.findOne((long) it.next()).get();
			quotes += q.getText() + "/" + q.getAuthor() + "/" + q.getBook() + ";";
		}
		
		Iterator<String> iter = topic.getTexts().iterator();
		String texts = "";
		while (iter.hasNext()) {
			texts += iter.next() + ";";
		}

		map.put("quote", quotes);
		map.put("texts", texts);

		Assert.notNull(TEMPLATE_NAME, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
			Iterator<Map.Entry<String, String>> itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				Map.Entry<String, String> pair = itMap.next();
				ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}

		String processedHtml = templateEngine.process(TEMPLATE_NAME, ctx);
		FileOutputStream os = null;
		String fileName = UUID.randomUUID().toString();
		File outputFile = null;
		try {
			outputFile = File.createTempFile(fileName, ".pdf");
			os = new FileOutputStream(outputFile);

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			renderer.createPDF(os, false);
			renderer.finishPDF();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputFile;

	}
}
