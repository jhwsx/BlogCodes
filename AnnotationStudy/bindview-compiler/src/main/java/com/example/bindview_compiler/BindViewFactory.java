package com.example.bindview_compiler;

import com.example.bindview_annotations.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.example.bindview_compiler.ProcessorConstants.BIND_METHOD_NAME;
import static com.example.bindview_compiler.ProcessorConstants.TARGET_ARGUMENT;

/**
 * @author wangzhichao
 * @since 2020/12/29
 */
public class BindViewFactory {
    private MethodSpec.Builder methodSpecBuilder;
    /**
     * 使用 @BindView 注解的字段所在的类节点
     */
    private final ClassName className;

    private final Messager messager;

    private final Types typeUtils;

    private final Elements elementUtils;

    public BindViewFactory(Builder builder) {
        this.className = builder.className;
        this.elementUtils = builder.elementUtils;
        this.typeUtils = builder.typeUtils;
        this.messager = builder.messager;
        methodSpecBuilder = MethodSpec.methodBuilder(BIND_METHOD_NAME)
                .addParameter(builder.parameterSpec)
                .returns(void.class)
                .addModifiers(Modifier.PUBLIC);
    }

    public static class Builder {
        private ClassName className;

        private Messager messager;

        private Types typeUtils;

        private Elements elementUtils;

        private ParameterSpec parameterSpec;

        public Builder(ParameterSpec parameterSpec) {
            this.parameterSpec = parameterSpec;
        }

        public Builder className(ClassName className) {
            this.className = className;
            return this;
        }

        public Builder messager(Messager messager) {
            this.messager = messager;
            return this;
        }

        public Builder typeUtils(Types typeUtils) {
            this.typeUtils = typeUtils;
            return this;
        }

        public Builder elementUtils(Elements elementUtils) {
            this.elementUtils = elementUtils;
            return this;
        }

        public BindViewFactory build() {
            if (parameterSpec == null) {
                throw new IllegalArgumentException("parameterSpec cannot be null");
            }
            if (className == null) {
                throw new IllegalArgumentException("className cannot be null");
            }
            if (messager == null) {
                throw new IllegalArgumentException("messager cannot be null");
            }
            if (typeUtils == null) {
                throw new IllegalArgumentException("typeUtils cannot be null");
            }
            if (elementUtils == null) {
                throw new IllegalArgumentException("elementUtils cannot be null");
            }
            return new BindViewFactory(this);
        }
    }

    public void addFirstStatement() {
        // MainActivity activity = (MainActivity) target;
        methodSpecBuilder.addStatement("$T activity = ($T) $N", className, className, TARGET_ARGUMENT);
    }

    public void buildStatement(Element element) {
        // activity.tv = activity.findViewById(R.id.tv);
        String fieldName = element.getSimpleName().toString();
        BindView bindViewAnnotation = element.getAnnotation(BindView.class);
        int id = bindViewAnnotation.value();
        methodSpecBuilder.addStatement("activity.$L = activity.findViewById($L)", fieldName, id);
    }

    public MethodSpec build() {
        return methodSpecBuilder.build();
    }
}
