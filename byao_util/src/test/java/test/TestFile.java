package test;

import java.util.ArrayList;
import java.util.List;

public class TestFile
{
    public static void main(String[] args)
    {
        var testList = testList();

        testList.forEach((item)-> System.out.println(item));
    }

    public static List<String> testList()
    {
        var list = new ArrayList<String>();
        list.add("p001");
        list.add("p002");
        list.add("p003");
        list.add("p004");

        return list;
    }
}
