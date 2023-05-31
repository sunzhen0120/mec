package com.njupt.service;

import com.njupt.dto.port.CreatePortReq;
import com.njupt.res.CommonResult;


public interface PortService {

    CommonResult selectPorts();

    CommonResult createPort(CreatePortReq createPortReq);

    CommonResult deletePort(String portID);


}
