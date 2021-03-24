package test.lang.nio;

import java.nio.channels.Pipe;

public class Pipetest {
    public static void main(String[] args) throws Exception{
        Pipe open = Pipe.open();
        Pipe.SinkChannel sink = open.sink();
        Pipe.SourceChannel source = open.source();
    }
}
