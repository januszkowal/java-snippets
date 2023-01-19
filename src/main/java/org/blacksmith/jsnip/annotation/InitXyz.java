package org.blacksmith.jsnip.annotation;

import com.schenker.translation.lib.base.core.annotation.EnableEsShellAttributes;
import com.schenker.translation.lib.base.core.annotation.EsShellAnnotationParser;
import com.schenker.translation.lib.base.core.frontend.esshell.ShellTranslationProperties;
import com.schenker.translation.lib.reactive.core.EnableShellTranslations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

public class InitXyz implements ImportAware {

    private final EnableXyzAnotationParser annotationParser;

    private XyzAttributes xyzAttributes;

    public InitXyz(EnableXyzAnotationParser annotationParser) {
        this.annotationParser = annotationParser;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        xyzAttributes = annotationParser.getAttributes(importMetadata, EnableXyz.class);
    }

    @Bean
    public XyzProperties shellTranslationProperties() {
        return new XyzProperties(this.xyzAttributes);
    }
}
