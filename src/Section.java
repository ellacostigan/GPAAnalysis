import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//section
public class Section{
    public String student_name_array[];
    public String student_id_array[];
    public String student_gpa_array[];
    public String section_name;
    public String section_professor;
    public float section_average;
    public String grade;
    private double gradeNum;


    public Section(String section) throws FileNotFoundException {
        
        File tempFile = new File("src/" + section);
        Scanner scan = new Scanner(tempFile);
        String topLine = scan.nextLine();
        String topLineArray[] = topLine.split(" ");
        this.section_name = topLineArray[0];
            
            String tempLine = "";
            Scanner tempScanner = new Scanner(tempFile);
            int count = 0;
            while (tempScanner.hasNextLine()) {
                count++;
                tempScanner.nextLine();
            }
            student_name_array = new String[count];
            student_id_array = new String[count];
            student_gpa_array = new String[count];

            int counter = 0;
            while (scan.hasNextLine() && counter < count){
                tempLine = scan.nextLine();
                String[] lineArray;

                int third_count = 0;
                for(int i=0; i < tempLine.length(); i++)
                {    if(tempLine.charAt(i) == ',');
                        third_count++;
                }
                    lineArray = tempLine.split(",");
                    student_id_array[counter] = lineArray[0];
                    //student_name_array[counter] = lineArray[1] + lineArray[2];
                    //student_gpa_array[counter] = lineArray[3];
                counter += 1;
            }
    }
    
    public void build_student_arrays(){

    }
    public void calculate_average(){

    }
    public void compare_section(){

    }
    public void construct_section(String showGrade){
        grade = showGrade;
        gradeNum=0;
    }
    public double get_num_grade(){
        String sym;
        sym = grade.substring(1);
        if(sym.isEmpty()){
            return gradeNum;
        }else if(gradeNum == 0){
            return gradeNum;
        }
        if(sym.equals("+") && gradeNum != 4.0){
            gradeNum = gradeNum + 0.3;
        }else if(sym.equals("-")){
            gradeNum = - 0.3;
        }

        String letterGrade = grade.substring(0);

        if(letterGrade.equalsIgnoreCase("A")) {
            gradeNum = 4.0;
        } else if(letterGrade.equalsIgnoreCase("B")) {
            gradeNum = 3.0;
        } else if(letterGrade.equalsIgnoreCase("C")) {
            gradeNum = 2.0;
        } else if(letterGrade.equalsIgnoreCase("D")) {
            gradeNum = 1.0;
        } else if(letterGrade.equalsIgnoreCase("F")) {
            gradeNum = 0.0;
        } else {
            System.out.println("Invalid letter grade");
        }

        return gradeNum;
    }

    

    }

