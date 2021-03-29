package com.study.thrift.client.client;

import cn.hutool.json.JSONUtil;
import com.study.thrift.client.service.StudyService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author chengzhihua
 * @description
 * @date 2021/1/13
 */
public class ThriftClientTest {
    public static void main(String[] args) throws TException {
        TTransport tTransport = new TFramedTransport(new TSocket("127.0.0.1", 9991), 600);
        tTransport.open();
        //协议对象 这里使用协议对象需要和服务器的一致
        TProtocol tProtocol = new TCompactProtocol(tTransport);
        StudyService.Client client = new StudyService.Client(tProtocol);

        String s = client.thriftFun("测试名称");
        System.out.println(JSONUtil.toJsonStr(s));

    }
}
