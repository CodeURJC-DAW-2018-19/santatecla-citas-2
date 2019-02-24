package com.urjc.daw.practica.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class DocumentGenerationService {

    /**
     * Toma la ruta de fichero y devuelve un stream abierto para su lectura.
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    public InputStream loadDocumentAsStream(String filePath) throws IOException {
        URL url = new File(filePath).toURI().toURL();
        InputStream documentAsStream = url.openStream();
        return documentAsStream;
    }

    /**
     * Carga en el objeto context el valor de las variables.
     * 
     * @param report
     * @param variablesToBeReplaced
     * @return
     * @throws XDocReportException
     */
    public IContext loadVariables(Map<String, Object> variables, IContext context) throws XDocReportException {
        for (Map.Entry<String,Object> variable : variables.entrySet()) {
            context.put(variable.getKey(), variable.getValue());
        }

        return context;
    }

    /**
     * Carga en el objeto context las imágenes a partir de las rutas dadas y guarda en
     * el objeto report los metadatos de las imágenes.
     * 
     * @param report
     * @param variablesToBeReplaced
     * @param context
     */
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
    	
        // Cargar el fichero y configurar el Template Engine
        InputStream inputStream = loadDocumentAsStream(templatePath);
        IXDocReport xdocReport = XDocReportRegistry.getRegistry().loadReport(inputStream, templateEngine);

        // Se crea el contexto y se cargan las variables de reemplazo
        IContext context = xdocReport.createContext();
        loadVariables(variablesMap, context);
        loadImages(xdocReport, imagesPathMap, context);

        // Se genera la salida a partir de la plantilla.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (converPdf) {
            // Se configura PDF como el formato de salida del conversor
            Options options = Options.getTo(ConverterTypeTo.PDF);

            // Se genera el documento remplazando las variables y convirtiendo
            // la salida a PDF.
            xdocReport.convert(context, options, out);
        } else {
            // Se genera el documento remplazando las variables manteniendo el
            // formato original.
            xdocReport.process(context, out);
        }

        return out.toByteArray();
    }
}