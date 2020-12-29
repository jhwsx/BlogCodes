package com.example.bindview_compiler;

import com.example.bindview_annotations.BindView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import static com.example.bindview_compiler.ProcessorConstants.TARGET_ARGUMENT;

/**
 * @author wangzhichao
 * @since 2020/12/28
 */
// 这个注解的作用是用来生成 META-INF/javax.annotation.processing.Processor 这个文件，文件里就是
// 注解处理器的全路径，这个文件会被 ServiceLoader 类使用，用于加载注解服务。
@AutoService(Processor.class)
// 指定注解处理器支持的 JDK 编译版本
@SupportedSourceVersion(SourceVersion.RELEASE_7)
// 指定注解处理器支持处理的注解
@SupportedAnnotationTypes({ProcessorConstants.BINDVIEW_FULLNAME})

@SupportedOptions({ProcessorConstants.MODULE_NAME, ProcessorConstants.PACKAGENAME_FOR_APT})
public class BindViewProcessor extends AbstractProcessor {

    /**
     * 操作 Element 的工具类（类，函数，属性，枚举，构造方法都是 Element）
     */
    private Elements elementUtils;

    /**
     * 打印日志类
     */
    private Messager messager;

    /**
     * 用来对类型进行操作的实用工具方法
     */
    private Types typeUtils;

    /**
     * 按 Activity 存放使用了 @BindView 注解的集合
     * 键是 Activity
     * 值是使用了 @BindView 的元素列表
     */
    private Map<TypeElement, List<Element>> map = new HashMap<>();

    /**
     * 文件生成器
     */
    private Filer filer;
    private String moduleName;
    private Map<String, String> options;
    private String packagenameForAPT;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
        typeUtils = processingEnvironment.getTypeUtils();
        filer = processingEnvironment.getFiler();
        options = processingEnvironment.getOptions();
        parseOptions();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.NOTE, "process: set=" + set);
        if (!set.isEmpty()) {
            Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
            if (!elements.isEmpty()) {
                populateMap(elements);
                if (map.isEmpty()) {
                    return true;
                }
                //public class MainActivity$$BindView implements BindViewInterface {
                //    @Override
                //    public void bind(Object target) {
                //        MainActivity activity = (MainActivity) target;
                //        activity.tv = activity.findViewById(R.id.tv);
                //        activity.cl = activity.findViewById(R.id.cl);
                //    }
                //}
                TypeElement activityType = elementUtils.getTypeElement(ProcessorConstants.ACTIVITY_FULLNAME);
                TypeElement bindViewInterfaceType = elementUtils.getTypeElement(ProcessorConstants.BINDVIEWINTERFACE_FULLNAME);

                ParameterSpec parameterSpec = ParameterSpec.builder(ClassName.get("java.lang", "Object"), TARGET_ARGUMENT).build();
                for (Map.Entry<TypeElement, List<Element>> entry : map.entrySet()) {
                    TypeElement key = entry.getKey();
                    if (!typeUtils.isSubtype(key.asType(), activityType.asType())) {
                        messager.printMessage(Diagnostic.Kind.ERROR,
                                "@BindView can only be annotated in Activity");
                    } else {
                        ClassName className = ClassName.get(key);
                        BindViewFactory bindViewFactory = new BindViewFactory.Builder(parameterSpec)
                                .className(className)
                                .elementUtils(elementUtils)
                                .messager(messager)
                                .typeUtils(typeUtils)
                                .build();
                        bindViewFactory.addFirstStatement();
                        List<Element> elementList = entry.getValue();
                        for (Element element : elementList) {
                            bindViewFactory.buildStatement(element);
                        }
                        MethodSpec methodSpec = bindViewFactory.build();
                        TypeSpec typeSpec = TypeSpec.classBuilder(className.getClass().getSimpleName() + "$$BindView")
                                .addModifiers(Modifier.PUBLIC)
                                .addMethod(methodSpec)
                                .addSuperinterface(ClassName.get(bindViewInterfaceType))
                                .build();
                        JavaFile javaFile = JavaFile.builder(className.packageName(), typeSpec).build();

                        try {
                            javaFile.writeTo(filer);
                        } catch (IOException e) {
                            messager.printMessage(Diagnostic.Kind.ERROR, "create bindview file fail: " + e);
                        }
                    }
                }
            }
        }
        return false;
    }

    private void populateMap(Set<? extends Element> elements) {
        for (Element element : elements) {
            TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
            if (map.containsKey(enclosingElement)) {
                map.get(enclosingElement).add(element);
            } else {
                ArrayList<Element> list = new ArrayList<>();
                list.add(element);
                map.put(enclosingElement, list);
            }
        }
    }

    private void parseOptions() {
        moduleName = options.get(ProcessorConstants.MODULE_NAME);
        packagenameForAPT = options.get(ProcessorConstants.PACKAGENAME_FOR_APT);
        messager.printMessage(Diagnostic.Kind.NOTE, "moduleName=" + moduleName +
                ",packagenameForAPT=" + packagenameForAPT);
        if (moduleName != null && packagenameForAPT != null) {
            messager.printMessage(Diagnostic.Kind.NOTE, "APT environment success");
        } else {
            messager.printMessage(Diagnostic.Kind.NOTE, "APT environment fail");
        }
    }
}
