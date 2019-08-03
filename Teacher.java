import java.util.Scanner;
import java.util.Vector;

public class Teacher extends User {
    int workID;
    String level;

    public void show() {
        System.out.println("工号：" + this.workID + " 教师姓名：" + this.name + " 职称：" + level);
    }

    public Teacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----教师信息录入-----");
        System.out.println("请输入姓名：");
        String name = sc.nextLine();
        this.name = name;
        System.out.println("请输入密码：");
        this.setPassword(sc.nextLine());
        System.out.println("请输入工号：");
        int id = sc.nextInt();
        sc.nextLine();
        this.workID = id;
        System.out.println("请输入职称：");
        String level = sc.nextLine();
        this.level = level;
    }

    public Teacher(int workID, String name, String password, String level) {
        this.workID = workID;
        this.name = name;
        this.setPassword(password);
        this.level = level;
    }

    public static Teacher searchtcher(int wkid) {
        for (int i = 0; i < teacherlist.size(); i++) {
            if (teacherlist.elementAt(i).workID == wkid)
                return teacherlist.get(i);
        }
        return null;
    }

    /**
     * 输入教师工号，返回教师信息
     * 
     * @return Teacher
     */
    public static Teacher searchTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入工号：");
        int id = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < teacherlist.size(); i++) {
            if (teacherlist.elementAt(i).workID == id)
                return teacherlist.get(i);
        }
        return null;
    }

    public static void addTeacher(Teacher t) {
        teacherlist.add(t);
        System.out.println("工号：" + t.workID + " 录入成功！");
    }

    public static void addTeacher() {
        Teacher t = new Teacher();
        teacherlist.add(t);
        System.out.println("工号：" + t.workID + " 录入成功！");
    }

    public static void delTeacher() {
        Teacher t = searchTeacher();
        if (t == null) {
            System.out.println("该教师不存在！");
        } else {
            teacherlist.remove(t);
            System.out.println("工号：" + t.workID + " 删除成功！");
        }
    }

    public static void showTeacherList() {
        for (int i = 0; i < teacherlist.size(); i++) {
            teacherlist.elementAt(i).show();
        }
    }

    public static int login() {
        Scanner sc = new Scanner(System.in);
        Teacher t = searchTeacher();
        if (t == null) {
            System.out.println("教师工号不存在！");
            return 0;
        }
        System.out.println("请输入密码：");
        String pswd = sc.nextLine();
        if (t.getPassword().equals(pswd))
            return t.workID;
        else
            return 0;
    }

    public static void resetTeacherP() {
        Scanner sc = new Scanner(System.in);
        System.out.println("重置老师的密码，恢复默认：123456");
        System.out.println("请输入工号：");
        int id = sc.nextInt();
        sc.nextLine();
        boolean flag = false;
        for (int i = 0; i < teacherlist.size(); i++) {
            if (teacherlist.elementAt(i).workID == id) {
                teacherlist.elementAt(i).setPassword("123456");
                flag = true;
            }
        }
        if (flag == false)
            System.out.println("该老师不存在！");
    }

    public void ShowMyClasses() {
        System.out.println("教师课表：");
        System.out.println("---------------");
        for (int i = 0; i < Course.courselist.size(); i++) {
            if (Course.courselist.elementAt(i).teachername.equals(this.name)) {
                Course.courselist.elementAt(i).show();
            }
        }
    }

    public static void showClassStu() {
        System.out.println("请输入要查询的课程编号:");
        Scanner sc = new Scanner(System.in);
        int cid = sc.nextInt();
        sc.nextLine();
        Course c = Course.searchCourse(cid);
        if (c == null) {
            System.out.println("该课程不存在！");
        } else {
            c.show();
            System.out.println("学生信息：");
            for (int i = 0; i < c.stulist.size(); i++) {
                Student s = Student.searchstu(c.stulist.get(i));
                s.show();
            }
            System.out.println("-------------");
        }
    }

}