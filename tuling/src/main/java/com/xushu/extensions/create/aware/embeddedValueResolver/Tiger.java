package com.xushu.extensions.create.aware.embeddedValueResolver;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class Tiger implements EmbeddedValueResolverAware {
     @Override
     public void setEmbeddedValueResolver(StringValueResolver resolver) {

        String val = resolver.resolveStringValue("当前操作系统为：${os.name},表达式（3*4）的结果：#{3*4}");
        System.err.println("解析后的字符串=》" + val);
    }

}