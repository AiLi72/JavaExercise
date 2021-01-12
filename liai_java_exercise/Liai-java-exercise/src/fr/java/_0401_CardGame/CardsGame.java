package fr.java._0401_CardGame;

import java.util.ArrayList;
import java.util.Collections;

public class CardsGame {
    public static void main(String[] args) {
        String[] color = {"♥️","♠️","♣️","♦️"};

        ArrayList<Integer> number = new ArrayList<>();
        for (int i = 0; i <17 ; i++) {
            number.add(i);
        }

        ArrayList<String> cards = new ArrayList<>();
        for (int i = 0; i < color.length; i++) {
            for (int i1 = 0; i1 < number.size(); i1++) {
                cards.add(color[i]+(number.get(i1)+1));
            }
        }
        cards.add("大王");
        cards.add("小王");
        //System.out.println(cards.toString());

        //洗牌
        Collections.shuffle(cards);

        //发牌
        ArrayList<String> one = new ArrayList<>();
        ArrayList<String> two = new ArrayList<>();
        ArrayList<String> three = new ArrayList<>();
        ArrayList<String> rest = new ArrayList<>();

        for (int i = 0; i < cards.size()-3; i++) {
            if ( i % 3 == 0 ){
                one.add(cards.get(i));
            }else if ( i % 3 == 1 ){
                two.add(cards.get(i));
            }else if ( i % 3 == 2 ){
                three.add(cards.get(i));
            }
        }
        for (int i = cards.size()-3; i < cards.size(); i++) {
            rest.add(cards.get(i));
        }

        System.out.println(one.toString());
        System.out.println(two.toString());
        System.out.println(three.toString());
        System.out.println(rest.toString());


    }
}
