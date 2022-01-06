
import java.io.*;
import java.util.*;

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
    public void Savefile_History(String text) throws IOException {

        List<String> loadHis = loadFileHistory();
        int n= loadHis.size()-1;
        FileWriter fstream = new FileWriter (fileHistory);
        BufferedWriter info = new BufferedWriter(fstream);

        for(int i = 0; i < n; i++) {
            info.write(loadHis.get(i));
            info.write("\n");
        }
        info.write(text);
        info.write("\n");
        info.close();
    }
    public List<String> loadFileHistory () throws IOException {
        List <String> loadHis =  new ArrayList<String>();
        File file = new File(fileHistory);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        loadHis.add(line);
        while (line != null) {
            line = reader.readLine();
            loadHis.add(line);
        }
        reader.close();
        return loadHis;
    }
    public String[] search_with_slang_word(String key) throws IOException{
        Set<String>  keys = words.keySet();
        List <String> key_search = new ArrayList<String>();
        int i=0;

        for (String s : keys) {
                if (s.contains(key)){
                    key_search.add(s);
                    i++;
                }
        }
        String[] result= new String[i];
        result= key_search.toArray(new String[0]);
        Savefile_History(key);
        if (key_search == null) {
            return null;
        } else {
            return result;
        }

    }

    public  String [][] find_slang_word_with_keyword (String keyword) throws IOException {
        String result[][] = new String[size][3];
        Savefile_History(keyword);
        int temp=0;
        for (Map.Entry<String, List<String>> entry : words.entrySet()) {
            List<String> meanings = entry.getValue();
            for (String s : meanings) {
                if (s.toLowerCase().contains(keyword.toLowerCase())){
                    result[temp][0]=entry.getKey();
                    result[temp][1]=s;
                    String str=String.join(",", meanings);
                    result[temp][2]=str;
                    temp++;
                    break;
                }
            }
        }
        return result;
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
                    if (definition.equals(""))
                        definition=definition +k;
                    else
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

