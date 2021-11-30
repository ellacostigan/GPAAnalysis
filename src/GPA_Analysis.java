import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class GPA_Analysis {

    static Section[] sections = {};
    static Group[] groups = {};
    public static void main(String[]args) throws FileNotFoundException{
       import_files();
    
       System.out.println(groups[1].group_name);
       //Should be Computer Science Program Group
       System.out.println(sections[0].section_name);
       //Should be COMSC234_01
       System.out.println(sections[1].student_id_array[0]);
       //Should be kaobzsm946
    }

    public static void import_files() throws FileNotFoundException{
        //File file = new File("src/AllGroups.txt");
        //Scanner sc = new Scanner(file);
        
        //while (sc.hasNextLine())
        //    System.out.println(sc.nextLine());
        

        ArrayList<String> listOfSections = new ArrayList<String>();
        ArrayList<String> listOfGroups = new ArrayList<String>();
        ArrayList<String> allGroupsList = new ArrayList<String>();

        String[] files;

        File directory = new File("src");

        files = directory.list();
        
        //System.out.println("Files in SRC \"" + directory + "\":");

        for (int i = 0; i < files.length; i++) {
            String tempFileName = files[i];
            File tempFile = new File(files[i]);
            String fileExtension = tempFile.toString();
            int fileExtensionCount = fileExtension.lastIndexOf(".");
            String extension = "";

            if(fileExtensionCount > 0) {
                extension = fileExtension.substring(fileExtensionCount + 1);
                //System.out.println("File extension is " + extension);
            }

            if (extension.equalsIgnoreCase("sec")) {
                listOfSections.add(tempFileName);
            } else if (extension.equalsIgnoreCase("grp")) {
                listOfGroups.add(tempFileName);
            } else if (extension.equalsIgnoreCase("txt")) {
                allGroupsList.add(tempFileName);
            }
            
            
        }
            
        sections = new Section[listOfSections.size()];
        groups = new Group[listOfGroups.size()];
            
            for (int j = 0; j < listOfSections.size(); j++){
                sections[j] = new Section(listOfSections.get(j));
            }
            for (int k = 0; k < listOfGroups.size(); k++){
                groups[k] = new Group(listOfGroups.get(k), sections);
            }
    }
    public void create_report(){
    
    }
    
    public void export_file(){
    
    
    }

}