import java.util.ArrayList;

public class Motions {
    static boolean hole;
    public static void swap(String color, int newx, int newy){
        Main.board.get(Main.cordx).set(Main.cordy,color);
        Main.board.get(newx).set(newy, "*");
        if (color.equals("R")) {
            Main.point += 10;
            Main.board.get(Main.cordx).set(Main.cordy, "X");
        }
        else if (color.equals("Y")) {
            Main.point += 5;
            Main.board.get(Main.cordx).set(Main.cordy, "X");
        }
        else if (color.equals("B")) {
            Main.point -= 5;
            Main.board.get(Main.cordx).set(Main.cordy, "X");
        }

    }
    public static boolean motions(String direction){
        int newx = Main.cordx;
        int newy = Main.cordy;
        if(direction.equals("L")){
            if(Main.cordy != 0 && !Main.board.get(newx).get((newy-1+Main.lengthy)%Main.lengthy).equals("W"))
                newy = (Main.cordy - 1+Main.lengthy)%Main.lengthy;
            else if(!Main.board.get(newx).get((newy-1+Main.lengthy)%Main.lengthy).equals("W"))
                newy = Main.lengthy - 1;
            else
                newy=(Main.cordy+1)%Main.lengthy;
        }

        else if(direction.equals("R")){
            if(Main.cordy != Main.lengthy - 1 && !Main.board.get(newx).get((newy+1)%Main.lengthy).equals("W"))
                newy = (Main.cordy + 1)%Main.lengthy;
            else if(!Main.board.get(newx).get((newy+1)%Main.lengthy).equals("W"))
                newy = 0;
            else
                newy=(Main.cordy-1+Main.lengthy)%Main.lengthy;
        }

        else if(direction.equals("D")){
            if(Main.cordy != Main.lengthy - 1 && !Main.board.get((newx+1)%Main.lengthx).get(newy).equals("W"))
                newx = (Main.cordx + 1)%Main.lengthx;
            else if(!Main.board.get((newx+1)%Main.lengthx).get(newy).equals("W"))
                newx = 0;
            else
                newx = (Main.cordx - 1 + Main.lengthx)%Main.lengthx;
        }

        else if(direction.equals("U")){
            if(Main.cordy != 0&& !Main.board.get((newx-1+Main.lengthx)%Main.lengthx).get(newy).equals("W"))
                newx = (Main.cordx-1+Main.lengthx)%Main.lengthx;
            else if(!Main.board.get((newx-1+Main.lengthx)%Main.lengthx).get(newy).equals("W"))
                newx = Main.lengthx-1;
            else
                newx = (Main.cordx + 1) % Main.lengthx;
        }


        if(!value(newx,newy)){
            return false;
        }
        Main.cordx = newx;
        Main.cordy = newy;
        return true;
    }


    public static boolean value(int newx,int newy){

        if (Main.board.get(newx).get(newy).equals("R")){ // +10
            swap("R",newx,newy);
        }
        else if (Main.board.get(newx).get(newy).equals("Y")){ // +5
            swap("Y",newx,newy);
        }
        else if (Main.board.get(newx).get(newy).equals("B")){ // -5
            swap("B",newx,newy);
        }
        else if (Main.board.get(newx).get(newy).equals("H")){
            //Main.board.get(newx).set(newy," ");
            Main.board.get(Main.cordx).set(Main.cordy," ");
            hole = true;
            return false;
        }
        else if (Main.board.get(newx).get(newy).equals("W")){
            swap(Main.board.get(Main.cordx).get(Main.cordy),newx,newy);
        }
        else {
            swap(Main.board.get(newx).get(newy),newx,newy);
        }
        return true;
    }
}
