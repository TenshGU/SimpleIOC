package cn.edu.scau.shui;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2020/11/28
 */
public class BeanInfo {
    //对象的标识，即配置文件中bean标签的id属性
    private String id;
    //对象的类型，即全类名，配置文件中bean标签的type属性
    private String type;
    //对象的属性及值的集合，即配置文件中bean标签下的property标签的属性
    private Map<String,Object> properties = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public void addProperty(String name,String value) {
        properties.put(name,value);
    }
}
