import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//group
public class Group{

    public ArrayList<Section> group_of_sections_array = new ArrayList<Section>();
    public float group_gpa;
    public String group_name;

    public Group(String groups, Section[] sections) throws FileNotFoundException {
        System.out.println(sections[0].section_name);
        File tempFile = new File("src/" + groups);
        Scanner scan = new Scanner(tempFile);
        this.group_name = scan.nextLine();
        String tempLine = "";
        while (scan.hasNextLine()){
            tempLine = scan.nextLine();
                String[] str = tempLine.split("\\.");
                System.out.println(tempLine);
                System.out.println(str.length);
                for (int i = 0; i < sections.length; i++){
                    if (str[0].equals(sections[i].section_name))
                        group_of_sections_array.add(sections[i]);
                        System.out.println("Success");
                }  
        }
    }

    public void compare_section_to_group(){
        //code
    }
    public void compare_group(){
        //code
    }

}