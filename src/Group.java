import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//group
public class Group{

    public ArrayList<Section> group_of_sections_array = new ArrayList<Section>();
    public ArrayList<String> sectionsFromFile = new ArrayList<String>();
    public ArrayList<String> files_not_found = new ArrayList<String>();
    public double group_gpa;
    public String group_name;

    public Group(String groups, Section[] sections) throws FileNotFoundException {
        double tempAvg = 0;
        File tempFile = new File("src/" + groups);
        Scanner scan = new Scanner(tempFile);
        this.group_name = scan.nextLine();
        String tempLine = "";
        //System.out.println(group_name);
        int counter = 0;
        while (scan.hasNextLine()){
            tempLine = scan.nextLine();
            String[] tempString3 = tempLine.split("\\.");
            sectionsFromFile.add(tempString3[0]);
            counter++;
        }

        scan.close();
                
        for (int i = 0; i < sectionsFromFile.size(); i++){
            //System.out.println(sectionsFromFile.get(i).toString());
            boolean isPresent = false;
            for (int j = 0; j < sections.length; j++){
                //System.out.println(sections[j].section_name + " " + sectionsFromFile.get(i) + i);
                    if (sectionsFromFile.get(i).equalsIgnoreCase(sections[j].section_name)){
                        sections[j].section_name = sections[j].section_name.toUpperCase();
                        group_of_sections_array.add(sections[j]);
                        j = sections.length;
                        isPresent = true;
                    }      
        
            }
            if (isPresent == false){
                files_not_found.add(sectionsFromFile.get(i));
            }
        }
        for (int averageCounter = 0; averageCounter < group_of_sections_array.size(); averageCounter ++){
            Section tempsection = group_of_sections_array.get(averageCounter);
            tempAvg += tempsection.section_average;
        }
        group_gpa = tempAvg / group_of_sections_array.size();
        group_gpa = Math.round(group_gpa * 100.0) / 100.0;
    }    
    public void compare_section_to_group(Section section){
        //code
    }
    public void compare_group(){
        //code
    }

}