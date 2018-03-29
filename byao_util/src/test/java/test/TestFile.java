package test;

import java.util.ArrayList;

public class TestFile
{
    public static void main(String[] args)
    {
        var testList=new ArrayList<String>();
        testList.add("sfs");
        testList.add("sfsff");
        testList.add("sgafdfh");

        testList.forEach((item)-> System.out.println(item));
    }
}
