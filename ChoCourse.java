
public class ChoCourse extends Course {
    public int Credits;

    public ChoCourse(int id,String coursename,String teachername,int type,int stunum,int Credits){
        this.id = id;
        this.coursename = coursename;
        this.teachername = teachername;
        this.type = type;
        this.stunum = stunum;
        this.Credits=Credits;
    }

    public void show() {
        System.out.println("课程编号：" + this.id);
        System.out.println("课程名称：" + this.coursename);
        System.out.println("任课教师：" + this.teachername);
        System.out.println("课程性质：" + this.type);
        System.out.println("选课人数：" + this.stunum);
        System.out.println("课程学分：" + this.Credits);
        System.out.println("------------------------");
    }

    public static void addCourseList(ChoCourse cc) {
        courselist.add(cc);
        System.out.println("课程名称 " + cc.coursename + " 编号 " + cc.id + "录入成功！");
    }
}