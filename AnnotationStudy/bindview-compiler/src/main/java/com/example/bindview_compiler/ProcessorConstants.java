package com.example.bindview_compiler;

/**
 * @author wangzhichao
 * @since 2020/12/28
 */
public class ProcessorConstants {

    public static final String BINDVIEW_FULLNAME = "com.example.bindview_annotations.BindView";

    /**
     * Activity 类的全路径
     */
    public static final String ACTIVITY_FULLNAME = "android.app.Activity";

    /**
     * bindview-api 模块的包名
     */
    public static final String BINDVIEW_API_PACKAGENAME = "com.example.bindview_api";

    /**
     * bindview-api 模块的 BindViewInterface 接口全类名
     */
    public static final String BINDVIEWINTERFACE_FULLNAME = BINDVIEW_API_PACKAGENAME + ".BindViewInterface";

    public static final String TARGET_ARGUMENT = "target";

    public static final String BIND_METHOD_NAME = "bind";

    /**
     * 模块名
     */
    public static final String MODULE_NAME = "moduleName";

    /**
     * 存放生成的 APT 文件的包名
     */
    public static final String PACKAGENAME_FOR_APT = "packageNameForAPT";

}
