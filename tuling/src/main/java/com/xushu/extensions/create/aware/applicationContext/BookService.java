package com.xushu.extensions.create.aware.applicationContext;

import com.xushu.extensions.create.aware.MainStart;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    public void doSomething(){
		MainStart bean = SpringApplicationContextHolder.getBean(MainStart.class);
        System.out.println(bean);
    }
}