package org.blacksmith.jsnip.annotation;

import com.schenker.translation.lib.base.core.annotation.EsShellAnnotationParser;
import com.schenker.translation.lib.base.core.service.PlatformTranslationService;
import org.springframework.context.annotation.Bean;

public final class XyzConfiguration {

    @Bean
    public EnableXyzAnnotationParser enableXyzAnnotationParser() {
        return new EnableXyzAnnotationParser();
    }
    //and anything else
}
