package cn.edu.scau.shui.reader;



import cn.edu.scau.shui.BeanInfo;

import java.util.Map;

/**
 * 注册读取器接口
 * 负责读取用户注册的对象
 * 实现的子类，可以对应于不同的配置方式（如：注解，XML，txt等等）
 */
public interface SourceReader {
    /**
     * 读取用户注册的对象信息
     * @param filePath 读取路径
     * @return 注册信息对象信息 对应于AbstractBeanFactory中的container
     */
    Map<String, BeanInfo> loadBeans(String filePath);
}
