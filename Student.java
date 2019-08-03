import java.util.Scanner;
import java.util.Vector;

public class Student extends User {
    int schoolID;
    String classroom;

    public void show() {
        System.out.println("学号：" + this.schoolID + " 学生姓名：" + this.name + " 班级：" + classroom);
    }

    public Student() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----学生信息录入-----");
        System.out.println("请输入姓名：");
        String name = sc.nextLine();
        this.name = name;
        System.out.println("请输入密码：");
        this.setPassword(sc.nextLine());
        System.out.println("请输入学生学号：");
        int id = sc.nextInt();
        sc.nextLine();
        this.schoolID = id;
        System.out.println("请输入班级：");
        String classroom = sc.nextLine();
        this.classroom = classroom;
    }
    public Student(int schoolID,String name,String password,String classroom){
        this.schoolID=schoolID;
        this.setPassword(password);
        this.name=name;
        this.classroom=classroom;       
    }
    public Student(int schoolID,String name,String classroom){
        this.schoolID=schoolID;
        this.setPassword("123456");
        this.name=name;
        this.classroom=classroom;       
    }

/**
 * search the student
 * @return if existed:Student s,not:null
 */
    public static Student searchStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号：");
        int id = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < studentlist.size(); i++) {
            if (studentlist.elementAt(i).schoolID == id)
                return studentlist.get(i);
        }
        return null;
    }
/**
 * add a existed student to the studentlist
 */
    public static void addStudent(Student stu) {
        studentlist.add(stu);
        System.out.println("学号：" + stu.schoolID + " 录入成功！");
    }
/**
 * initialize a new student,and add him to the studentlist
 */
    public static void addStudent() {
        Student stu = new Student();
        studentlist.add(stu);
        System.out.println("学号：" + stu.schoolID + " 录入成功！");

    }
/**
 * delete a student in the studentlist
 * when opreated,the regeristered ID in each Course will be deleted too
 */
    public static void delStudent() {
        Student stu = searchStudent();
        if (stu == null) {
            System.out.println("该学生不存在！");
        } else {
            studentlist.remove(stu);
            for (int i = 0; i < Course.courselist.size(); i++) {
                for(int j=0;j<Course.courselist.elementAt(i).stulist.size();j++){
                    if (Course.courselist.elementAt(i).stulist.elementAt(j) == stu.schoolID) {
                        Course.courselist.elementAt(i).stulist.remove(stu.schoolID);
                }
                }
            }
            System.out.println("学号：" + stu.schoolID + " 删除成功！");
        }
    }
/**
 * show the list
 */
    public static void showStudentList() {
        for (int j = 0; j < studentlist.size(); j++) {
            studentlist.elementAt(j).show();
        }
    }

    public static int login() {
        Scanner sc = new Scanner(System.in);
        Student u = searchStudent();
        if (u == null) {
            System.out.println("学生学号不存在！");
            return 0;
        }
        System.out.println("请输入密码：");
        String pswd = sc.nextLine();
        if (u.getPassword().equals(pswd)) {
            return u.schoolID;
        } else
            return 0;
    }
/**
 * 重置学生的密码，默认为123456
 */
    protected static void resetStudentP() {
        System.out.println("重置学生的密码，恢复默认：123456");
        System.out.println("请输入学号：");
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < studentlist.size(); i++) {
            if (studentlist.elementAt(i).schoolID == id) {
                studentlist.elementAt(i).setPassword("123456");
                flag = true;
            }
        }
        if (flag == false)
            System.out.println("该学生不存在！");
    }

    /**
     * 这种搜索情况下已知列表中必定有该学号的学生
     * @param int id
     * @return Student obj
     */
    public static Student searchstu(int id){
        for (int i = 0; i < studentlist.size(); i++) {
            if (studentlist.elementAt(i).schoolID == id) {
                return studentlist.get(i);
            }
        }
        return null;
    }
    public static void chooseCourses(Student stu) {
        Vector<Integer> tmp = new Vector<Integer>();
        System.out.println("选修课清单：");
        for (int i = 0; i < Course.courselist.size(); i++) {
            if (Course.courselist.elementAt(i).type == 0) {
                Course.courselist.elementAt(i).show();
                tmp.add(Course.courselist.elementAt(i).id);
            }
        }
        if (tmp.size() == 0) {
            System.out.println("暂无选修课信息！");
            return;
        }
        System.out.println("输入要选择的课程编号：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        boolean flag = false;
        for (int i = 0; i < tmp.size(); i++) {
            if (id == tmp.elementAt(i)) {
                EleCourse tmpc=(EleCourse)Course.searchCourse(id);
                if(tmpc.stunum>=tmpc.MaxNum){
                    System.out.println("相应课程已经达到最大人数限制！");
                    return;    
                }
                tmpc.stulist.add(stu.schoolID);
                tmpc.stunum++;
                System.out.println("操作成功！");
                flag = true;
            }
        }
        if (flag == false)
            System.out.println("该课程不存在！");
    }
/**
 * show the courses which the student choose
 * @param int schoolID
 */
    public void ShowMyCourses(int schid) {
        System.out.println("必修课表：");
        System.out.println("---------------------");
        for (int i = 0; i < Course.courselist.size(); i++) {
            if (Course.courselist.elementAt(i).type == 1) {
                for (int j = 0; j < Course.courselist.elementAt(i).stulist.size(); j++) {
                    if (Course.courselist.elementAt(i).stulist.elementAt(j) == schid) {
                        Course.courselist.elementAt(i).show();
                    }
                }
            }
        }
        System.out.println("选修课表：");
        System.out.println("---------------------");
        for (int i = 0; i < Course.courselist.size(); i++) {
            if (Course.courselist.elementAt(i).type == 0) {
                for (int j = 0; j < Course.courselist.elementAt(i).stulist.size(); j++) {
                    if (Course.courselist.elementAt(i).stulist.elementAt(j) == schid) {
                        Course.courselist.elementAt(i).show();
                    }
                }
            }
        }
    }
}