import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//group
public class Group{

public Section group_of_sections_array[];
    public float group_gpa;
    public String group_name;

    public Group(String groups, Section[] sections) throws FileNotFoundException {
        File tempFile = new File("src/" + groups);
        Scanner scan = new Scanner(tempFile);
        this.group_name = scan.nextLine();
        String tempLine = "";
        while (scan.hasNextLine()){
            tempLine = scan.nextLine();
            // Organizing groups
        }
    }

public void compare_section_to_group(){

}
public void compare_group(){
    
}

}