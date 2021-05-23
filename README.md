# SimpleIOC
简易版IOC
支持两种格式的配置文件：txt和XML

XML：
<beans>
    <bean id="person" class="cn.edu.scau.shui.bean.Person">
        <property name="name" value="张三"></property>
        <property name="age" value="19"></property>
    </bean>

    <bean id="family" class="cn.edu.scau.shui.bean.Family">
        <property name="name" value="幸福家庭"></property>
        <property name="count" value="5"></property>
        <property name="master" bean="person"></property>
    </bean>
</beans>

结果：

—————————————————————————————————————————————————————
