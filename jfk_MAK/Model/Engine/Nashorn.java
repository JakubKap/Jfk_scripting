package jfk_MAK.Model.Engine;

import jfk_MAK.Model.Entity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

public class Nashorn {

    private List<Entity> entities;
    private ScriptEngine engine;

    public Nashorn(List<Entity> entities) {

        this.entities = entities;
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
}
