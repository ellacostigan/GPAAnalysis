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
                String tempString = "";
                if (groups[i].group_of_sections_array.get(j).isSignificant == true){
                    if (groups[i].group_of_sections_array.get(j).significantDirection.equals("+")){
                        tempString = " | Significantly Greater than " + groups[i].group_gpa;
                    } else if (groups[i].group_of_sections_array.get(j).significantDirection.equals("-")){
                        tempString = " | Significantly Less than " + groups[i].group_gpa;
                    }
                } else {
                    tempString = " | No Significance";
                }
                fileWriter.write("Section: " + groups[i].group_of_sections_array.get(j).section_name + " Section GPA: " + groups[i].group_of_sections_array.get(j).section_average + " " + tempString);
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

    public static void import_files() throws FileNotFoundException{ // Method that imports all the group
        
        ArrayList<String> listOfSections = new ArrayList<String>(); //ArrayLists are used to store the groups and sections pulled from the directory
        ArrayList<String> listOfGroups = new ArrayList<String>();
        ArrayList<String> allGroupsList = new ArrayList<String>();

        String[] files; 

        File directory = new File("src");

        files = directory.list(); // grabs all files within the src directory of the program
        
        for (int i = 0; i < files.length; i++) { //loop through each file in the dir, determine whether it is a group, section or txt file and adds it to its respected array
            
            String tempFileName = files[i]; //grabs the file name and creates a file
            File tempFile = new File(files[i]);
            String fileExtension = tempFile.toString(); //grabs the file extension by splitting over 
            int fileExtensionCount = fileExtension.lastIndexOf(".");
            String extension = "";

            if(fileExtensionCount > 0) { // Makes sure there is actually an extension being grabbed
                extension = fileExtension.substring(fileExtensionCount + 1);
            }

            if (extension.equalsIgnoreCase("sec")) { // takes the file extension grabbed and checks whether it is an sec, grp, or txt
                listOfSections.add(tempFileName);
            } else if (extension.equalsIgnoreCase("grp")) {
                listOfGroups.add(tempFileName);
            } else if (extension.equalsIgnoreCase("txt")) {
                allGroupsList.add(tempFileName);
            }
            
        }
            sections = new Section[listOfSections.size()]; //converts arrayLists into a static and better to work with datatype.
            groups = new Group[listOfGroups.size()];
            
            for (int j = 0; j < listOfSections.size(); j++){ // loops through the arrayList of imported Sections and creates them as objects using the Section Class.
                sections[j] = new Section(listOfSections.get(j));
            }
            for (int k = 0; k < listOfGroups.size(); k++){ // loops through the arrayList of imported Groups and creates them as objects using the Group Class.
                groups[k] = new Group(listOfGroups.get(k), sections);
            }
           
    }

}