package fr.java._0508_Annotation;

//我们可以看到右侧有很多黄色的警告，我们可以使用注解压制所有("all")警告
@SuppressWarnings("all")

public class JDKAnnotation {

    @Override //查看是否正确重写了父类或者接口的方法
    public String toString() {
        return super.toString();
    }

    @Deprecated
    public void show01() {
    }

    //当show01方法不再使用，被show02方法代替时，但是我们不可以直接删除这个方法，否则低版本就不兼容了
    //还是可以使用show01方法，只是不建议用而已
    public void show02() {
    }

    public void demo() {
        show01(); //当我们调用时，会出现斜杠
    }


}
