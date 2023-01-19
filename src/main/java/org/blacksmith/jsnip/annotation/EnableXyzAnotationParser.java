package org.blacksmith.jsnip.annotation;

import com.schenker.translation.lib.base.core.exception.TranslationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.util.Map;

public class EsShellAnnotationParser implements EmbeddedValueResolverAware {
    private StringValueResolver stringValueResolver;
    private static final String PROP1 = "prop1";
    private static final String PROP2 = "prop2";

    public EnableXyzAttributes getEsShellAttributes(AnnotationMetadata importMetadata, Class<?> xyzAnnotation) {
        LOGGER.info("Start processing {} annotation...", xyzAnnotation.getSimpleName());
        final Map<String, Object> attributeMap = importMetadata.getAnnotationAttributes(xyzAnnotation.getName());
        final AnnotationAttributes attributes = AnnotationAttributes.fromMap(attributeMap);
        if (attributes == null) {
            throw new TranslationException("Not found any annotation attributes for xyz annotation!");
        }

        final var xyzAttributes = new EnableXyzAttributes(
                resolveValue(PROP1, attributes),
                resolveBooleanValue(PROP2, attributes));
        LOGGER.info("Processing {} annotation was completed!", xyzAnnotation.getSimpleName());

        return xyzAttributes;
    }

    private String resolveValue(@NonNull String attributeName, @NonNull AnnotationAttributes attributes) {
        var value = attributes.getString(attributeName);
        if (StringUtils.hasText(value)) {
            value = stringValueResolver.resolveStringValue(value);
        }
        return value;
    }

    private boolean resolveBooleanValue(@NonNull String attributeName, @NonNull AnnotationAttributes attributes) {
        return Boolean.parseBoolean(resolveValue(attributeName, attributes));
    }

    @Override
    public void setEmbeddedValueResolver(@NonNull StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }

}
