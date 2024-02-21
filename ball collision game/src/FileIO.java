import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileIO {
    /**
     * Reads the file at the given path and returns contents of it in a string array.
     *
     * @param path              Path to the file that is going to be read.
     * @param discardEmptyLines If true, discards empty lines with respect to trim; else, it takes all the lines from the file.
     * @param trim              Trim status; if true, trims (strip in Python) each line; else, it leaves each line as-is.
     * @return Contents of the file as a string array, returns null if there is not such a file or this program does not have sufficient permissions to read that file.
     */

    static PrintStream ps = null;

    public ArrayList<ArrayList<String>> readFile(String path, boolean discardEmptyLines, boolean trim) {
        try{
            ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[length];
            for(String line : Files.readAllLines(Paths.get(path))){
                results[i++] = line;
            }

            for(String line : results) {
                String[] strsplit = line.split(" ");
                ArrayList<String> row = new ArrayList<String>(Arrays.asList(strsplit));
                table.add(row);
            }

            for(int t = 0; t < table.size();t++){
                for(int j = 0; j< table.get(0).size();j++){
                    if(table.get(t).get(j).equals("*")){
                        Main.cordx = t;
                        Main.cordy = j;
                    }
                }
            }

            return table;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] readMove(String path) throws IOException {
        int length = Files.readAllLines(Paths.get(path)).size();
        String[] results = new String[length];
        List<String> line = Files.readAllLines(Paths.get(path));

        return line.get(0).split(" ");
    }


    /**
     * This function writes given content to file at given path.
     *
     * @param path    Path for the file content is going to be written.
     * @param content Content that is going to be written to file.
     * @param append  Append status, true if wanted to append to file if it exists, false if wanted to create file from zero.
     * @param newLine True if wanted to append a new line after content, false if vice versa.
     */
    public static void writeToFile(String path, String content, boolean append, boolean newLine) {
        try {
            ps = new PrintStream(new FileOutputStream(path, append));
            ps.print(content + (newLine ? "\n" : ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) { //Flushes all the content and closes the stream if it has been successfully created.
                ps.flush();
                ps.close();
            }
        }
    }

    public static void board_writer(){
        for(int i = 0; i < Main.board.size() ; i++){
            for(int j = 0; j < Main.board.get(0).size()-1; j++){
                writeToFile("output.txt",Main.board.get(i).get(j) + " ",true, false);
            }
            writeToFile("output.txt",Main.board.get(i).get(Main.board.get(0).size()-1),true, false);

            writeToFile("output.txt","",true,true);
        }
        writeToFile("output.txt","",true,true);
    }
}