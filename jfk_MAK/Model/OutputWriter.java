package jfk_MAK.Model;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.Writer;

public class OutputWriter extends Writer {

    StringBuilder sb;
    TextArea textArea;

    public OutputWriter(TextArea textArea) {
        this.sb = new StringBuilder();
        this.textArea = textArea;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = off; i < len; i++) {
            sb.append(cbuf[i]);
        }
        textArea.setText(sb.toString());
    }

    public void clear() {
        sb = new StringBuilder();
        textArea.setText("");
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }
}

