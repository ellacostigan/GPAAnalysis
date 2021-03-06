import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//section
public class Section implements Comparable<Section> {
    public String student_name_array[];
    public String student_id_array[];
    public String student_gpa_array[];
    public double student_gpa_number_array[];
    public String section_name;
    public String section_professor;
    public double section_average;
    public double section_stdev;
    public static int tempSize =0;
    public String section;
    public static double GPA;
    public boolean isSignificant;
    public String significantDirection;

    public Section(String section) throws FileNotFoundException {
        section_average = 0.0;
        
        File tempFile = new File("src/" + section);
        Scanner scan = new Scanner(tempFile);
        String topLine = scan.nextLine();
        String topLineArray[] = topLine.split(" ");
        this.section_name = topLineArray[0].replaceAll("\\.","_");
            
            String tempLine = "";
            Scanner tempScanner = new Scanner(tempFile);
            int count = 0;
            while (tempScanner.hasNextLine()) {
                count++;
                tempScanner.nextLine();
            }
            tempScanner.close();
            
            student_name_array = new String[count];
            student_id_array = new String[count];
            student_gpa_array = new String[count];
            student_gpa_number_array = new double[count];

            int counter = 0;
            while (scan.hasNextLine() && counter < count){
                tempLine = scan.nextLine();
                String[] lineArray;

                    lineArray = tempLine.split("\"");

                    if (lineArray[0].charAt(lineArray[0].length()-1) == ','){
                      student_id_array[counter] = lineArray[0].substring(0, lineArray[0].length() - 1);
                    } else {
                      student_id_array[counter] = lineArray[0].replaceAll("\\s", "");
                    }
                    if (lineArray[2].charAt(0) == ','){
                      student_gpa_array[counter] = lineArray[2].substring(1);
                    }
                    else {
                      student_gpa_array[counter] = lineArray[2].replaceAll("\\s", "");
                    }
                    student_name_array[counter] = lineArray[1];
                counter += 1;
            }
            scan.close();

            tempSize = student_gpa_array.length;

            for (int i = 0; i < student_gpa_array.length-1; i++){
                student_gpa_number_array[i] = get_num_grade(student_gpa_array[i]);
            }
            section_average = Math.round(calculate_average() * 100.0) / 100.0;
           
            
            
    }
    public Section(){
        this.section_name = "Empty Section";
    }
    public Section(String s, double gpa ){
        section = s;
        GPA = gpa;
    }
    public String getSection(){
        return section;
    }
    public static double getGPA(){
        return GPA;
    }


    public double calculate_average(){
        double avg = 0;
        for (int i = 0; i < student_gpa_array.length; i++){
            avg += student_gpa_number_array[i];
        }
        avg /= student_gpa_array.length-1;
        return avg + 0.0;
    }
    

   public int compareTo(Section s){
    return Double.valueOf(GPA).compareTo(Section.getGPA());
    }

    public double get_num_grade(String inputGrade){
        double gradeNum=0;
        
        String letterGrade = inputGrade.substring(0,1);

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

        String sym = "";
        if (inputGrade.length() > 1){
            sym = inputGrade.substring(1);
            if(sym.isEmpty()){
                return gradeNum;
            }else if(gradeNum == 0){
                return gradeNum;
            }
            if(sym.equals("+") && gradeNum != 4.0){
                gradeNum += 0.3;
            }else if(sym.equals("-")){
                gradeNum -= 0.3;
            }
        }

        return gradeNum;
    }
}

