package tutorial;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by i on 2014/9/22.
 */
public class MultiplicationServer {
    public static MultiplicationHandler handler;
    public static MultiplicationService.Processor processor;

    public static void main(String[] args)
    {
        handler = new MultiplicationHandler();
        processor = new MultiplicationService.Processor(handler);

        Runnable simple = new Runnable() {
            @Override
            public void run() {
                try {
                    simple(processor);
                } catch (TTransportException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(simple).start();
    }

    public static void simple(MultiplicationService.Processor processor) throws TTransportException {
        TServerTransport transport = new TServerSocket(9000);

        TServer server = new TSimpleServer(new TServer.Args(transport).processor(processor));

        System.out.println("Starting the simple server...");
        server.serve();

    }
}
