import java.io.*;
import java.util.ArrayList;

public class GPA_Analysis {

    static Section[] sections = {};
    static Group[] groups = {};
 
    
    public static void main(String[]args) throws IOException{
       import_files();
    
       //System.out.println(groups[1].group_name);
       //Should be Computer Science Program Group
       //System.out.println(sections[0].section_name);
       //Should be COMSC234_01
       //System.out.println(sections[3].student_id_array[0]);
       //Should be qaqqottfsm014
       //System.out.println(groups[0].group_of_sections_array.get(0).section_name);       
       //Should be COMSC330_02
       //System.out.println(sections[3].student_gpa_array[0]);

        for (int i = 0; i < groups.length; i++){
            System.out.println("Group:" + groups[i].group_name + " Group GPA: " + groups[i].group_gpa);
            System.out.println();
            for (int j = 0; j < groups[i].group_of_sections_array.size(); j++){
                System.out.println("Section: " + groups[i].group_of_sections_array.get(j).section_name + " Section GPA: " + groups[i].group_of_sections_array.get(j).section_average);
                System.out.println();
                for (int l = 0; l < groups[i].group_of_sections_array.get(j).student_id_array.length-1; l++){
                    System.out.print(groups[i].group_of_sections_array.get(j).student_id_array[l] + " ");
                    System.out.print(groups[i].group_of_sections_array.get(j).student_name_array[l] + " ");
                    System.out.print(groups[i].group_of_sections_array.get(j).student_gpa_array[l] + " ");
                    System.out.print(groups[i].group_of_sections_array.get(j).student_gpa_number_array[l] + " ");
                    System.out.println();
                }
                System.out.println();
                System.out.println("--------------------------");
                System.out.println();
            }
            for (int k = 0; k < groups[i].files_not_found.size(); k++){
                System.out.println("*" + groups[i].files_not_found.get(k));
            }
            
            System.out.println();
            System.out.println("==========================");  
        }
    }

    public static void import_files() throws FileNotFoundException{
        
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