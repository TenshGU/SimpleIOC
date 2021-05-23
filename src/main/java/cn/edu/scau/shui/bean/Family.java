package cn.edu.scau.shui.bean;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2020/11/28
 */
public class Family {
    //家庭名字
    private String name;
    //家庭人数
    private Integer count;
    //一家之主
    private Person master;

    public void printInfo() {
        System.out.println("这是" + name + "，一家之主是：" + master.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Person getMaster() {
        return master;
    }

    public void setMaster(Person master) {
        this.master = master;
    }

    @Override
    public String toString() {
        return "Family{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", master=" + master +
                '}';
    }
}
