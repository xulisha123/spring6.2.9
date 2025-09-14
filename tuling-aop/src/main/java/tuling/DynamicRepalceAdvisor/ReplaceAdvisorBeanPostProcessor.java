package tuling.DynamicRepalceAdvisor;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.JoinPoint;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJMethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.*;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.BeanFactoryAdvisorRetrievalHelper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
public class ReplaceAdvisorBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware
{
    @Nullable
    private BeanFactoryAspectJAdvisorsBuilder aspectJAdvisorsBuilder;
    private  AspectJAdvisorFactory advisorFactory;
    private boolean isreplace=false;

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        if(aspectJAdvisorsBuilder!=null && !isreplace){
            List<Advisor> advisors = this.aspectJAdvisorsBuilder.buildAspectJAdvisors();
            int i=0;
            for (Advisor advisor : advisors) {
                // 前置通知
                if(advisor.getAdvice() instanceof AspectJMethodBeforeAdvice){
                    AspectJMethodBeforeAdvice beforeAdvice= (AspectJMethodBeforeAdvice) advisor.getAdvice();

                    AspectInstanceFactory aspectInstanceFactory = beforeAdvice.getAspectInstanceFactory();
                    Method replaceMethodBefore=null;
                    try {
                         replaceMethodBefore = ReplaceAdvisorBeanPostProcessor.class.getMethod("replaceMethodBefore", JoinPoint.class);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    Advisor newadvisor= new MyInstantiationModelAwarePointcutAdvisorImpl(beforeAdvice.getPointcut(),replaceMethodBefore ,
                            advisorFactory, (MetadataAwareAspectInstanceFactory) aspectInstanceFactory,   beforeAdvice.getDeclarationOrder(), beforeAdvice.getAspectName());

                    Collections.replaceAll(advisors,advisor,newadvisor);
                    isreplace=true;
                }
                i++;
            }
        }
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
         this.aspectJAdvisorsBuilder =
                new MyBeanFactoryAspectJAdvisorsBuilderAdapter((ListableBeanFactory) beanFactory,
                       new ReflectiveAspectJAdvisorFactory(beanFactory));

        this.advisorFactory= new ReflectiveAspectJAdvisorFactory(beanFactory);
    }

    private class MyBeanFactoryAspectJAdvisorsBuilderAdapter extends BeanFactoryAspectJAdvisorsBuilder {

        public MyBeanFactoryAspectJAdvisorsBuilderAdapter(
                ListableBeanFactory beanFactory, AspectJAdvisorFactory advisorFactory) {

            super(beanFactory, advisorFactory);
        }
 
    }

    public void replaceMethodBefore(JoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("替換的前置通知");
    }
    
}
