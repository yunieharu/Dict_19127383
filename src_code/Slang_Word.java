
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Slang_Word {
    private TreeMap<String, List<String>> words = new TreeMap<>();
    private int size;
    private String filename = "slang_current.txt";
    private String fileHistory = "slang_history.txt";
    Slang_Word() throws IOException {
        System.out.println("Loading file slang_current.txt...");
        Load_file(filename);
        System.out.println("Loading successfully!");
    }
    public void Load_file(String filename) throws IOException {
        BufferedReader reader = null;
        String line = "";
        try{
            reader = new BufferedReader(new FileReader(filename));
            String s = reader.readLine();
            while((line = reader.readLine()) != null){
                List<String> meanings = new ArrayList<String>();
                if (line.contains("`")){
                    String[] str = line.split("`");
                    try{

                        if (str[1].contains("|"))
                        {
                            String[] split_mean= str[1].split("\\|");
                            int n = split_mean.length;
                            meanings = List.of(split_mean);
                            size=size+n;
                        }
                        else {
                            meanings.add(str[1]);
                            size = size +1;
                        }
                        words.put(str[0], meanings);
                    }
                    catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                }

            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
    }
    public List<String> search_with_slang_word(String key) throws IOException{
        List<String> search_key = words.get(key);
        FileWriter myWriter = new FileWriter("fileHistory");
        StringBuilder builder = new StringBuilder();
        if (search_key == null) {
            builder.append(key + "    |Can not find in Dictionary");
            builder.append('\n');
            myWriter.write(builder.toString());
            myWriter.close();
            System.out.println("Cant find");
            return null;
        } else {
            builder.append(key + "    |Found in Dictionary");
            builder.append('\n');
            myWriter.write(builder.toString());
            myWriter.close();
            System.out.println(search_key);
            return search_key;
        }

    }
}

