package fr.java._0325_InterfaceExercise;

public class WeaponMain {

    public static void main(String[] args) {
        Weapon weapon = new Weapon("剑",1);

        Hero hero = new Hero("Ai",20,weapon);
        weapon.setKind(2);
        hero.setName("Adrien");
//        SkillImpl impl = new SkillImpl();
//        hero.setSkill(impl);
//        hero.attack();

//        Skill skill = new Skill(){
//            @Override
//            public void use() {
//                System.out.println("hhhhhh");
//            }
//        };
//        hero.setSkill(skill);
//        hero.attack();

        hero.setSkill(new Skill() {
            @Override
            public void use() {
                System.out.println("iiiiii");
            }
        });hero.attack();
    }
}
