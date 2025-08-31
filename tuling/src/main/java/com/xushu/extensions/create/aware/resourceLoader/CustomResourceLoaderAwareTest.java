package com.xushu.extensions.create.aware.resourceLoader;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class CustomResourceLoaderAwareTest implements ResourceLoaderAware, ApplicationContextAware {


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

		Resource resource = resourceLoader.getResource("classpath:spring.xml");


		try {
			showResourceData(resource);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}



	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		try {
			Resource[] resources  = applicationContext.getResources("classpath:com/xushu/*.class");
			for (Resource resource : resources) {
				System.out.println(resource.contentLength());
				System.out.println(resource.getFilename());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

    public void showResourceData( Resource banner) throws IOException
    {
        InputStream in = banner.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
        reader.close();
    }

}