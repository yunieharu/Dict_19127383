
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Slang_Word {
    private TreeMap<String, List<String>> words = new TreeMap<>();
    private int size;
    private String filename = "slang_current.txt";
    private String fileHistory = "slang_history.txt";
    private String fileRoot = "slang.txt";
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
                        }
                        else {
                            meanings.add(str[1]);

                        }
                        size = size +1;
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
        FileWriter myWriter = new FileWriter(fileHistory);
        StringBuilder builder = new StringBuilder();
        File file = new File(fileHistory);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        builder.append(line);
        while (line != null) {
                line = reader.readLine();
                builder.append(line);
            }

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
    public  TreeMap<String,String> find_slang_word_with_keyword (String keyword){
        TreeMap<String,String> search_defi = new TreeMap<>();

        for (Map.Entry<String, List<String>> entry : words.entrySet()) {
            List<String> meanings = entry.getValue();
            for (String s : meanings) {
                if (s.toLowerCase().contains(keyword.toLowerCase())){
                  search_defi.put(entry.getKey(),s);
                  System.out.println(entry.getKey()+"    :"+ s);
                }
            }
        }
        return search_defi;
    }

    public void Add_slang_word(String word, String meaning){
        String[] split_mean= meaning.split("\\|");
        int n = split_mean.length;
        List<String> mean_1 = List.of(split_mean);
        size = size+1;
        words.put(word, mean_1);
    }
    public void Overwrite_word(String word, String meaning){
        String[] split_mean= meaning.split("\\|");
        List<String> mean_1 = List.of(split_mean);
        List<String> search_key = words.get(word);
        words.put(word, mean_1);
    }
    public void Duplicate_word(String word, String meaning){
        String[] split_mean= meaning.split("\\|");
        int n = split_mean.length;
        List<String> search_key = words.get(word);
        for (int i=0; i<n; i++){
            search_key.add(split_mean[i]);
        }
        words.put(word, search_key);
    }
    public void Edit_slang_word(String word, String meaning){
        Overwrite_word(word,meaning);
    }
    public void Delete_slang_word(String word){
        words.remove(word);
    }
    public boolean check(String word) {
        for (String s : words.keySet()) {
            if (s.equals(word))
                return true;
        }
        return false;
    }
    public String[] Random_word(){
        String[] ran_word = new String[2];
        int min = 0;
        int max = size-1;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        int i=0;
        for (String s: words.keySet()){
            if (i==random_int){
                ran_word[0] = s;
                List<String> mean = words.get(s);
                String definition = "";
                for (String k: mean){
                    definition=definition+" | " + k;
                }
                ran_word[1]=definition;
                break;
            }
            i++;
        }
        return ran_word;
    }
}

