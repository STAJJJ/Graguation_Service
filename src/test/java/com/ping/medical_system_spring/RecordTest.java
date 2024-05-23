package com.ping.medical_system_spring;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RecordTest {

    //获取配置文件路径
    public final String configFile = "src/main/resources/config-example.toml";

    @Test
    public void test() throws Exception{
        // 初始化BcosSDK对象
        BcosSDK sdk = BcosSDK.build(configFile);
        // 获取Client对象，此处传入的群组ID为1
        Client client = sdk.getClient(Integer.valueOf(1));
        // 构造AssembleTransactionProcessor对象，需要传入client对象，CryptoKeyPair对象和abi、binary文件存放的路径。abi和binary文件需要在上一步复制到定义的文件夹中。
        CryptoKeyPair keyPair = client.getCryptoSuite().createKeyPair();

        AssembleTransactionProcessor transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(client, keyPair, "F:\\文档\\毕设\\project\\medical_system_spring_\\src\\main\\resources\\abi", "");

//        //同步方式发送交易
//        // 创建调用交易函数的参数
//        String recordId = "4";
//        String description = "description4";
//        String remark = "remark4";
//
//        List<Object> params =new ArrayList<>();
//        params.add(recordId);
//        params.add(description);
//        params.add(remark);
//
//        //调用智能合约，调用名为addRecord
//        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader("Record", "0xa2d0a80385195ca538ba1f229b0c19a84040453b", "addRecord", params);
//
//        //打印返回值
//        List<Object> returnValues = transactionResponse.getReturnObject();
//        if(returnValues != null){
//            for (Object value : returnValues){
//                System.out.println("上链返回值：" + value.toString());
//            }
//        }

        List<Object> params2 =new ArrayList<>();
        params2.add("4");


        //调用智能合约，调用名为addRecord
        TransactionResponse transactionResponse2 = transactionProcessor.sendTransactionAndGetResponseByContractLoader("Record", "0xa2d0a80385195ca538ba1f229b0c19a84040453b", "getRecord", params2);

        //打印返回值
        List<Object> returnValues2 = transactionResponse2.getReturnObject();
        if(returnValues2 != null){
            if (returnValues2.size() == 2 ){
                String description = (String) returnValues2.get(0);
                String remark = (String) returnValues2.get(1);
                System.out.println("Decription:" + description);
                System.out.println("Remark:" + remark);
            }else{
                System.out.println("返回值长度不正确");
            }
        }
    }

}
