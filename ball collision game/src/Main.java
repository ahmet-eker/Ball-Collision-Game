import jdk.nashorn.internal.objects.Global;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static ArrayList<ArrayList<String>> board;
    static int cordx, cordy, point;
    static int lengthx, lengthy;
    static String[] moves;
    public static void main(String[] args) throws IOException {
        FileIO read_write_file = new FileIO();
        FileIO.writeToFile("output.txt","Game board",false,true);
        board = read_write_file.readFile(args[0],true,true);
        FileIO.board_writer();
        FileIO.writeToFile("output.txt","Your movement is:",true,true);


        lengthx = board.size();
        lengthy = board.get(0).size();
        moves = read_write_file.readMove(args[1]);
        int y = moves.length;
        for(int i = 0; i<moves.length; i++){
            if(!Motions.motions(moves[i])){
                y = i+1;
                break;
            }
        }

        for(int k = 0; k < y-1; k++){
            FileIO.writeToFile("output.txt", moves[k] + " ", true, false);
        }

        if(!Motions.hole) {
            FileIO.writeToFile("output.txt", moves[moves.length-1], true, false);
        }
        else
            FileIO.writeToFile("output.txt", moves[y-1], true, false);

        FileIO.writeToFile("output.txt", "\n\n", true, false);
        FileIO.writeToFile("output.txt","Your output is:",true,true);
        FileIO.board_writer();
        if(Motions.hole)
            FileIO.writeToFile("output.txt","Game Over!",true,true);
        FileIO.writeToFile("output.txt","Score: " + point,true,false);


    }
}