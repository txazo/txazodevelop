namespace java org.txazo.ws.rpc.thrift.core

service ThriftService {

    bool login(1:i32 id, 2:string password)
	
}