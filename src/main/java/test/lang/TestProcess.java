package test.lang;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.Console;
import java.io.PrintWriter;
import java.util.Map;

public class TestProcess {
    public static void main(String[] args) throws Exception {
        greet();
    }

    public static void greet() throws ScriptException {

        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine engine = manager.getEngineByName("JavaScript");

        Object eval = engine.eval("var a  =1; a");
        System.out.println(eval);

    }
}
