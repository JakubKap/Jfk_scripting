package jfk_MAK.Model.Engine;

import jfk_MAK.Model.Entity;
import org.python.core.PyException;
import org.python.core.PySyntaxError;
import org.python.util.PythonInterpreter;

import java.io.Writer;
import java.util.List;

public class JythonEngine {

    private PythonInterpreter pythonInterpreter;

    public JythonEngine(List<Entity> entities) {
        pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.set("entities", entities);
    }

    public String run(String script) {

        try {
            pythonInterpreter.exec(script);
        } catch(PyException e) {
            return e.toString();
        }
        return null;
    }

    public void setStdout(Writer writer) {
        pythonInterpreter.setOut(writer);
    }

    public void setStderr(Writer writer) {
        pythonInterpreter.setErr(writer);
    }
}
