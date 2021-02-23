package com.ranapplications.newsapp.global;

public class NameToPhoto {

    public static String nameToPhoto(String name){
        String one=name.substring(0,1);
        one=one.toLowerCase();

        if (one.equals("א") || one.equals("ל") || one.equals("a")){
            return "https://goo.gl/EqD6BJ";

        }else if(one.equals("ב") || one.equals("מ") || one.equals("b") || one.equals("m") || one.equals("w")){
            return "https://goo.gl/mdGkRn";

        }else if(one.equals("ג") || one.equals("נ") || one.equals("c") || one.equals("n") || one.equals("x")){
            return "https://goo.gl/1aDXgP";

        }else if(one.equals("ד") || one.equals("ס") || one.equals("d") || one.equals("o") || one.equals("y")){
            return "https://goo.gl/igeSXJ";

        }else if(one.equals("ה") || one.equals("ע") || one.equals("e") || one.equals("p") || one.equals("z")){
            return "https://goo.gl/bNMWzX";

        }else if(one.equals("ו") || one.equals("פ") || one.equals("f") || one.equals("q")){
            return "https://goo.gl/D3Qerq";

        }else if(one.equals("ז") || one.equals("צ") || one.equals("g") || one.equals("r")){
            return "https://goo.gl/Pm9uVf";

        }else if(one.equals("ח") || one.equals("ק") || one.equals("h") || one.equals("s")){
            return "https://goo.gl/ThDRkc";

        }else if(one.equals("ט") || one.equals("ר") || one.equals("i") || one.equals("t")){
            return "https://goo.gl/grV34C";

        }else if(one.equals("י") || one.equals("ש") || one.equals("j") || one.equals("u")){
            return "https://goo.gl/MHJ5Tn";

        }else if(one.equals("כ") || one.equals("ת") || one.equals("k") || one.equals("v")){
            return "https://goo.gl/g4nwRP";

        }else{
            return "https://goo.gl/ZjHeaj";
        }
    }
}
