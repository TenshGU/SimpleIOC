package cn.edu.scau.shui.factory;


import cn.edu.scau.shui.BeanInfo;
import cn.edu.scau.shui.reader.SourceReader;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @description: AbstractBeanFactory实际上定义了完整的获取bean的方法，
 * 具体的配置文件，需要具体到对应的XxxContext中，不同配置文件需要不同的XxxReader
 * @author: Tensh
 * @createDate: 2020/11/28
 */
public abstract class AbstractBeanFactory implements BeanFactory{
    //注册文件路径
    private String filePath;
    //注册对象信息Map，key:文件中对象的标识符 value:对应的BeanInfo
    private Map<String, BeanInfo> container;
    //对象注册读取器
    protected SourceReader reader;

    public AbstractBeanFactory(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 交由具体的子类XxxContext实现（如XMLContext，TXTContext）
     * @param reader 具体配置文件对应的Reader
     */
    protected abstract void setSourceReader(SourceReader reader);

    /**
     *从注册读取器中读取注册对象的信息Map
     */
    protected void registerBeans() {
        this.container = this.reader.loadBeans(filePath);
    }


    @Override
    public Object getBean(String name) {
        BeanInfo beanInfo = container.get(name);
        //判断给出的对象标识符对应的BeanInfo是否存在
        if (beanInfo != null) {
            //存在就需要解析beanInfo，通过反射配置其属性
            return this.parseBeanInfo(beanInfo);
        } else {
            //不存在就返回null
            return null;
        }
    }

    /**
     * 通过反射获取其Class对象以及setter方法，为其新建对象以及为其属性赋值
     * @param beanInfo 需要解析的BeanInfo
     * @return 实际生成的BeanInfo 对应的对象
     */
    protected Object parseBeanInfo(BeanInfo beanInfo) {
        Class clazz;
        //处理各种异常，如找不到类的全限定名，不存在对应的属性
        try {
            clazz = Class.forName(beanInfo.getType());
            Object bean = clazz.getConstructor().newInstance();
            Method[] methods = clazz.getMethods();
            //获取配置的属性
            Set<String> properties = beanInfo.getProperties().keySet();
            for (String property : properties) {
                //得到setter方法名
                String setter = "set"
                        + property.substring(0,1).toUpperCase() + property.substring(1,property.length());
                for (Method method : methods) {
                    if (method.getName().equals(setter)) {
                        //得到属性值，但要判断是否为BeanInfo类型
                        Object propertyValue = beanInfo.getProperties().get(property);
                        Class<?> propertyClazz = propertyValue.getClass();
                        if (propertyClazz == BeanInfo.class) {
                            //是为BeanInfo类型，即该属性为对象类型，递归调用getBean方法，获取到该BeanInfo对应的对象
                            propertyValue = getBean(((BeanInfo) propertyValue).getId());
                            method.invoke(bean,propertyValue);
                        } else {
                            //调用setter赋值
                            method.invoke(bean,method.getParameterTypes()[0].getConstructor(String.class).newInstance(propertyValue));
                        }
                        //已经为当前属性设置了值，继续为下一属性赋值，退出当前内循环
                        break;
                    }
                }
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
