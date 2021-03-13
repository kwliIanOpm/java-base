package test.lang;



import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.Assert;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.ProtectionDomain;
import java.util.*;

/**
 * @author kwli
 */
public class ClassTest {


    /**
     * 无法手动new Class,即使是反射
     *
     * @throws Exception e
     */
    public static void constructor() throws Exception {
        Constructor<?> constructor = Class.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true); //Cannot make a java.lang.Class constructor accessible
        Object o = constructor.newInstance((Object) null);
    }

    /**
     * toString 会根据class interface  primitive 执行不同的逻辑
     *
     * @throws Exception e
     */
    public static void cLassToString() throws Exception {
        System.out.println(Class.class.toString()); //class java.lang.Class
        System.out.println(List.class.toString()); //interface java.util.List
        System.out.println(int.class.toString()); // int
    }

    /**
     * 带类型参数的toString
     *
     * @throws Exception e
     */
    public static void toGenericString() throws Exception {
        System.out.println(Class.class.toGenericString()); //public final class java.lang.Class<T>
        System.out.println(List.class.toGenericString()); //public abstract interface java.util.List<E>
        System.out.println(int.class.toGenericString()); // int
    }

    /**
     * forName 加载class
     *
     * @throws Exception e
     */
    public static void forName() throws Exception {
        Class<?> aClass = Class.forName("test.lang.MethodTest");
        System.out.println(aClass); //class test.lang.MethodTest
        // Class.forName() 使用当前类加载器加载该类,如果已经被父加载器加载过就直接返回
        System.out.println(aClass.getClassLoader() == ClassTest.class.getClassLoader()); //true

        Class<?> aClass1 = Class.forName("java.lang.String");
        System.out.println(aClass1.getClassLoader() == ClassTest.class.getClassLoader()); //false 不会重复加载String


        // initialize 属性设置是否进行类的初始化 (<clinit>)
        Class.forName("test.lang.FieldTest", false, ClassTest.class.getClassLoader());
    }

    /**
     * 使用无参构造函数
     *
     * @throws Exception e
     */
    public static void newInstance() throws Exception {
        FieldTest.class.newInstance();
    }

    @SuppressWarnings("all")
    public static void isMethods() {
        // isInstance  确定指定的对象是否与此类表示的对象赋值兼容
        System.out.println(List.class.isInstance(new ArrayList<String>())); //true

        //isAssignableFrom 确定由此Class对象表示的类或接口是否与指定的Class参数表示的类或接口相同，或者是该类或接口的超类或超接口
        System.out.println(Collection.class.isAssignableFrom(List.class)); //true

        // isInterface 如果此对象表示接口，则为true否则为true 。 否则为false
        System.out.println(List.class.isInterface()); //true

        // 如果此对象表示数组类，则为true否则为true 。 否则为false
        System.out.println(Object[].class.isArray()); //true

        // isPrimitive  boolean ， byte ， char ， short ， int ， long ， float和double   还有 void
        System.out.println(int.class.isPrimitive()); //true
        System.out.println(void.class.isPrimitive()); //true
        System.out.println(int.class == Integer.TYPE); //true

        // 如果此Class对象表示注解类型，则返回true。 请注意，如果此方法返回true，则isInterface()也将返回true，因为所有注释类型也是接口
        System.out.println(Override.class.isAnnotation()); // true

        // todo java语言规范说道:
        //    一个不是有源码里显式或隐式声明的结构需要标记为  Synthetic
        System.out.println(ClassTest.class.isSynthetic()); //  flase

    }

    /**
     * 获取name
     */
    public static void getName() {
        System.out.println(Class.class.getName()); // java.lang.Class
        System.out.println(List.class.getName()); // java.util.List
        System.out.println(int.class.getName()); // int
    }

    /**
     * 获取加载该类的类加载器
     */
    public static void getClassLoader(){
        System.out.println(String.class.getClassLoader()); //null
        System.out.println(ClassTest.class.getClassLoader()); // sun.misc.Launcher$AppClassLoader@4e0e2f2a
    }

    /**
     * 获取类型参数
     */
    public static void getTypeParameters(){
        System.out.println(Arrays.toString(String.class.getTypeParameters())); //[]
        System.out.println(Arrays.toString(HashMap.class.getTypeParameters())); //[K,V]
        System.out.println(Arrays.toString(AbstractList.class.getTypeParameters())); //[E]
    }

    /**
     * 获取父类
     */
    public static void getSuperclass(){
        System.out.println(String.class.getSuperclass()); //[]
        System.out.println(ClassTest.class.getSuperclass()); //[K,V]
        System.out.println(ArrayList.class.getSuperclass()); //[E]
        System.out.println(Arrays.toString(ArrayList.class.getSuperclass().getTypeParameters())); //[E]
    }

    /**
     * 获取带类型参数的type
     */
    public static void getGenericSuperclass(){
        Type genericSuperclass = ClassTest.class.getGenericSuperclass();
        Assert.isTrue(genericSuperclass instanceof Class, "父类没有带类型参数,所以是Class");
        Type type = ArrayList.class.getGenericSuperclass();
        Assert.isTrue(type instanceof ParameterizedType,"父类带参数所以是参数化类型");
        System.out.println(Object.class.getGenericSuperclass()); //null
        System.out.println(int.class.getGenericSuperclass()); //null
        System.out.println(ClassTest[].class.getGenericSuperclass()); //class java.lang.Object
    }

    /**
     * 获取Package
     */
    public static  void getPackage(){
        System.out.println(String.class.getPackage()); // package java.lang, Java Platform API Specification, version 1.8
        System.out.println(BeanFactory.class.getPackage()); //package org.springframework.beans.factory
    }

    /**
     * 获取该类直接实现的接口
     */
    public static void getInterfaces(){
        System.out.println(Arrays.toString(ArrayList.class.getInterfaces())); //[interface java.util.List, interface java.util.RandomAccess, interface java.lang.Cloneable, interface java.io.Serializable]
        System.out.println(Arrays.toString(HashMap.class.getInterfaces())); //[interface java.util.Map, interface java.lang.Cloneable, interface java.io.Serializable]
        System.out.println(Arrays.toString(ClassTest.class.getInterfaces())); //[]
    }

    /**
     * 与 getGenericSuperclass 类似
     */
    public static void getGenericInterfaces(){
        Type[] types = AbstractList.class.getGenericInterfaces();
        Assert.isTrue(types.length==1,"只有一个接口");
        Type type = types[0];
        Assert.isTrue(type instanceof ParameterizedType,"参数化类型");
        System.out.println(type); //java.util.List<E>
    }

    /**
     * 获取数组类型的元素类型
     */
    public static void  getComponentType(){
        System.out.println(ClassTest.class.getComponentType()); //null
        System.out.println(ClassTest[].class.getComponentType()); //class test.lang.ClassTest
    }


    /**
     * 见java虚拟机规范
     */
    public static void getModifiers(){
        System.out.println(Modifier.toString(ClassTest.class.getModifiers()));
        System.out.println(Modifier.toString(void.class.getModifiers()));
        System.out.println(Modifier.toString(ClassTest[].class.getModifiers()));
        System.out.println(Modifier.toString(List.class.getModifiers()));
    }

    /**
     *todo
     */
    public static void getSigners(){
        System.out.println(Arrays.toString(String.class.getSigners())); //null
        System.out.println(Arrays.toString(ClassTest.class.getSigners())); //null
        System.out.println(Arrays.toString(ClassTest[].class.getSigners())); //null
        System.out.println(Arrays.toString(int.class.getSigners())); //null
    }

    /**
     * todo
     */
    public static void getEnclosingMethodAndGetEnclosingConstructor(){
        class Inner{}
        System.out.println(Inner.class.getEnclosingMethod()); // public static void test.lang.ClassTest.getEnclosingMethod()
        System.out.println(Inner.class.getEnclosingConstructor()); //null
    }

    /**
     * TODO
     */
    public static void getDeclaringClass(){
        class Inner{}
        System.out.println(Inner.class.getDeclaringClass()); //null
        System.out.println(ClassTest.class.getDeclaringClass()); //null
    }

    // There are five kinds of classes (or interfaces):
    // a) Top level classes
    // b) Nested classes (static member classes)
    // c) Inner classes (non-static member classes)
    // d) Local classes (named classes declared within a method)
    // e) Anonymous classes
    // JVM Spec 4.8.6: A class must have an EnclosingMethod
    // attribute if and only if it is a local class or an
    // anonymous class.
    public static void getEnclosingClass(){
        class Inner{}
        System.out.println(Inner.class.getEnclosingClass()); //class test.lang.ClassTest
    }

    /**
     * 获取简称
     */
    public static void getSimpleName(){
        System.out.println(ClassTest.class.getSimpleName());   //ClassTest
    }

    /**
     * ??与getClassName有何区别
     */
    public static void getTypeName(){
        System.out.println(ClassTest.class.getTypeName());
        System.out.println(ArrayList.class.getTypeName());
    }

    /**
     *获取规范名称 (java语言规范)
     */
    public static void getCanonicalName(){
        System.out.println(int.class.getCanonicalName());
        System.out.println(ArrayList.class.getCanonicalName());
        System.out.println(long[].class.getCanonicalName());
    }

    // a) Top level classes
    // b) Nested classes (static member classes)
    // c) Inner classes (non-static member classes)
    // d) Local classes (named classes declared within a method)
    // e) Anonymous classes
    public static void isAnonymousClassAndIsLocalClass(){
        class Inner{}
        System.out.println(Inner.class.isAnonymousClass()); //false
        System.out.println(Inner.class.isLocalClass()); //true
        System.out.println(Inner.class.isMemberClass()); //false
    }

    /**
     * 获取这个类声明的其他public class
     */
    public static void getClasses(){
        System.out.println(Arrays.toString(ClassTest.class.getClasses())); //[]
        System.out.println(Arrays.toString(ArrayList.class.getClasses())); //[]
    }

    public static void getDeclaredFieldsAndGetDeclaredMethods(){
        System.out.println(Arrays.toString(ArrayList.class.getDeclaredFields()));
        System.out.println(Arrays.toString(ArrayList.class.getDeclaredMethods()));
    }

    public static void resource(){
        System.out.println(ClassTest.class.getResource("/hello.txt")); // file:/home/kwli/IdeaProjects/java-base/build/resources/main/hello.txt
        System.out.println(ClassTest.class.getResource("hello.txt")); // file:/home/kwli/IdeaProjects/java-base/build/resources/main/test/lang/hello.txt
        System.out.println(ClassTest.class.getResourceAsStream("/hello.txt")); // java.io.BufferedInputStream@2a139a55
    }

    /**
     *保护域
     */
    public static void  getProtectionDomain(){
        ProtectionDomain protectionDomain = ClassTest.class.getProtectionDomain();
        System.out.println(protectionDomain);
    }


    public static void main(String[] args) throws Exception {
//        getProtectionDomain();
        System.out.println(Enum.class.isAssignableFrom(season.class));
        System.out.println(season.class.isEnum());
    }

}
enum season{
    spring,
    ccc;
}