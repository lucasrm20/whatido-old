package com.whatido.util.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.whatido.service.NegocioException;
import com.whatido.util.email.TipoEmail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerConfig implements Serializable {

	private static final long serialVersionUID = -3940279792111873488L;

	private Configuration getFreemarkerConfig() throws IOException, URISyntaxException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(new File(getClass().getClassLoader().getResource("/emails").toURI()));
		cfg.setDefaultEncoding("UTF-8");

		return cfg;
	}

	public String getEmailComTemplate(TipoEmail tipoEmail, Object parametro) {
		try {
			Configuration cfg = getFreemarkerConfig();
			Template tmplt = cfg.getTemplate(tipoEmail.getDescricao());

			// Insere parametros
			Map<String, Object> data = new HashMap<>();
			data.put("parametro", parametro);

			// Constroi a saida
			Writer out = new StringWriter();
			tmplt.process(data, out);

			return out.toString();
		} catch (IOException | URISyntaxException | TemplateException e) {
			throw new NegocioException("O template do email não pode ser processado.");
		}
	}

}
