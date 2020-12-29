package com.example.bindview_compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import javax.annotation.processing.Messager;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author wangzhichao
 * @since 2020/12/29
 */
public class BindViewFactory {
    private MethodSpec.Builder methodSpecBuilder;
    /**
     * 使用 @BindView 注解的字段所在的类节点
     */
    private ClassName className;

    private Messager messager;

    private Types typeUtils;

    private Elements elementUtils;


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
    }
}
