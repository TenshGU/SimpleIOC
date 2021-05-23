package cn.edu.scau.shui.factory;

import cn.edu.scau.shui.reader.SourceReader;
import cn.edu.scau.shui.reader.TXTSourceReader;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2020/11/29
 */
public class TXTContext extends AbstractBeanFactory{
    public TXTContext(String filePath) {
        super(filePath);
        this.setSourceReader(new TXTSourceReader());
        this.registerBeans();
    }

    @Override
    protected void setSourceReader(SourceReader reader) {
        this.reader = reader;
    }
}
