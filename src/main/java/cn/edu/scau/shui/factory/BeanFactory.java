package cn.edu.scau.shui.factory;

/**
 * IOC容器的顶层接口
 */
public interface BeanFactory {
    /**
     * 
     * @param name 对象标识符，对象描述信息中的id属性
     * @return 返回生成的对象
     */
    Object getBean(String name);
}
