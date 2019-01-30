package jfk_MAK.Model.Engine;

import jfk_MAK.Model.Entity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.Writer;
import java.util.List;

public class Nashorn {

    private ScriptEngine engine;

    public Nashorn(List<Entity> entities) {

        engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.put("entities", entities);
    }

    public String run(String script) {

        try {
            engine.eval(script);
        } catch(ScriptException e) {
            return e.getMessage();
        }

        return null;
    }

    public void setStdout(Writer writer) {
        engine.getContext().setWriter(writer);
    }

    public void setStderr(Writer writer) {
        engine.getContext().setErrorWriter(writer);
    }
}
