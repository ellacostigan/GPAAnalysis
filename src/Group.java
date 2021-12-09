import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//group
public class Group{

    public ArrayList<Section> group_of_sections_array = new ArrayList<Section>();
    public double[] group_of_sections_gpa_array;
    public ArrayList<String> sectionsFromFile = new ArrayList<String>();
    public ArrayList<String> files_not_found = new ArrayList<String>();
    public double group_gpa;
    public String group_name;
    public String group;
    public static double GPA;
    public double groupDev;

    public Group(String groups, Section[] sections) throws FileNotFoundException {
        double tempAvg = 0;
        File tempFile = new File("src/" + groups);
        Scanner scan = new Scanner(tempFile);
        this.group_name = scan.nextLine();
        String tempLine = "";
        int counter = 0;
        while (scan.hasNextLine()){
            tempLine = scan.nextLine();
            String[] tempString3 = tempLine.split("\\.");
            sectionsFromFile.add(tempString3[0]);
            counter++;
        }
        scan.close();
                
        for (int i = 0; i < sectionsFromFile.size(); i++){
            boolean isPresent = false;
            for (int j = 0; j < sections.length; j++){
                    if (sectionsFromFile.get(i).equalsIgnoreCase(sections[j].section_name)){
                        sections[j].section_name = sections[j].section_name.toUpperCase();
                        group_of_sections_array.add(sections[j]);
                        j = sections.length;
                        isPresent = true;
                    }      
        
            }
            group_of_sections_gpa_array = new double[group_of_sections_array.size()];
            if (isPresent == false){
                files_not_found.add(sectionsFromFile.get(i));
            }
        }

        for (int averageCounter = 0; averageCounter < group_of_sections_array.size(); averageCounter ++){
            Section tempsection = group_of_sections_array.get(averageCounter);
            tempAvg += tempsection.section_average;
            group_of_sections_gpa_array[averageCounter] = tempsection.section_average;
        }
        group_gpa = tempAvg / group_of_sections_array.size();
        group_gpa = Math.round(group_gpa * 100.0) / 100.0;
        double tempNum = calculate_stdev(group_of_sections_gpa_array, group_gpa);
        groupDev = tempNum;
        for (int k = 0; k < group_of_sections_array.size(); k++){
            double temp_section_avg = group_of_sections_array.get(k).section_average;
            if (group_gpa < temp_section_avg){
                double tempNum2 = group_gpa + tempNum;
                if (tempNum2< temp_section_avg){
                    group_of_sections_array.get(k).isSignificant = true;
                    group_of_sections_array.get(k).significantDirection = "+";
                } else {
                    group_of_sections_array.get(k).isSignificant = false;
                    group_of_sections_array.get(k).significantDirection = ""; 
                }
            } else if (group_gpa > temp_section_avg){
                double tempNum3 = group_gpa - tempNum;
                if (tempNum3> temp_section_avg){
                    group_of_sections_array.get(k).isSignificant = true;
                    group_of_sections_array.get(k).significantDirection = "-";
                } else {
                    group_of_sections_array.get(k).isSignificant = false;
                    group_of_sections_array.get(k).significantDirection = "";
                }
            } else {
                group_of_sections_array.get(k).isSignificant = false;
                group_of_sections_array.get(k).significantDirection = " ";
            }
        }
    }    
    public Section compare_section_to_group(Section section){
        // need to update code here
        return section;

    }
    public Group(){
        this.group_name = "Empty Section";
    }
    public Group(String g, double gpa ){
        group =g;
        GPA = gpa;
    }
    public String getGroup(){
        return group;
    }
    public static double getGPA(){
        return GPA;
    }
    public int compareTo(Group g){
        return Double.valueOf(GPA).compareTo(Section.getGPA());
    }

    public double calculate_stdev(double n[],double avg){
       int tempSize = n.length;
       double sum=0;
       double first_sum = 0;
       double average = 0;

       for(int i = 0; i < n.length; i++) {
        first_sum = (sum += n[i]);
       }
       average = first_sum/(n.length);
       
       double tempSum=0;
       double finalTotal=0;
       double[] tempAverage = new double[n.length+1];

       for (int i = 0; i<n.length; i++){
        double fvalue = (Math.pow((n[i] - average), 2));
        tempAverage[i]= fvalue;
       }

       
       for (int j = 0; j < tempAverage.length; j++){
           finalTotal = (tempSum += tempAverage[j]);
       }

       Double AverageX = finalTotal/(n.length);
       return Math.sqrt(AverageX);
     }

}