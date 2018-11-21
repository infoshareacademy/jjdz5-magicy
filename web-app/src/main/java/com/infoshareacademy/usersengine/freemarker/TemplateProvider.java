package com.infoshareacademy.usersengine.freemarker;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@ApplicationScoped
public class TemplateProvider {

    private static final String UTF_8_ENCODING = "UTF-8";
    private static final String TEMPLATE_DIRECTORY_PATH = "WEB-INF/fm-templates";
    private static final String TEMPLATE_EXTENSION = ".ftlh";

    private Logger LOG = LoggerFactory.getLogger(TemplateProvider.class);

    public Template getTemplate(ServletContext servletContext, String templateName) throws IOException{
        final Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDefaultEncoding(UTF_8_ENCODING);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(true);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setServletContextForTemplateLoading(servletContext, TEMPLATE_DIRECTORY_PATH);
        return cfg.getTemplate(templateName + TEMPLATE_EXTENSION);
    }

    public void build(ServletContext servletContext, String templateName, Map dataModel,
                      HttpServletResponse resp) {
        try {
            getTemplate(servletContext, templateName).process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        } catch (TemplateException | IOException e) {
            LOG.error("TemplateException. Template cannot be created.");
        }
    }

}
