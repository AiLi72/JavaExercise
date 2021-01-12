package fr.java._0324_Interface;

public class MyBody {
    public class Heart{
        public void heartMethod (){
            System.out.println("method intérieur");
        }
    }
    public void bodyMethod (){
        System.out.println("method extérieur");
    }
    public void bodyMethod2 (){
        new Heart().heartMethod();
    }
}
