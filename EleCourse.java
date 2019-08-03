import java.util.Scanner;
import java.util.Vector;

public class EleCourse extends Course {
    int MaxNum;

    public EleCourse(int id,String coursename,String teachername,int type,int stunum,int MaxNum){
        this.id = id;
        this.coursename = coursename;
        this.teachername = teachername;
        this.type = type;
        this.stunum = stunum;
        this.MaxNum=MaxNum;
    }

    public void show() {
        System.out.println("课程编号：" + this.id);
        System.out.println("课程名称：" + this.coursename);
        System.out.println("任课教师：" + this.teachername);
        System.out.println("课程性质：" + this.type);
        System.out.println("选课人数：" + this.stunum);
        System.out.println("最大人数：" + this.MaxNum);
        System.out.println("------------------------");
    }

    public static void addCourseList(EleCourse ec) {
        ec.stunum=0;
        courselist.add(ec);
        System.out.println("课程名称 " + ec.coursename + " 编号 " + ec.id + "录入成功！");
    }

    
}