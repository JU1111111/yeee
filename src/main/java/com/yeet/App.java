package com.yeet;
public class App {
    public static void main(String[] args) throws Exception {
        new GUI();
        //testing();
    }
    public static void testing() {
        Vokabeltest voc = new Vokabeltest();
        voc.addVoc("bean","BEEEEEANZ");
        voc.addVoc("bruh","reeee");
        voc.addVoc("bbbbb","Yeeeeeeet");


        for (int i = 0; i < voc.Vokabelliste.getLength()-1; i++){
            VokabelWort V = (VokabelWort) voc.Vokabelliste.getItem(i);
            System.out.println(V.word);
        }

        voc.shuffleVocList();
        System.out.println(voc.Vokabelliste.getLength());

        for (int i = 0; i < voc.Vokabelliste.getLength(); i++){
            VokabelWort V = (VokabelWort) voc.Vokabelliste.getItem(i);
            System.out.println(V.word);
        }
           
    }
}
