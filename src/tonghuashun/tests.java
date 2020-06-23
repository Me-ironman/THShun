package tonghuashun;

import org.junit.Assert;
import org.junit.Test;
import tonghuashun.TongHuaShun;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
public class tests {
    public static void main(String[] ages){
        System.out.println("刘园-17140120097\n本示例将四张相同情况拆分为三张相同的三条和一张散列(三条>两对)");
        testM1();
        testM2();
        testM3();
        testM4();
        testM5();
        testM6();
    }
    @Test
    public static void testM1() {
        TongHuaShun mc = new TongHuaShun();
        String data = "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH\r\n";
        System.out.print("\n1.输入示例:"+data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String get_output=mc._main();
        try {
            Assert.assertEquals("White wins", get_output);
            System.out.println("测试成功");
        }catch (Exception e){
            System.out.println("测试错误");
        }
    }
    public static void testM2() {
        TongHuaShun mc = new TongHuaShun();
        String data = "Black: 2H 4S 4C 2D 4H White: 2S 8S AS QS 3S\r\n";
        System.out.print("\n2.输入示例:"+data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String get_output=mc._main();
        try {
            Assert.assertEquals("White wins", get_output);
            System.out.println("测试成功");
        }catch (Exception e){
            System.out.println("测试错误");
        }
    }
    public static void testM3() {
        TongHuaShun mc = new TongHuaShun();
        String data = "Black: 2H 3H 5H 9H KH White: 2C 3H 4S 5C 6H\r\n";
        System.out.print("\n3.输入示例:"+data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String get_output=mc._main();
        try {
            Assert.assertEquals("Black wins", get_output);
            System.out.println("测试成功");
        }catch (Exception e){
            System.out.println("测试错误");
        }
    }
    public static void testM4() {
        TongHuaShun mc = new TongHuaShun();
        String data = "Black: 2H 3D 5S 9C KD White: 2D 3H 5C 9S KH\r\n";
        System.out.print("\n4.输入示例:"+data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String get_output=mc._main();
        try {
            Assert.assertEquals("Tie", get_output);
            System.out.println("测试成功");
        }catch (Exception e){
            System.out.println("测试错误");
        }
    }
    public static void testM5() {
        TongHuaShun mc = new TongHuaShun();
        String data = "Black: 2H 3D 5S 9C KD White: 3D 7H 5C 9S AH\r\n";
        System.out.print("\n5.输入示例:"+data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String get_output=mc._main();
        try {
            Assert.assertEquals("White wins", get_output);
            System.out.println("测试成功");
        }catch (Exception e){
            System.out.println("测试错误");
        }
    }
    public static void testM6() {
        TongHuaShun mc = new TongHuaShun();
        String data = "Black: 2H 2D 2S 2C KD White: 3D 3H 3C 3S AH\r\n";
        System.out.print("\n6.输入示例:"+data);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String get_output=mc._main();
        try {
            Assert.assertEquals("White wins", get_output);
            System.out.println("测试成功");
        }catch (Exception e){
            System.out.println("测试错误");
        }
    }
}