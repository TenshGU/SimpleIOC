package cn.edu.scau.shui.reader;


import cn.edu.scau.shui.BeanInfo;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 读取XML形式的配置文件
 * @author: Tensh
 * @createDate: 2020/11/28
 */
public class XMLSourceReader implements SourceReader{
    @Override
    public Map<String, BeanInfo> loadBeans(String filePath) {
        SAXReader sr = new SAXReader();
        Map<String,BeanInfo> map = new HashMap<>();

        try {
            Document document = sr.read(filePath);
            Element rootElement = document.getRootElement();
            //获取每个bean标签
            List<Element> beanList = rootElement.elements("bean");
            for (Element bean : beanList) {
                //每个bean标签对应一个BeanInfo对象
                BeanInfo beanInfo = new BeanInfo();
                String id = bean.attributeValue("id");
                String className = bean.attributeValue("class");
                beanInfo.setId(id);
                beanInfo.setType(className);

                //获取bean标签下的property标签
                List<Element> propertyList = bean.elements("property");
                //BeanInfo中的属性Map key:属性名 value:属性值
                Map<String, Object> properties = new HashMap<>();
                for (Element property : propertyList) {
                    String propertyName = property.attributeValue("name");
                    Object propertyValue = null;
                    String attributeBeanId = property.attributeValue("bean");
                    if (attributeBeanId != null) {
                        //遇到依赖关系，即这个property标签是引用其他bean标签的，直接设propertyValue为BeanInfo对象，交由AbstractFactory处理
                        BeanInfo attributeBean = map.get(attributeBeanId);
                        propertyValue = attributeBean;
                    } else {
                        propertyValue = property.attributeValue("value");
                    }
                    properties.put(propertyName,propertyValue);
                }
                beanInfo.setProperties(properties);

                map.put(beanInfo.getId(),beanInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
