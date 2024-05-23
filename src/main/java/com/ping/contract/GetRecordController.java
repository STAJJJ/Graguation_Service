package com.ping.contract;


import com.ping.VO.RecordRequest;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetRecordController {

    public final String configFile = "src/main/resources/config-example.toml";

    @PostMapping("/getRecord")
    public GetRecord getRecord(@RequestBody GetRecordRequest getRecordRequest) throws Exception {
        // 在这里调用智能合约来获取记录信息
        BcosSDK sdk = BcosSDK.build(configFile);
        Client client = sdk.getClient(Integer.valueOf(1));
        CryptoKeyPair keyPair = client.getCryptoSuite().createKeyPair();
        AssembleTransactionProcessor transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(client, keyPair, "F:\\文档\\毕设\\project\\medical_system_spring_\\src\\main\\resources\\abi", "");

        List<Object> params2 =new ArrayList<>();
        params2.add(getRecordRequest.getRecordId());


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
                GetRecord getRecord = new GetRecord(description, remark);

                return getRecord;

            }else{
                System.out.println("返回值长度不正确");
            }
        }
        // 这里简单返回一个模拟的 GetRecord
       GetRecord getRecord = new GetRecord("description", "remark");

        return getRecord;
    }
}