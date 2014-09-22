package tutorial;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by i on 2014/9/22.
 */
public class MultiplicationClient {
    public static void main(String []args) throws TException {
        TTransport transport;

        transport = new TSocket("localhost", 9000);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);
        MultiplicationService.Client client = new MultiplicationService.Client(protocol);

        perform(client);
    }

    private static void perform(MultiplicationService.Client client) throws TException {
        int product = client.multiply(3, 5);
        System.out.print("3*5=" + product);
    }
}
