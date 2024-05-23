package com.ping.contract;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.abi.ABICodecException;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.fisco.bcos.sdk.transaction.model.exception.TransactionBaseException;
import org.junit.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class AddController {
    public final String configFile = "src/main/resources/config-example.toml";

    @PostMapping("/api/addRecord")
    public String addRecord(@RequestBody Record record) throws Exception {
        // 在实际应用中，您可以调用智能合约发送交易
        // 初始化BcosSDK对象
        BcosSDK sdk = BcosSDK.build(configFile);
        // 获取Client对象，此处传入的群组ID为1
        Client client = sdk.getClient(Integer.valueOf(1));
        // 构造AssembleTransactionProcessor对象，需要传入client对象，CryptoKeyPair对象和abi、binary文件存放的路径。abi和binary文件需要在上一步复制到定义的文件夹中。
        CryptoKeyPair keyPair = client.getCryptoSuite().createKeyPair();

        AssembleTransactionProcessor transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(client, keyPair, "F:\\文档\\毕设\\project\\medical_system_spring_\\src\\main\\resources\\abi", "");

        //同步方式发送交易
        // 创建调用交易函数的参数
        String recordId = record.getRecordId();
        String description = record.getDescription();
        String remark = record.getRemark();

        List<Object> params =new ArrayList<>();
        params.add(recordId);
        params.add(description);
        params.add(remark);

        //调用智能合约，调用名为addRecord
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader("Record", "0xa2d0a80385195ca538ba1f229b0c19a84040453b", "addRecord", params);

        //打印返回值
        List<Object> returnValues = transactionResponse.getReturnObject();
        if(returnValues != null){
            for (Object value : returnValues){
                System.out.println("上链返回值：" + value.toString());
            }
        }
        // 这里只是简单地返回接收到的记录
        return "man Record added: " + record.getRecordId() + ", " + record.getDescription() + ", " + record.getRemark();
    }
}
