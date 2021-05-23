package cn.edu.scau.shui.factory;

import cn.edu.scau.shui.reader.SourceReader;
import cn.edu.scau.shui.reader.XMLSourceReader;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2020/11/28
 */
public class XMLContext extends AbstractBeanFactory {
    public XMLContext(String filePath) {
        super(filePath);
        this.setSourceReader(new XMLSourceReader());
        this.registerBeans();
    }

    @Override
    protected void setSourceReader(SourceReader reader) {
        this.reader = reader;
    }
}
