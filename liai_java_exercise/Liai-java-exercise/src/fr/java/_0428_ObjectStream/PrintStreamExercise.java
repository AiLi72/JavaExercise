package fr.java._0428_ObjectStream;
//打印流printStream：extends OutputStream,用来在文件中写入内容，只输出，不读取
import java.io.FileNotFoundException;
import java.io.PrintStream;

//不会抛出IOException,只会抛出FileNotFoundException
public class PrintStreamExercise {
    public static void main(String[] args) throws FileNotFoundException {
        //参数是目的地：打印流的流向，即将输出语句打印到哪里，不需要new FileOutputStream对象
        PrintStream ps = new PrintStream("/Users/liai/IdeaProjects/basic-code/Liai-java-exercise/aaa/1/print.txt");
        //如果使用父类的write方法，将会查询编码表，97-->a
        ps.write(97);
        //如果使用自己的特有方法print\println，则可以原封不动的输出任意类型的数据
        ps.println("hello");
        ps.println(5.5);
        ps.println('c');

        //⚠️我们可以用以下方法改变输出语句的目的地：默认是打印在控制台
        //将目的地改成了ps的流向
        System.setOut(ps);

        System.out.println("je suis pas dans le console");
        ps.close();

    }
}
