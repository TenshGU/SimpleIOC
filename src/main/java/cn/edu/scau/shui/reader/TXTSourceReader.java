package cn.edu.scau.shui.reader;

import cn.edu.scau.shui.BeanInfo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2020/11/29
 */
public class TXTSourceReader implements SourceReader{
    private List<String> getTxtAllLines(String filePath) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            return lines;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, BeanInfo> loadBeans(String filePath) {
        Map<String, BeanInfo> map = new HashMap<>();

        try {
            List<String> linesList = getTxtAllLines(filePath);
            BeanInfo pointer = null;
            Map<String, Object> propertiesMap = null;
            String propertyName = null;
            Object propertyValue = null;

            for (String str : linesList) {
                String[] splits = str.split("\\s+");
                if (splits.length == 0) {
                    //空行
                    continue;
                } else if (splits.length == 1) {
                    //只有一个标识符
                    if (("beans").equals(splits[0])) {
                        continue;
                    } else if (("/beans").equals(splits[0])) {
                        //结束
                        return map;
                    }
                } else {
                    //bean标识符 或者 property标识符
                    if ("bean".equals(splits[1])) {
                        pointer = new BeanInfo();
                        pointer.setId(splits[2].split("=")[1]);
                        pointer.setType(splits[3].split("=")[1]);
                        propertiesMap = new HashMap<>();
                        continue;
                    } else if (("/bean").equals(splits[1])) {
                        pointer.setProperties(propertiesMap);
                        map.put(pointer.getId(),pointer);
                        pointer = null;
                        continue;
                    } else {
                        propertyName = splits[2].split("=")[1];
                        if ("bean".equals(splits[3].split("=")[0])) {
                            propertyValue = map.get(splits[3].split("=")[1]);
                        } else {
                            propertyValue = splits[3].split("=")[1];
                        }
                        propertiesMap.put(propertyName,propertyValue);
                        propertyName = null;
                        propertyValue = null;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
