import java.io.*;
import java.util.ArrayList;



public class GPA_Analysis {

    static Section[] sections = {};
    static Group[] groups = {};
    static FileWriter fileWriter = null;
    
    public static void main(String[]args) throws IOException{
      try{ 

        import_files();
        fileWriter = new FileWriter("GPA_Analysis_Report");
      
        for (int i = 0; i < groups.length; i++){
            fileWriter.write("Group:" + groups[i].group_name + " Group GPA: " + groups[i].group_gpa);
            fileWriter.write("\n");

            for (int j = 0; j < groups[i].group_of_sections_array.size(); j++){

                fileWriter.write("Section: " + groups[i].group_of_sections_array.get(j).section_name + " Section GPA: " + groups[i].group_of_sections_array.get(j).section_average);
                fileWriter.write("\n");

                for (int l = 0; l < groups[i].group_of_sections_array.get(j).student_id_array.length-1; l++){
                    fileWriter.write(groups[i].group_of_sections_array.get(j).student_id_array[l] + " ");
                    fileWriter.write(groups[i].group_of_sections_array.get(j).student_name_array[l] + " ");
                    fileWriter.write(groups[i].group_of_sections_array.get(j).student_gpa_array[l] + " ");
                    fileWriter.write(groups[i].group_of_sections_array.get(j).student_gpa_number_array[l] + " ");
                    fileWriter.write("\n");
                  }

            }
                fileWriter.write("\n");
                fileWriter.write("--------------------------");
                fileWriter.write("\n");
        }
            
            fileWriter.write("\n");
            fileWriter.write("==========================");  
        
    }catch(Exception e){
        e.printStackTrace();
    }finally{
        try{
            if(fileWriter != null){
                fileWriter.flush();
                fileWriter.close();
            }  
    }catch (IOException e){
        e.printStackTrace();
    }
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

}