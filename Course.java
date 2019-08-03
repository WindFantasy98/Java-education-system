import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    public static Vector<Course> courselist = new Vector<Course>();
    public Vector<Integer> stulist = new Vector<Integer>();
    public int id;
    public int type;// 课程性质：0.选修1.必修
    public String teachername;
    public String coursename;
    public int stunum;

    public Course() {
    }

    public Course(int id, String coursename, String teachername, int type, int stunum) {
        this.id = id;
        this.coursename = coursename;
        this.teachername = teachername;
        this.type = type;
        this.stunum = stunum;
    }

    /**
     * 设置课程信息
     * 
     * @return
     */
    public static Course setCourse() {
        Course c = new Course();
        System.out.println("请输入课程编号：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("请输入课程名称：");
        String coursename = sc.nextLine();
        System.out.println("请输入授课教师姓名：");
        String teachername = sc.nextLine();
        System.out.println("请输入课程性质：");
        int type = sc.nextInt();
        if (type == 1) {
            for (int i = 0; i < Student.studentlist.size(); i++) {
                c.stulist.add(Student.studentlist.elementAt(i).schoolID);
            }
        }
        System.out.println("请输入授课人数：");
        int stunum = sc.nextInt();
        sc.nextLine();
        if (type == 0) {
            System.out.println("请输入选课最大人数：");
            int maxnum = sc.nextInt();
            sc.nextLine();
            c = new EleCourse(id, coursename, teachername, type, stunum, maxnum);
        } else {
            System.out.println("请输入学分数：");
            int credits = sc.nextInt();
            sc.nextLine();
            c = new ChoCourse(id, coursename, teachername, type, stunum, credits);
        }
        return c;
    }

    /**
     * 设置任课教师
     */
    public static void setTeacher() {
        System.out.println("请输入课程编号：");
        Scanner sc = new Scanner(System.in);
        int chgid = sc.nextInt();
        sc.nextLine();
        boolean flag1 = false, flag2 = false;
        for (int i = 0; i < courselist.size(); i++) {
            if (courselist.elementAt(i).id == chgid) {
                flag2 = true;
                System.out.println("请输入任课老师工号：");
                int tid = sc.nextInt();
                sc.nextLine();
                for (int j = 0; j < User.teacherlist.size(); j++) {

                    if (tid == Teacher.teacherlist.elementAt(j).workID) {
                        flag1 = true;
                        Teacher teacher = Teacher.teacherlist.get(j);
                        courselist.elementAt(i).teachername = teacher.name;
                        System.out.println("操作成功！ " + teacher.name + " 将教授 " + courselist.elementAt(i).coursename);
                    }
                }
                if (flag1 == false)
                    System.out.println("该老师不存在，请检查！");
            }
        }
        if (flag2 == false)
            System.out.println("该课程不存在，请检查！");
    }

    public static void delCourse() {
        System.out.println("请输入需要删除的课程编号：");
        Scanner sc = new Scanner(System.in);
        int delid = sc.nextInt();
        boolean flag = false;
        for (int i = 0; i < courselist.size(); i++) {
            if (courselist.elementAt(i).id == delid) {
                courselist.remove(courselist.elementAt(i));
                System.out.println("课号" + delid + " 删除完成！");
                flag = true;
            }
        }
        if (flag == false)
            System.out.println("课程编号不存在，请检查！");
    }

    public static void addCourses() {
        System.out.println("请输入需要录入课程的数量：");
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        s.nextLine();
        for (int i = 0; i < num; i++) {
            Course c = new Course();
            c = setCourse();
            addCourseList(c);
        }

    }

    /**
     * 把一门已经存在的课程加入课表
     * 
     * @param course
     */
    public static void addCourseList(Course course) {
        courselist.add(course);
        System.out.println("课程" + course.coursename + " 编号" + course.id + "录入成功！");
    }

    /**
     * 展示课程信息
     */
    public void show() {
        System.out.println("课程编号：" + this.id);
        System.out.println("课程名称：" + this.coursename);
        System.out.println("任课教师：" + this.teachername);
        System.out.println("课程性质：" + this.type);
        System.out.println("选课人数：" + this.stunum);
    }

    /**
     * 展示课表
     */
    public static void showCourseList() {
        System.out.println("课表信息");
        for (int i = 0; i < courselist.size(); i++) {
            ((Course) courselist.get(i)).show();
        }

    }

    /**
     * 根据选课人数进行课程排序
     */
    public static void SortbyStunum() {
        Collections.sort(courselist, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                if (o1.stunum > o2.stunum)
                    return 1;
                if (o1.stunum < o2.stunum)
                    return -1;
                return 0;
            }
        });
    }

    /**
     * 根据课程编号进行排序
     */
    public static void SortbyID() {
        Collections.sort(courselist, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                if (o1.id > o2.id)
                    return 1;
                if (o1.id < o2.id)
                    return -1;
                return 0;
            }
        });
    }

    /**
     * 搜索老师的信息
     */
    public static void SearchTeacher() {
        System.out.println("请输入要查询的老师的姓名：");
        Scanner sc2 = new Scanner(System.in);
        String name = sc2.nextLine();
        boolean flag = false;
        System.out.println(name + "的教授课程：");
        for (int i = 0; i < courselist.size(); i++) {
            if (name.equals(courselist.elementAt(i).teachername)) {
                System.out.println(courselist.elementAt(i).coursename);
                flag = true;
            }
        }
        if (flag == false)
            System.out.println("Error:暂无相关信息");
    }

    /**
     * 寻找课程
     * 
     * @param int courseID
     * @return if existed:Course c else:null
     */
    public static Course searchCourse(int cid) {
        for (int i = 0; i < courselist.size(); i++) {
            if (courselist.elementAt(i).id == cid)
                return courselist.get(i);
        }
        return null;
    }

    /**
     * 更新必修课表
     */
    public static void UpdateChoC() {
        for (int i = 0; i < courselist.size(); i++) {
            if (courselist.elementAt(i).type == 1) {
                courselist.elementAt(i).stulist.clear();
                for (int j = 0; j < Student.studentlist.size(); j++) {
                    courselist.elementAt(i).stulist.add(Student.studentlist.elementAt(j).schoolID);
                }
                courselist.elementAt(i).stunum = Student.studentlist.size();
            }
        }
    }

    public static void SaveCourse() {
        try {
            File fo = new File("Course");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fo));
            oos.writeObject(Course.courselist);
            oos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void ReadCourse() {
        try {
            File fi = new File("Course");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fi));
            Course.courselist = (Vector<Course>) ois.readObject();
            ois.close();
        } catch (Exception e) {
        }
    }
}