package fr.java._0324_Interface;

public class ClassInterne {

    public static void main(String[] args) {

        new MyBody().bodyMethod2();

        MyBody.Heart heart = new MyBody(). new Heart();
        heart.heartMethod();

        Interface obj = new Interface(){
            @Override
            public void methodabs() {
                System.out.println("匿名内部类");
            }
        };
        obj.methodabs();

    }
}