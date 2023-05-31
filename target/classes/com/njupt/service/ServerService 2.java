package com.njupt.service;

import com.njupt.dto.server.CreateServerReq;
import com.njupt.dto.server.*;
import com.njupt.res.CommonResult;

public interface ServerService {

    CommonResult getServerList();

    CommonResult showServerDetail(String serverId);

    CommonResult deleteServer(String serverId);

    CommonResult forceDeleteServer(String serverId);

    CommonResult changeAdminPassword(ChangePwdReq changePwdReq);

    CommonResult confirmReszie(ConfirmReszieReq confirmReszieReq);

    CommonResult createImage(CreateImageReq createImageReq);

    CommonResult pauseServer(String serverId);

    CommonResult lockServer(String serverId);

    CommonResult unLockServer(String serverId);

    CommonResult unpauseServer(String serverId);

    CommonResult rebootServer(String serverId);

    CommonResult rescueServer(RescueServerReq rescueServerReq);

    CommonResult unrescueServer(String serverId);

    CommonResult startServer(String serverId);

    CommonResult stopServer(String serverId);

    CommonResult suspendServer(String serverId);

    CommonResult resizeServer(ResizeServerReq resizeServerReq);

    CommonResult revertResizeServer(String serverId);

    CommonResult addSecurityGroup(AddSecurityGroupReq addSecurityGroupReq);

    CommonResult removeSecurityGroup(RemoveSecurityGroupReq removeSecurityGroupReq);

    CommonResult resumeSuspendServer(String serverId);

    CommonResult createFlavor(CreateFlavorReq createFlavorReq);

    CommonResult listFlavors();

    CommonResult showFlavorDetails(String flavorId);

    CommonResult confirmResizedServer(String serverId);

    CommonResult createServer(CreateServerReq createServerReq);

    CommonResult migrateServer(String serverId);

    CommonResult updateServer(UpdateServerReq updateServerReq);

    CommonResult showConsoleOutput(String serverId);

    CommonResult ips(String serverId);

    CommonResult metadata(String serverId);
}
