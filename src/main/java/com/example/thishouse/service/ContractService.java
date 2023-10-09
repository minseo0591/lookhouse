package com.example.thishouse.service;

import com.example.thishouse.domain.contract.Contract;
import com.example.thishouse.domain.contract.Lessoer;
import com.example.thishouse.domain.contract.Tenant;
import com.example.thishouse.mapper.ContractMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContractService {

    private final ContractMapper contractMapper;
    @Transactional
    public void contract_request(Contract contract, int lessoer_num,int tenant_num) {

        contract.setTenant_idx(tenant_num);
        contract.setLessoer_idx(lessoer_num);
        System.out.println("#3333333333333333333333333333333333333");
        System.out.println(contract.getMiddle_payment());
        System.out.println(contract.getMiddle_payment_deadline());
        contractMapper.contract_request(contract);
    }

    @Transactional
    public void tenant_info(Tenant tenant) {
        contractMapper.tenant_info(tenant);
    }

    @Transactional
    public void lessoer_info(Lessoer lessoer) {
        contractMapper.lessoer_info(lessoer);
    }


    public int get_lessoer(int houseNum) {
        return contractMapper.get_lessoer(houseNum);
    }


    public int get_tenant(int houseNum) {
        return contractMapper.get_tenant(houseNum);
    }
}
