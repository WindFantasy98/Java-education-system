import java.util.Scanner;

public class exp01 {
    /**
     * Author:20175630 Date:2019/7/15
     */
    public static void main(String[] args) throws Exception {
        User.ReadUsers();
        Course.ReadCourse();
        Surface();
        Course.SaveCourse();
        User.SaveUsers();
    }
    
    public static void Surface() {
        while (true) {
            System.out.println("###############################################################");
            System.out.println("#");
            System.out.println("#                      请选择用户类型");
            System.out.println("#                      【1】管理员");
            System.out.println("#                      【2】教师");
            System.out.println("#                      【3】学生");
            System.out.println("#");
            System.out.println("###############################################################");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            sc.nextLine();
            menu(n);
            System.out.println("退出登陆？Y/N");
            String yn = sc.nextLine();
            if (!yn.equals("y") && !yn.equals("Y")) {
                break;
            }
        }
    }

    public static void menu(int n) {
        switch (n) {
        case 1:
            System.out.println("--欢迎管理员Admin--");
            int cnt = 0;
            boolean flag = true;
            while (User.login() == 0 && flag) {
                System.out.println("密码错误QAQ");
                cnt++;
                if (cnt == 2) {
                    System.out.println("您还有最后一次尝试机会！");
                    flag = false;
                }
            }
            if (!flag)
                System.exit(0);
            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("[1]添加课程");
                System.out.println("[2]删除课程");
                System.out.println("[3]按照选课人数排序");
                System.out.println("[4]显示课程清单");
                System.out.println("[5]修改授课教师");
                System.out.println("[6]显示教师列表");
                System.out.println("[7]显示学生列表");
                System.out.println("[8]添加老师");
                System.out.println("[9]删除老师");
                System.out.println("[10]添加学生");
                System.out.println("[11]删除学生");
                System.out.println("[12]恢复老师原始密码");
                System.out.println("[13]恢复学生原始密码");
                System.out.println("请选择您要进行的操作：");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                case 1:
                    Course.addCourses();
                    break;
                case 2:
                    Course.delCourse();
                    break;
                case 3:
                    Course.SortbyStunum();
                    break;
                case 4:
                    Course.showCourseList();
                    break;
                case 5:
                    Course.setTeacher();
                    break;
                case 6:
                    Teacher.showTeacherList();
                    break;
                case 7:
                    Student.showStudentList();
                    break;
                case 8:
                    Teacher.addTeacher();
                    break;
                case 9:
                    Teacher.delTeacher();
                    break;
                case 10:
                    Student.addStudent();
                    break;
                case 11:
                    Student.delStudent();
                    break;
                case 12:
                    Teacher.resetTeacherP();
                    break;
                case 13:
                    Student.resetStudentP();
                    break;
                }
                System.out.println("是否要继续？y/n");
                String yn = sc.nextLine();
                if (!yn.equals("y") && !yn.equals("Y"))
                    break;
            }

            break;
        case 2:
            System.out.println("--欢迎老师--");
            int cnt1 = 0;
            boolean flag1 = true;
            int wkid = Teacher.login();
            while (wkid == 0 && flag1) {
                System.out.println("密码错误");
                cnt1++;
                if (cnt1 == 2) {
                    System.out.println("您的尝试次数过多，还有最后一次机会！");
                    flag1 = false;
                }
            }
            if (!flag1)
                System.exit(0);
            Teacher t = Teacher.searchtcher(wkid);
            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("[1]修改密码");
                System.out.println("[2]查看自己教授课程");
                System.out.println("[3]查看课程所有学生");
                System.out.println("请选择您要进行的操作：");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                case 1:
                    t.chgPassword();
                    break;
                case 2:
                    t.ShowMyClasses();
                    break;
                case 3:
                    Teacher.showClassStu();
                    break;
                }
                System.out.println("是否要继续？y/n");
                String yn = sc.nextLine();
                if (!yn.equals("y") && !yn.equals("Y"))
                    break;
            }
            break;
        case 3:
            System.out.println("--欢迎学生--");
            int cnt2 = 0;
            boolean flag2 = true;
            int schid = Student.login();
            while (schid == 0 && flag2) {
                System.out.println("密码错误");
                cnt2++;
                if (cnt2 == 2) {
                    System.out.println("您的尝试次数过多，还有最后一次机会！");
                    flag2 = false;
                }
            }
            if (!flag2)
                System.exit(0);
            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("[1]修改密码");
                System.out.println("[2]选修课选课");
                System.out.println("[3]查看自己所选的课程");
                System.out.println("请选择您要进行的操作：");
                int choice = sc.nextInt();
                sc.nextLine();
                Student s = Student.searchstu(schid);
                switch (choice) {
                case 1:
                    s.chgPassword();
                    break;
                case 2:
                    Student.chooseCourses(s);
                    break;
                case 3:
                    s.ShowMyCourses(s.schoolID);
                    break;
                }
                System.out.println("是否要继续？y/n");
                String yn = sc.nextLine();
                if (!yn.equals("y") && !yn.equals("Y"))
                    break;
            }
            break;
        default:
            System.out.println("无效输入");
        }
        Course.UpdateChoC();
    }
}
