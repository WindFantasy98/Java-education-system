import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    public static User admin = new User("Admin_User", "helloworld");
    public static Vector<Teacher> teacherlist = new Vector<Teacher>();
    public static Vector<Student> studentlist = new Vector<Student>();
    String name;
    private String password;

    public User() {
    }

    public User(String name, String pswd) {
        this.name = name;
        password = pswd;
    }

    public void show() {
        System.out.println("用户名：" + this.name);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 修改密码
     */
    public void chgPassword() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        boolean flag = false;
        int i = 1;
        do {
            System.out.println("请输入原密码：");
            String input = sc.nextLine();
            i++;
            if (input.equals(this.getPassword())) {
                flag = true;
                System.out.println("请输入新密码：");
                this.setPassword(sc.nextLine());
                System.out.println("密码修改成功！");
                break;
            }
        } while (i <= 3);
        if (flag == false)
            System.out.println("您已经失败三次，无法再次修改密码！");
    }

    public static int login() {
        System.out.println("请输入密码：");
        Scanner sc = new Scanner(System.in);
        if (admin.getPassword().equals(sc.nextLine()))
            return 1;
        else
            return 0;
    }

    /**
     * 保存用户信息到文档
     */
    public static void SaveUsers() {
        try {
            File fo = new File("Users");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fo));
            oos.writeObject(User.studentlist);
            oos.writeObject(User.teacherlist);
            oos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 读取用户文件信息
     */
    public static void ReadUsers() {
        try {
            File fi = new File("Users");
            if (!fi.exists())
                fi.createNewFile();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fi));
            User.studentlist = (Vector<Student>) ois.readObject();
            User.teacherlist = (Vector<Teacher>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}