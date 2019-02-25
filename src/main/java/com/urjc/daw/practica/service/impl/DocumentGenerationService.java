package com.urjc.daw.practica.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Service;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

@Service
public class DocumentGenerationService {


    public InputStream loadDocumentAsStream(String filePath) throws IOException {
        URL url = new File(filePath).toURI().toURL();
        InputStream documentAsStream = url.openStream();
        ZipInputStream zip = new ZipInputStream(documentAsStream);
        return zip;
    }


    public IContext loadVariables(Map<String, Object> variables, IContext context) throws XDocReportException {
        for (Map.Entry<String,Object> variable : variables.entrySet()) {
            context.put(variable.getKey(), variable.getValue());
        }

        return context;
    }


    public void loadImages(IXDocReport report, Map<String, String> variables, IContext context) {
        FieldsMetadata metadata = new FieldsMetadata();
        for (Map.Entry<String,String> variable : variables.entrySet()) {
            metadata.addFieldAsImage(variable.getKey());
            context.put(variable.getKey(), new FileImageProvider(new File(variable.getValue()), true));
        }
        report.setFieldsMetadata(metadata);
    }


    public byte[] generateDocument(String templatePath, TemplateEngineKind templateEngine,
            Map<String, Object> variablesMap, Map<String, String> imagesPathMap, boolean converPdf)
                    throws IOException, XDocReportException {

        InputStream inputStream = loadDocumentAsStream(templatePath);
        IXDocReport xdocReport = XDocReportRegistry.getRegistry().loadReport(inputStream, templateEngine);

        IContext context = xdocReport.createContext();
        loadVariables(variablesMap, context);
        loadImages(xdocReport, imagesPathMap, context);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (converPdf) {
            Options options = Options.getTo(ConverterTypeTo.PDF);
            xdocReport.convert(context, options, out);
        } else {
            xdocReport.process(context, out);
        }

        return out.toByteArray();
    }
}